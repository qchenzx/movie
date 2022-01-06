package com.chenzx.movie.service.collection.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.address.MovieFavoriteParam;
import com.chenzx.movie.entity.collection.CollectionMovie;
import com.chenzx.movie.entity.collection.CollectionMovieParam;
import com.chenzx.movie.entity.collection.SysMovieCollection;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.mapper.collection.CollectionMovieMapper;
import com.chenzx.movie.mapper.movie.MovieInfoMapper;
import com.chenzx.movie.service.collection.ICollectionManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 9:50
 */
@Service
public class CollectionManageServiceImpl implements ICollectionManageService {

    @Autowired
    private CollectionMovieMapper collectionMovieMapper;
    @Autowired
    private MovieInfoMapper infoMapper;

    @Override
    public List<CollectionMovie> getCollectionList(IUser user) {
        return collectionMovieMapper.selectUserCollectionMovieInfo(user.getId());
    }

    @Override
    public String collectionMovie(CollectionMovieParam param, IUser user) {
        Long movieId = param.getMovieId();
        MovieInfoDo movie = infoMapper.selectById(movieId);
        if (movie == null) {
            throw new BusException("未找到电影信息,请检查入参电影主键是否正确!");
        }
        SysMovieCollection sysMovieCollection = collectionMovieMapper.selectOne(
                new LambdaQueryWrapper<SysMovieCollection>()
                        .eq(SysMovieCollection::getMovieId, movieId)
                        .eq(SysMovieCollection::getUserId, user.getId()));

        if (sysMovieCollection == null) {
            sysMovieCollection = new SysMovieCollection(null, user.getId(), movieId);
            collectionMovieMapper.insert(sysMovieCollection);
            return "收藏成功!";
        } else {
            collectionMovieMapper.deleteById(sysMovieCollection);
            return "取消收藏成功!";
        }
    }

    @Override
    public Boolean isMovieFavorite(MovieFavoriteParam param, IUser user) {
        SysMovieCollection sysMovieCollection = collectionMovieMapper.selectOne(
                new LambdaQueryWrapper<SysMovieCollection>()
                        .eq(SysMovieCollection::getMovieId, param.getMovieId())
                        .eq(SysMovieCollection::getUserId, user.getId()));
        return sysMovieCollection != null;
    }
}
