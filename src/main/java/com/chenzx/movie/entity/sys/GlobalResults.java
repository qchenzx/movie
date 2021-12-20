package com.chenzx.movie.entity.sys;

import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 9:20
 */
@Data
public class GlobalResults {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String desc;
    /**
     * 数据
     */
    private Object data;
    /**
     * 接口执行时间
     */
    private String execution;
}
