package com.xin.pay.aop.exception.handler;

import com.xin.pay.aop.exception.entry.PaySystemExceptionEntry;
import com.xin.pay.aop.exception.factory.PaySystemExceptionFac;
import com.xin.pay.aop.exception.redis.PaySystemExceptionRedis;
import com.xin.pay.aop.exception.utils.ConvertUtils;
import com.xin.pay.aop.exception.constant.RedisPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lpwang
 * @Title PaySystemExceptionHandler
 * @Package com.xin.pay.exception.aop.handler
 * @Description: 支付系统系统错误处理类
 * @date 2018-06-11 14:15
 */
@ControllerAdvice
public class PaySystemExceptionHandler {

    @Autowired
    private PaySystemExceptionRedis systemExceptionRedis;

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public PaySystemExceptionEntry handleException(Throwable e) {
        PaySystemExceptionEntry paySystemExceptionEntry = PaySystemExceptionFac.newInstance();
        String key = RedisPrefix.UXIN_PAY_SYS_EXCEPTION + ConvertUtils.StringChatToInt(e.getClass().getName());
        paySystemExceptionEntry.setErrorCode(systemExceptionRedis.getSysExcCode(key));
        paySystemExceptionEntry.setExceptionMsg(e.getMessage());
        paySystemExceptionEntry.setExceptionSimpleName(e.getClass().getName());
        return paySystemExceptionEntry;
    }

}
