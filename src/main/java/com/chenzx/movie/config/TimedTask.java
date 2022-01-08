package com.chenzx.movie.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.entity.ticketing.MovieOrder;
import com.chenzx.movie.entity.ticketing.MovieOrderStatusEnum;
import com.chenzx.movie.entity.ticketing.MovieSeatDo;
import com.chenzx.movie.mapper.ticketing.MovieOrderMapper;
import com.chenzx.movie.mapper.ticketing.MovieSeatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 11:50
 */
@Slf4j
@Component
public class TimedTask {

    @Autowired
    private MovieOrderMapper orderMapper;
    @Autowired
    private MovieSeatMapper seatMapper;

    @Scheduled(cron = "*/15 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void deleteTimeoutOrder() {
        List<MovieOrder> orders = orderMapper.queryTimeoutOrder();
        int deleteSeatCount = 0;
        for (MovieOrder order : orders) {
            List<MovieSeatDo> movieSeats = seatMapper.selectList(new LambdaQueryWrapper<MovieSeatDo>()
                    .eq(MovieSeatDo::getOrderId, order.getId()));
            deleteSeatCount += seatMapper.deleteBatchIds(movieSeats.stream().map(MovieSeatDo::getId).collect(Collectors.toList()));
            order.setStatus(MovieOrderStatusEnum.CANCELLED.getValue());
            orderMapper.updateById(order);
        }

        if (orders.size() != 0 && deleteSeatCount != 0) {
            log.info("[{}]: cron ---> 共取消{}个订单 释放{}个座位", DateUtil.now(), orders.size(), deleteSeatCount);
        }
    }
}
