package com.chenzx.movie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import com.chenzx.movie.service.movie.impl.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private MovieServiceImpl movieService;

    @GetMapping
    public IPage<MovieInfoDo> getMovieInfo(Page<MovieInfoDo> page, @RequestParam(required = false) String movieName) {
        return movieService.queryAllMovieInfo(page);
    }
}
