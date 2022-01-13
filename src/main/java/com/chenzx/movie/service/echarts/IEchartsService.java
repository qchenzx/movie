package com.chenzx.movie.service.echarts;

import com.chenzx.movie.entity.echarts.CommoditySalesData;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/13 17:55
 */
public interface IEchartsService {

    /**
     * 获取商品销量信息
     *
     * @return 商品销量信息
     */
    List<CommoditySalesData> getCommoditySalesData();

}
