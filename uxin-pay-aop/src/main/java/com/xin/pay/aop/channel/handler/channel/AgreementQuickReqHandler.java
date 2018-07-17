package com.xin.pay.aop.channel.handler.channel;

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
 * @Title AgreementQuickReqHandler
 * @Package com.xin.pay.aop.channel.handler.channel
 * @Description: 快捷协议请求处理类
 * @date 2018-07-09 11:41
 */

@Aspect
@Component
public class AgreementQuickReqHandler extends AbstractHandler {

    @Pointcut(value = PointCutConfig.AGREEMENT_REQ_INSERT_POINTCUT)
    public void insertPointCut() {

    }

    @Pointcut(value = PointCutConfig.AGREEMENT_REQ_INSERTSELECT_POINTCUT)
    public void insertSelectivePointCut() {

    }

    @Before("insertPointCut() || insertSelectivePointCut()")
    @Override
    public void before(JoinPoint joinPoint) {
        ChannelEntry channelEntry = new ChannelEntry().buildQuickInsertMapper(joinPoint);
        LogFactory.newLoggerWrite().writeChannelLog(channelEntry);
    }

}
