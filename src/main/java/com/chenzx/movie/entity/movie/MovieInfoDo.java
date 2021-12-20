package com.chenzx.movie.entity.movie;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * movie_info
 * @author
 */
@Data
@TableName("movie_info")
public class MovieInfoDo implements Serializable {
    /**
     * 电影信息主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 电影名
     */
    private String name;

    /**
     * 电影类型
     */
    private String type;

    /**
     * 电影主演
     */
    private String cast;

    /**
     * 电影上映时间
     */
    private Date date;

    /**
     * 电影评分
     */
    private BigDecimal ratio;

    private static final long serialVersionUID = 1L;
}
