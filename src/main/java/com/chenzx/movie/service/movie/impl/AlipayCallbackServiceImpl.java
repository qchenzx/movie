package com.chenzx.movie.service.movie.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.sys.IUserDo;
import com.chenzx.movie.entity.ticketing.MovieOrder;
import com.chenzx.movie.entity.ticketing.MovieOrderStatusEnum;
import com.chenzx.movie.mapper.sys.IUserMapper;
import com.chenzx.movie.mapper.ticketing.MovieOrderMapper;
import com.chenzx.movie.service.movie.IAlipayCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 13:24
 */
@Slf4j
@Service
public class AlipayCallbackServiceImpl implements IAlipayCallbackService {

    @Autowired
    private MovieOrderMapper orderMapper;
    @Autowired
    private IUserMapper userMapper;

    @Override
    public void notifyCallback(Map<String, String> params) {
        paymentOrderCallback(params);
    }

    @Async("doSomethingExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void paymentOrderCallback(Map<String, String> params) {
        String orderNo = params.get("out_trade_no");
        Integer orderPrice = Integer.valueOf(params.get("total_amount").split("\\.")[0]);

        MovieOrder order = orderMapper.selectOne(
                new LambdaQueryWrapper<MovieOrder>().eq(MovieOrder::getId, orderNo).eq(MovieOrder::getTotalPrice, orderPrice));
        if (order == null) {
            log.warn("回调信息与订单不匹配,该回调信息将会被忽略.请求内容: {}", params.toString());
            throw new BusException("回调信息与订单不匹配,该回调信息将会被忽略");
        }
        order.setStatus(MovieOrderStatusEnum.PAID.getValue());
        orderMapper.updateById(order);
        IUserDo user = userMapper.selectById(order.getUserId());
        user.setIntegral(user.getIntegral() + orderPrice);
        userMapper.updateById(user);
        log.info("[{}]: order: {} payment successful! user: {} add {} integral", DateUtil.now(), order.getId(), user.getAccount(), orderPrice);
    }
}
