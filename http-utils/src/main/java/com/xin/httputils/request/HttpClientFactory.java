package com.xin.httputils.request;

import okhttp3.OkHttpClient;

/**
 * @author lpwang
 * @Title HttpClientFactory
 * @Package com.xin.httputils.request
 * @Description: 请求工厂
 * @date 2018-05-29 18:32
 */
public class HttpClientFactory {

    private static final OkHttpClient client = new OkHttpClient();

    private HttpClientFactory(){}

    public static OkHttpClient getInstance() {
        return client;
    }

    public static OkHttpClient getNewInstance() {
        return new OkHttpClient();
    }

}
