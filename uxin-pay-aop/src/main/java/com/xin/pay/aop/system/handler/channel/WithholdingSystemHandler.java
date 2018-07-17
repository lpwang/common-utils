package com.xin.pay.aop.system.handler.channel;

import com.xin.pay.aop.config.PointCutConfig;
import com.xin.pay.aop.exception.ChannelSystemException;
import com.xin.pay.aop.system.handler.ISystemExceptionHandler;
import com.xin.pay.aop.utils.StackExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lpwang
 * @Title SystemExceptionAopHandler
 * @Package com.xin.pay.aop.system.handler
 * @Description: 系统异常aop处理类
 * @date 2018-06-23 15:13
 */

@Aspect
@Component
public class WithholdingSystemHandler implements ISystemExceptionHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = PointCutConfig.JD_WITHHOLDING_SYSTEM_POINTCUT)
    public void JDWithholding() {

    }

    @Pointcut(value = PointCutConfig.KFT_WITHHOLDING_SYSTEM_POINTCUT)
    public void KFTWithholding() {

    }

    @Pointcut(value = PointCutConfig.BFB_WITHHOLDING_SYSTEM_POINTCUT)
    public void BaiFuBaoWithholding() {

    }

    @Pointcut(value = PointCutConfig.UCF_WITHHOLDING_SYSTEM_POINTCUT)
    public void UCFWithholding() {

    }

    @Around("JDWithholding() || KFTWithholding() || BaiFuBaoWithholding() || UCFWithholding()")
    @Override
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable e) {
            logSystemException(e, redisTemplate);
            throw new ChannelSystemException(StackExceptionUtils.getStackMessage(e));
        }
    }

}
