package com.chenzx.movie.service.address.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.address.AddAddressParam;
import com.chenzx.movie.entity.address.AddressDo;
import com.chenzx.movie.entity.address.AddressVo;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.mapper.address.AddressMapper;
import com.chenzx.movie.service.address.IAddressManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 13:45
 */
@Service
public class AddressManageServiceImpl implements IAddressManageService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void addAddress(AddAddressParam param, IUser user) {
        AddressDo address = param.newAddressDo(user.getId());
        addressMapper.insert(address);
    }

    @Override
    public AddressVo getAddress(IUser user) {
        List<AddressDo> address = addressMapper.selectList(new LambdaQueryWrapper<AddressDo>()
                .eq(AddressDo::getUserId, user.getId()));

        if (address.size() == 0) {
            throw new BusException("没有查询到您的收货地址信息,请您创建一个吧!");
        }

        String defaultAddressId = null;
        for (AddressDo addressDo : address) {
            if (addressDo.getIsDefaultAddress()) {
                defaultAddressId = addressDo.getId();
            }
        }
        AddressVo result = new AddressVo();
        result.setAddress(address);
        result.setDefaultAddressId(defaultAddressId);
        return result;
    }
}
