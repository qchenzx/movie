package com.chenzx.movie.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/14 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IUser implements UserDetails {

    /**
     * 用户主键
     */
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
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 用户积分
     */
    private Long integral;
    /**
     * 是否启用
     */
    private Boolean enabled = true;
    /**
     * 头像存放地址
     */
    private String avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
