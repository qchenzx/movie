package com.chenzx.movie.entity.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 14:35
 */

@Data
@ApiModel(value = "MovieFavoriteParam")
public class MovieFavoriteParam {

    /**
     * 电影信息主键
     */
    @ApiModelProperty(value = "电影信息主键")
    @NotNull(message = "电影信息主键不能为空!")
    private Long movieId;

}
