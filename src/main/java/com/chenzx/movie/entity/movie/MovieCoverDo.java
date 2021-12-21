package com.chenzx.movie.entity.movie;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * movie_cover
 * @author chenzx
 */
@Data
@TableName("movie_cover")
public class MovieCoverDo implements Serializable {

    /**
     * 图片主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 图片名字
     */
    private String name;

    /**
     * 图片存储路径
     */
    private String path;

    /**
     * 电影主键
     */
    private Long movieId;

    private static final long serialVersionUID = 1L;
}
