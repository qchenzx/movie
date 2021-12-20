package com.chenzx.movie.service.movie.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import com.chenzx.movie.mapper.movie.MovieInfoMapper;
import com.chenzx.movie.service.movie.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:13
 */
@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private MovieInfoMapper infoMapper;

    @Override
    public IPage<MovieInfoDo> fuzzyQueryMovieInfo(Page<MovieInfoDo> page, String movieName) {
        return null;
    }

    @Override
    public IPage<MovieInfoDo> queryAllMovieInfo(Page<MovieInfoDo> page) {
        return infoMapper.selectPage(page, null);
    }
}
