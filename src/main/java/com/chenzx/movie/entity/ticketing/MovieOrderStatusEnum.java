package com.chenzx.movie.entity.ticketing;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/24 16:02
 */
public enum MovieOrderStatusEnum {

    /**
     * 已提交
     */
    SUBMITTED(1),
    /**
     * 已支付
     */
    PAID(2),
    /**
     * 已取消
     */
    CANCELLED(3);

    private final Integer value;

    public Integer getValue() {
        return value;
    }

    MovieOrderStatusEnum(Integer value) {
        this.value = value;
    }
}
