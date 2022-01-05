package com.chenzx.movie.entity.ticketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:00
 */
@Data
@TableName("movie_order")
public class MovieOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 订单状态
     */
    private Integer status = MovieOrderStatusEnum.SUBMITTED.getValue();
    /**
     * 用户主键
     */
    private Long userId;
    /**
     * 手机号
     */
    private String mobileNumber;
    /**
     * 总价
     */
    private Long totalPrice;
    /**
     * 创建订单时间
     */
    private Date createTime = new Date();
}
