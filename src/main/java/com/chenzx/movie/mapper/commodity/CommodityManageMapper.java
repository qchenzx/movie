package com.chenzx.movie.mapper.commodity;

import com.chenzx.movie.entity.commodity.CommodityInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 20:05
 */
@Mapper
public interface CommodityManageMapper {

    /**
     * 查询商品信息
     *
     * @param infoId 商品主键
     * @return 商品详细信息
     */
    CommodityInfo getCommodityInfo(Long infoId);

}
