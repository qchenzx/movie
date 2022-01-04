package com.chenzx.movie.entity.movie;

import java.io.Serializable;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:13
 */
@Data
public class MovieInfoType implements Serializable {
    /**
     * 电影信息主键
     */
    private Long infoId;

    /**
     * 电影类型主键
     */
    private String typeId;

    private static final long serialVersionUID = 1L;
}
