package com.xin.httputils;

import com.xin.httputils.callback.IHttpCallBack;

import java.util.Map;

/**
 * @author lpwang
 * @Title IPost
 * @Package com.xin.httputils
 * @Description: post请求接口
 * @date 2018-05-31 12:15
 */
public interface IPost {

    String jsonRequest(String url, Object obj);

    String formReqeust(String url, Map<String, String> requestMap);

    String xmlRequest(String url, String xmlStr);

}
