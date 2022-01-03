package com.chenzx.movie.service.sys;

import com.chenzx.movie.entity.sys.CityList;
import com.chenzx.movie.entity.sys.LonLatParam;
import com.chenzx.movie.entity.sys.SysRegionArea;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 10:55
 */
public interface ISysService {

    /**
     * 获取城市列表
     *
     * @return 城市列表
     */
    List<CityList> getCityList();

    /**
     * 获取当前位置的定位信息
     *
     * @param paramBean 经纬度信息
     * @return 位置信息
     */
    SysRegionArea getLocation(LonLatParam paramBean);

}
