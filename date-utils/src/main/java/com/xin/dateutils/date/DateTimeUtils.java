package com.xin.dateutils.date;

import com.xin.dateutils.Contants.DateTimeFormatConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lpwang
 * @Title DateTimeUtils
 * @Package com.xin.dateutils.date
 * @Description: 时间工具类
 * @date 2018-07-17 19:29
 */
public class DateTimeUtils {

    private DateTimeUtils() {

    }

    public static String theMinuteOfPoint(LocalDateTime localDateTime, int minuteGap) {
        String theDateTime = null;
        if (minuteGap > 0) {
            theDateTime = localDateTime.plusMinutes(minuteGap).format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmssSSS));
        } else if (minuteGap < 0) {
            minuteGap = Math.abs(minuteGap);
            theDateTime = localDateTime.minusMinutes(minuteGap).format(DateTimeFormatter.ofPattern(DateTimeFormatConstant.yyyyMMddHHmmssSSS));
        } else {
            theDateTime = CurrentDateTimeUtils.MSPlain();
        }
        return theDateTime;
    }

}
