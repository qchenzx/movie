package com.chenzx.movie.entity.ticketing;

import lombok.Data;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/22 9:53
 */
@Data
public class MovieHallInfo {
    /**
     * 影厅id
     */
    private String id;
    /**
     * 影厅共有x行座位
     */
    private Integer rowTotal;
    /**
     * 影厅共有x列座位
     */
    private Integer colTotal;
    /**
     * 电影名字
     */
    private String movieName;
    /**
     * 座位信息
     */
    private List<MovieSeat> seats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(Integer rowTotal) {
        this.rowTotal = rowTotal;
    }

    public Integer getColTotal() {
        return colTotal;
    }

    public void setColTotal(Integer colTotal) {
        this.colTotal = colTotal;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<MovieSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<MovieSeat> seats) {
        this.seats = seats;
    }
}
