package com.chenzx.movie.service.commodity.constant;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/9 21:29
 */
public enum QueryRankValueEnum {
    /**
     * 父级
     */
    PARENT(1),
    /**
     * 子级
     */
    CHILDREN(2);

    /**
     * 枚举的值
     */
    private final Integer value;

    QueryRankValueEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
