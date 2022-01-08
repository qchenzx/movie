package com.chenzx.movie.mapper.ticketing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzx.movie.entity.ticketing.MovieOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/25 10:39
 */
@Mapper
public interface MovieOrderMapper extends BaseMapper<MovieOrder> {

    /**
     * 查询所有的超时订单
     *
     * @return 所有的超时订单对象
     */
    @Select("select * from movie_order where create_time < subdate(now(),INTERVAL 10 MINUTE) and status = 1 order by create_time desc")
    List<MovieOrder> queryTimeoutOrder();

}
