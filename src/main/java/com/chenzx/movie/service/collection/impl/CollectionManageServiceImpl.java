package com.chenzx.movie.service.collection.impl;

import com.chenzx.movie.entity.collection.CollectionMovie;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.mapper.collection.CollectionMovieMapper;
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

    @Override
    public List<CollectionMovie> getCollectionList(IUser user) {
        return collectionMovieMapper.selectUserCollectionMovieInfo(user.getId());
    }
}
