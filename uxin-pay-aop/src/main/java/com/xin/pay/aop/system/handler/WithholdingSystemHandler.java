package com.xin.pay.aop.system.handler;

import com.xin.pay.aop.config.PointCutConfig;
import com.xin.pay.aop.log.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut(value = PointCutConfig.JD_WITHHOLDING_SYSTEM_POINTCUT)
    public void JDWithholding() {

    }

    @Pointcut(value = PointCutConfig.KFT_WITHHOLDING_SYSTEM_POINTCUT)
    public void KFTWithholding() {

    }

    @Pointcut(value = PointCutConfig.BFB_WITHHOLDING_SYSTEM_POINTCUT)
    public void BaiFuBaoWithholding() {

    }

    @AfterThrowing(pointcut = "JDWithholding() || KFTWithholding() || BaiFuBaoWithholding()",throwing = "exception")
    public void handleException(Throwable exception) {
        //todo 调用redis获取exception对用的系统错误码
        LogFactory.newLoggerWrite().writeSystemLog(exception.getMessage());
    }

}
