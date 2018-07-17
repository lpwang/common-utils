package com.xin.pay.aop.system.handler;

import com.alibaba.fastjson.JSONObject;
import com.xin.pay.aop.channel.constant.ErrorCode;
import com.xin.pay.aop.channel.entry.ErrorCodeValueEntry;
import com.xin.pay.aop.channel.entry.RespPaymentContentEntry;
import com.xin.pay.aop.log.LogFactory;
import com.xin.pay.aop.utils.StackExceptionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author lpwang
 * @Title IHandleSystemException
 * @Package com.xin.pay.aop.system.handler
 * @Description: 处理系统异常的接口
 * @date 2018-07-10 11:05
 */
public interface IHandleSystemException {

    public static final String SYS_PREFIX = "uxinpay:pay-sys:errorCode:";

    default String handleException(Throwable e, StringRedisTemplate redisTemplate) {
        LogFactory.newLoggerWrite().writeSystemLog(StackExceptionUtils.getStackMessage(e));
        RespPaymentContentEntry respPaymentContentEntry = new RespPaymentContentEntry();
        String key = SYS_PREFIX + e.getClass().getName();
        String errorCodeValueStr = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(errorCodeValueStr)) {
            respPaymentContentEntry.setSystemErrorCode(ErrorCode.SYSTEM_UNKNOW_CODE);
        } else {
            ErrorCodeValueEntry errorCodeValueEntry = JSONObject.parseObject(errorCodeValueStr, ErrorCodeValueEntry.class);
            respPaymentContentEntry.setSystemErrorCode(errorCodeValueEntry.getErrCode());
        }
        respPaymentContentEntry.setExceptionName(e.getClass().getName());
        return JSONObject.toJSONString(respPaymentContentEntry);
    }

    default void logSystemException(Throwable e, StringRedisTemplate redisTemplate) {
        String exceptionName = e.getClass().getName();
        String key = SYS_PREFIX + exceptionName;
        String val = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(val)) {
            val = ErrorCode.SYSTEM_UNKNOW_CODE;
        }
        String exceptionMessage = StackExceptionUtils.getStackMessage(e);
        exceptionMessage = val + "|" + exceptionName + "|" + exceptionMessage;
        LogFactory.newLoggerWrite().writeSystemLog(exceptionMessage);
    }


}
