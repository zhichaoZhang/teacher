package com.szr.jkxsx.teacher.app;

import android.app.Application;
import android.support.design.BuildConfig;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.szr.jkxsx.teacher.app.config.ImagePipelineConfigFactory;

import timber.log.Timber;

/**
 * 自定义application
 * <p/>
 * Created by zczhang on 15/10/8.
 */
public class BaseApplication extends Application {
    /**
     * 存储全局数据的引擎，可以在其中存储和读取在应用整个生命周期有效的数据
     */
    private AppDataEngine appDataEngine;
    private static BaseApplication baseApplication;

    public BaseApplication() {
        super();
        baseApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //fresco图片框架初始化
        Fresco.initialize(this, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(this));
        //---------log日志类库------支持自定义标签、默认是类名
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }

    /**
     * 获取一个全局数据引擎
     */
    public AppDataEngine getAppConfigDataEngine() {
        if (appDataEngine == null) {
            appDataEngine = new AppDataEngine(baseApplication);
        }
        return appDataEngine;
    }
}
