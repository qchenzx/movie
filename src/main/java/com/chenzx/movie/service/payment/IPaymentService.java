package com.chenzx.movie.service.payment;

import com.chenzx.movie.entity.payment.OrderMsg;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 20:13
 */
public interface IPaymentService {

    /**
     * 提交支付请求
     *
     * @param orderMsg 订单信息
     * @param response 返回头
     */
    void submit(OrderMsg orderMsg, HttpServletResponse response);

}
