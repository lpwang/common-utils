package com.xin.httputils;

import com.xin.httputils.callback.IHttpCallBack;

import java.util.Map;

/**
 * @author lpwang
 * @Title IGet
 * @Package com.xin.httputils
 * @Description: 同步get请求接口
 * @date 2018-05-31 12:14
 */
public interface IGet {

    String paramRequest(String url, Map<String, String> paramMap);

}
