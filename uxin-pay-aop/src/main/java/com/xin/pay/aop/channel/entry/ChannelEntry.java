package com.xin.pay.aop.channel.entry;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.xin.dateutils.date.CurrentDateTimeUtils;
import com.xin.pay.aop.channel.constant.*;
import com.xin.pay.aop.exception.ChannelEntryBuildException;
import org.aspectj.lang.JoinPoint;

import java.math.BigDecimal;

/**
 * @author lpwang
 * @Title ChannelEntry
 * @Package com.xin.pay.aop.channel.entry
 * @Description: 渠道监控日志输出实体
 * @date 2018-06-22 18:17
 */
public class ChannelEntry {

    private String serviceName = "";
    private String command  = "";
    private String channelCode  = "";
    private String channelNo  = "";
    private String channelReturnNO  = "";
    private String amount  = "0";
    private String status = ChannelStatus.HANDLE;
    private String time = CurrentDateTimeUtils.plain();
    private String errorCode  = "";
    private String errorMsg  = "";
    private String respCode  = "";
    private String respMsg  = "";

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelReturnNO() {
        return channelReturnNO;
    }

    public void setChannelReturnNO(String channelReturnNO) {
        this.channelReturnNO = channelReturnNO;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public ChannelEntry buildInsertMapper(JoinPoint joinPoint) {
        try {
            Object instants = joinPoint.getArgs()[0];
            Class<?> instantsClass = instants.getClass();
            BigDecimal amount = (BigDecimal) instantsClass.getDeclaredMethod(Withholding.AMOUNT_METHOD_NAME).invoke(instants);
            String channel_code = (String) instantsClass.getDeclaredMethod(Withholding.CHANNEL_CODE_METHOD_NAME).invoke(instants);
            String channel_no = (String) instantsClass.getDeclaredMethod(Withholding.CHANNEL_NO_METHOD_NAME).invoke(instants);
            String requestDateTime = (String) instantsClass.getDeclaredMethod(Withholding.REQUEST_DATATIME_METHOD_NAME).invoke(instants);
            this.setChannelCode(channel_code);
            this.setAmount(String.valueOf(amount.longValue()));
            this.setChannelNo(channel_no);
            this.setCommand(Command.PAY_DEDUCTED);
            this.setServiceName(ServiceName.PAY_CHANNEL);
            if (null != requestDateTime) {
                this.setTime(requestDateTime);
            }
            return this;
        } catch (Exception e) {
            throw new ChannelEntryBuildException("插入代扣表实体解析构建失败" + e.getMessage());
        }
    }

    public ChannelEntry buildRespPayment(JoinPoint joinPoint) {
        try {
            Object respObj = joinPoint.getArgs()[0];
            Class<?> respObjClass = respObj.getClass();
            String command = (String) respObjClass.getDeclaredMethod(RespPayment.GET_COMMAND).invoke(respObj);
            String content = (String) respObjClass.getDeclaredMethod(RespPayment.CONTENT).invoke(respObj);
            JSONObject contentJsonObject = JSONObject.parseObject(content);

            this.setChannelCode((String) JSONPath.eval(contentJsonObject, "$.channelCode"));
            this.setServiceName(ServiceName.PAY_CHANNEL);
            this.setTime((String) JSONPath.eval(contentJsonObject, "$.payTime"));
            this.setStatus((String) JSONPath.eval(contentJsonObject, "$.payStatus"));
            this.setCommand(command);
            this.setChannelNo((String) JSONPath.eval(contentJsonObject, "$.channelNo"));
            this.setChannelReturnNO((String) JSONPath.eval(contentJsonObject, "$.channelReturnNo"));
            this.setChannelCode((String) JSONPath.eval(contentJsonObject, "$.channelCode"));
            this.setRespCode((String) JSONPath.eval(contentJsonObject, "$.responseCode"));
            this.setRespMsg((String) JSONPath.eval(contentJsonObject, "$.responseMessage"));
            return this;
        } catch (Exception e) {
            throw new ChannelEntryBuildException("返回payment的实体解析构建失败" + e.getMessage());
        }
    }

    public ChannelEntry buildQuickRespPayment(Object respObj) {
        try {
            Class<?> respObjClass = respObj.getClass();

            this.setServiceName(ServiceName.PAY_CHANNEL);
            this.setTime((String) respObjClass.getDeclaredMethod("getPayTime").invoke(respObj));
            this.setStatus((String) respObjClass.getDeclaredMethod("getPayStatus").invoke(respObj));
            this.setCommand(Command.PAY_QUICK);
            this.setChannelNo((String) respObjClass.getDeclaredMethod("getChannelNo").invoke(respObj));
            this.setChannelReturnNO((String) respObjClass.getDeclaredMethod("getChannelReturnNo").invoke(respObj));
            this.setRespCode((String) respObjClass.getDeclaredMethod("getResponseCode").invoke(respObj));
            this.setRespMsg((String) respObjClass.getDeclaredMethod("getResponseMessage").invoke(respObj));
            return this;
        } catch (Exception e) {
            throw new ChannelEntryBuildException("返回payment的实体解析构建失败" + e.getMessage());
        }
    }

    public ChannelEntry buildQuickInsertMapper(JoinPoint joinPoint) {
        try {
            Object instants = joinPoint.getArgs()[0];
            Class<?> instantsClass = instants.getClass();
            BigDecimal amount = (BigDecimal) instantsClass.getDeclaredMethod(Withholding.AMOUNT_METHOD_NAME).invoke(instants);
            String channel_code = (String) instantsClass.getDeclaredMethod(Withholding.CHANNEL_CODE_METHOD_NAME).invoke(instants);
            String channel_no = (String) instantsClass.getDeclaredMethod(Withholding.CHANNEL_NO_METHOD_NAME).invoke(instants);
            String requestDateTime = (String) instantsClass.getDeclaredMethod(Withholding.REQUEST_DATATIME_METHOD_NAME).invoke(instants);
            this.setChannelCode(channel_code);
            this.setAmount(String.valueOf(amount.longValue()));
            this.setChannelNo(channel_no);
            this.setCommand(Command.PAY_QUICK);
            this.setServiceName(ServiceName.PAY_CHANNEL);
            if (null != requestDateTime) {
                this.setTime(requestDateTime);
            }
            return this;
        } catch (Exception e) {
            throw new ChannelEntryBuildException("插入快捷表实体解析构建失败" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
