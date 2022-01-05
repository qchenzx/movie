package com.chenzx.movie.entity.address;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 13:48
 */
@Data
@TableName("sys_user_address")
@ApiModel("AddressDo")
public class AddressDo {

    @ApiModelProperty(value = "地址主键", example = "5248e9debde807a29c3c6979c49ec56b")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 收货人名字
     */
    @ApiModelProperty(value = "收货人名字", example = "小明")
    private String name;

    /**
     * 收货人手机号
     */
    @ApiModelProperty(value = "收货人手机号", example = "19850255199")
    private String mobileNumber;

    /**
     * 收货人详细地址
     */
    @ApiModelProperty(value = "收货人详细地址", example = "江苏省常州市武进区常州机电职业技术学院")
    private String detailedAddress;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编", example = "213100")
    private String zipCode;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 是否为默认地址
     */
    private Boolean isDefaultAddress = false;

}
