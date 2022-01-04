package com.chenzx.movie.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import com.chenzx.movie.entity.movie.MovieInfoParam;
import com.chenzx.movie.entity.movie.MovieType;
import com.chenzx.movie.service.movie.IMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 11:12
 */
@Api(tags = "电影信息处理")
@Slf4j
@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    @ApiOperation("获取电影信息接口")
    public IPage<MovieInfoDo> getMovieInfo(MovieInfoParam param, Page<MovieInfoDo> page) {
        if (StrUtil.isBlank(param.getMovieName())) {
            return movieService.queryAllMovieInfo(param, page);
        }
        return movieService.fuzzyQueryMovieInfo(param, page);
    }

    @ApiOperation("获取电影的封面图片")
    @GetMapping(value = {"img/{movieId}"})
    public byte[] getMovieCoverImg(@ApiParam(value = "电影主键", required = true) @PathVariable(required = false) Long movieId) {
        if (movieId == null) {
            throw new BusException("电影id不能为空!");
        }
        return movieService.getMovieCoverImgById(movieId);
    }

    @ApiOperation("获取电影的所有分类")
    @GetMapping(value = "getType")
    public List<MovieType> getMovieAllType() {
        return movieService.getMovieAllType();
    }
}
