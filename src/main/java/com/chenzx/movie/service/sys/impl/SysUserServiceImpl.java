package com.chenzx.movie.service.sys.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.movie.MovieCoverDo;
import com.chenzx.movie.entity.sys.*;
import com.chenzx.movie.mapper.sys.IUserMapper;
import com.chenzx.movie.mapper.sys.SysRoleMapper;
import com.chenzx.movie.mapper.sys.SysUserAvatarMapper;
import com.chenzx.movie.mapper.sys.SysUserMapper;
import com.chenzx.movie.service.sys.ISysUserService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 10:32
 */
@Slf4j
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private IUserMapper iUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserAvatarMapper userAvatarMapper;
    @Value("${security.default-role-after-registration}")
    private String defaultRoleAfterRegistration;
    @Value("${local-file.avatar-path}")
    private String uploadAvatarPath;

    @Override
    public List<PathRoleMapping> queryPathRequiredRole() {
        return sysUserMapper.queryPathRequiredRole();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterParam param) {
        QueryWrapper<IUserDo> iUserQueryWrapper = new QueryWrapper<>();
        iUserQueryWrapper.eq("account", param.getAccount());
        IUserDo user = iUserMapper.selectOne(iUserQueryWrapper);
        if (user != null) {
            throw new BusException("???????????????!");
        }
        IUserDo iUserDo = new IUserDo();
        iUserDo.setAccount(param.getAccount());
        iUserDo.setNickname(param.getNickname());
        iUserDo.setPassword(passwordEncoder.encode(param.getPassword()));
        iUserDo.setAuthorities(defaultRoleAfterRegistration);
        iUserDo.setCreateDate(new Date());
        iUserMapper.insert(iUserDo);
    }

    @Override
    public UserInfo getUserInfoByIUser(IUser iUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(iUser);
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        List<String> roles = Lists.newArrayList();
        for (GrantedAuthority authority : iUser.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
        if (roles.size() > 1) {
            log.error("??????????????????????????????! ????????????:{} ???????????????:{} ??????????????????:{} ??????????????????/??????,???????????????????????????"
                    , iUser.getNickname(), iUser.getId(), iUser.getAuthorities());
            throw new BusException("??????????????????????????????");
        }
        sysRoleQueryWrapper.eq("value", roles.get(0));
        SysRole sysRole = sysRoleMapper.selectOne(sysRoleQueryWrapper);
        userInfo.setUserGroupName(sysRole.getName());
        return userInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(ChangePassordParam param, IUser iUser) {
        IUserDo user = iUserMapper.selectById(iUser.getId());
        if (user == null) {
            log.error("??????????????????????????????????????????");
            throw new BusException("?????????,??????????????????!");
        }
        if (!passwordEncoder.matches(param.getOldPassword(), user.getPassword())) {
            throw new BusException("???????????????,????????????????????????!");
        }
        user.setPassword(passwordEncoder.encode(param.getNewPassword()));
        iUserMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadAvatar(MultipartFile file, IUser user) throws IOException {
        if (file.isEmpty() || file.getBytes().length == 0) {
            throw new BusException("??????????????????!");
        }
        FileUtil.mkdir(uploadAvatarPath);

        IUserDo userDo = iUserMapper.selectById(user.getId());
        SysUserAvatar sysUserAvatar = new SysUserAvatar(null, userDo.getId(), new Date());
        userAvatarMapper.insert(sysUserAvatar);
        userDo.setAvatar(sysUserAvatar.getId());
        iUserMapper.updateById(userDo);

        File saveFile = new File(uploadAvatarPath, sysUserAvatar.getId());
        file.transferTo(saveFile);
        return "????????????!";
    }

    @Override
    public byte[] getUserAvatar(IUser user) throws IOException {
        IUserDo userDo = iUserMapper.selectById(user.getId());
        SysUserAvatar sysUserAvatar = userAvatarMapper.selectOne(
                new LambdaQueryWrapper<SysUserAvatar>()
                        .eq(SysUserAvatar::getId, userDo.getAvatar()));
        File filePath = new File(uploadAvatarPath);
        if (!filePath.exists()) {
            log.error("?????????????????????:{} ??????", uploadAvatarPath);
            throw new BusException("???????????????????????????!");
        }
        File file = new File(uploadAvatarPath, sysUserAvatar.getId());
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] temp = new byte[10240000];
        int len = 0;
        while ((len = fis.read(temp)) != -1) {
            byteArrayOutputStream.write(temp, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
