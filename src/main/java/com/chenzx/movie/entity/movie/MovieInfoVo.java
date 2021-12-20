package com.chenzx.movie.entity.movie;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 11:14
 */
@Data
public class MovieInfoVo {

    /**
     * 电影主键
     */
    private Long cId;

    /**
     * 电影名
     */
    private String cName;

    /**
     * 电影类型
     */
    private String cType;

    /**
     * 电影主演
     */
    private String cCast;

    /**
     * 电影上映时间
     */
    private Date cDate;

    /**
     * 电影评分
     */
    private BigDecimal cRatio;

    /**
     * 图片base64加密后数据
     */
    private String imageData;

}
