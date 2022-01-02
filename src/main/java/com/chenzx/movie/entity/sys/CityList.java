package com.chenzx.movie.entity.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 10:30
 */
@Data
public class CityList implements Serializable {

    /**
     * 首字母
     */
    private String initials;

    /**
     * 城市
     */
    private List<String> city;

}
