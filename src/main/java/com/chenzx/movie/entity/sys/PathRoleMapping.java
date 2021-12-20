package com.chenzx.movie.entity.sys;

import lombok.Data;

import java.util.List;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 10:09
 */
@Data
public class PathRoleMapping {

    private String path;
    private List<String> roles;

}
