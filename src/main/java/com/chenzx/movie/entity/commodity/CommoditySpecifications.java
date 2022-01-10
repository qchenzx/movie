package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 19:30
 */
@Data
@ApiModel(value = "CommoditySpecifications")
public class CommoditySpecifications {

    @ApiModelProperty(value = "规格主键")
    private Long specificationsId;

    @ApiModelProperty(value = "显示名称")
    private String name;

    /**
     * 商品单价
     */
    @ApiModelProperty(value = "商品单价(积分)")
    private Double unitPrice;

    @ApiModelProperty(value = "商品规格图片")
    private String image;
}
