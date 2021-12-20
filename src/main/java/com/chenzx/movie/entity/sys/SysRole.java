package com.chenzx.movie.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * sys_role
 * @author
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {
    /**
     * 权限主键
     */
    @TableId
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限值
     */
    private String value;

    /**
     * 该用户组下所有用户是否启用
     */
    private Boolean enabled;

    private static final long serialVersionUID = 1L;
}
