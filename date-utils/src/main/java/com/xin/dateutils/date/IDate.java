package com.xin.dateutils.date;

import com.xin.dateutils.Contants.DateFormatConstant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author lpwang
 * @Title IDate
 * @Package lpwang.github.io.date
 * @Description: 日期工具通用接口
 * @date 2018-05-29 12:01
 */
public interface IDate {

    DateFormat PLAIN_SDF = new SimpleDateFormat(DateFormatConstant.plain);
    DateFormat STANDARD_SDF = new SimpleDateFormat(DateFormatConstant.standard);

}
