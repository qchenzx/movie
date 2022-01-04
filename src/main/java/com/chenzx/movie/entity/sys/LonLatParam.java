package com.chenzx.movie.entity.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/3 11:02
 */
@Data
@ApiModel(value = "com.chenzx.movie.entity.sys.LonLatParam", description = "经纬度对象")
public class LonLatParam {

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private Double lon;
    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private Double lat;

}
