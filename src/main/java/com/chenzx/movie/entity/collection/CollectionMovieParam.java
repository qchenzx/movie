package com.chenzx.movie.entity.collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 12:44
 */
@Data
@ApiModel("CollectionMovieParam")
public class CollectionMovieParam {

    /**
     * 电影id
     */
    @NotNull
    @ApiModelProperty(value = "电影id", example = "2")
    private Long movieId;

}
