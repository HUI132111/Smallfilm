package com.bwie.aizhonghui.mvpfmodule.utils;

import com.bwie.aizhonghui.mvpfmodule.Interce.ParamsInterceptor;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DANGEROUS_HUI on 2017/11/14.
 */

public class RetrofitUtils {
   public static RetrofitUtils mInstance;
   private Retrofit retrofit;

    public RetrofitUtils(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
    public static class Builder{

        public RetrofitUtils build(String url){

            OkHttpClient.Builder okbuilder=new OkHttpClient.Builder();
            okbuilder.addInterceptor(new ParamsInterceptor())
                     .sslSocketFactory(createSSLSocketFactory())
                     .hostnameVerifier(new TrustAllHostnameVerifier())
                     .connectTimeout(10, TimeUnit.SECONDS)
                     .readTimeout(10,TimeUnit.SECONDS)
                     .writeTimeout(10,TimeUnit.SECONDS)
                     .retryOnConnectionFailure(false);

            Retrofit retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(url)
                    .client(okbuilder.build())
                    .build();
            mInstance=new RetrofitUtils(retrofit);
            return mInstance;
        }
    }

    //================================信任所有证书======================================
    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }
}
