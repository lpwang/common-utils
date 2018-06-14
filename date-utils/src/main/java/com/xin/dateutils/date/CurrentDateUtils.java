package com.xin.dateutils.date;

import java.time.LocalDate;

/**
 * @author lpwang
 * @Title CurrentDateUtils
 * @Package lpwang.github.io.date
 * @Description: 当前日期工具类
 * @date 2018-05-29 12:19
 */
public class CurrentDateUtils {

    private CurrentDateUtils(){}

    public static LocalDate currentDate() {
        return LocalDate.now();
    }

}
