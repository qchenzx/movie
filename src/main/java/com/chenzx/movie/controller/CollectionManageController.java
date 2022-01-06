package com.chenzx.movie.controller;

import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.collection.CollectionMovie;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.service.collection.ICollectionManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/6 9:19
 */
@Slf4j
@RestController
@RequestMapping("/api/collection")
@Api(tags = "用户收藏电影管理接口")
public class CollectionManageController {

    @Autowired
    private ICollectionManageService collectionManageService;

    @GetMapping("get")
    @ApiOperation(value = "获取用户收藏电影列表接口", notes = "该接口用于用户个人资料页面的收藏列表展示,该接口访问时必须登录,需要获取用户信息,根据用户信息查找该用户收藏的电影,所以权限设置为未登录不可以访问")
    public List<CollectionMovie> getCollectionMovieList(@ApiIgnore @AuthenticationPrincipal IUser user) {
        if (user == null) {
            throw new BusException("您未登录,请先登陆!");
        }
        return collectionManageService.getCollectionList(user);
    }

}
