package com.xin.pay.aop.channel.entry;

/**
 * @author lpwang
 * @Title ErrorCodeValueEntry
 * @Package com.xin.pay.aop.channel.entry
 * @Description: 错误码redis值json实体
 * @date 2018-07-09 18:20
 */
public class ErrorCodeValueEntry {

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
