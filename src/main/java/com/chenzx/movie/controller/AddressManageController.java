package com.chenzx.movie.controller;

import com.chenzx.movie.entity.address.AddAddressParam;
import com.chenzx.movie.entity.address.AddressVo;
import com.chenzx.movie.entity.address.EditAddressParam;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.service.address.IAddressManageService;
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

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 12:52
 */
@Slf4j
@RestController
@Api(tags = "地址管理接口")
@RequestMapping("/api/address")
public class AddressManageController {

    @Autowired
    private IAddressManageService addressManageService;

    @ApiOperation(value = "添加地址接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "地址对象", paramType = "body", dataType = "AddAddressParam")
    })
    @PostMapping(value = "/add")
    public String addAddress(@RequestBody @Valid AddAddressParam param, @ApiIgnore @AuthenticationPrincipal IUser iUser) {
        addressManageService.addAddress(param, iUser);
        return "创建地址成功!";
    }

    @ApiOperation(value = "查询地址接口", notes = "获取当前登录用户的所有保存的地址信息")
    @GetMapping(value = "/get")
    public AddressVo getAddress(@ApiIgnore @AuthenticationPrincipal IUser iUser) {
        return addressManageService.getAddress(iUser);
    }

    @ApiOperation(value = "修改地址接口", notes = "修改指定的收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "修改后的收货地址数据", paramType = "body", dataType = "EditAddressParam")
    })
    @PutMapping(value = "/edit")
    public String editAddress(@RequestBody @Valid EditAddressParam param, @ApiIgnore @AuthenticationPrincipal IUser iUser) {
        addressManageService.editAddress(param, iUser);
        return "修改成功!";
    }

    @ApiOperation(value = "删除收货地址接口", notes = "删除指定的收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "要删除的收货地址主键", paramType = "query", dataType = "String")
    })
    @DeleteMapping(value = "/delete")
    public String deleteAddress(@RequestParam String addressId, @ApiIgnore @AuthenticationPrincipal IUser iUser) {
        addressManageService.deleteAddress(addressId, iUser);
        return "删除成功!";
    }

}
