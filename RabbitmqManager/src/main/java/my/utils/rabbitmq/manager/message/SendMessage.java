package my.utils.rabbitmq.manager.message;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import my.utils.rabbitmq.manager.entry.MessageEntry;
import my.utils.rabbitmq.manager.http.HttpFactory;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;

/**
 * @author lpwang
 * @Title SendMessage
 * @Package my.utils.rabbitmq.manager.message
 * @Description: 发送消息到消息队列
 * @date 2018-07-04 13:03
 */
public class SendMessage {

    public static final String URL = "http://172.16.2.26:15672/api/exchanges/uxinpay.xin.com/all-to-monitor/publish/";
    //public static final String URL = "http://10.70.93.75:15672/api/exchanges/%2f/payment-to-account/publish/";

    public static void main(String[] args) throws Exception {
        File file = new File("/data/rmq/monitor-4");
        BufferedReader reader = Files.newReader(file, Charsets.UTF_8);
        int count = 0;
        OkHttpClient client = HttpFactory.getNewInstants();

        String line = null;
        while (null != (line = reader.readLine())) {
            MessageEntry messageEntry = new MessageEntry();
            MessageEntry.properties properties = new MessageEntry.properties();
            properties.setContent_type("application/json");
            messageEntry.setProperties(properties);
            messageEntry.setPayload(line);
            String jsonString = JSONObject.toJSONString(messageEntry);

            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), jsonString);

            Request request = new Request.Builder()
                    .url(URL)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(++count+" == "+response.body().string());
            Thread.sleep(250);
        }
    }

}
