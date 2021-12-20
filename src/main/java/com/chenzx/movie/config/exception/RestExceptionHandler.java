package com.chenzx.movie.config.exception;

import com.chenzx.movie.entity.sys.GlobalResults;
import com.chenzx.movie.utils.GlobalResultsStatusCodeEnum;
import com.chenzx.movie.utils.GlobalResultsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/7 13:50
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ExceptionHandler({BusException.class})
    public GlobalResults exception(Exception e) {
        log.error("全局异常信息 异常类型 = {},Exception = {}",e.getClass().getName(), e.getMessage());
        return GlobalResultsUtil.error(GlobalResultsStatusCodeEnum.FAIL.getValue(), "接口调用失败", e.getMessage());
    }
}
