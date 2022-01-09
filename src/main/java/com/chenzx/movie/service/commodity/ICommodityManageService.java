package com.chenzx.movie.service.commodity;

import com.chenzx.movie.entity.commodity.CommodityInfo;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 20:01
 */

public interface ICommodityManageService {

    /**
     * 获取商品信息方法
     *
     * @param infoId 商品主键
     * @return 商品详细信息
     */
    CommodityInfo getCommodityInfo(Long infoId);

}
