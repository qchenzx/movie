package com.chenzx.movie.service.ticketing.impl;

import com.chenzx.movie.entity.ticketing.MovieHallInfo;
import com.chenzx.movie.service.ticketing.ITicketingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TicketingServiceImplTest {

    @Autowired
    private ITicketingService ticketingService;

    @Test
    void getTicketingInfoByMovieId() {
        MovieHallInfo ticketingInfoByMovieId = ticketingService.getTicketingInfoByMovieId(1002L);
        log.info(ticketingInfoByMovieId.toString());
    }
}
