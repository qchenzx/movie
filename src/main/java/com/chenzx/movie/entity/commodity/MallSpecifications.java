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
@TableName(value = "mall_specifications")
@Data
public class MallSpecifications implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 商品规格主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 规格显示名称
     */
    private String name;
    /**
     * 商品单价
     */
    private Double unitPrice;
    /**
     * 商品信息主键
     */
    private Long infoId;
}
