package com.chenzx.movie.entity.ticketing;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/7 13:42
 */
@Data
@TableName("movie_hall")
public class MovieHallDo implements Serializable {
    /**
     * 电影座位主键
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 影厅座位行
     */
    private Integer rowTotal;
    /**
     * 影厅座位列
     */
    private Integer colTotal;
    /**
     * 单价
     */
    private Long price = 4500L;
    /**
     * 电影主键
     */
    private Long infoId;

    private static final long serialVersionUID = 1L;
}
