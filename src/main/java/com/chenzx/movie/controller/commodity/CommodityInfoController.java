package com.chenzx.movie.controller.commodity;

import com.chenzx.movie.entity.commodity.CommodityInfo;
import com.chenzx.movie.service.commodity.ICommodityManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 18:40
 */
@Slf4j
@RestController
@Api(tags = "商品信息接口")
@RequestMapping("/api/commodity/info")
public class CommodityInfoController {

    @Autowired
    private ICommodityManageService commodityManageService;

    @ApiOperation(value = "获取商品信息接口")
    @GetMapping
    public CommodityInfo getCommodityInfo(Long infoId) {
        return commodityManageService.getCommodityInfo(infoId);
    }

}
