package com.chenzx.movie.controller.commodity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.commodity.CommodityDescribe;
import com.chenzx.movie.entity.commodity.CommodityDescribeParam;
import com.chenzx.movie.entity.commodity.CommodityInfo;
import com.chenzx.movie.service.commodity.ICommodityManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ApiOperation(value = "获取商品具体信息")
    @GetMapping
    public CommodityInfo getCommodityInfo(Long infoId) {
        return commodityManageService.getCommodityInfo(infoId);
    }

    @ApiOperation(value = "获取商品描述信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页字段", paramType = "query", dataType = "Page"),
            @ApiImplicitParam(name = "param", value = "查询商品的入参", paramType = "query", dataType = "CommodityDescribeParam"),
    })
    @PostMapping
    public IPage<CommodityDescribe> getCommodityDesc(Page<CommodityDescribe> page
            , CommodityDescribeParam param) {
        return commodityManageService.getCommodityDesc(page, param);
    }

}
