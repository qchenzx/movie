package com.chenzx.movie.entity.sys;

import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/16 14:25
 */
@Data
public class UserInfo {

    /**
     * 用户对象
     */
    private IUser user;
    /**
     * 用户组中文名
     */
    private String userGroupName;
}
