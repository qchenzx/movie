package com.chenzx.movie.service.city.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.entity.city.CityDetailedInfo;
import com.chenzx.movie.entity.sys.SysRegionArea;
import com.chenzx.movie.mapper.sys.SysRegionAreaMapper;
import com.chenzx.movie.service.city.ICityInfoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/13 18:27
 */
@Service
public class CityInfoManageServiceImpl implements ICityInfoManageService {

    @Autowired
    private SysRegionAreaMapper sysRegionAreaMapper;

    @Override
    @Cacheable(cacheNames = "sys_all_province")
    public List<CityDetailedInfo> getAllProvinces() {
        List<SysRegionArea> sysRegionAreas = sysRegionAreaMapper.selectList(
                new LambdaQueryWrapper<SysRegionArea>()
                        .eq(SysRegionArea::getParentId, 0)
        );
        return sysRegionAreaListConvert(sysRegionAreas);
    }

    @Override
    public List<CityDetailedInfo> getAllCity(Integer provincesId) {
        List<SysRegionArea> sysRegionAreas = sysRegionAreaMapper.selectList(
                new LambdaQueryWrapper<SysRegionArea>()
                        .eq(SysRegionArea::getParentId, provincesId)
                        .eq(SysRegionArea::getLevel, 2)
        );
        return sysRegionAreaListConvert(sysRegionAreas);
    }

    private List<CityDetailedInfo> sysRegionAreaListConvert(List<SysRegionArea> var1) {
        return var1.stream().map((v) -> {
            CityDetailedInfo cityDetailedInfo = new CityDetailedInfo();
            cityDetailedInfo.setId(v.getId());
            cityDetailedInfo.setAreaCode(v.getAreaCode());
            cityDetailedInfo.setName(v.getName());
            cityDetailedInfo.setPinYin(v.getPinYin());
            cityDetailedInfo.setSimplePy(v.getSimplePy());
            cityDetailedInfo.setWholeName(v.getWholeName());
            return cityDetailedInfo;
        }).collect(Collectors.toList());
    }
}
