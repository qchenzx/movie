package com.chenzx.movie.service.ticketing.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.entity.sys.IUserDo;
import com.chenzx.movie.entity.ticketing.*;
import com.chenzx.movie.mapper.sys.IUserMapper;
import com.chenzx.movie.mapper.ticketing.MovieHallMapper;
import com.chenzx.movie.mapper.ticketing.MovieOrderMapper;
import com.chenzx.movie.mapper.ticketing.MovieSeatMapper;
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
    @Autowired
    private MovieSeatMapper movieSeatMapper;
    @Autowired
    private MovieOrderMapper movieOrderMapper;
    @Autowired
    private IUserMapper userMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MovieOrder submitSeat(MovieSeatParam param, IUser user) {
        MovieHallDo movieHallDo = movieHallMapper.selectById(param.getHallId());
        if (movieHallDo == null) {
            throw new BusException("没有查询到放映场次信息,请刷新后重新尝试");
        }
        IUserDo iUser = userMapper.selectById(user.getId());
        Long total = movieHallDo.getPrice() * param.getSeats().size();

        MovieOrder movieOrder = new MovieOrder();
        movieOrder.setUserId(iUser.getId());
        movieOrder.setMobileNumber(iUser.getPhoneNumber());
        movieOrder.setTotalPrice(total);
        movieOrderMapper.insert(movieOrder);

        for (MovieSeatLocation location : param.getSeats()) {
            if (isSeatsSold(location.getRow(), location.getCol(), param.getHallId())) {
                throw new BusException("座位已售出!请刷新后重新尝试购买");
            }
            MovieSeatDo movieSeat = new MovieSeatDo();
            movieSeat.setIsSold(true);
            movieSeat.setRowLocation(location.getRow());
            movieSeat.setColLocation(location.getCol());
            movieSeat.setType(TicketingSeatEnum.NORMAL_SEAT.getType());
            movieSeat.setHallId(param.getHallId());
            movieSeat.setOrderId(movieOrder.getId());
            movieSeatMapper.insert(movieSeat);
        }
        return movieOrder;
    }

    /**
     * 用来判断当前座位是否被售出,如果已售出返回true,否则false
     *
     * @param row 座位排
     * @param col 座位座
     * @return 座位是否被售出
     */
    private Boolean isSeatsSold(Integer row, Integer col, String hallId) {
        MovieSeatDo hasMovieSeat = movieSeatMapper.selectOne(
                new LambdaQueryWrapper<MovieSeatDo>()
                        .eq(MovieSeatDo::getRowLocation, row)
                        .eq(MovieSeatDo::getColLocation, col)
                        .eq(MovieSeatDo::getHallId, hallId));
        return hasMovieSeat != null && hasMovieSeat.getIsSold();
    }

    private void addMovieHallSeatLayout(Long movieId) {
        MovieHallDo movieHallDo = new MovieHallDo();
        movieHallDo.setInfoId(movieId);
        movieHallDo.setRowTotal(9);
        movieHallDo.setColTotal(8);
        movieHallMapper.insert(movieHallDo);
    }
}
