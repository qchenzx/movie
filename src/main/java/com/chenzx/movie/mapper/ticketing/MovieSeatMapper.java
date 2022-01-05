package com.chenzx.movie.mapper.ticketing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzx.movie.entity.ticketing.MovieSeatDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/24 16:23
 */
@Mapper
public interface MovieSeatMapper extends BaseMapper<MovieSeatDo> {
}
