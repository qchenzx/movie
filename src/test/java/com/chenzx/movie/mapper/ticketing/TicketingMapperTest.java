package com.chenzx.movie.mapper.ticketing;

import com.chenzx.movie.entity.ticketing.MovieHallInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TicketingMapperTest {

    @Autowired
    private TicketingMapper ticketingMapper;

    @Test
    void selectMovieHallInfoByMovieId() {
        MovieHallInfo movieHallInfo = ticketingMapper.selectMovieHallInfoByMovieId(10L);
        log.info(movieHallInfo.toString());
    }
}
