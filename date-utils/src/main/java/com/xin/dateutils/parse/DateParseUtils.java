package com.xin.dateutils.parse;

import com.xin.dateutils.date.IDate;
import com.xin.dateutils.Contants.DateFormatConstant;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lpwang
 * @Title DateParseUtils
 * @Package lpwang.github.io.parse
 * @Description: 日期解析工具类
 * @date 2018-05-29 12:07
 */
public class DateParseUtils implements IDate {

    private DateParseUtils(){}

    public static Date plain(String plainStr) {
        try {
            return PLAIN_SDF.parse(plainStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数异常,日期格式错误");
        }
    }

    public static LocalDate plain4LDT(String plainStr) {
        return LocalDate.parse(plainStr, DateTimeFormatter.ofPattern(DateFormatConstant.plain));
    }

    public static Date standard(String standardStr) {
        try {
            return STANDARD_SDF.parse(standardStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("非法参数异常,日期格式错误");
        }
    }

    public static LocalDate standard4LDT(String standardStr) {
        return LocalDate.parse(standardStr, DateTimeFormatter.ofPattern(DateFormatConstant.standard));
    }
}
