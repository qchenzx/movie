package com.chenzx.movie.service.echarts.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.entity.commodity.MallInfo;
import com.chenzx.movie.entity.echarts.CommoditySalesData;
import com.chenzx.movie.mapper.commodity.MallInfoMapper;
import com.chenzx.movie.service.echarts.IEchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/13 17:56
 */
@Service
public class EchartsServiceImpl implements IEchartsService {

    @Autowired
    private MallInfoMapper mallInfoMapper;

    @Override
    public List<CommoditySalesData> getCommoditySalesData() {
        List<MallInfo> mallInfos = mallInfoMapper.selectList(new LambdaQueryWrapper<MallInfo>().orderByDesc(MallInfo::getSalesVolume));
        return mallInfos.stream().map((v) -> {
            CommoditySalesData commoditySalesData = new CommoditySalesData();
            commoditySalesData.setCommodityName(v.getName());
            commoditySalesData.setSalesVolume(v.getSalesVolume());
            return commoditySalesData;
        }).collect(Collectors.toList());
    }
}
