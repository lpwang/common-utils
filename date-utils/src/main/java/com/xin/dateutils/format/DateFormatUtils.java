package com.xin.dateutils.format;

import com.xin.dateutils.date.IDate;
import com.xin.dateutils.Contants.DateFormatConstant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lpwang
 * @Title DateFormatUtils
 * @Package lpwang.github.io.format
 * @Description: 日期格式化工具
 * @date 2018-05-29 11:55
 */
public class DateFormatUtils implements IDate {

    private DateFormatUtils(){}

    public static String plain(Date date) {
        return PLAIN_SDF.format(date);
    }

    public static String plain(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DateFormatConstant.plain));
    }

    public static String standard(Date date) {
        return STANDARD_SDF.format(date);
    }

    public static String standard(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DateFormatConstant.standard));
    }

}
