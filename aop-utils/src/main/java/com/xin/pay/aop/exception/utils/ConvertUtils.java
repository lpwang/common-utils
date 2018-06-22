package com.xin.pay.aop.exception.utils;

/**
 * @author lpwang
 * @Title ConvertUtils
 * @Package com.xin.pay.exception.aop.utils
 * @Description: 转化工具
 * @date 2018-06-12 17:20
 */
public class ConvertUtils {

    public static int StringChatToInt(String string) {
        int sum = 0;
        for (char c : string.toCharArray()) {
            int charInt = c;
            sum += charInt;
        }
        return sum;
    }

}
