package com.chenzx.movie.service.city;

import com.chenzx.movie.entity.city.CityDetailedInfo;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/13 18:26
 */
public interface ICityInfoManageService {

    /**
     * 获取中国全部的省份信息
     *
     * @return 全部省份信息
     */
    List<CityDetailedInfo> getAllProvinces();

    /**
     * 根据省份id获取该省份下所有市的信息
     *
     * @param provincesId 省份id
     * @return 该省下的所有市
     */
    List<CityDetailedInfo> getAllCity(Integer provincesId);

}
