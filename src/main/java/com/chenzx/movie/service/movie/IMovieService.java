package com.chenzx.movie.service.movie;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import com.chenzx.movie.entity.movie.MovieInfoParam;
import com.chenzx.movie.entity.movie.MovieType;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:00
 */
public interface IMovieService {

    /**
     * 模糊搜索电影信息列表,用于搜索
     *
     * @param param 方法入参对象
     * @param page  分页字段
     * @return 查询结果
     */
    IPage<MovieInfoDo> fuzzyQueryMovieInfo(MovieInfoParam param, Page<MovieInfoDo> page);

    /**
     * 查询所有的电影列表
     *
     * @param param 方法入参对象
     * @param page  分页字段
     * @return 查询结果
     */
    IPage<MovieInfoDo> queryAllMovieInfo(MovieInfoParam param, Page<MovieInfoDo> page);

    /**
     * 通过电影的ID获取电影的封面图片
     *
     * @param movieId 电影id
     * @return base64加密后的图片
     */
    byte[] getMovieCoverImgById(Long movieId);

    /**
     * 获取电影的所有分类
     *
     * @return 电影的所有分类
     */
    List<MovieType> getMovieAllType();

}
