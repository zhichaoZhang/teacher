package com.szr.jkxsx.teacher.app.dependency.repository;

import android.support.annotation.Nullable;

import com.szr.jkxsx.data.service.UserService;
import com.szr.jkxsx.data.service.retrofit.HErrorHandler;
import com.szr.jkxsx.data.service.retrofit.HRetrofitCreator;
import com.szr.jkxsx.data.service.retrofit.HRetrofitInterceptor;
import com.szr.jkxsx.teacher.app.dependency.repository.qualifier.ApiDomain;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;

/**
 * 网络请求接口服务配置依赖
 * <p/>
 * Created by zcZhang on 15/6/12.
 */
@Module(includes = ServerConfigModule.class)
public class ApiServiceModule {

    @Provides
    @Named("Near")
    RequestInterceptor provideNEARHttpInterceptor(@Nullable @Named("cookie") String cookie, @Nullable @Named("UserAgent") String userAgent) {
        return new HRetrofitInterceptor(cookie, userAgent);
    }

    @Provides
    @Named("MMWD")
    RequestInterceptor provideMMWDHttpInterceptor(@Nullable @Named("mmwd_cookie") String cookie, @Nullable @Named("UserAgent") String userAgent) {
        return new HRetrofitInterceptor(cookie, userAgent);
    }

    @Provides
    @Named("COMMON")
    RequestInterceptor provideWXHttpInterceptor() {
        return new HRetrofitInterceptor();
    }

    @Provides
    ErrorHandler provideHttpErrorHandler() {
        return new HErrorHandler();
    }

    @Provides
    @Named("HRetrofitCreator")
    HRetrofitCreator provideRetrofitCreator(@ApiDomain String apiDomain, @Named("COMMON") RequestInterceptor interceptor, ErrorHandler errorHandler, @Named("IsDebugging") Boolean isDebugging) {
        return new HRetrofitCreator(apiDomain, interceptor, errorHandler, isDebugging);
    }

    @Provides
    UserService provideUserService(@Named("HRetrofitCreator") HRetrofitCreator creator) {
        return creator.getService(UserService.class);
    }
}
