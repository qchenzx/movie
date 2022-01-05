package com.chenzx.movie.entity.ticketing;

import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 9:05
 */
@Data
public class TicketingInfoVo {

    /**
     * 电影名字
     */
    private String movieName;
    /**
     * 票价单价
     */
    private Long price;
    /**
     * 电影厅
     */
    private String cinema;

}
