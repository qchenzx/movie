package com.chenzx.movie.entity.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/13 18:20
 */
@Data
@ApiModel("CityDetailedInfo")
public class CityDetailedInfo {

    @ApiModelProperty(value = "省份id", notes = "如果需要查询该省的所有市,则需要将该值传递进查询全部市接口中")
    private Integer id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "全名", notes = "例如江苏省,常州市,武进区")
    private String wholeName;

    @ApiModelProperty(value = "邮政编码")
    private String areaCode;

    @ApiModelProperty(value = "拼音")
    private String pinYin;

    @ApiModelProperty(value = "首字母缩写")
    private String simplePy;

}
