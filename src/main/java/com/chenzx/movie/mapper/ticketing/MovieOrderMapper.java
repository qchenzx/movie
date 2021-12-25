package com.chenzx.movie.mapper.ticketing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzx.movie.entity.ticketing.MovieOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/25 10:39
 */
@Mapper
public interface MovieOrderMapper extends BaseMapper<MovieOrder> {
}
