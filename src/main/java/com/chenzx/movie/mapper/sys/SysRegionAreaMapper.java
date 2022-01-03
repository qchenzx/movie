package com.chenzx.movie.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzx.movie.entity.sys.LonLatParam;
import com.chenzx.movie.entity.sys.SysRegionArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/3 13:41
 */
@Mapper
public interface SysRegionAreaMapper extends BaseMapper<SysRegionArea> {

    /**
     * 根据给定经纬度查询最近的点
     *
     * @param param 经纬度
     * @return 所有最近的点的集合
     */
    SysRegionArea queryNearestPoint(LonLatParam param);

}
