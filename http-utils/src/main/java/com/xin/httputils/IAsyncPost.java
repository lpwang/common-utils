package com.xin.httputils;

import com.xin.httputils.callback.IHttpCallBack;

import java.util.Map;

/**
 * @author lpwang
 * @Title IAsyncPost
 * @Package com.xin.httputils
 * @Description: post请求接口
 * @date 2018-05-29 18:14
 */
public interface IAsyncPost {

    void jsonRequest(String url, Object obj, IHttpCallBack callBack);

    void formReqeust(String url, Map<String, String> requestMap,IHttpCallBack callBack);

    void xmlRequest(String url, String xmlStr, IHttpCallBack callBack);

}
