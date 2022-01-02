package com.chenzx.movie.controller;

import com.chenzx.movie.entity.sys.CityList;
import com.chenzx.movie.service.sys.ISysService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
