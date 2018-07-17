package com.xin.pay.aop.exception;

/**
 * @author lpwang
 * @Title AopOperationException
 * @Package com.xin.pay.aop.exception
 * @Description: aop操作异常
 * @date 2018-07-10 10:29
 */
public class AopOperationException extends RuntimeException {

    public AopOperationException(String msg) {
        super(msg);
    }

    public AopOperationException(Throwable throwable) {
        super(throwable);
    }

}
