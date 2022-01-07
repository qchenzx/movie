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
     * @return 支付宝的支付form表单
     */
    String submit(OrderMsg orderMsg);

}
