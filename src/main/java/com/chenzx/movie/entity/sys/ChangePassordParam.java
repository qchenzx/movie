package com.chenzx.movie.entity.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/3 14:55
 */
@Data
public class ChangePassordParam {

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空!")
    private String newPassword;
    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空!")
    private String oldPassword;

}
