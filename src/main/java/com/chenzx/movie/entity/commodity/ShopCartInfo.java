package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/10 16:22
 */
@Data
@ApiModel(value = "ShopCartInfo")
public class ShopCartInfo {

    /**
     * 商品主键
     */
    @ApiModelProperty("商品主键")
    private Long commodityId;

    /**
     * 商品名字
     */
    @ApiModelProperty("商品名字")
    private String commodityName;

    /**
     * 存放在购物车中的商品规格的单价
     */
    @ApiModelProperty("存放在购物车中的商品规格的单价")
    private Double unitPrice;

    /**
     * 商品规格名字
     */
    @ApiModelProperty("商品规格名字")
    private String specificationsName;

    /**
     * 商品规格id
     */
    @ApiModelProperty("商品规格id")
    private Long specificationsId;

    /**
     * 图片base64
     */
    @ApiModelProperty("图片base64")
    private String image;

    /**
     * 存放的数量
     */
    @ApiModelProperty("存放的数量")
    private Integer total;

}
