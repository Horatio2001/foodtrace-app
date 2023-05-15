package com.example.foodtrace.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 耗时统计
 */
@Aspect
@Component
public class TakeTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(TakeTimeAspect.class);


    //统计请求的处理时间
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 带有@TakeTime注解的方法
     */
    @Pointcut("@annotation(com.example.foodtrace.config.TakeTime)")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求的内容
        logger.info("请求URL:" + request.getRequestURL().toString());
        logger.info("请求METHOD:" + request.getMethod());
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) {
        //处理完请求后，返回内容
        logger.info("方法返回值:" + JSON.toJSONString(ret));
        logger.info("方法执行时间:" + (System.currentTimeMillis() - startTime.get()));
    }


}