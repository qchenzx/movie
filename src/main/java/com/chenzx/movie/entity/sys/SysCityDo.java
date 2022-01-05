package com.chenzx.movie.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 13:48
 */
@Data
@TableName("sys_city")
public class SysCityDo implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
