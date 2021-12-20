package com.chenzx.movie.service.movie.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceImplTest {

    @Autowired
    private MovieServiceImpl movieService;

    @Test
    void queryAllMovieInfo() {
        IPage<MovieInfoDo> movieInfoDoIPage = movieService.queryAllMovieInfo(new Page<>(1, 10));
        System.out.println();
    }
}
