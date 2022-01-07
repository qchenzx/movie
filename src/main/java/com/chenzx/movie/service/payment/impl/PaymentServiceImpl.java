package com.chenzx.movie.service.payment.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.payment.OrderMsg;
import com.chenzx.movie.entity.ticketing.MovieOrder;
import com.chenzx.movie.entity.ticketing.MovieOrderStatusEnum;
import com.chenzx.movie.mapper.ticketing.MovieOrderMapper;
import com.chenzx.movie.service.payment.IPaymentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 20:14
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private MovieOrderMapper orderMapper;
    @Autowired
    private AlipayClient alipayClient;

    @SneakyThrows
    @Override
    public String submit(OrderMsg orderMsg) {
        MovieOrder movieOrder = orderMapper.selectOne(new LambdaQueryWrapper<MovieOrder>()
                .eq(MovieOrder::getStatus, MovieOrderStatusEnum.SUBMITTED.getValue())
                .eq(MovieOrder::getId, orderMsg.getOrderId()));
        if (movieOrder == null) {
            throw new BusException("无效的订单!请刷新后重新尝试");
        }
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", movieOrder.getId());
        bizContent.put("total_amount", movieOrder.getTotalPrice());
        bizContent.put("subject", "电影票支付-" + movieOrder.getId());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl("");
        request.setReturnUrl("");
        request.setBizContent(bizContent.toString());

        return alipayClient.pageExecute(request).getBody();
    }
}
