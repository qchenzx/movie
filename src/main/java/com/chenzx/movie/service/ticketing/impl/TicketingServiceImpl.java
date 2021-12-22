package com.chenzx.movie.service.ticketing.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.entity.ticketing.MovieHallDo;
import com.chenzx.movie.entity.ticketing.MovieHallInfo;
import com.chenzx.movie.mapper.ticketing.MovieHallMapper;
import com.chenzx.movie.mapper.ticketing.TicketingMapper;
import com.chenzx.movie.service.ticketing.ITicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 9:47
 */
@Service
public class TicketingServiceImpl implements ITicketingService {

    @Autowired
    private MovieHallMapper movieHallMapper;
    @Autowired
    private TicketingMapper ticketingMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MovieHallInfo getTicketingInfoByMovieId(Long movieId) {
        MovieHallDo movieHall = movieHallMapper.selectOne(new LambdaQueryWrapper<MovieHallDo>()
                .eq(MovieHallDo::getInfoId, movieId));
        if (movieHall == null) {
            addMovieHallSeatLayout(movieId);
        }
        return ticketingMapper.selectMovieHallInfoByMovieId(movieId);
    }

    private void addMovieHallSeatLayout(Long movieId) {
        MovieHallDo movieHallDo = new MovieHallDo();
        movieHallDo.setInfoId(movieId);
        movieHallDo.setRowTotal(9);
        movieHallDo.setColTotal(8);
        movieHallMapper.insert(movieHallDo);
    }
}
