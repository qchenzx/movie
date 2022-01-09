package com.chenzx.movie.service.commodity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.commodity.CommodityDescribe;
import com.chenzx.movie.entity.commodity.CommodityDescribeParam;
import com.chenzx.movie.entity.commodity.CommodityInfo;
import com.chenzx.movie.entity.commodity.MallType;

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

}
