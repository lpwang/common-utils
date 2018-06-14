package com.xin.httputils.https.async;

import com.alibaba.fastjson.JSONObject;
import com.xin.httputils.IAsyncHttp;
import com.xin.httputils.callback.IHttpCallBack;
import com.xin.httputils.request.HttpsClientFactory;
import com.xin.httputils.request.RequestFactory;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author lpwang
 * @Title AsyncHttps
 * @Package com.xin.httputils.https.async
 * @Description: 异步https工具类
 * @date 2018-05-31 14:15
 */
public class AsyncHttps implements IAsyncHttp {

    private boolean useSafe = false;
    private AsyncHttps () {

    }
    public static AsyncHttps getNewInstance(boolean useSafe) {
        AsyncHttps asyncHttps = new AsyncHttps();
        asyncHttps.setUseSafe(useSafe);
        return asyncHttps;
    }

    @Override
    public void paramRequest(String url, Map<String, String> paramMap, IHttpCallBack callBack) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newGetRequest(url, paramMap);
            call(client, request, callBack);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void jsonRequest(String url, Object obj, IHttpCallBack callBack) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newPostRequest(url, JSONObject.toJSONString(obj));
            call(client, request, callBack);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void formReqeust(String url, Map<String, String> requestMap, IHttpCallBack callBack) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newFormRequest(url, requestMap);
            call(client, request, callBack);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void xmlRequest(String url, String xmlStr, IHttpCallBack callBack) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newPostRequest(url, xmlStr);
            call(client, request, callBack);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
