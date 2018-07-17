package com.xin.dateutils.date;

import com.xin.dateutils.Contants.DateFormatConstant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author lpwang
 * @Title DateUtils
 * @Package com.xin.dateutils.date
 * @Description: 日期工具类
 * @date 2018-07-17 19:16
 */
public class DateUtils {

    private DateUtils() {

    }

    public static String theDayOfPoint(LocalDate localDate,int dateGap) {
        String theDate = null;
        if (dateGap > 0) {
            theDate = localDate.plusDays(dateGap).format(DateTimeFormatter.ofPattern(DateFormatConstant.point));
        } else if (dateGap < 0) {
            dateGap = Math.abs(dateGap);
            theDate = localDate.minusDays(dateGap).format(DateTimeFormatter.ofPattern(DateFormatConstant.point));
        } else {
            theDate = CurrentDateUtils.point();
        }
        return theDate;
    }


}
