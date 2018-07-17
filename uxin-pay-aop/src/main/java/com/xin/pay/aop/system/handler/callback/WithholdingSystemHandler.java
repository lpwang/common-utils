package com.xin.pay.aop.system.handler.callback;

import com.xin.pay.aop.config.PointCutConfig;
import com.xin.pay.aop.system.handler.ISystemExceptionHandler;
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
 * @Package com.xin.pay.aop.system.handler.callback
 * @Description: 代扣系统异常处理aop
 * @date 2018-07-11 14:25
 */
@Aspect
@Component
public class WithholdingSystemHandler implements ISystemExceptionHandler {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = PointCutConfig.JD_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT)
    public void JDWithholding() {

    }

    @Pointcut(value = PointCutConfig.BFB_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT)
    public void BFBWithholding() {

    }

    @Pointcut(value = PointCutConfig.UCF_KF_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT)
    public void UCFKFWithholding() {

    }

    @Pointcut(value = PointCutConfig.UCF_YQ_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT)
    public void UCFYQWithholding() {

    }


    @Around("JDWithholding() || BFBWithholding() || UCFKFWithholding() || UCFYQWithholding()")
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
