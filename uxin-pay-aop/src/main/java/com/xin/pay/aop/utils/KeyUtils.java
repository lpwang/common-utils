package com.xin.pay.aop.utils;

/**
 * @author lpwang
 * @Title KeyUtils
 * @Package com.xin.pay.aop.utils
 * @Description: 获取redis键值的工具
 * @date 2018-07-09 18:09
 */
public class KeyUtils {

    public static final String DEP_PREFIX = "uxinpay";
    public static final String TYPE_FLAG = "errorCode";
    public static final String CONNECT_SIGN = ":";

    public static String getKey(String channelCode, String respCode) {
        StringBuilder keyStr = new StringBuilder();
        keyStr.append(DEP_PREFIX)
                .append(CONNECT_SIGN)
                .append(channelCode)
                .append(CONNECT_SIGN)
                .append(TYPE_FLAG)
                .append(CONNECT_SIGN)
                .append(respCode);
        return keyStr.toString();
    }

}
