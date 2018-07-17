package com.xin.pay.aop.channel.handler;

import com.alibaba.fastjson.JSONObject;
import com.xin.pay.aop.channel.entry.ChannelEntry;
import com.xin.pay.aop.channel.entry.ErrorCodeValueEntry;
import com.xin.pay.aop.channel.entry.RespPaymentContentEntry;
import com.xin.pay.aop.log.LogFactory;
import com.xin.pay.aop.system.handler.IHandleSystemException;
import com.xin.pay.aop.utils.KeyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author lpwang
 * @Title AbstractHandler
 * @Package com.xin.pay.aop.channel.handler
 * @Description: 渠道监控处理类抽象类
 * @date 2018-06-22 10:57
 */
public class AbstractHandler implements IHandler, IHandleSystemException {

    @Override
    public void before(JoinPoint joinPoint) {

    }

    @Override
    public void after() {

    }

    @Override
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return null;
    }

    @Override
    public void afterReturning(JoinPoint joinPoint, Object responses) {

    }

    @Override
    public void throwException(Throwable exception) {

    }

    public Object handleAsynResp(ProceedingJoinPoint proceedingJoinPoint,StringRedisTemplate redisTemplate) throws Throwable {
        Object arg = proceedingJoinPoint.getArgs()[0];
        ErrorCodeValueEntry errorCodeValueEntry = new ErrorCodeValueEntry();
        Class<?> argClazz = arg.getClass();
        String content = (String) argClazz.getDeclaredMethod("getContent").invoke(arg);

        RespPaymentContentEntry respPaymentContentEntry = JSONObject.parseObject(content, RespPaymentContentEntry.class);
        String channelCode = respPaymentContentEntry.getChannelCode();
        String key = KeyUtils.getKey(channelCode, respPaymentContentEntry.getResponseCode());
        String errorCodeValueStr = redisTemplate.opsForValue().get(key);

        if (StringUtils.isEmpty(errorCodeValueStr)) {
            errorCodeValueEntry.setErrCode(respPaymentContentEntry.getResponseCode());
            errorCodeValueEntry.setErrMsg(respPaymentContentEntry.getResponseMessage());
        } else {
            errorCodeValueEntry = JSONObject.parseObject(errorCodeValueStr, ErrorCodeValueEntry.class);
        }
        respPaymentContentEntry.setResponseCode(errorCodeValueEntry.getErrCode());
        respPaymentContentEntry.setResponseMessage(errorCodeValueEntry.getErrMsg());
        String modContent = JSONObject.toJSONString(respPaymentContentEntry);
        argClazz.getDeclaredMethod("setContent", String.class).invoke(arg, modContent);
        return arg;
    }

    public Object handleSyncResp(ProceedingJoinPoint proceedingJoinPoint,StringRedisTemplate redisTemplate) throws Throwable {
        ErrorCodeValueEntry errorCodeValueEntry = new ErrorCodeValueEntry();

        Object returnObj = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        Class<?> returnClazz = returnObj.getClass();
        String responseCode = (String) returnClazz.getDeclaredMethod("getResponseCode").invoke(returnObj);
        String responseMessage = (String) returnClazz.getDeclaredMethod("getResponseMessage").invoke(returnObj);
        String channelCode = (String) returnClazz.getDeclaredMethod("getChannelCode").invoke(returnObj);

        String key = KeyUtils.getKey(channelCode, responseCode);
        String errorCodeValStr = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(errorCodeValStr)) {
            errorCodeValueEntry.setErrCode(responseCode);
            errorCodeValueEntry.setErrMsg(responseMessage);
        } else {
            errorCodeValueEntry = JSONObject.parseObject(errorCodeValStr, ErrorCodeValueEntry.class);
        }
        returnClazz.getDeclaredMethod("setResponseCode", String.class).invoke(returnObj, errorCodeValueEntry.getErrCode());
        returnClazz.getDeclaredMethod("setResponseMessage", String.class).invoke(returnObj, errorCodeValueEntry.getErrMsg());
        ChannelEntry channelEntry = new ChannelEntry().buildQuickRespPayment(returnObj);
        LogFactory.newLoggerWrite().writeChannelLog(channelEntry);
        return returnObj;
    }

}
