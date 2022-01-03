package com.chenzx.movie.service.sys;

import com.chenzx.movie.entity.sys.*;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 10:31
 */
public interface ISysUserService {

    /**
     * 将SysUserMapper中queryPathRequiredRole方法
     * 中LinkedHashMap<String,String>返回类型转换为PathRoleMapping
     *
     * @return List<PathRoleMapping>
     */
    List<PathRoleMapping> queryPathRequiredRole();

    /**
     * 用户注册功能
     *
     * @param param 账号、密码、昵称
     */
    void register(RegisterParam param);

    /**
     * 通过IUser对象查询登录用户信息
     *
     * @param iUser 当前登录用户的对象
     * @return 前端所需的用户信息
     */
    UserInfo getUserInfoByIUser(IUser iUser);

    /**
     * 修改密码
     *
     * @param param 新密码与旧密码
     * @param iUser 用户对象
     */
    void changePassword(ChangePassordParam param, IUser iUser);
}
