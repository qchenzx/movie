package com.chenzx.movie.service.movie;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.movie.MovieInfoDo;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:00
 */
public interface IMovieService {

    /**
     * 模糊搜索电影信息列表,用于搜索
     *
     * @param page      分页对象
     * @param movieName 模糊查询的电影名字
     * @return 查询结果
     */
    IPage<MovieInfoDo> fuzzyQueryMovieInfo(Page<MovieInfoDo> page, String movieName);

    /**
     * 查询所有的电影列表
     *
     * @param page 分页对象
     * @return 查询结果
     */
    IPage<MovieInfoDo> queryAllMovieInfo(Page<MovieInfoDo> page);

    /**
     * 通过电影的ID获取电影的封面图片
     *
     * @param movieId 电影id
     * @return base64加密后的图片
     */
    byte[] getMovieCoverImgById(Long movieId);

}
