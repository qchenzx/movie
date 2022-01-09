package com.chenzx.movie.controller.commodity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.commodity.CommodityDescribe;
import com.chenzx.movie.entity.commodity.CommodityDescribeParam;
import com.chenzx.movie.entity.commodity.CommodityInfo;
import com.chenzx.movie.entity.commodity.MallType;
import com.chenzx.movie.service.commodity.ICommodityManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "获取商品分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rank", value = "如果为1,则返回父级类目,如果为2则返回父级中子级类目", dataType = "Integer"),
            @ApiImplicitParam(name = "typeId", value = "如果rank为2,则该值必传,传入父级目录的id", dataType = "Integer")
    })
    @GetMapping(value = "type")
    public List<MallType> getCommodityClassification(@RequestParam Integer rank, @RequestParam(required = false) Long typeId) {
        return commodityManageService.getCommodityClassification(rank, typeId);
    }

}
