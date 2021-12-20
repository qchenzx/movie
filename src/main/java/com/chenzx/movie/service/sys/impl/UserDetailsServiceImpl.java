package com.chenzx.movie.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenzx.movie.entity.sys.IUserDo;
import com.chenzx.movie.mapper.sys.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/14 14:29
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserMapper iUserMapper;

    @Autowired
    public UserDetailsServiceImpl(IUserMapper iUserMapper) {
        this.iUserMapper = iUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        QueryWrapper<IUserDo> iUserQueryWrapper = new QueryWrapper<>();
        iUserQueryWrapper.eq("account", account);
        IUserDo iUserDo = iUserMapper.selectOne(iUserQueryWrapper);
        if (iUserDo == null) {
            throw new UsernameNotFoundException("账号或密码错误!");
        }
        return iUserDo.getIUser();
    }
}
