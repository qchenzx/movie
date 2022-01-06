package com.chenzx.movie.service.collection;

import com.chenzx.movie.entity.collection.CollectionMovie;
import com.chenzx.movie.entity.collection.CollectionMovieParam;
import com.chenzx.movie.entity.sys.IUser;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 9:22
 */
public interface ICollectionManageService {

    /**
     * 获取用户收藏列表
     *
     * @param user 用户对象
     * @return 收藏列表
     */
    List<CollectionMovie> getCollectionList(IUser user);

    /**
     * 收藏电影,如果电影已经收藏,则取消收藏
     *
     * @param param 要收藏/取消收藏的电影信息
     * @param user  用户对象
     * @return 反馈给用户的提示
     */
    String collectionMovie(CollectionMovieParam param, IUser user);
}
