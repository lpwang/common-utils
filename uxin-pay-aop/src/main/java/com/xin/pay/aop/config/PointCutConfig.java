package com.xin.pay.aop.config;

/**
 * @author lpwang
 * @Title PointCutConfig
 * @Package com.xin.pay.aop.config
 * @Description: 切面配置类
 * @date 2018-06-26 20:27
 */
public class PointCutConfig {

    // 响应payment的aop
    public static final String JOB_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.job.rmq.send.SourceSend.send(..))";
    public static final String CHANNEL_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.channel.rmq.send.SourceSend.sendMessages(..))";
    public static final String CALLBACK_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.callback.rmq.send.SourceSend.sendMessages(..))";
    // 代扣mapper入库aop
    public static final String WITHHOLDING_REQ_INSERT_POINTCUT = "execution(public * com.uxin.pay.channel.repo.mapper.UxinChannelWithholdingMapper.insert(..))";
    public static final String WITHHOLDING_REQ_INSERTSELECT_POINTCUT = "execution(public * com.uxin.pay.channel.repo.mapper.UxinChannelWithholdingMapper.insertSelective(..))";
    // 系统错误拦截aop
    public static final String JD_WITHHOLDING_SYSTEM_POINTCUT ="execution(public * com.uxin.pay.channel.rmq.receive.command.JdWithholdingCommand.singlePay(..))";
    public static final String KFT_WITHHOLDING_SYSTEM_POINTCUT ="execution(public * com.uxin.pay.channel.rmq.receive.command.KftWithholdingCommand.singlePay(..))";
    public static final String BFB_WITHHOLDING_SYSTEM_POINTCUT ="execution(public * com.uxin.pay.channel.rmq.receive.command.BaifubaoWithholdingCommand.payOrderByBaifubaoWithholding(..))";


}
