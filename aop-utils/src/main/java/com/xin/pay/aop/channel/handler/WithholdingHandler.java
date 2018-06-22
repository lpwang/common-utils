package com.xin.pay.aop.channel.handler;

import com.xin.pay.aop.channel.constant.Withholding;
import com.xin.pay.aop.exception.handler.AbstractHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @author lpwang
 * @Title ChannelMonitorHandler
 * @Package com.xin.pay.exception.aop.handler
 * @Description: 渠道代扣处理类
 * @date 2018-06-21 15:41
 */

@Aspect
@Component
public class WithholdingHandler extends AbstractHandler {

    @Pointcut("execution(public * com.uxin.pay.channel.repo.mapper.UxinChannelWithholdingMapper.insert(..))")
    public void withholding() {

    }

    @Before("withholding()")
    @Override
    public void before(JoinPoint joinPoint) {
        String reflectName = joinPoint.getSignature().getName();
        for (Method method : joinPoint.getTarget().getClass().getMethods()) {
            String name = method.getName();
            if (name.equals(reflectName)) {
                Object instants = joinPoint.getArgs()[0];
                Class<?> instantsClass = instants.getClass();
                try {
                    BigDecimal amount = (BigDecimal) instantsClass.getDeclaredMethod(Withholding.AMOUNT_METHOD_NAME).invoke(instants);
                    String status = (String) instantsClass.getDeclaredMethod(Withholding.STATUS_METHOD_NAME).invoke(instants);
                    String channel_code = (String) instantsClass.getDeclaredMethod(Withholding.CHANNEL_CODE_METHOD_NAME).invoke(instants);
                    String channel_no = (String) instantsClass.getDeclaredMethod(Withholding.CHANNEL_NO_METHOD_NAME).invoke(instants);
                    String requestDateTime = (String) instantsClass.getDeclaredMethod(Withholding.REQUEST_DATATIME_METHOD_NAME).invoke(instants);
                    System.out.println(status + "," + channel_code + "," + channel_no + "," + requestDateTime + String.valueOf(amount.longValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
