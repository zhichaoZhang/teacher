package com.szr.jkxsx.data.service.retrofit;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 *  简单的 Retrofit Service 创建者
 *
 * Created by zcZhang on 3/11/15.
 */
public class HRetrofitCreator {
    private RestAdapter mRestAdapter;

    public HRetrofitCreator(String endPoint, RequestInterceptor interceptor, ErrorHandler errorHandler, boolean isTesting) {
        RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.FULL;
        if (!isTesting) {
            logLevel = RestAdapter.LogLevel.NONE;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(10*1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(10*1000,TimeUnit.MILLISECONDS);
//        okHttpClient.networkInterceptors().add(new StethoInterceptor());

        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setRequestInterceptor(interceptor)
                .setErrorHandler(errorHandler)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(logLevel)
                .build();
    }

    public <T> T getService(Class<T> serviceClazz) {
        return mRestAdapter.create(serviceClazz);
    }
}
