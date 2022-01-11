package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/10 20:52
 */
@Data
@ApiModel("AddCommodityToCartParam")
public class AddCommodityToCartParam {

    /**
     * 商品主键
     */
    @ApiModelProperty("商品主键")
    @NotNull
    private Long commodityId;

    /**
     * 规格主键
     */
    @ApiModelProperty("规格主键")
    @NotNull
    private Long specificationsId;

    /**
     * 添加到购物车中的商品数量
     */
    @ApiModelProperty("添加到购物车中的数量")
    @Max(value = 200, message = "购物车中最多存放200件商品")
    @Min(value = 1, message = "至少向购物车中添加一件商品")
    private Integer amount;

}
