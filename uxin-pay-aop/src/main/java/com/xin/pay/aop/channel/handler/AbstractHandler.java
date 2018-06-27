package com.xin.pay.aop.channel.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author lpwang
 * @Title AbstractHandler
 * @Package com.xin.pay.aop.channel.handler
 * @Description: 渠道监控处理类抽象类
 * @date 2018-06-22 10:57
 */
public class AbstractHandler implements IHandler {

    public void before(JoinPoint joinPoint) {

    }

    public void after() {

    }

    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return null;
    }

    public void afterReturning(JoinPoint joinPoint, Object responses) {

    }

    public void throwException(Throwable exception) {

    }
}
