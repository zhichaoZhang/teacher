# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/joye/develop/android/tools/sdk/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 混淆的初始化配置
-optimizationpasses 7
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# 忽略所有的警告
-ignorewarnings

# 不混淆 Android 组件
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * implements java.io.Serializable {*;}
-keep class **.R$* { *;}


-dontwarn android.support.**
-dontwarn com.android.support.**
-keep class android.support.v7.** { *; }
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


# Retrofit, OkHttp, Gson
-keepattributes *Annotation*
-keepattributes Signature
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn rx.**
-keep class rx.** { *; }
-dontwarn rx.android.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class com.google.gson.** { *;}
-dontwarn com.google.gson.**

-keep class dagger.** { *;}
-dontwarn dagger.**


-keep class timber.log.** { *; }

-keep class com.facebook.** { *; }
-keep interface com.facebook.** { *; }
-dontwarn com.facebook.**

-keep class jp.wasabeef.recyclerview.** { *; }
-keep interface jp.wasabeef.recyclerview.** { *; }
-dontwarn jp.wasabeef.recyclerview.**

-keep class android.support.annotation.** { *; }
-keep interface android.support.annotation.** { *; }
-dontwarn android.support.annotation.**

-keep class okio.** { *; }
-keep interface okio.** { *; }
-dontwarn okio.**

-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-keep class com.nineoldandroids.** { *; }
-keep interface com.nineoldandroids.** { *; }
-dontwarn com.nineoldandroids.**

-keep class javax.inject.** { *; }
-keep interface javax.inject.** { *; }
-dontwarn javax.inject.**

-keep class javax.annotation.** { *; }
-keep interface javax.annotation.** { *; }
-dontwarn javax.annotation.**

-keep class bolts.** { *; }
-keep interface bolts.** { *; }
-dontwarn bolts.**

 #oneapm 性能监控
-dontwarn org.apache.commons.**
-keep class org.apache.http.impl.client.**
-dontwarn org.apache.commons.**
-keep class com.blueware.** { *; }
-dontwarn com.blueware.**
-keepattributes Exceptions, Signature, InnerClasses

 #个推
-dontwarn com.igexin.**
-keep class com.igexin.**{*;}

# 微信登陆 用到
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}

# 友盟统计用到
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
 # butterknife用到
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

 # 接口返回数据model类不混淆
 -dontwarn com.qfpay.near.data.service.json.**
 -keep class com.qfpay.near.data.service.json.**{*;}

 # EventBus用到
 -keep class org.simple.** { *; }
 -keep interface org.simple.** { *; }
 -keepclassmembers class * {
     @org.simple.eventbus.Subscriber <methods>;
 }

 # LeakCanary
 -keep class org.eclipse.mat.** { *; }
 -keep class com.squareup.leakcanary.** { *; }

 # 保留class文件中的调试信息
 -renamesourcefileattribute SourceFile
 -keepattributes SourceFile,LineNumberTable

 # qq分享 用到
 -dontwarn com.tencent.**
 -keep class com.tencent.** {*;}