package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 19:41
 */
@Data
@ApiModel(value = "CommodityCoupon")
public class CommodityCoupon {

    @ApiModelProperty(value = "优惠卷显示名字")
    private String name;

    @ApiModelProperty(value = "优惠卷值")
    private String value;

}
