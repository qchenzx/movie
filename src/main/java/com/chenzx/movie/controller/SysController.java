package com.chenzx.movie.controller;

import com.chenzx.movie.entity.sys.CityList;
import com.chenzx.movie.entity.sys.LonLatParam;
import com.chenzx.movie.entity.sys.SysRegionArea;
import com.chenzx.movie.service.sys.ISysService;
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
@RequestMapping("/api/sys")
public class SysController {

    @Autowired
    private ISysService sysService;

    @GetMapping("city")
    public List<CityList> getCityList() {
        return sysService.getCityList();
    }

    @PostMapping("location")
    public SysRegionArea getLocation(@RequestBody @Valid LonLatParam paramBean) {
        return sysService.getLocation(paramBean);
    }
}
