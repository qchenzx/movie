package com.chenzx.movie.entity.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 12:54
 */
@Data
@ApiModel(value = "AddAddressParam", description = "创建收货地址对象")
public class AddAddressParam {

    /**
     * 收货人名字
     */
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

    public AddressDo newAddressDo(Long userId) {
        AddressDo address = new AddressDo();
        address.setName(name);
        address.setMobileNumber(mobileNumber);
        address.setDetailedAddress(detailedAddress);
        address.setZipCode(zipCode);
        address.setUserId(userId);
        return address;
    }

}
