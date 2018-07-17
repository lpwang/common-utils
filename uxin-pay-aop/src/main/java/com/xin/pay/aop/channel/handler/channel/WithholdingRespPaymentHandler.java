package com.xin.pay.aop.channel.handler.channel;

import com.alibaba.fastjson.JSONObject;
import com.xin.pay.aop.channel.entry.ChannelEntry;
import com.xin.pay.aop.channel.entry.ErrorCodeValueEntry;
import com.xin.pay.aop.channel.entry.RespPaymentContentEntry;
import com.xin.pay.aop.channel.handler.AbstractHandler;
import com.xin.pay.aop.config.PointCutConfig;
import com.xin.pay.aop.exception.AopOperationException;
import com.xin.pay.aop.exception.ChannelSystemException;
import com.xin.pay.aop.log.LogFactory;
import com.xin.pay.aop.system.handler.IHandleSystemException;
import com.xin.pay.aop.utils.KeyUtils;
import com.xin.pay.aop.utils.StackExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author lpwang
 * @Title RespPaymentHandler
 * @Package com.xin.pay.aop.channel.handler
 * @Description: channel响应payment切面拦截
 * @date 2018-06-23 11:53
 */

@Aspect
@Component
public class WithholdingRespPaymentHandler extends AbstractHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = PointCutConfig.CHANNEL_RESP_PAYMENT_POINTCUT)
    public void pointCut() {

    }

    @Before("pointCut()")
    @Override
    public void before(JoinPoint joinPoint) {
        ChannelEntry channelEntry = new ChannelEntry().buildRespPayment(joinPoint);
        LogFactory.newLoggerWrite().writeChannelLog(channelEntry);
    }

    @Around("pointCut()")
    @Override
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Object arg = handleAsynResp(proceedingJoinPoint, redisTemplate);
            return proceedingJoinPoint.proceed(new Object[]{arg});
        } catch (Throwable e) {
            throw new ChannelSystemException(StackExceptionUtils.getStackMessage(e));
        }

    }

}
