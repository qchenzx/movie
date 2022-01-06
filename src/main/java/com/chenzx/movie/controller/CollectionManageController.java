package com.chenzx.movie.controller;

import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.collection.CollectionMovie;
import com.chenzx.movie.entity.collection.CollectionMovieParam;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.service.collection.ICollectionManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
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

    @ApiOperation(value = "收藏电影接口", notes = "需要传入要收藏的电影id,如果当前电影已经被用户收藏,则取消收藏。如果当前电影没有被用户收藏,则收藏该电影")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "要收藏的电影信息", paramType = "body", dataType = "CollectionMovieParam")
    })
    @PostMapping("click")
    public String collectionMovie(@RequestBody @Valid CollectionMovieParam param, @ApiIgnore @AuthenticationPrincipal IUser user) {
        return collectionManageService.collectionMovie(param, user);
    }

}
