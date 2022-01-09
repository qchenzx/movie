package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/9 14:33
 */
@Data
@ApiModel("CommodityDescribeParam")
public class CommodityDescribeParam {

    /**
     * 商品分类主键
     */
    @ApiModelProperty(value = "商品的分类主键")
    private Long type;

    /**
     * 要搜索的商品名字
     */
    @ApiModelProperty(value = "要搜索的商品名字")
    private String name;

}
