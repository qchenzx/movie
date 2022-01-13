package com.chenzx.movie.controller;

import com.chenzx.movie.entity.echarts.CommoditySalesData;
import com.chenzx.movie.service.echarts.IEchartsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/12 21:46
 */
@Api(tags = "数据可视化接口")
@RestController
@RequestMapping("/api/echarts/")
public class EchartsController {

    @Autowired
    private IEchartsService echartsService;

    @ApiOperation(value = "获取商品销量信息", notes = "该接口用于获取商品销量信息,可以分析出用户喜欢的商品")
    @GetMapping("commoditySalesData")
    public List<CommoditySalesData> getCommoditySalesData() {
        return echartsService.getCommoditySalesData();
    }

    public void getUserShoppingCartGoodsTotalPrice() {

    }

}
