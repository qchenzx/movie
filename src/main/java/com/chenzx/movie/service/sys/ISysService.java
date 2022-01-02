package com.chenzx.movie.service.sys;

import com.chenzx.movie.entity.sys.CityList;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 10:55
 */
public interface ISysService {

    /**
     * 获取城市列表
     * @return 城市列表
     */
    List<CityList> getCityList();

}
