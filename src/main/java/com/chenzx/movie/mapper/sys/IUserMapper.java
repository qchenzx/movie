package com.chenzx.movie.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzx.movie.entity.sys.IUserDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/14 14:45
 */
@Mapper
public interface IUserMapper extends BaseMapper<IUserDo> {
}
