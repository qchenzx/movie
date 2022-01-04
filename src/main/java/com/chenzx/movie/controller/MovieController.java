package com.chenzx.movie.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import com.chenzx.movie.entity.movie.MovieInfoParam;
import com.chenzx.movie.entity.movie.MovieType;
import com.chenzx.movie.service.movie.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 11:12
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    public IPage<MovieInfoDo> getMovieInfo(MovieInfoParam param,Page<MovieInfoDo> page) {
        if (StrUtil.isBlank(param.getMovieName())) {
            return movieService.queryAllMovieInfo(param,page);
        }
        return movieService.fuzzyQueryMovieInfo(param,page);
    }

    @GetMapping(value = {"img/{movieId}", "img"})
    public byte[] getMovieCoverImg(@PathVariable(required = false) Long movieId) {
        if (movieId == null) {
            throw new BusException("电影id不能为空!");
        }
        return movieService.getMovieCoverImgById(movieId);
    }

    @GetMapping(value = "getType")
    public List<MovieType> getMovieAllType() {
        return movieService.getMovieAllType();
    }
}
