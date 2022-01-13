package com.chenzx.movie.entity.echarts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/13 17:52
 */
@Data
@ApiModel("CommoditySalesData")
public class CommoditySalesData {

    /**
     * 商品名字
     */
    @ApiModelProperty("商品名字")
    private String commodityName;

    /**
     * 商品销量
     */
    @ApiModelProperty("该商品的销量")
    private Long salesVolume;

}
