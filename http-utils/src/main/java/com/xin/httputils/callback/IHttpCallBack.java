package com.xin.httputils.callback;

/**
 * @author lpwang
 * @Title IHttpCallBack
 * @Package com.xin.httputils.callback
 * @Description: 回调接口
 * @date 2018-05-30 11:45
 */
public interface IHttpCallBack {

    void respHandle(String respStr);

    void respException(Exception e);
}
