package com.xin.pay.aop.exception.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.xin.pay.aop.exception.annotation.PayChannelException;
import com.xin.pay.aop.exception.constant.MethodPrefix;
import com.xin.pay.aop.exception.exception.UxinPaySysException;
import com.xin.pay.aop.exception.constant.RedisPrefix;
import com.xin.pay.aop.exception.enums.ReturnDataFormatEnum;
import com.xin.pay.aop.exception.redis.ChannelExceptionRedis;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lpwang
 * @Title PayExceptionHandler
 * @Package com.xin.pay.exception.aop.handler
 * @Description: PayException处理类
 * @date 2018-06-09 15:26
 */
@Component
@Aspect
public class PayChannelExceptionHandler extends AbstractHandler {

    @Autowired
    private ChannelExceptionRedis channelExceptionRedis;

    @Override
    @Pointcut("@annotation(com.xin.pay.aop.exception.annotation.PayChannelException)")
    public void pointCut() {

    }

    @Override
    @Before("pointCut()")
    public void before() {

    }

    @Override
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            Object responses = proceedingJoinPoint.proceed();
            PayChannelException channelAnn = null;
            Class<?> returnType = null;
            Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
            String methodName = proceedingJoinPoint.getSignature().getName();
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    channelAnn = method.getAnnotation(PayChannelException.class);
                    returnType = method.getReturnType();
                }
            }

            if (returnType == String.class && channelAnn.returnDataFormat() == ReturnDataFormatEnum.JSON) {
                return handleStringJSON(channelAnn, (String) responses);
            } else if (returnType != String.class && channelAnn.returnDataFormat() == ReturnDataFormatEnum.JSON) {
                return handleNotStringJSON(channelAnn, returnType, responses);
            } else {
                throw new UxinPaySysException("返回数据类型不正确");
            }
        } catch (Throwable e) {
            throw e;
        }
    }

    private Object handleStringJSON(PayChannelException channelAnn, String response) {
        String findPath = "$." + channelAnn.respCodeFieldName();
        JSONObject jsonObject = JSONObject.parseObject(response);
        String respCode = (String) JSONPath.eval(jsonObject, findPath);
        if (null == respCode || "".equals(respCode.trim())) {
            return response;
        }

        if (JSONPath.remove(jsonObject, findPath)) {
            JSONPath.set(jsonObject, findPath, channelExceptionRedis.getChaExcCode(RedisPrefix.UXIN_PAY_CHA_EXCEPTION + respCode));
            return jsonObject.toJSONString();
        } else {
            throw new UxinPaySysException("没有在返回的json字符串中找到对应的respCode");
        }
    }

    private Object handleNotStringJSON(PayChannelException channelAnn,Class<?> returnType,Object responses) {
        try {
            Object returnObj = returnType.cast(responses);
            StringBuilder callMethodName = new StringBuilder();
            callMethodName.append(MethodPrefix.GET);
            callMethodName.append(Character.toUpperCase(channelAnn.respCodeFieldName().charAt(0)));
            callMethodName.append(channelAnn.respCodeFieldName().substring(1, channelAnn.respCodeFieldName().length()));
            String callMethodNameStr = callMethodName.toString();
            String respCode = (String) returnObj.getClass().getDeclaredMethod(callMethodNameStr).invoke(returnObj);
            if (null == respCode || "".equals(respCode.trim())) {
                return responses;
            }
            callMethodNameStr = callMethodNameStr.replaceAll(MethodPrefix.GET, MethodPrefix.SET);
            returnObj.getClass().getDeclaredMethod(callMethodNameStr, String.class).invoke(returnObj, channelExceptionRedis.getChaExcCode(RedisPrefix.UXIN_PAY_CHA_EXCEPTION + respCode));
            return returnObj;
        } catch (Exception e) {
            throw new UxinPaySysException(e);
        }
    }

}
