package com.chenzx.movie.service.movie.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.movie.*;
import com.chenzx.movie.mapper.movie.MovieCoverMapper;
import com.chenzx.movie.mapper.movie.MovieInfoMapper;
import com.chenzx.movie.mapper.movie.MovieInfoTypeMapper;
import com.chenzx.movie.mapper.movie.MovieTypeMapper;
import com.chenzx.movie.service.movie.IMovieService;
import com.google.common.base.Joiner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private MovieTypeMapper typeMapper;
    @Autowired
    private MovieInfoTypeMapper infoTypeMapper;
    @Value("${local-file.img-path}")
    private String imgPath;

    @Override
    public IPage<MovieInfoDo> fuzzyQueryMovieInfo(MovieInfoParam param, Page<MovieInfoDo> page) {
        QueryWrapper<MovieInfoDo> movieInfoQueryWrapper = new QueryWrapper<>();
        List<Long> movieInfo = getMoviePrimaryKeyUnderCategory(param.getType());
        if (movieInfo != null) {
            movieInfoQueryWrapper.inSql("id", Joiner.on(",").join(movieInfo));
        }
        movieInfoQueryWrapper.like("name", param.getMovieName());
        if (SortingRulesEnum.TIME.getValue().equals(param.getOrderBy())) {
            movieInfoQueryWrapper.orderByDesc("date");
        } else if (SortingRulesEnum.EVALUATE.getValue().equals(param.getOrderBy())) {
            movieInfoQueryWrapper.orderByDesc("ratio");
        } else {
            throw new BusException("??????????????????");
        }
        Page<MovieInfoDo> movieInfoDoPage = infoMapper.selectPage(page, movieInfoQueryWrapper);
        populateClassificationFields(movieInfoDoPage.getRecords());
        return movieInfoDoPage;
    }

    @Override
    public IPage<MovieInfoDo> queryAllMovieInfo(MovieInfoParam param, Page<MovieInfoDo> page) {
        LambdaQueryWrapper<MovieInfoDo> movieInfoQueryWrapper = new LambdaQueryWrapper<>();
        List<Long> movieInfo = getMoviePrimaryKeyUnderCategory(param.getType());
        if (movieInfo != null) {
            movieInfoQueryWrapper.inSql(MovieInfoDo::getId, Joiner.on(",").join(movieInfo));
        }
        if (SortingRulesEnum.TIME.getValue().equals(param.getOrderBy())) {
            movieInfoQueryWrapper.orderByDesc(MovieInfoDo::getDate);
        } else if (SortingRulesEnum.EVALUATE.getValue().equals(param.getOrderBy())) {
            movieInfoQueryWrapper.orderByDesc(MovieInfoDo::getRatio);
        } else {
            throw new BusException("??????????????????");
        }
        Page<MovieInfoDo> movieInfoDoPage = infoMapper.selectPage(page, movieInfoQueryWrapper);
        populateClassificationFields(movieInfoDoPage.getRecords());
        return movieInfoDoPage;
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param movieInfo ????????????
     */
    public void populateClassificationFields(List<MovieInfoDo> movieInfo) {
        for (MovieInfoDo movie : movieInfo) {
            List<MovieInfoType> movieInfoTypes = infoTypeMapper.selectList(
                    new LambdaQueryWrapper<MovieInfoType>().eq(MovieInfoType::getInfoId, movie.getId()));
            String condition = movieInfoTypes.stream().map(MovieInfoType::getTypeId).collect(Collectors.joining("\",\""));
            List<MovieType> movieTypes = typeMapper.selectList(new QueryWrapper<MovieType>()
                    .inSql("id", "\"" + condition + "\""));
            movie.setType(movieTypes.stream().map(MovieType::getName).collect(Collectors.joining(",")));
        }
    }

    /**
     * ???????????????????????????
     *
     * @param type ????????????
     * @return ?????????????????????list
     */
    private List<Long> getMoviePrimaryKeyUnderCategory(String type) {
        String allMovieTypeKey = "0";
        if (allMovieTypeKey.equals(type)) {
            return null;
        }
        List<Long> movieId = infoTypeMapper.selectList(new LambdaQueryWrapper<MovieInfoType>().eq(MovieInfoType::getTypeId, type))
                .stream().map(MovieInfoType::getInfoId).collect(Collectors.toList());
        if (movieId.size() == 0) {
            throw new BusException("????????????????????????????????????");
        }
        return movieId;
    }

    @SneakyThrows
    @Override
    public byte[] getMovieCoverImgById(Long movieId) {
        QueryWrapper<MovieCoverDo> coverQueryWrapper = new QueryWrapper<>();
        coverQueryWrapper.eq("movie_id", movieId);
        MovieCoverDo movieCover = coverMapper.selectOne(coverQueryWrapper);
        File filePath = new File(imgPath);
        if (!filePath.exists()) {
            log.error("?????????????????????:{} ??????", imgPath);
            throw new BusException("???????????????????????????!");
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

    @Override
    @Cacheable(cacheNames = "movie_type")
    public List<MovieType> getMovieAllType() {
        List<MovieType> movieAllType = typeMapper.selectList(null);
        movieAllType.add(0, new MovieType("0", "??????"));
        return movieAllType;
    }
}
