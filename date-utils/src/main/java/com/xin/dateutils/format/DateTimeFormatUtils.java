package com.xin.dateutils.format;

import com.xin.dateutils.date.IDateTime;
import com.xin.dateutils.Contants.DateTimeFormatConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lpwang
 * @Title DateTimeFormatConstant
 * @Package lpwang.github.io
 * @Description: 日期格式化工具
 * @date 2018-05-28 16:13
 */
public class DateTimeFormatUtils implements IDateTime {

    private DateTimeFormatUtils() {
    }

    public static String plain(Date date) {
        return yyyyMMddHHmmssSDF.format(date);
    }

    public static String plain(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmss));
    }

    public static String standard(Date date) {
        return yMdHmsSDF.format(date);
    }

    public static String standard(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yMdHms));
    }

    public static String MSPlain(Date date) {
        return yyyyMMddHHmmssSSSSDF.format(date);
    }

    public static String MSPlain(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmssSSS));
    }

    public static String MSStandard(Date date) {
        return yMdHmsSSDF.format(date);
    }

    public static String MSStandard(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yMdHmsS));
    }

}
