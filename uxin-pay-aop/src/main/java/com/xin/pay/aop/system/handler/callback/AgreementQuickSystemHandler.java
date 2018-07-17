package com.xin.pay.aop.system.handler.callback;

import com.xin.pay.aop.config.PointCutConfig;
import com.xin.pay.aop.system.handler.ISystemExceptionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lpwang
 * @Title AgreementQuickSystemHandler
 * @Package com.xin.pay.aop.system.handler.callback
 * @Description: 协议快捷系统异常处理
 * @date 2018-07-11 14:42
 */
@Aspect
@Component
public class AgreementQuickSystemHandler implements ISystemExceptionHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = PointCutConfig.AGREEMENT_BAIFUBAO_CALLBACK_RESP_PAYMENT_POINTCUT)
    public void BFBWithholding() {

    }

    @Override
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable e) {
            logSystemException(e, redisTemplate);
            return "fail";
        }
    }
}
