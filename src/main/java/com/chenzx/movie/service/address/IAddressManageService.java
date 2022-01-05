package com.chenzx.movie.service.address;

import com.chenzx.movie.entity.address.AddAddressParam;
import com.chenzx.movie.entity.address.AddressVo;
import com.chenzx.movie.entity.sys.IUser;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 13:43
 */
public interface IAddressManageService {

    /**
     * 添加地址接口
     *
     * @param param 地址对象
     * @param user  用户对象
     */
    void addAddress(AddAddressParam param, IUser user);

    /**
     * 查询地址接口
     *
     * @param user 用户对象
     * @return 给用户展示的地址对象
     */
    AddressVo getAddress(IUser user);

}
