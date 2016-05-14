package com.szr.jkxsx.data.service.retrofit;


import com.szr.jkxsx.data.common.Constants;
import com.szr.jkxsx.data.exception.RequestException;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * 网络层错误拦截
 *
 * Created by zcZhang on 3/11/15.
 */
public class HErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {
        String shownMsg = "获取数据失败，请稍后重试";
        switch (cause.getKind()) {
            case HTTP:
                shownMsg = Constants.SERVER_ERROR;
                break;
            case NETWORK:
                shownMsg = Constants.INTERNET_ERROR;
                break;
            case CONVERSION:
                shownMsg = Constants.CONVERSION_ERROR;
                break;
            case UNEXPECTED:
                shownMsg = "未知的网络异常哦";
                break;
        }
        return new RequestException(shownMsg);
    }
}
