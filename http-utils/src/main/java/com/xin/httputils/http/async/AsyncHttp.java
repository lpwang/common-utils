package com.xin.httputils.http.async;

import com.alibaba.fastjson.JSONObject;
import com.xin.httputils.IAsyncHttp;
import com.xin.httputils.callback.IHttpCallBack;
import com.xin.httputils.request.HttpClientFactory;
import com.xin.httputils.request.RequestFactory;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author lpwang
 * @Title AsyncHttp
 * @Package com.xin.httputils.http.async
 * @Description: 异步http请求工具类
 * @date 2018-05-29 18:28
 */
public class AsyncHttp implements IAsyncHttp {

    private boolean useSafe = false;
    private AsyncHttp() {

    }
    public static AsyncHttp getNewInstance(boolean useSafe) {
        AsyncHttp asyncHttp = new AsyncHttp();
        asyncHttp.setUseSafe(useSafe);
        return asyncHttp;
    }

    @Override
    public void paramRequest(String url, Map<String, String> paramMap, IHttpCallBack callBack) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newGetRequest(url,paramMap);
        call(client, request, callBack);
    }

    @Override
    public void jsonRequest(String url, Object obj, IHttpCallBack callBack) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newPostRequest(url, JSONObject.toJSONString(obj));
        call(client, request, callBack);
    }

    @Override
    public void formReqeust(String url, Map<String, String> requestMap, IHttpCallBack callBack) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newFormRequest(url, requestMap);
        call(client, request, callBack);
    }

    @Override
    public void xmlRequest(String url, String xmlStr, IHttpCallBack callBack) {
        OkHttpClient client = HttpClientFactory.getNewInstance();
        Request request = RequestFactory.newPostRequest(url, xmlStr);
        call(client, request, callBack);
    }

    private void call(OkHttpClient client, Request request, IHttpCallBack callBack) {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                callBack.respException(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String respBody = response.body().string();
                callBack.respHandle(respBody);
            }
        });
    }

    public boolean isUseSafe() {
        return useSafe;
    }

    public void setUseSafe(boolean useSafe) {
        this.useSafe = useSafe;
    }
}
