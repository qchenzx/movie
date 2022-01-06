package com.chenzx.movie.entity.collection;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_movie_collection")
public class SysMovieCollection implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
