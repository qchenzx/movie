package com.chenzx.movie.mapper.commodity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.commodity.CommodityDescribe;
import com.chenzx.movie.entity.commodity.CommodityDescribeParam;
import com.chenzx.movie.entity.commodity.CommodityInfo;
import com.chenzx.movie.entity.commodity.ShopCartInfo;
import com.chenzx.movie.entity.sys.IUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询商品描述信息
     *
     * @param page  分页对象
     * @param param 参数对象
     * @return 分页结果
     */
    IPage<CommodityDescribe> getCommodityDesc(Page<?> page, @Param("param") CommodityDescribeParam param);

    /**
     * 查询用户的购物车中存放的商品
     *
     * @param user 用户对象
     * @return 购物车中的商品
     */
    List<ShopCartInfo> getShopCartContentByUser(IUser user);

}
