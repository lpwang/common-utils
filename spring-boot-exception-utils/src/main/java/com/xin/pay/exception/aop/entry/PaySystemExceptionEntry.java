package com.xin.pay.exception.aop.entry;

/**
 * @author lpwang
 * @Title PaySystemExceptionEntry
 * @Package com.xin.pay.exception.aop.entry
 * @Description: 支付系统异常返回实体
 * @date 2018-06-11 14:43
 */
public class PaySystemExceptionEntry {

    private String errorCode;
    private String exceptionSimpleName;
    private String exceptionMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getExceptionSimpleName() {
        return exceptionSimpleName;
    }

    public void setExceptionSimpleName(String exceptionSimpleName) {
        this.exceptionSimpleName = exceptionSimpleName;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
