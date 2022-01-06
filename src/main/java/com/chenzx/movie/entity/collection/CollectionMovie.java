package com.chenzx.movie.entity.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 9:31
 */
@Data
@ApiModel("CollectionMovie")
public class CollectionMovie {

    /**
     * 上映日期
     */
    @ApiModelProperty(value = "上映日期", example = "2022-1-1")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    /**
     * 电影名字
     */
    @ApiModelProperty(value = "电影名字", example = "秘不可言")
    private String movieName;

    /**
     * 主演
     */
    @ApiModelProperty(value = "电影主演", example = "xxx,xxx")
    private String cast;

    /**
     * 电影类型
     */
    @ApiModelProperty(value = "电影类型", example = "爱情,动作")
    private List<String> type;

    /**
     * 评分
     */
    @ApiModelProperty(value = "电影评分", example = "9.0")
    private String score;

    @ApiModelProperty(value = "电影主键", notes = "用于取消收藏")
    private Long movieId;

}
