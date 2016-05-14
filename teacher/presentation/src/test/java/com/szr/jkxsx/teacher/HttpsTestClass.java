package com.szr.jkxsx.teacher;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by zczhang on 15/12/27.
 */
public class HttpsTestClass {
    @Test
    public void testHttpsRequest() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //设置信任所有
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(new KeyManager[0], xtmArray, new SecureRandom());
            SSLSocketFactory socketFactory = context.getSocketFactory();
            mOkHttpClient.setSslSocketFactory(socketFactory);

            // Allow all hostnames
            mOkHttpClient.setHostnameVerifier(HOSTNAME_VERIFIER);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

//        try {
//            setCertificates(mOkHttpClient, new FileInputStream("/Users/joye/Downloads/srca12306/srca.cer"));
//            setCertificates(mOkHttpClient, new FileInputStream("/Users/joye/Downloads/load-der.crt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//创建一个Request
        final Request request = new Request.Builder()
                .url("https://certs.cac.washington.edu/CAtest/")
//                .url("https://raw.github.com/square/okhttp/master/README.md")
//                .url("https://kyfw.12306.cn/otn/")
//                .url("https://www.baidu.com")
                .build();
//new call
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            System.out.println("response----->" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //信任全部
    private static X509TrustManager xtm = new X509TrustManager() {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] chain, String authType)
                throws CertificateException {
            // TODO Auto-generated method stub
            System.out.println("cert: " + chain[0].toString() + ", authType: "
                    + authType);

        }
    };

    private static final AllowAllHostnameVerifier HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
    static X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };

    //设置ssl信任证书
    public void setCertificates(OkHttpClient okHttpClient, InputStream... certificates) {
        try {
            //创建证书工厂
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            //创建秘钥库
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                //证书工厂根据证书文件数据流生成证书,并添加到秘钥库中
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            //ssl上下文,用来创建定制的ssl链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            //信任管理器工厂实例
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //根据秘钥库初始化信任管理器
            trustManagerFactory.init(keyStore);
            //根据需要信任的证书初始化ssl链接
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            //为HTTPclient设置sslSocketFactory
            okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
