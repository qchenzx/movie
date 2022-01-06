package com.chenzx.movie.mapper.collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzx.movie.entity.collection.CollectionMovie;
import com.chenzx.movie.entity.collection.SysMovieCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 9:59
 */
@Mapper
public interface CollectionMovieMapper extends BaseMapper<SysMovieCollection> {

    /**
     * 查询用户收藏的电影信息
     *
     * @param id 用户主键
     * @return 该用户收藏的电影信息
     */
    List<CollectionMovie> selectUserCollectionMovieInfo(Long id);

}
