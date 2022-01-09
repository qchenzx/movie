package com.chenzx.movie.entity.commodity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/9 15:25
 */
@Data
@ApiModel("MallType")
@TableName("mall_type")
public class MallType {

    /**
     * 商品分类主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("当前商品分类主键")
    private Long id;

    /**
     * 商品分类名字
     */
    @ApiModelProperty("当前商品分类名字")
    private String name;

    /**
     * 父级分类主键
     */
    @ApiModelProperty("父级商品主键(该字段为保留拓展性,所以可忽略)")
    private Long parentId;

}
