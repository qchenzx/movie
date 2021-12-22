package com.chenzx.movie.service.ticketing;

import com.chenzx.movie.entity.ticketing.MovieHallInfo;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 9:47
 */
public interface ITicketingService {

    /**
     * 通过电影主键,获取电影的座位信息
     *
     * @param movieId 电影主键
     * @return 座位信息
     */
    MovieHallInfo getTicketingInfoByMovieId(Long movieId);
}
