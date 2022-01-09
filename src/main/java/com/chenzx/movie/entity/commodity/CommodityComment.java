package com.chenzx.movie.entity.commodity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 19:32
 */
@Data
@ApiModel("CommodityComment")
public class CommodityComment {

    /**
     * 商品评论的作者
     */
    @ApiModelProperty(value = "评论的作者")
    private String author;

    /**
     * 商品评论的内容
     */
    @ApiModelProperty(value = "评论的内容")
    private String content;

    @JsonFormat(pattern = "yyyy年MM月dd日")
    @ApiModelProperty(value = "评论的时间")
    private Date time;

}
