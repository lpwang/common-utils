package com.xin.dateutils.date;

import com.xin.dateutils.Contants.DateTimeFormatConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lpwang
 * @Title CurrentDateTimeUtils
 * @Package lpwang.github.io.date
 * @Description: 当前时间工具类
 * @date 2018-05-28 20:33
 */
public class CurrentDateTimeUtils implements IDateTime {

    public static synchronized Date date() {
        return new Date();
    }

    public static synchronized LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    public static synchronized String plain() {
        return localDateTime().format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmss));
    }

    public static synchronized String standard() {
        return localDateTime().format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yMdHms));
    }

    public static synchronized String MSPlain() {
        return localDateTime().format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmssSSS));
    }

    public static synchronized String MSStandard() {
        return localDateTime().format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yMdHmsS));
    }

}
