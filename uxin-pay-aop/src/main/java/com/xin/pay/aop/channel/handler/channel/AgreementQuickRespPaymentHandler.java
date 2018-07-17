package com.xin.pay.aop.channel.handler.channel;

import com.xin.pay.aop.channel.handler.AbstractHandler;
import com.xin.pay.aop.config.PointCutConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lpwang
 * @Title AgreementQuickRespPaymentHandler
 * @Package com.xin.pay.aop.channel.handler.channel
 * @Description: 协议快捷响应payment处理器
 * @date 2018-07-06 16:24
 */
@Aspect
@Component
public class AgreementQuickRespPaymentHandler extends AbstractHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = PointCutConfig.AGREEMENT_BAIFUBAO_CHANNEL_RESP_PAYMENT_POINTCUT)
    public void baiFuBao() {

    }

    @Around("baiFuBao()")
    @Override
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Object returnObj = handleSyncResp(proceedingJoinPoint, redisTemplate);
            return returnObj;
        } catch (Throwable e) {
            return handleException(e, redisTemplate);
        }
    }

}
