package com.chenzx.movie.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 13:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_avatar")
public class SysUserAvatar implements Serializable {
    /**
     * 头像主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 头像上传时间
     */
    private Date createDate;

    private static final long serialVersionUID = 1L;
}
