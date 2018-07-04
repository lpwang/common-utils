package my.utils.rabbitmq.manager.http;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lpwang
 * @Title HttpFactory
 * @Package my.utils.rabbitmq.manager.http
 * @Description: http实例工程
 * @date 2018-07-04 10:51
 */
public class HttpFactory {

    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT=100;
    public final static int WRITE_TIMEOUT=60;

    private HttpFactory(){}

    public static OkHttpClient getNewInstants() {
        return new OkHttpClient.Builder().authenticator(new Authenticator() {
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic("xx", "xx");
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }
        })
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
                .build();
    }

}
