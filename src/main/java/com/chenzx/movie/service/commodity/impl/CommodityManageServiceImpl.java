package com.chenzx.movie.service.commodity.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.entity.commodity.CommodityDescribe;
import com.chenzx.movie.entity.commodity.CommodityDescribeParam;
import com.chenzx.movie.entity.commodity.CommodityInfo;
import com.chenzx.movie.mapper.commodity.CommodityManageMapper;
import com.chenzx.movie.service.commodity.ICommodityManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 20:03
 */
@Slf4j
@Service
public class CommodityManageServiceImpl implements ICommodityManageService {

    @Autowired
    private CommodityManageMapper commodityManageMapper;

    @Override
    public CommodityInfo getCommodityInfo(Long infoId) {
        return commodityManageMapper.getCommodityInfo(infoId);
    }

    @Override
    public IPage<CommodityDescribe> getCommodityDesc(Page<CommodityDescribe> page, CommodityDescribeParam param) {
        IPage<CommodityDescribe> commodityDesc = commodityManageMapper.getCommodityDesc(page, param);
        for (CommodityDescribe desc : commodityDesc.getRecords()) {
            desc.setBase64Image(reloadImageById(desc.getBase64Image()));
        }
        return commodityDesc;
    }

    /**
     * 通过图片主键查找图片,并将图片转换为base64编码返回
     *
     * @param imageId 图片主键
     * @return 该图片的base64编码
     */
    private String reloadImageById(String imageId) {
        //TODO 这里临时写成这样
        return imageId;
    }
}
