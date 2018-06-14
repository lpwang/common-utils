package com.xin.httputils;

import com.xin.httputils.callback.IHttpCallBack;

import java.util.Map;

/**
 * @author lpwang
 * @Title IAsyncGet
 * @Package com.xin.httputils
 * @Description: get请求接口
 * @date 2018-05-29 18:22
 */
public interface IAsyncGet {

    void paramRequest(String url, Map<String, String> paramMap, IHttpCallBack callBack);

}
