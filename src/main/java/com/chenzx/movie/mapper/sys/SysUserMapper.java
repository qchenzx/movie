package com.chenzx.movie.mapper.sys;

import com.chenzx.movie.entity.sys.PathRoleMapping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 10:06
 */
@Mapper
public interface SysUserMapper {

    /**
     * 查询所有的路径与权限的对应关系
     *
     * @return path:路径,roles:路径所需的角色
     */
    List<PathRoleMapping> queryPathRequiredRole();

}
