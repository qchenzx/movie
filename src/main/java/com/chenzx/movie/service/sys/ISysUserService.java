package com.chenzx.movie.service.sys;

import com.chenzx.movie.entity.sys.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    /**
     * 上传头像
     *
     * @param file 头像文件
     * @param user 用户对象
     * @return 头像上传后的提示
     * @throws IOException 异常
     */
    String uploadAvatar(MultipartFile file, IUser user) throws IOException;

    /**
     * 获取用户头像接口
     *
     * @param user 用户对象
     * @return 头像二进制流
     */
    byte[] getUserAvatar(IUser user) throws IOException;
}
