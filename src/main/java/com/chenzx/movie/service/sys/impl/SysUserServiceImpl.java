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
            throw new BusException("账号已存在!");
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
            log.error("数据库中角色配置异常! 异常用户:{} 该用户主键:{} 该用户的权限:{} 需要一个权限/角色,但数据库中出现多个"
                    , iUser.getNickname(), iUser.getId(), iUser.getAuthorities());
            throw new BusException("数据库中角色配置异常");
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
            log.error("无法从数据库中查询到用户信息");
            throw new BusException("出错了,请联系管理员!");
        }
        if (!passwordEncoder.matches(param.getOldPassword(), user.getPassword())) {
            throw new BusException("原密码错误,请检查后重新输入!");
        }
        user.setPassword(passwordEncoder.encode(param.getNewPassword()));
        iUserMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadAvatar(MultipartFile file, IUser user) throws IOException {
        if (file.isEmpty() || file.getBytes().length == 0) {
            throw new BusException("上传头像为空!");
        }
        FileUtil.mkdir(uploadAvatarPath);

        IUserDo userDo = iUserMapper.selectById(user.getId());
        SysUserAvatar sysUserAvatar = new SysUserAvatar(null, userDo.getId(), new Date());
        userAvatarMapper.insert(sysUserAvatar);
        userDo.setAvatar(sysUserAvatar.getId());
        iUserMapper.updateById(userDo);

        File saveFile = new File(uploadAvatarPath, sysUserAvatar.getId());
        file.transferTo(saveFile);
        return "上传成功!";
    }

    @Override
    public byte[] getUserAvatar(IUser user) throws IOException {
        IUserDo userDo = iUserMapper.selectById(user.getId());
        SysUserAvatar sysUserAvatar = userAvatarMapper.selectOne(
                new LambdaQueryWrapper<SysUserAvatar>()
                        .eq(SysUserAvatar::getId, userDo.getAvatar()));
        File filePath = new File(uploadAvatarPath);
        if (!filePath.exists()) {
            log.error("在磁盘中未找到:{} 目录", uploadAvatarPath);
            throw new BusException("无法定位到文件目录!");
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
