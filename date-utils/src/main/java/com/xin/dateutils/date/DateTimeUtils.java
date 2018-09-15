package com.xin.dateutils.date;

import com.xin.dateutils.Contants.DateTimeFormatConstant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static synchronized Date convertLDT(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static synchronized LocalDateTime convertDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    public static synchronized String theMinuteOfPoint(LocalDateTime localDateTime, int minuteGap) {
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
