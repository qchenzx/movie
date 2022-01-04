package com.chenzx.movie.service.sys.impl;

import com.chenzx.movie.entity.sys.CityList;
import com.chenzx.movie.entity.sys.LonLatParam;
import com.chenzx.movie.entity.sys.SysCityDo;
import com.chenzx.movie.entity.sys.SysRegionArea;
import com.chenzx.movie.mapper.sys.SysCityMapper;
import com.chenzx.movie.mapper.sys.SysRegionAreaMapper;
import com.chenzx.movie.service.sys.ISysService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 10:56
 */
@Service
public class SysServiceImpl implements ISysService {

    @Autowired
    private SysCityMapper cityMapper;
    @Autowired
    private SysRegionAreaMapper regionAreaMapper;

    @Override
    @Cacheable(cacheNames = "movie_city")
    public List<CityList> getCityList() {
        List<SysCityDo> sysCityDos = cityMapper.selectList(null);
        Map<String, List<SysCityDo>> collect = sysCityDos.stream().collect(Collectors.groupingBy(SysCityDo::getInitials));
        ArrayList<CityList> cityLists = Lists.newArrayList();
        for (String initials : collect.keySet()) {
            CityList cityList = new CityList();
            cityList.setInitials(initials);
            cityList.setCity(collect.get(initials).stream().map(SysCityDo::getName).collect(Collectors.toList()));
            cityLists.add(cityList);
        }
        return cityLists;
    }

    @Override
    public SysRegionArea getLocation(LonLatParam paramBean) {
        return regionAreaMapper.queryNearestPoint(paramBean);
    }
}
