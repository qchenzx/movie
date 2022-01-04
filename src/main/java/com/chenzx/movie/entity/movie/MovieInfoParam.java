package com.chenzx.movie.entity.movie;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/4 8:25
 */
@Data
public class MovieInfoParam {

    /**
     * 电影名字,如果有,那么进行模糊查询,如果没有,则查询全部
     */
    private String movieName;
    /**
     * 排序字段
     */
    @NotNull
    private Integer orderBy;
    /**
     * 分类字段
     */
    private String type;

}
