package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/9 14:32
 */
@Data
@ApiModel("CommodityDescribe")
public class CommodityDescribe {

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long id;

    /**
     * 商品名字
     */
    @ApiModelProperty(value = "商品名字")
    private String name;

    /**
     * 商品单价
     */
    @ApiModelProperty(value = "商品单价")
    private String unitPrice;

    /**
     * base64格式的商品主图
     */
    @ApiModelProperty(value = "base64格式的商品主图")
    private String base64Image;


}
