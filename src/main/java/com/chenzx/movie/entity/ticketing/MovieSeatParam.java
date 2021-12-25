package com.chenzx.movie.entity.ticketing;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/24 11:58
 */
@Data
public class MovieSeatParam {

    /**
     * 影厅Id
     */
    @NotEmpty(message = "影厅id不能为空")
    private String hallId;
    /**
     * 座位定位对象
     */
    @Valid
    @NotEmpty(message = "座位定位对象不能为空,至少要有一个值")
    private List<MovieSeatLocation> seats;
}
