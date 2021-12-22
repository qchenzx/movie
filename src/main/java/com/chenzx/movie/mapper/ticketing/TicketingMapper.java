package com.chenzx.movie.mapper.ticketing;

import com.chenzx.movie.entity.ticketing.MovieHallInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 9:52
 */
@Mapper
public interface TicketingMapper {

    /**
     * 通过影片id查询电影放映厅座位信息
     *
     * @param movieId 影片id
     * @return 电影放映厅座位信息
     */
    MovieHallInfo selectMovieHallInfoByMovieId(Long movieId);

}
