package com.chenzx.movie.entity.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 13:50
 */
@Data
@ApiModel("AddressVo")
public class AddressVo {

    @ApiModelProperty(value = "默认收货地址 地址id")
    private String defaultAddressId;

    @ApiModelProperty(value = "该用户的地址列表")
    private List<AddressDo> address;

}
