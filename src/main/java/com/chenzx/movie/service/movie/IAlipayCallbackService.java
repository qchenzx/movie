package com.chenzx.movie.service.movie;

import java.util.Map;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 13:23
 */
public interface IAlipayCallbackService {

    /**
     * 异步回调
     *
     * @param params 请求参数
     */
    void notifyCallback(Map<String, String> params);

}
