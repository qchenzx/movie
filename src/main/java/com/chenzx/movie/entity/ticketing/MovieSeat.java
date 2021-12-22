package com.chenzx.movie.entity.ticketing;

import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 10:47
 */
@Data
public class MovieSeat {
    /**
     * 座位主键
     */
    private String id;
    /**
     * 当前座位的行坐标
     */
    private Integer rowLocation;
    /**
     * 当前座位的列坐标
     */
    private Integer colLocation;
    /**
     * 当前座位类型
     */
    private Integer type;
    /**
     * 是否售出
     */
    private Boolean isSold;
}
