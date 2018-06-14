package com.xin.httputils.request;

import okhttp3.OkHttpClient;
import okhttp3.internal.tls.OkHostnameVerifier;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * @author lpwang
 * @Title HttpsClientFactory
 * @Package com.xin.httputils.request
 * @Description: https客户端工厂类
 * @date 2018-05-31 14:18
 */
public class HttpsClientFactory {

    private HttpsClientFactory() {
    }

    public static OkHttpClient getHttpsClient() throws Exception {
        try {
            //获取本地证书
            ArrayList<InputStream> certificates = getCertsStream();
            //用我们的证书创建一个keystore
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = "server" + Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null) {
                        certificate.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //创建一个trustmanager，只信任我们创建的keystore
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(
                    null,
                    trustManagerFactory.getTrustManagers(),
                    new SecureRandom()
            );

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(OkHostnameVerifier.INSTANCE);
            OkHttpClient client = builder.build();
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
    * @Description: 获取信任所有证书的https客户端
    * @Param:
    * @return:
    * @Author: lpwang
    * @Date: 18-5-31
    */
    public static OkHttpClient getUnSafeHttpsClient() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        }, new SecureRandom());
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(socketFactory);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        OkHttpClient client = builder.build();
        return client;
    }

    public static ArrayList<InputStream> getCertsStream() {
        //获取证书地址
        InputStream wechat = HttpsClientFactory.class.getClassLoader().getResourceAsStream("certs/wechat.pem");
        ArrayList<InputStream> certs = new ArrayList<>();
        certs.add(wechat);
        return certs;
    }

}
