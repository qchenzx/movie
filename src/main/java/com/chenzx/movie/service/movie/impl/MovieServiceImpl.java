package com.chenzx.movie.service.movie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.movie.MovieCoverDo;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import com.chenzx.movie.mapper.movie.MovieCoverMapper;
import com.chenzx.movie.mapper.movie.MovieInfoMapper;
import com.chenzx.movie.service.movie.IMovieService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:13
 */
@Slf4j
@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private MovieInfoMapper infoMapper;
    @Autowired
    private MovieCoverMapper coverMapper;
    @Value("${local-file.img-path}")
    private String imgPath;

    @Override
    public IPage<MovieInfoDo> fuzzyQueryMovieInfo(Page<MovieInfoDo> page, String movieName) {
        QueryWrapper<MovieInfoDo> movieInfoQueryWrapper = new QueryWrapper<>();
        movieInfoQueryWrapper.like("name", movieName);
        return infoMapper.selectPage(page, movieInfoQueryWrapper);
    }

    @Override
    public IPage<MovieInfoDo> queryAllMovieInfo(Page<MovieInfoDo> page) {
        return infoMapper.selectPage(page, null);
    }

    @SneakyThrows
    @Override
    public byte[] getMovieCoverImgById(Long movieId) {
        QueryWrapper<MovieCoverDo> coverQueryWrapper = new QueryWrapper<>();
        coverQueryWrapper.eq("movie_id", movieId);
        MovieCoverDo movieCover = coverMapper.selectOne(coverQueryWrapper);
        File filePath = new File(imgPath);
        if (!filePath.exists()) {
            log.error("在磁盘中未找到:{} 目录", imgPath);
            throw new BusException("无法定位到文件目录!");
        }
        File file = new File(imgPath, movieCover.getId());
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] temp = new byte[10240000];
        int len = 0;
        while ((len = fis.read(temp)) != -1) {
            byteArrayOutputStream.write(temp, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
