package com.chenzx.movie.entity.movie;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:13
 */
@Data
public class MovieInfoType implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 电影信息主键
     */
    private Long infoId;
    /**
     * 电影类型主键
     */
    private String typeId;
}
