package com.chenzx.movie.service.commodity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.commodity.*;
import com.chenzx.movie.entity.sys.IUser;

import java.util.List;

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

    /**
     * 查询商品描述信息
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return 描述信息
     */
    IPage<CommodityDescribe> getCommodityDesc(Page<CommodityDescribe> page, CommodityDescribeParam param);

    /**
     * 查询分类字段,如果rank为1则查询所有父级目录,如果rank为2,则查询所有该父级下自己目录
     * 当rank为2时,typeId为必传
     *
     * @param rank   查询等级
     * @param typeId rank为2时,分类字段的父级id
     * @return 分类列表
     */
    List<MallType> getCommodityClassification(Integer rank, Long typeId);

    /**
     * 查询用户的购物车中存放的商品
     *
     * @param user 用户对象
     * @return 购物车中的商品
     */
    List<ShopCartInfo> getShopCartContentByUser(IUser user);

    /**
     * 添加商品到购物车中
     *
     * @param param 商品信息
     * @param user  用户对象
     * @return 结果提示
     */
    String addCommodityToCart(AddCommodityToCartParam param, IUser user);

    /**
     * 从购物车中删除商品,支持批量删除
     *
     * @param param 要删除的购物车商品id
     * @param user  用户对象
     * @return 删除结果提示
     */
    String deleteCommodityFromCart(DeleteShopCartParam param, IUser user);

}
