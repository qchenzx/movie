package com.chenzx.movie.service.address;

import com.chenzx.movie.entity.address.AddAddressParam;
import com.chenzx.movie.entity.address.AddressVo;
import com.chenzx.movie.entity.address.EditAddressParam;
import com.chenzx.movie.entity.address.MovieFavoriteParam;
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

    /**
     * 修改收货地址接口
     *
     * @param param 修改后的收货地址
     * @param user  用户对象
     */
    void editAddress(EditAddressParam param, IUser user);

    /**
     * 删除收获地址接口
     *
     * @param addressId 要删除的收货地址主键
     * @param user      用户对象
     */
    void deleteAddress(String addressId, IUser user);

    /**
     * 设置默认收货地址
     *
     * @param addressId 要设置默认收货地址的主键
     * @param user      用户对象
     */
    void setDefaultHarvestAddress(String addressId, IUser user);


}
