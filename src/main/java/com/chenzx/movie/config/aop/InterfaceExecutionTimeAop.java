package com.chenzx.movie.config.aop;

import cn.hutool.core.date.DateUtil;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/5 15:56
 */
@Component
@Aspect
@Slf4j
public class InterfaceExecutionTimeAop {

    @Pointcut("execution(* com.chenzx.movie.controller..*.*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object timing(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object;
        Stopwatch stopwatch = Stopwatch.createStarted();

        object = joinPoint.proceed();

        log.info("[{}]: execute {} success! args: {} ,execution time: {}"
                , DateUtil.now()
                , joinPoint.getTarget().getClass().getName()
                , joinPoint.getArgs(), stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        return object;
    }

}
