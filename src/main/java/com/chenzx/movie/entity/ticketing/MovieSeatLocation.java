package com.chenzx.movie.entity.ticketing;

import lombok.Data;

import javax.validation.constraints.Digits;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/24 11:37
 */
@Data
public class MovieSeatLocation {
    /**
     * 行
     */
    @Digits(integer = 2, fraction = 0, message = "row属性必须是整数,且整数范围为0~99")
    private Integer row;
    /**
     * 列
     */
    @Digits(integer = 2, fraction = 0, message = "col属性必须是整数,且整数范围为0~99")
    private Integer col;

}
