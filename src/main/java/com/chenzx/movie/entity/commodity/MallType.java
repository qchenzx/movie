package com.chenzx.movie.entity.commodity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/9 15:25
 */
@Data
@TableName("mall_type")
public class MallType {

    /**
     * 商品分类主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品分类名字
     */
    private String name;

}
