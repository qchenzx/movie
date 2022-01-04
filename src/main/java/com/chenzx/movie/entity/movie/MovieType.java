package com.chenzx.movie.entity.movie;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieType implements Serializable {
    /**
     * 电影类型主键
     */
    private String id;

    /**
     * 电影分类名字
     */
    private String name;

    private static final long serialVersionUID = 1L;
}
