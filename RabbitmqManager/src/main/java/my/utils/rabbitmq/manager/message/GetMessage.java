package my.utils.rabbitmq.manager.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import my.utils.rabbitmq.manager.http.HttpFactory;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @author lpwang
 * @Title GetMessage
 * @Package my.utils.rabbitmq.manager
 * @Description: 获取队列的消息
 * @date 2018-07-04 10:49
 */
public class GetMessage {

    public static final String URL = "http://xx.xx.xx.xx:15672/api/queues/uxinpay.xin.com/pay-account-queue.dlq/get";
    public static final String REQ_CONTENT = "{\"count\":23,\"requeue\":false,\"encoding\":\"auto\",\"truncate\":50000,\"name\":\"pay-account-queue.dlq\",\"vhost\":\"uxinpay.xin.com\"}";


    public static void main(String[] args) throws Exception {

        File file = new File("/data/rmq/pay-account");
        Files.createParentDirs(file);
        OkHttpClient client = HttpFactory.getNewInstants();

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), REQ_CONTENT);

        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        JSONArray jsonArray = JSONArray.parseArray(response.body().string());
        int len = jsonArray.size();
        for (int i = 0; i < len; i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String payload = (String)jsonObject.get("payload");
            Files.append(payload+"\r\n", file, Charsets.UTF_8);
        }
    }

}
