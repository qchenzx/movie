package com.chenzx.movie.utils;


import com.chenzx.movie.entity.sys.GlobalResults;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 16:35
 */
public class GlobalResultsUtil {

    public static  GlobalResults isOk(Object t) {
        GlobalResults results = new GlobalResults();
        results.setCode(GlobalResultsStatusCodeEnum.SUCCESS.getValue());
        results.setDesc("成功");
        results.setData(t);
        return results;
    }

    public static GlobalResults error(Integer code, String desc, Object t) {
        GlobalResults results = new GlobalResults();
        results.setCode(code);
        results.setDesc(desc);
        results.setData(t);
        return results;
    }

}
