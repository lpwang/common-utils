package com.xin.httputils.https.sync;

import com.alibaba.fastjson.JSONObject;
import com.xin.httputils.IHttp;
import com.xin.httputils.request.HttpsClientFactory;
import com.xin.httputils.request.RequestFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;

/**
 * @author lpwang
 * @Title SyncHttps
 * @Package com.xin.httputils.https.sync
 * @Description: 同步https客户端
 * @date 2018-05-31 21:06
 */
public class SyncHttps implements IHttp {

    private boolean useSafe = false;
    private SyncHttps () {

    }

    public static SyncHttps getNewInstance(boolean useSafe) {
        SyncHttps syncHttps = new SyncHttps();
        syncHttps.setUseSafe(useSafe);
        return syncHttps;
    }

    @Override
    public String paramRequest(String url, Map<String, String> paramMap) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newGetRequest(url, paramMap);
            return call(client, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String jsonRequest(String url, Object obj) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newPostRequest(url, JSONObject.toJSONString(obj));
            return call(client, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String jsonRequest(String url, Object obj, int connectTimeout, int readTimeout, int writeTimeout) {
        //todo 未开始
        return null;
    }

    @Override
    public String formReqeust(String url, Map<String, String> requestMap, int connectTimeout, int readTimeout, int writeTimeout) {
        //todo 未开始
        return null;
    }

    @Override
    public String xmlRequest(String url, String xmlStr, int connectTimeout, int readTimeout, int writeTimeout) {
        //todo 未开始
        return null;
    }

    @Override
    public String formReqeust(String url, Map<String, String> requestMap) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newFormRequest(url, requestMap);
            return call(client, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String xmlRequest(String url, String xmlStr) {
        OkHttpClient client = null;
        try {
            if (useSafe) {
                client = HttpsClientFactory.getHttpsClient();
            } else {
                client = HttpsClientFactory.getUnSafeHttpsClient();
            }
            Request request = RequestFactory.newPostRequest(url, xmlStr);
            return call(client, request);
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

    private String call(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
