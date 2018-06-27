package com.xin.pay.aop.log;

import com.xin.pay.aop.log.constant.LogFlag;
import com.xin.pay.aop.log.constant.LoggerName;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * @author lpwang
 * @Title LogFactory
 * @Package com.xin.pay.aop.log
 * @Description: 日志工厂类
 * @date 2018-06-26 18:22
 */
public class LogFactory {

    private LogFactory() {

    }

    public static LogWrite newLoggerWrite() {
        return new LogWrite();
    }

    public static class LogWrite {

        public void writeChannelLog(Object writeLog) {
            ThreadContext.put(LogFlag.CHANNEL, "true");
            LoggerFactory.getLogger(LoggerName.FILE).info("{}", writeLog);
            ThreadContext.clearMap();
        }

        public void writeSystemLog(Object writeLog) {
            ThreadContext.put(LogFlag.SYSTEM, "true");
            LoggerFactory.getLogger(LoggerName.FILE).info("{}", writeLog);
            ThreadContext.clearMap();
        }

    }

}
