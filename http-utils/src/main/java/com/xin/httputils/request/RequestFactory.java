package com.xin.httputils.request;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Iterator;
import java.util.Map;

/**
 * @author lpwang
 * @Title RequestFactory
 * @Package com.xin.httputils.request
 * @Description: 请求实体封装工厂类
 * @date 2018-05-29 18:43
 */
public class RequestFactory {

    private RequestFactory(){}

    public static Request newGetRequest(String url, Map<String, String> paramMap) {
        if (null != paramMap && paramMap.size() > 0) {
            StringBuilder params = new StringBuilder("?");
            for (Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                params = params.append(key).append("&").append(value);
            }
            url = url + params.substring(0, params.length() - 1);
        }
        return new Request.Builder().get().url(url).build();
    }

    public static Request newPostRequest(String url, String respBody) {
        if (null != respBody && respBody.length() > 0) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), respBody);
            return new Request.Builder().post(requestBody).url(url).build();
        } else {
            throw new RuntimeException("请求体数据不能为空!");
        }
    }

    public static Request newFormRequest(String url, Map<String, String> paramMap) {
        if (null != paramMap && paramMap.size() > 0) {
            FormBody.Builder body = new FormBody.Builder();
            for (Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, String> entry = iterator.next();
                body = body.add(entry.getKey(), entry.getValue());
            }
            RequestBody requestBody = body.build();
            return new Request.Builder().post(requestBody).url(url).build();
        } else {
            throw new RuntimeException("请求体数据不能为空!");
        }
    }


}
