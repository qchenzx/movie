package com.chenzx.movie.entity.payment;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 20:06
 */
@Data
public class OrderMsg {

    /**
     * 订单ID
     */
    @NotEmpty
    private String orderId;
}
