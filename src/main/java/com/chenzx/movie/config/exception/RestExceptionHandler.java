package com.chenzx.movie.config.exception;

import com.chenzx.movie.entity.sys.GlobalResults;
import com.chenzx.movie.utils.GlobalResultsStatusCodeEnum;
import com.chenzx.movie.utils.GlobalResultsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
        log.error("全局异常信息 异常类型 = {},Exception = {}", e.getClass().getName(), e.getMessage());
        return GlobalResultsUtil.error(GlobalResultsStatusCodeEnum.FAIL.getValue(), "接口调用失败", e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public GlobalResults paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return GlobalResultsUtil.error(
                        GlobalResultsStatusCodeEnum.REQUEST_PARAMETER_ERROR.getValue(),
                        fieldError.getDefaultMessage(), null);
            }
        }
        return GlobalResultsUtil.error(
                GlobalResultsStatusCodeEnum.REQUEST_PARAMETER_ERROR.getValue(),
                "请求参数错误",
                e.getMessage());
    }
}
