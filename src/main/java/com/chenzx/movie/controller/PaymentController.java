package com.chenzx.movie.controller;

import com.chenzx.movie.entity.payment.OrderMsg;
import com.chenzx.movie.service.payment.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 17:18
 */
@Slf4j
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("submit")
    public String submit(@RequestBody @Valid OrderMsg order) {
        return paymentService.submit(order);
    }

}
