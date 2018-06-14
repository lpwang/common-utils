package com.xin.pay.exception.aop.exception;

/**
 * @author lpwang
 * @Title UxinPaySysException
 * @Package com.xin.pay.exception.aop.exception
 * @Description: 优信支付系统异常
 * @date 2018-06-11 15:19
 */
public class UxinPaySysException extends RuntimeException {

    public UxinPaySysException(Throwable cause) {
        super(cause);
    }

    public UxinPaySysException(String message) {
        super(message);
    }
}
