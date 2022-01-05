package com.chenzx.movie.entity.movie;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/4 8:25
 */
@Data
@ApiModel(value = "com.chenzx.movie.entity.movie.MovieInfoParam", description = "要查询的电影信息字段")
public class MovieInfoParam {

    /**
     * 电影名字,如果有,那么进行模糊查询,如果没有,则查询全部
     */
    @ApiModelProperty("电影名字,用于指定电影名字查询")
    private String movieName;
    /**
     * 排序字段
     */
    @NotNull
    @ApiModelProperty("分组字段,如果为1则按照时间排序,如果为2则按照评价排序")
    private Integer orderBy;
    /**
     * 分类字段
     */
    @ApiModelProperty("分类字段,该字段需要使用/api/movie/getType接口中返回的某一个分类的id,作为方法入参")
    private String type;

}
