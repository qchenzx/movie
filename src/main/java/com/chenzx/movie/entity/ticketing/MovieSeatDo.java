package com.chenzx.movie.entity.ticketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:00
 */
@Data
@TableName("movie_seat")
public class MovieSeatDo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 选座具体信息主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 行
     */
    private Integer rowLocation;
    /**
     * 列
     */
    private Integer colLocation;
    /**
     * 座位类型
     */
    private Integer type;
    /**
     * 是否已经售出
     */
    private Boolean isSold;
    /**
     * 电影选座信息主键
     */
    private String hallId;
    /**
     * 订单主键
     */
    private String orderId;
}
