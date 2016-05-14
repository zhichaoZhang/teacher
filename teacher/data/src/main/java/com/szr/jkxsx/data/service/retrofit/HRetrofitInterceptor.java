package com.szr.jkxsx.data.service.retrofit;

import retrofit.RequestInterceptor;

/**
 * Retrofit 的 请求拦截组件
 * <p/>
 * Created by zcZhang on 3/3/15.
 */
public class HRetrofitInterceptor implements RequestInterceptor {
    private String cookie;
    private String userAgent;

    public HRetrofitInterceptor() {
    }

    public HRetrofitInterceptor(String cookie) {
        this.cookie = cookie;
    }

    public HRetrofitInterceptor(String cookie, String userAgent) {
        this.cookie = cookie;
        this.userAgent = userAgent;
    }

    @Override
    public void intercept(RequestFacade request) {
        if (cookie != null && !cookie.equals("")) {
            request.addHeader("Cookie", cookie);
        }
        if (userAgent != null && !"".equals(userAgent)) {
            request.addHeader("User-Agent", userAgent);
        }
    }
}
