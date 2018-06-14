package com.xin.pay.exception.aop.redis;

import com.xin.pay.exception.aop.constant.UnknowExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lpwang
 * @Title ChannelExceptionRedis
 * @Package com.xin.pay.exception.aop.redis
 * @Description: 渠道异常码redis操作类
 * @date 2018-06-12 16:31
 */
@Component
public class ChannelExceptionRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getChaExcCode(String respCode) {
        String chaCode = redisTemplate.opsForValue().get(respCode);
        if (null == chaCode) {
            return UnknowExceptionCode.C99999;
        }
        return chaCode;
    }

}
