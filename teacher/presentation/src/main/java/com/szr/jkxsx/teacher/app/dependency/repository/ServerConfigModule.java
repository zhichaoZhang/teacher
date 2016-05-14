package com.szr.jkxsx.teacher.app.dependency.repository;

import android.support.annotation.Nullable;

import com.szr.jkxsx.teacher.BuildConfig;
import com.szr.jkxsx.teacher.app.BaseApplication;
import com.szr.jkxsx.teacher.app.dependency.repository.qualifier.ApiDomain;
import com.szr.jkxsx.teacher.app.dependency.repository.qualifier.WebDomain;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * 服务器相关配置依赖
 *
 * Created by zcZhang on 15/6/12.
 */
@Module
public class ServerConfigModule {
    @Provides
    @Named("IsDebugging")
    Boolean providesIsDebugging() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @ApiDomain
    String provideApiDomain() {
        return BaseApplication.getInstance().getAppConfigDataEngine().getApiDomain();
    }
//
//    @Provides @WebDomain
//    String provideWebDomain() {
//        return "";
//    }
//
//    @Provides @Named("WxLoginApi")
//    String provideWxLoginApi() {
//        return NearApplication.getInstance().getAppConfigDataEngine().getWxApiDomain();
//    }
//
//    @Provides @Named("cookie") @Nullable
//    String provideCookie() {
//        return NearApplication.getInstance().getAppConfigDataEngine().getCookie();
//    }
//
//    @Provides @Named("UserAgent") @Nullable
//    String provideUserAgent() {
//        return NearApplication.getInstance().getAppConfigDataEngine().getUserAgent();
//    }
//
//    @Provides @Named("PayApiDomain") @Nullable
//    String providesPayApiDomain() {
//        return NearApplication.getInstance().getAppConfigDataEngine().getPayApiDomain();
//    }
//
//    @Provides @Named("mmwd_cookie") @Nullable
//    String provideMMWDCookie() {
//        return "near_uid = " + NearApplication.getInstance().getAppConfigDataEngine().getUserId();
//    }
}
