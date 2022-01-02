package com.chenzx.movie.entity.sys;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * sys_city
 * @author
 */
@Data
@TableName("sys_city")
public class SysCityDo implements Serializable {
    /**
     * 城市主键
     */
    @TableId
    private Integer id;

    /**
     * 城市名
     */
    private String name;

    /**
     * 首字母
     */
    private String initials;

    private static final long serialVersionUID = 1L;
}
