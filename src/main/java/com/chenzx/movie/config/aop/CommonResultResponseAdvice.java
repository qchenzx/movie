package com.chenzx.movie.config.aop;

import com.chenzx.movie.entity.sys.GlobalResults;
import com.chenzx.movie.utils.GlobalResultsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/7 13:42
 */
@RestControllerAdvice
public class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        String className = returnType.getExecutable().getDeclaringClass().getName();
        String methodName = returnType.getExecutable().getName();
        if ("com.chenzx.movie.controller.MovieController".equals(className) && "getMovieCoverImg".equals(methodName)) {
            return false;
        }
        if ("com.chenzx.movie.controller.SysUserController".equals(className) && "getUserAvatar".equals(methodName)) {
            return false;
        }
        return !returnType.getGenericParameterType().equals(GlobalResults.class) &&
                !returnType.getDeclaringClass().getName().contains("springfox");
    }

    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType
            , Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return objectMapper.writeValueAsString(GlobalResultsUtil.isOk(body));
        }
        return GlobalResultsUtil.isOk(body);
    }
}
