package com.xin.dateutils.date;

import com.xin.dateutils.Contants.DateTimeFormatConstant;

import java.text.SimpleDateFormat;

/**
 * @author lpwang
 * @Title IDateTime
 * @Package lpwang.github.io
 * @Description: 时间工具通用接口
 * @date 2018-05-28 20:23
 */
public interface IDateTime {

    SimpleDateFormat yyyyMMddHHmmssSDF = new SimpleDateFormat(DateTimeFormatConstant.yyyyMMddHHmmss);
    SimpleDateFormat yyyyMMddHHmmssSSSSDF = new SimpleDateFormat(DateTimeFormatConstant.yyyyMMddHHmmssSSS);
    SimpleDateFormat yMdHmsSDF = new SimpleDateFormat(DateTimeFormatConstant.yMdHms);
    SimpleDateFormat yMdHmsSSDF = new SimpleDateFormat(DateTimeFormatConstant.yMdHmsS);

}
