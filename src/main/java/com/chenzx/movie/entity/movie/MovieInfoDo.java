package com.chenzx.movie.entity.movie;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * movie_info
 *
 * @author chenzx
 */
@Data
@TableName("movie_info")
public class MovieInfoDo implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @JsonFormat(pattern = "yyyy-MM-dd日 HH:mm", timezone = "GMT+8")
    private Date date;
    /**
     * 电影评分
     */
    private BigDecimal ratio;
}
