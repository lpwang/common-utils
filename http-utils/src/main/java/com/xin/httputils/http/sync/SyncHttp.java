package com.xin.httputils.http.sync;

import com.alibaba.fastjson.JSONObject;
import com.xin.httputils.IHttp;
import com.xin.httputils.request.HttpClientFactory;
import com.xin.httputils.request.RequestFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;

/**
 * @author lpwang
 * @Title SyncHttp
 * @Package com.xin.httputils.http.sync
 * @Description: 同步http工具类
 * @date 2018-05-31 12:03
 */
public class SyncHttp implements IHttp {

    private boolean useSafe = false;
    private SyncHttp() {

    }
    public static SyncHttp getNewInstance(boolean useSafe) {
        SyncHttp syncHttp = new SyncHttp();
        syncHttp.setUseSafe(useSafe);
        return syncHttp;
    }

    @Override
    public String paramRequest(String url, Map<String, String> paramMap) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newGetRequest(url,paramMap);
        return call(client, request);
    }

    @Override
    public String jsonRequest(String url, Object obj) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newPostRequest(url, JSONObject.toJSONString(obj));
        return call(client, request);
    }

    @Override
    public String jsonRequest(String url, Object obj, int connectTimeout, int readTimeout, int writeTimeout) {
        OkHttpClient client = HttpClientFactory.getNewInstance(connectTimeout, readTimeout, writeTimeout);
        Request request = RequestFactory.newPostRequest(url, JSONObject.toJSONString(obj));
        return call(client, request);
    }

    @Override
    public String formReqeust(String url, Map<String, String> requestMap) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newFormRequest(url, requestMap);
        return call(client, request);
    }

    @Override
    public String formReqeust(String url, Map<String, String> requestMap, int connectTimeout, int readTimeout, int writeTimeout) {
        OkHttpClient client = HttpClientFactory.getNewInstance(connectTimeout, readTimeout, writeTimeout);
        Request request = RequestFactory.newFormRequest(url, requestMap);
        return call(client, request);
    }

    @Override
    public String xmlRequest(String url, String xmlStr) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newPostRequest(url, xmlStr);
        return call(client, request);
    }

    @Override
    public String xmlRequest(String url, String xmlStr, int connectTimeout, int readTimeout, int writeTimeout) {
        OkHttpClient client = HttpClientFactory.getNewInstance(connectTimeout, readTimeout, writeTimeout);
        Request request = RequestFactory.newPostRequest(url, xmlStr);
        return call(client, request);
    }

    private String call(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isUseSafe() {
        return useSafe;
    }

    public void setUseSafe(boolean useSafe) {
        this.useSafe = useSafe;
    }
}
