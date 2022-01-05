package com.chenzx.movie.entity.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/20 13:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieType implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 电影类型主键
     */
    private String id;
    /**
     * 电影分类名字
     */
    private String name;
}
