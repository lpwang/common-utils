package com.xin.pay.aop.exception.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author lpwang
 * @Title AbstractHandler
 * @Package com.xin.pay.exception.aop.handler
 * @Description: 抽象处理类
 * @date 2018-06-11 11:10
 */
public abstract class AbstractHandler implements IHandler {

    public void pointCut() {

    }

    public void before() {

    }

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
