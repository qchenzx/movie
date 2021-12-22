package com.chenzx.movie.controller;

import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.ticketing.MovieHallInfo;
import com.chenzx.movie.service.ticketing.ITicketingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 9:01
 */
@Slf4j
@RestController
@RequestMapping("/api/ticketing")
public class TicketingController {

    @Autowired
    private ITicketingService ticketingService;

    @GetMapping
    public MovieHallInfo getTickingInfo(@RequestParam(required = false) Long movieId) {
        if (movieId == null) {
            throw new BusException("电影主键不能为空");
        }
        return ticketingService.getTicketingInfoByMovieId(movieId);
    }

}
