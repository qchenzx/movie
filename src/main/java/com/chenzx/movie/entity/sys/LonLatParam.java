package com.chenzx.movie.entity.sys;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/3 11:02
 */
@Data
public class LonLatParam {

    /**
     * 经度
     */
    private Double lon;
    /**
     * 纬度
     */
    private Double lat;

}
