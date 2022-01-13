package com.chenzx.movie.controller;

import com.chenzx.movie.entity.city.CityDetailedInfo;
import com.chenzx.movie.service.city.ICityInfoManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/13 18:17
 */
@Api(tags = "城市管理接口")
@RequestMapping("/sys/city/")
@RestController
public class CityInfoManageController {

    @Autowired
    private ICityInfoManageService cityInfoManageService;

    @ApiOperation("获取所有省份信息")
    @GetMapping("allProvinces")
    public List<CityDetailedInfo> getAllProvinces() {
        return cityInfoManageService.getAllProvinces();
    }

    @ApiOperation("根据省份id获取所有该省下面的市的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provincesId", value = "省份id", dataType = "Integer", paramType = "param")
    })
    @GetMapping("allCity")
    public List<CityDetailedInfo> getAllCity(@RequestParam Integer provincesId) {
        return cityInfoManageService.getAllCity(provincesId);
    }

    @ApiOperation("根据市")
    public List<CityDetailedInfo> getAllArea(@RequestParam Integer cityId) {
        return null;

    }
}
