package com.chenzx.movie.entity.ticketing;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 11:05
 */
public enum TicketingSeatEnum {
    /**
     * 情侣座
     */
    LOVERS_SEAT(1),
    /**
     * 普通座
     */
    NORMAL_SEAT(2);

    private final Integer type;

    TicketingSeatEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
