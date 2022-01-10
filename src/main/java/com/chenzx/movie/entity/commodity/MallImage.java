package com.chenzx.movie.entity.commodity;

import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/10 8:55
 */
@Data
public class MallImage {

    /**
     * 图片主键
     */
    private String id;

    /**
     * 是否为主图
     */
    private Boolean isMainGraph;

    /**
     * 商品信息id
     */
    private Long infoId;

    /**
     * 商品类目id
     */
    private Long specificationsId;

}
