package com.chenzx.movie.service.commodity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenzx.movie.config.exception.BusException;
import com.chenzx.movie.entity.commodity.*;
import com.chenzx.movie.entity.sys.IUser;
import com.chenzx.movie.mapper.commodity.*;
import com.chenzx.movie.service.commodity.ICommodityManageService;
import com.chenzx.movie.service.commodity.constant.QueryRankValueEnum;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

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
    @Autowired
    private MallTypeMapper mallTypeMapper;
    @Autowired
    private MallImageMapper mallImageMapper;
    @Autowired
    private MallInfoMapper mallInfoMapper;
    @Autowired
    private MallSpecificationsMapper mallSpecificationsMapper;
    @Autowired
    private MallShoppingCartMapper mallShoppingCartMapper;
    @Value("${local-file.mall-path}")
    private String mallImagePath;

    @Override
    public CommodityInfo getCommodityInfo(Long infoId) {
        CommodityInfo commodityInfo = commodityManageMapper.getCommodityInfo(infoId);
        // TODO 此处待重构,需要增加商品详情页的展示图片、每个规格下的展示图片、商品主题
        // TODO 当前逻辑为获取所有与该商品有关的图片,不管是主图还是其他图片
        List<MallImage> mallImages = mallImageMapper.selectList(
                new LambdaQueryWrapper<MallImage>().eq(MallImage::getInfoId, commodityInfo.getMallId()));
        commodityInfo.setImages(Lists.newArrayList());
        List<String> images = commodityInfo.getImages();
        try {
            for (MallImage image : mallImages) {
                if (image.getIsMainGraph()) {
                    images.add(0, reloadImageById(image.getId()));
                } else {
                    images.add(reloadImageById(image.getId()));
                }
            }

            for (CommoditySpecifications item : commodityInfo.getSpecifications()) {
                MallImage specificationsImage = mallImageMapper.selectOne(
                        new LambdaQueryWrapper<MallImage>()
                                .eq(MallImage::getInfoId, commodityInfo.getMallId())
                                .eq(MallImage::getSpecificationsId, item.getSpecificationsId()));
                item.setImage(reloadImageById(specificationsImage.getId()));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new BusException("静态资源读取失败,请刷新页面后重新尝试");
        }
        return commodityInfo;
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

    @Override
    public List<MallType> getCommodityClassification(Integer rank, Long typeId) {
        LambdaQueryWrapper<MallType> wrapper = new LambdaQueryWrapper<>();
        if (QueryRankValueEnum.PARENT.getValue().equals(rank)) {
            wrapper.isNull(MallType::getParentId);
        } else if (QueryRankValueEnum.CHILDREN.getValue().equals(rank)) {
            if (typeId == null) {
                throw new BusException("不能传入空的typeId");
            }
            wrapper.eq(MallType::getParentId, typeId);
        }
        return mallTypeMapper.selectList(wrapper);
    }

    @Override
    public List<ShopCartInfo> getShopCartContentByUser(IUser user) {
        List<ShopCartInfo> shopCartContent = commodityManageMapper.getShopCartContentByUser(user);
        for (ShopCartInfo shopCartInfo : shopCartContent) {
            MallImage mallImage = mallImageMapper.selectOne(new LambdaQueryWrapper<MallImage>()
                    .eq(MallImage::getInfoId, shopCartInfo.getCommodityId())
                    .eq(MallImage::getSpecificationsId, shopCartInfo.getSpecificationsId()));
            if (mallImage == null) {
                throw new BusException("未找到静态资源,请刷新页面后重试");
            }
            try {
                shopCartInfo.setImage(reloadImageById(mallImage.getId()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusException("读取图片资源异常,请刷新页面后重试");
            }

        }
        return shopCartContent;
    }

    @Override
    public String addCommodityToCart(AddCommodityToCartParam param, IUser user) {
        MallInfo mallInfo = mallInfoMapper.selectById(param.getCommodityId());
        MallSpecifications mallSpecifications = mallSpecificationsMapper.selectById(param.getSpecificationsId());
        if (mallInfo == null || mallSpecifications == null) {
            throw new BusException("商品信息或规格信息错误,请检查后重新尝试!");
        }
        MallShoppingCart cart = mallShoppingCartMapper.selectOne(new LambdaQueryWrapper<MallShoppingCart>()
                .eq(MallShoppingCart::getCommodityId, param.getCommodityId())
                .eq(MallShoppingCart::getSpecificationsId, param.getSpecificationsId())
                .eq(MallShoppingCart::getUserId, user.getId()));
        if (cart != null) {
            if (cart.getTotal() + param.getAmount() > 200) {
                throw new BusException("购物车中最多存放200件商品");
            }
            cart.setTotal(cart.getTotal() + param.getAmount());
            mallShoppingCartMapper.updateById(cart);
            return "success";
        }
        MallShoppingCart mallShoppingCart = new MallShoppingCart();
        mallShoppingCart.setCommodityId(mallInfo.getId());
        mallShoppingCart.setSpecificationsId(mallSpecifications.getId());
        mallShoppingCart.setTotal(param.getAmount());
        mallShoppingCart.setUserId(user.getId());
        mallShoppingCartMapper.insert(mallShoppingCart);
        return "success";
    }

    @Override
    public String deleteCommodityFromCart(DeleteShopCartParam param, IUser user) {
        for (Long cartId : param.getShopCartIds()) {
            MallShoppingCart mallShoppingCart = mallShoppingCartMapper.selectById(cartId);
            if (mallShoppingCart == null || mallShoppingCart.getUserId().equals(user.getId())) {
                throw new BusException("购物车中没有id为" + cartId + "的商品");
            }
            mallShoppingCartMapper.deleteById(cartId);
        }
        return "success";
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
