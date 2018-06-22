package com.xin.pay.aop.exception.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author lpwang
 * @Title IHandler
 * @Package com.xin.pay.exception.aop.handler
 * @Description: 注解处理接口
 * @date 2018-06-11 10:40
 */
public interface IHandler {

    void pointCut();

    void before();

    void before(JoinPoint joinPoint);

    void after();

    Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

    void afterReturning(JoinPoint joinPoint, Object responses);

    void throwException(Throwable exception);

}
