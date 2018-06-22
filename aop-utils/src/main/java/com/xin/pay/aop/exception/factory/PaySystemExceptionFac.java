package com.xin.pay.aop.exception.factory;

import com.xin.pay.aop.exception.entry.PaySystemExceptionEntry;

/**
 * @author lpwang
 * @Title PaySystemExceptionFac
 * @Package com.xin.pay.exception.aop.factory
 * @Description: 支付系统异常实例工厂类
 * @date 2018-06-11 14:50
 */
public class PaySystemExceptionFac {
    private PaySystemExceptionFac() {

    }

    public static PaySystemExceptionEntry newInstance() {
        return new PaySystemExceptionEntry();
    }
}
