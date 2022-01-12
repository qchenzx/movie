package com.chenzx.movie.entity.commodity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/11 21:06
 */
@Data
@ApiModel("DeleteShopCartParam")
public class DeleteShopCartParam {

    @Valid
    @NotNull(message = "删除的商品不能为null")
    @ApiModelProperty(value = "购物车主键数组,如删除一个,那么只传一个元素即可")
    List<Long> shopCartIds;

}
