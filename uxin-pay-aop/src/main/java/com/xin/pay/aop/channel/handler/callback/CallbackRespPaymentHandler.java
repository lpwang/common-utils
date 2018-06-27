package com.xin.pay.aop.channel.handler.callback;

import com.xin.pay.aop.channel.entry.ChannelEntry;
import com.xin.pay.aop.channel.handler.AbstractHandler;
import com.xin.pay.aop.config.PointCutConfig;
import com.xin.pay.aop.log.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lpwang
 * @Title CallbackHandler
 * @Package com.xin.pay.aop.channel.handler
 * @Description: callback响应payment切面拦截
 * @date 2018-06-25 10:42
 */
@Aspect
@Component
public class CallbackRespPaymentHandler extends AbstractHandler {

    @Pointcut(value = PointCutConfig.CALLBACK_RESP_PAYMENT_POINTCUT)
    public void pointCut() {

    }

    @Before("pointCut()")
    @Override
    public void before(JoinPoint joinPoint) {
        ChannelEntry channelEntry = new ChannelEntry().buildRespPayment(joinPoint);
        LogFactory.newLoggerWrite().writeChannelLog(channelEntry);
    }


}
