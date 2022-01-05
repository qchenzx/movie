package com.chenzx.movie.entity.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 19:36
 */
@Data
@ApiModel(value = "com.chenzx.movie.entity.sys.RegisterParam", description = "注册信息参数")
public class RegisterParam {

    /**
     * 账号
     */
    @NotBlank
    @ApiModelProperty(value = "账号")
    private String account;
    /**
     * 昵称
     */
    @NotBlank
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 密码
     */
    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;

}
