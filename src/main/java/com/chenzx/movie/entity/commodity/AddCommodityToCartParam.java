package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

}
