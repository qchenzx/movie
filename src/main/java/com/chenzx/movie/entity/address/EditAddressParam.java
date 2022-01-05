package com.chenzx.movie.entity.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 17:39
 */
@Data
@ApiModel(value = "EditAddressParam", description = "修改收货地址的入参")
public class EditAddressParam {

    @ApiModelProperty(value = "收货地址主键", example = "5248e9debde807a29c3c6979c49ec56b")
    @Length(message = "收货地址主键不合法", max = 32, min = 32)
    @NotBlank(message = "收货地址主键不能为空")
    private String id;

    @ApiModelProperty(value = "收货人姓名", example = "小明")
    @NotBlank(message = "收货人姓名不能为空")
    private String name;

    /**
     * 收货人手机号
     */
    @ApiModelProperty(value = "收货人手机号", example = "19850255199")
    @Length(min = 11, max = 11, message = "请输入正确的手机号")
    private String mobileNumber;

    /**
     * 收货人详细地址
     */
    @ApiModelProperty(value = "收货人详细地址", example = "江苏省常州市武进区常州机电职业技术学院")
    @NotBlank(message = "收货人详细地址不能为空")
    private String detailedAddress;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编", example = "213100")
    @Length(min = 6, max = 6, message = "请输入正确的邮编")
    private String zipCode;

}
