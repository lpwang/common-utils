package com.xin.httputils.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * @author lpwang
 * @Title CertUtils
 * @Package com.xin.httputils.utils
 * @Description: 证书工具
 * @date 2018-05-31 15:39
 */
public class CertUtils {

    private static final String BEGIN_CERT ="-----BEGIN CERTIFICATE-----";
    private static final String END_CERT ="-----END CERTIFICATE-----";

    public static Certificate[] getServerHttpsCert(String serverURL) throws Exception {
        URL url = new URL(serverURL);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.connect();
        Certificate[] certs = conn.getServerCertificates();    //会拿到完整的证书链
        X509Certificate cert = (X509Certificate) certs[0];    //cert[0]是证书链的最下层
        /*System.out.println("序号：" + cert.getSerialNumber());
        System.out.println("颁发给：" + cert.getSubjectDN().getName());
        System.out.println("颁发者：" + cert.getIssuerDN().getName());
        System.out.println("起始：" + cert.getNotBefore());
        System.out.println("过期：" + cert.getNotAfter());
        System.out.println("算法：" + cert.getSigAlgName());
        System.out.println("指纹：" + getThumbPrint(cert));*/
        conn.disconnect();
        return certs;
    }


    private static String getThumbPrint(X509Certificate cert) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] der = cert.getEncoded();
        md.update(der);
        byte[] digest = md.digest();
        return bytesToHexString(digest);
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        Certificate[] serverHttpsCert = getServerHttpsCert("https://api.mch.weixin.qq.com/pay/unifiedorder");
        ExportCert(serverHttpsCert);
    }

    public static void ExportCert(Certificate[] certs) throws Exception {
        String certContent = "";
        for (int i = 0; i < certs.length; i++) {
            X509Certificate cert = (X509Certificate) certs[0];
            certContent += Base64.getEncoder().encode(cert.getEncoded());
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/lpwang/cert/mycert.cer"));
        writer.write(BEGIN_CERT);
        writer.newLine();
        writer.write(certContent);
        writer.newLine();
        writer.write(END_CERT);
        writer.flush();
        writer.close();
    }
}
