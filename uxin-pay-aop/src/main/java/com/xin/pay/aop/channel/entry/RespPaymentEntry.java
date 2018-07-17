package com.xin.pay.aop.channel.entry;

/**
 * @author lpwang
 * @Title RespPaymentEntry
 * @Package com.xin.pay.aop.channel.entry
 * @Description: 响应payment实体
 * @date 2018-07-06 11:21
 */
public class RespPaymentEntry {

    private String command;
    private String from;
    private String to;
    private String time;
    private String called;
    private String content;
    private String className;
    private String methodName;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
