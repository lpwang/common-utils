package com.xin.pay.aop.channel.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author lpwang
 * @Title IHandler
 * @Package com.xin.pay.aop.channel.handler
 * @Description: 渠道业务监控抽象处理接口
 * @date 2018-06-22 10:55
 */
public interface IHandler {

    void before(JoinPoint joinPoint);

    void after();

    Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

    void afterReturning(JoinPoint joinPoint, Object responses);

    void throwException(Throwable exception);

}
