package com.xin.pay.exception.aop.redis;

import com.xin.pay.exception.aop.constant.UnknowExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lpwang
 * @Title PaySystemExceptionRedis
 * @Package com.xin.pay.exception.aop.redis
 * @Description: 支付系统异常redis类
 * @date 2018-06-12 16:09
 */
@Component
public class PaySystemExceptionRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getSysExcCode(String javaException) {
        String paySysCode = redisTemplate.opsForValue().get(javaException);
        if (null == paySysCode) {
            return UnknowExceptionCode.S99999;
        }
        return paySysCode;
    }

}
