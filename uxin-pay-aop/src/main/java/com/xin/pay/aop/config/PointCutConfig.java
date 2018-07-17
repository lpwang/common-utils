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
    public static final String AGREEMENT_BAIFUBAO_CHANNEL_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.channel.service.baifubao.ctrl.BaifubaoAgreementQuickCtrl.pay(..))";
    // callback,withholding响应payment的aop
    public static final String JD_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.callback.service.jd.ctrl.JdWithholdingCallBackCtrl.agreementTradeNotify(..))";
    public static final String BFB_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.callback.service.baifubao.controller.BaifubaoWithholdingController.asynNoticeByBaifubaoWithholding(..))";
    public static final String UCF_KF_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.callback.service.ucfpay.controller.UcfpayWithholdingController.asynNoticeKf(..))";
    public static final String UCF_YQ_WITHHOLDING_CALLBACK_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.callback.service.ucfpay.controller.UcfpayWithholdingController.asynNoticeYq(..))";
    // callback,agreementQuick响应payment的aop
    public static final String AGREEMENT_BAIFUBAO_CALLBACK_RESP_PAYMENT_POINTCUT = "execution(public * com.uxin.pay.callback.service.baifubao.controller.BaifubaoAgreementQuickController.asynNoticeByBaifubaoWithholding(..))";

    // job,withholding响应payment的aop
    public static final String FIVE_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT ="execution(public * com.uxin.pay.job.service.job.FiveMinuteJob.withholding(..))";
    public static final String FIFTEEN_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT ="execution(public * com.uxin.pay.job.service.job.FifteenMinuteJob.withholding(..))";
    public static final String THIRTY_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT ="execution(public * com.uxin.pay.job.service.job.ThirtyMinuteJob.withholding(..))";
    public static final String ONEDAY_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT ="execution(public * com.uxin.pay.job.service.job.OneDayJob.withholding(..))";
    public static final String BAOFU_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT ="execution(public * com.uxin.pay.job.service.job.BaofuWithholdingJob.withholding(..))";
    public static final String KFT_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT ="execution(public * com.uxin.pay.job.service.job.KFTWithholdingJob.withholding(..))";
    public static final String UCF_WITHHOLDING_JOB_RESP_PAYMENT_POINTCUT ="execution(public * com.uxin.pay.job.service.job.UcfWithholdingJob.withholding(..))";

    // 代扣mapper入库aop
    public static final String WITHHOLDING_REQ_INSERT_POINTCUT = "execution(public * com.uxin.pay.channel.repo.mapper.UxinChannelWithholdingMapper.insert(..))";
    public static final String WITHHOLDING_REQ_INSERTSELECT_POINTCUT = "execution(public * com.uxin.pay.channel.repo.mapper.UxinChannelWithholdingMapper.insertSelective(..))";
    // 协议mapper入库aop
    public static final String AGREEMENT_REQ_INSERT_POINTCUT = "execution(public * com.uxin.pay.channel.repo.mapper.UxinChannelQuickMapper.insert(..))";
    public static final String AGREEMENT_REQ_INSERTSELECT_POINTCUT = "execution(public * com.uxin.pay.channel.repo.mapper.UxinChannelQuickMapper.insertSelective(..))";
    // 系统错误拦截aop,代扣
    public static final String JD_WITHHOLDING_SYSTEM_POINTCUT ="execution(public * com.uxin.pay.channel.rmq.receive.command.JdWithholdingCommand.singlePay(..))";
    public static final String KFT_WITHHOLDING_SYSTEM_POINTCUT ="execution(public * com.uxin.pay.channel.rmq.receive.command.KftWithholdingCommand.singlePay(..))";
    public static final String BFB_WITHHOLDING_SYSTEM_POINTCUT ="execution(public * com.uxin.pay.channel.rmq.receive.command.BaifubaoWithholdingCommand.payOrderByBaifubaoWithholding(..))";
    public static final String UCF_WITHHOLDING_SYSTEM_POINTCUT ="execution(public * com.uxin.pay.channel.rmq.receive.command.UcfWithholdingCommand.singlePay(..))";

}
