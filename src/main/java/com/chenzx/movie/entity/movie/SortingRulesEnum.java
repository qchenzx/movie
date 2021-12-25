package com.chenzx.movie.entity.movie;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/25 10:30
 */
public enum SortingRulesEnum {
    /**
     * 按时间排序
     */
    TIME(1),
    /**
     * 按评价排序
     */
    EVALUATE(2);

    private final Integer value;

    public Integer getValue() {
        return value;
    }

    SortingRulesEnum(Integer value) {
        this.value = value;
    }
}
