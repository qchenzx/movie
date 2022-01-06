package com.chenzx.movie.mapper.collection;

import com.chenzx.movie.entity.collection.CollectionMovie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 9:59
 */
@Mapper
public interface CollectionMovieMapper {

    /**
     * 查询用户收藏的电影信息
     *
     * @param id 用户主键
     * @return 该用户收藏的电影信息
     */
    List<CollectionMovie> selectUserCollectionMovieInfo(Long id);

}
