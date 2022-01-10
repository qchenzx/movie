package com.chenzx.movie.controller.commodity;

import com.chenzx.movie.entity.commodity.AddCommodityToCartParam;
import com.chenzx.movie.entity.commodity.ShopCartInfo;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.service.commodity.ICommodityManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/10 16:12
 */
@Api(tags = "购物车接口")
@RestController
@RequestMapping(value = "/api/commodity/cart")
public class CommodityShopCartController {

    @Autowired
    private ICommodityManageService commodityManageService;

    @ApiOperation(value = "根据用户获取用户存放在购物车中的商品")
    @GetMapping
    public List<ShopCartInfo> getShopCartContentByUser(@ApiIgnore @AuthenticationPrincipal IUser user) {
        return commodityManageService.getShopCartContentByUser(user);
    }

    @ApiOperation(value = "添加商品到购物车中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "要添加到购物车的商品", paramType = "body", dataType = "AddCommodityToCartParam")
    })
    @PostMapping
    public String addCommodityToCart(@RequestBody @Valid AddCommodityToCartParam param
            , @ApiIgnore @AuthenticationPrincipal IUser user) {
        return commodityManageService.addCommodityToCart(param, user);
    }

    @ApiOperation(value = "删除购物车中的商品")
    @DeleteMapping
    public void deleteCommodityForCart() {

    }


}
