package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 19:22
 */
@Data
@ApiModel("CommodityInfo")
public class CommodityInfo {

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long mallId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 商品最低单价
     */
    @ApiModelProperty(value = "商品最低单价")
    private Double minimumUnitPrice = null;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private List<CommoditySpecifications> specifications;

    /**
     * 商品评论
     */
    @ApiModelProperty(value = "商品评论")
    private List<CommodityComment> comments;

    /**
     * 商品优惠卷
     */
    @ApiModelProperty(value = "商品可以领取的优惠卷")
    private List<CommodityCoupon> coupons = null;

}
