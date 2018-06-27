package com.xin.pay.aop.system.handler;

/**
 * @author lpwang
 * @Title ISystemExceptionHandler
 * @Package com.xin.pay.aop.system.handler
 * @Description: 系统异常处理接口
 * @date 2018-06-23 16:03
 */
public interface ISystemExceptionHandler {

    void handleException(Throwable exception);

}
