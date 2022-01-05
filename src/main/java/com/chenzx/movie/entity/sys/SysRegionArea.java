package com.chenzx.movie.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/2 10:29
 */
@Data
@TableName("sys_region_area")
public class SysRegionArea implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 区域ID
     */
    @TableId
    private Integer id;
    /**
     * 上级区域ID
     */
    private Integer parentId;
    /**
     * 行政区域等级 1-省 2-市 3-区县 4-街道镇
     */
    private Boolean level;
    /**
     * 名称
     */
    private String name;
    /**
     * 完整名称
     */
    private String wholeName;
    /**
     * 本区域经度
     */
    private String lon;
    /**
     * 本区域维度
     */
    private String lat;
    /**
     * 电话区号
     */
    private String cityCode;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 行政区划代码
     */
    private String areaCode;
    /**
     * 名称全拼
     */
    private String pinYin;
    /**
     * 首字母简拼
     */
    private String simplePy;
    /**
     * 区域名称拼音的第一个字母
     */
    private String perPinYin;
}
