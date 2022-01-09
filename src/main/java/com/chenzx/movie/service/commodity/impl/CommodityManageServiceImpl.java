package com.chenzx.movie.service.commodity.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.commodity.CommodityDescribe;
import com.chenzx.movie.entity.commodity.CommodityDescribeParam;
import com.chenzx.movie.entity.commodity.CommodityInfo;
import com.chenzx.movie.mapper.commodity.CommodityManageMapper;
import com.chenzx.movie.service.commodity.ICommodityManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

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
    @Value("${local-file.mall-path}")
    private String mallImagePath;

    @Override
    public CommodityInfo getCommodityInfo(Long infoId) {
        return commodityManageMapper.getCommodityInfo(infoId);
    }

    @Override
    public IPage<CommodityDescribe> getCommodityDesc(Page<CommodityDescribe> page, CommodityDescribeParam param) {
        IPage<CommodityDescribe> commodityDesc = commodityManageMapper.getCommodityDesc(page, param);
        for (CommodityDescribe desc : commodityDesc.getRecords()) {
            try {
                desc.setBase64Image(reloadImageById(desc.getBase64Image()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusException("静态资源读取失败,请刷新页面后重新尝试");
            }
        }
        return commodityDesc;
    }

    /**
     * 通过图片主键查找图片,并将图片转换为base64编码返回
     *
     * @param imageId 图片主键
     * @return 该图片的base64编码
     */
    private String reloadImageById(String imageId) throws IOException {
        File filePath = new File(mallImagePath);
        if (!filePath.exists()) {
            log.error("在磁盘中未找到:{} 目录", mallImagePath);
            throw new BusException("无法定位到文件目录!");
        }
        File file = new File(mallImagePath, imageId);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] temp = new byte[10240000];
        int len = 0;
        while ((len = fis.read(temp)) != -1) {
            byteArrayOutputStream.write(temp, 0, len);
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
