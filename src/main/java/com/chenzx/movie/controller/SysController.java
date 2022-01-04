package com.chenzx.movie.controller;

import com.chenzx.movie.entity.sys.CityList;
import com.chenzx.movie.entity.sys.LonLatParam;
import com.chenzx.movie.entity.sys.SysRegionArea;
import com.chenzx.movie.service.sys.ISysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 10:29
 */
@Slf4j
@RestController
@Api(tags = "系统操作接口")
@RequestMapping("/api/sys")
public class SysController {

    @Autowired
    private ISysService sysService;

    @GetMapping("city")
    @ApiOperation("获取所有城市列表,用于选择城市")
    public List<CityList> getCityList() {
        return sysService.getCityList();
    }

    @PostMapping("location")
    @ApiOperation(value = "获取行政区划接口", notes = "根据传入的经纬度,进行行政区划判断,例如传入江苏省常州市的经纬度119.98148471:31.81579565,那么该方法将会返回江苏省常州市的具体信息")
    public SysRegionArea getLocation(@RequestBody @Valid LonLatParam paramBean) {
        return sysService.getLocation(paramBean);
    }
}
