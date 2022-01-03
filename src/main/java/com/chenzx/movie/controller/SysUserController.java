package com.chenzx.movie.controller;

import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.sys.ChangePassordParam;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.entity.sys.RegisterParam;
import com.chenzx.movie.entity.sys.UserInfo;
import com.chenzx.movie.service.sys.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 19:33
 */
@RequestMapping(value = "/sys/user/")
@RestController
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping(value = "/register/")
    public String register(@RequestBody @Valid RegisterParam param) {
        sysUserService.register(param);
        return "注册成功!";
    }

    @GetMapping(value = "/getInfo")
    public UserInfo getUserInfo(@AuthenticationPrincipal IUser iUser) {
        if (iUser.getId() == null) {
            throw new BusException("当前未登录!");
        }
        return sysUserService.getUserInfoByIUser(iUser);
    }

    @PostMapping(value = "/changePassword")
    public String changePassword(@Valid @RequestBody ChangePassordParam param, @AuthenticationPrincipal IUser iUser) {
        sysUserService.changePassword(param,iUser);
        // TODO 需要添加删除cookie的逻辑
        return "修改密码成功!";
    }
}
