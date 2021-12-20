package com.chenzx.movie.mapper.movie;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzx.movie.entity.movie.MovieInfoDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:15
 */
@Mapper
public interface MovieInfoMapper extends BaseMapper<MovieInfoDo> {
}
