package com.chenzx.movie.entity.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 19:36
 */
@Data
public class RegisterParam {

    /**
     * 账号
     */
    @NotBlank
    private String account;
    /**
     * 昵称
     */
    @NotBlank
    private String nickname;
    /**
     * 密码
     */
    @NotBlank
    private String password;

}
