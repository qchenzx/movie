package com.chenzx.movie.entity.commodity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 20:01
 */
@TableName(value = "mall_shopping_cart")
@Data
public class MallShoppingCart implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 购物车中商品主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 购物车中存放的数量
     */
    private Integer total;
    /**
     * 商品主键
     */
    private Long commodityId;
    /**
     * 用户主键
     */
    private Long userId;
    /**
     * 规格主键
     */
    private Long specificationsId;
}
