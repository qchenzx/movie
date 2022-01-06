package com.chenzx.movie.entity.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 9:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMovieCollection implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 电影主键
     */
    private Long movieId;

    private static final long serialVersionUID = 1L;
}
