package com.chenzx.movie.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.chenzx.movie.service.movie.IAlipayCallbackService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 8:36
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/alipayCallback")
public class AlipayCallbackController {

    @Autowired
    private IAlipayCallbackService alipayCallbackService;

    @PostMapping
    public String notifyCallback(HttpServletRequest request) throws AlipayApiException {
        Map<String, String> params = Maps.newHashMap();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCertCheckV1(params
                , "D:\\cert\\alipayCertPublicKey_RSA2.crt"
                , "UTF-8", "RSA2");
        if (signVerified) {
            alipayCallbackService.notifyCallback(params);
            return "success";
        } else {
            log.error("支付宝回调签名认证失败,paramsJson:{}", params.toString());
            return "failure";
        }
    }
}
