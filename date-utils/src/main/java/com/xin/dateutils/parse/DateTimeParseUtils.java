package com.xin.dateutils.parse;

import com.xin.dateutils.date.IDateTime;
import com.xin.dateutils.Contants.DateTimeFormatConstant;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lpwang
 * @Title DateTimeParseUtils
 * @Package lpwang.github.io.parse
 * @Description: 日期时间解析工具
 * @date 2018-05-28 18:41
 */
public class DateTimeParseUtils implements IDateTime {

    private DateTimeParseUtils() {
    }

    public static Date plain(String yyyyMMddHHmmss) {
        try {
            return yyyyMMddHHmmssSDF.parse(yyyyMMddHHmmss);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数异常,日期格式错误");
        }
    }

    public static LocalDateTime plain4LDT(String yyyyMMddHHmmss) {
        return LocalDateTime.parse(yyyyMMddHHmmss, DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmss));
    }


    public static Date standard(String yMdHms) {
        try {
            return yMdHmsSDF.parse(yMdHms);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数异常,日期格式错误");
        }
    }

    public static LocalDateTime standard4LDT(String yMdHms) {
        return LocalDateTime.parse(yMdHms, DateTimeFormatter.ofPattern(DateTimeFormatConstant.yMdHms));
    }

    public static Date MSPlain(String yyyyMMddHHmmssSSS) {
        try {
            return yyyyMMddHHmmssSSSSDF.parse(yyyyMMddHHmmssSSS);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数异常,日期格式错误");
        }
    }

    public static LocalDateTime MSPlain4LDT(String yyyyMMddHHmmssSSS) {
        return LocalDateTime.parse(yyyyMMddHHmmssSSS, DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmssSSS));
    }

    public static Date MSStandard(String yMdHmsS) {
        try {
            return yMdHmsSSDF.parse(yMdHmsS);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数异常,日期格式错误");
        }
    }

    public static LocalDateTime MSStandard4LDT(String yMdHmsS) {
        return LocalDateTime.parse(yMdHmsS, DateTimeFormatter.ofPattern(DateTimeFormatConstant.yMdHmsS));
    }
}
