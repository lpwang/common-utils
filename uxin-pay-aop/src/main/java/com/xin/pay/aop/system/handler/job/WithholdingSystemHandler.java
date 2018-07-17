package com.xin.pay.aop.system.handler.job;

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
 * @Title WithholdingSystemHandler
 * @Package com.xin.pay.aop.system.handler.job
 * @Description: 代扣系统错误处理
 * @date 2018-07-11 15:56
 */
@Aspect
@Component
public class WithholdingSystemHandler implements ISystemExceptionHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = PointCutConfig.FIVE_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT)
    public void five() {

    }

    @Pointcut(value = PointCutConfig.FIFTEEN_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT)
    public void fifteen() {

    }

    @Pointcut(value = PointCutConfig.THIRTY_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT)
    public void thirty() {

    }

    @Pointcut(value = PointCutConfig.ONEDAY_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT)
    public void oneDay() {

    }

    @Pointcut(value = PointCutConfig.BAOFU_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT)
    public void baoFu() {

    }

    @Pointcut(value = PointCutConfig.KFT_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT)
    public void KFT() {

    }

    @Pointcut(value = PointCutConfig.UCF_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT)
    public void UCF() {

    }

    @Around("fifteen() || thirty() || oneDay() || baoFu() || KFT() || UCF()")
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
