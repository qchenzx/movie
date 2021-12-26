package com.chenzx.movie.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 20:10
 */
@Data
@TableName(value = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class IUserDo {

    /**
     * 用户主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 权限、角色
     */
    private String authorities;
    /**
     * 账户未锁定
     */
    private Boolean accountNonLocked = true;
    /**
     * 注册时间
     */
    private Date createDate;
    /**
     * 用户积分
     */
    private Long integral;
    /**
     * 用户手机号码
     */
    private String phoneNumber;
    /**
     * 是否启用
     */
    private Boolean enabled = true;
    /**
     * 头像存放地址
     */
    private String avatar;

    public IUser getIUser() {
        IUser iUser = new IUser();
        iUser.setAuthorities(authorities);
        iUser.setAccount(account);
        iUser.setEnabled(enabled);
        iUser.setCreateDate(createDate);
        iUser.setAccountNonLocked(accountNonLocked);
        iUser.setPassword(password);
        iUser.setNickname(nickname);
        iUser.setId(id);
        iUser.setAvatar(avatar);
        iUser.setIntegral(integral);
        iUser.setPhoneNumber(phoneNumber);
        return iUser;
    }
}
