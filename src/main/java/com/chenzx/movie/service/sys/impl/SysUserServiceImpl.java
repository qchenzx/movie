package com.chenzx.movie.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.sys.*;
import com.chenzx.movie.mapper.sys.IUserMapper;
import com.chenzx.movie.mapper.sys.SysRoleMapper;
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
    @Value("${security.default-role-after-registration}")
    private String defaultRoleAfterRegistration;

    @Override
    public List<PathRoleMapping> queryPathRequiredRole() {
        return sysUserMapper.queryPathRequiredRole();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterParam param) {
        QueryWrapper<IUserDo> iUserQueryWrapper = new QueryWrapper<>();
        iUserQueryWrapper.eq("account", param.getAccount());
        if (iUserMapper.selectOne(iUserQueryWrapper) == null) {
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
}
