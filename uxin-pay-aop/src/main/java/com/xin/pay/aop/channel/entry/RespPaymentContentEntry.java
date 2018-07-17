package com.xin.pay.aop.channel.entry;

/**
 * @author lpwang
 * @Title RespPaymentContentEntry
 * @Package com.xin.pay.aop.channel.entry
 * @Description: 响应payment内容实体
 * @date 2018-07-06 11:25
 */
public class RespPaymentContentEntry {

    private String payNo;
    private String channelNo;
    private String channelCode;
    private String channelReturnNo;
    private String payTime;
    private String payStatus;
    private String responseCode;
    private String responseMessage;
    private String systemErrorCode;
    private String exceptionName;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelReturnNo() {
        return channelReturnNo;
    }

    public void setChannelReturnNo(String channelReturnNo) {
        this.channelReturnNo = channelReturnNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getSystemErrorCode() {
        return systemErrorCode;
    }

    public void setSystemErrorCode(String systemErrorCode) {
        this.systemErrorCode = systemErrorCode;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
