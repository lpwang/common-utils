package com.xin.pay.aop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lpwang
 * @Title StackExceptionUtils
 * @Package com.xin.pay.aop.utils
 * @Description: 堆栈异常工具类
 * @date 2018-03-23 17:42
 */
public class StackExceptionUtils {

    private StackExceptionUtils() {}

    private static final Logger LOG = LoggerFactory.getLogger(StackExceptionUtils.class);

    public static String getStackMessage (Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        try {
            e.printStackTrace(printWriter);
            String stackMessage = stringWriter.toString();
            return stackMessage;
        } finally {
            try {
                stringWriter.close();
            } catch (Exception e1) {
                LOG.error("堆栈异常工具类String流关闭异常");
            }
            printWriter.close();
        }
    }

}
