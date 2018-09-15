package com.xin.dateutils.date;

import com.xin.dateutils.Contants.DateFormatConstant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author lpwang
 * @Title CurrentDateUtils
 * @Package lpwang.github.io.date
 * @Description: 当前日期工具类
 * @date 2018-05-29 12:19
 */
public class CurrentDateUtils {

    private CurrentDateUtils(){}

    public static synchronized LocalDate currentDate() {
        return LocalDate.now();
    }

    public static synchronized String plain() {
        return currentDate().format(DateTimeFormatter.ofPattern(DateFormatConstant.plain));
    }

    public static synchronized String plain2Month() {
        return currentDate().format(DateTimeFormatter.ofPattern(DateFormatConstant.plain2Month));
    }

    public static synchronized String standard() {
        return currentDate().format(DateTimeFormatter.ofPattern(DateFormatConstant.standard));
    }

    public static synchronized String point() {
        return currentDate().format(DateTimeFormatter.ofPattern(DateFormatConstant.point));
    }

}
