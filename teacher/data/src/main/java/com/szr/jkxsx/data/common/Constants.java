package com.szr.jkxsx.data.common;


import java.io.IOException;

/**
 * 常量类
 *
 * Created by zcZhang on 3/3/15.
 */
public class Constants {
    /**
     * 整个app用到的数据存储sharedpreference的名称
     */
    public static final String APP_PREFERENCE_NAME = "jkxsx_teacher";

    public static final String API_DOMAIN_JKXSX = "http://120.26.215.30:9090";

    /**
     * 网络请求成功返回码
     */
    public static final String NET_CODE_SUCCESS = "0000";

    /**
     * 网络请求返回参数错误
     */
    public static final String NET_CODE_RESPONSE_PRAM_ERROR = "9999";

    public static final String NET_CODE_RESPONSE_PRAM_ERROR_MSG = "response param error";

    /**
     * 用户未登录错误返回码
     */
    public static final String SESSION_ERROR_CODE = "2002";
    /**
     * 通用分页请求每页数量
     */
    public static final int PAGE_SIZE = 10;

    /**
     * 对于行高较小的列表需要每页请求20条数据
     */
    public static final int PAGE_SIZE_20 = 20;

    /**
     * 缓存文件路径
     */
    public static final String CACHE_FILE_PATH = "/jkxsx_tearcher/cache/";

    /**
     * 缓存的消息文件名称
     */
    public static final String CACHE_FILE_NAME_MSG = "msg.temp";
    public static final String CACHE_FILE_NAME_COMPANIES = "companies.temp";
    public static final String CACHE_FILE_NAME_DISCOUNTSHOPS = "discountshops.temp";

    public static final int SEX_MAN = 0;//男性
    public static final int SEX_WOMAN = 1;//女性

    //关于用户角色的常量
    public static final int ROLE_TEACHER = 1;//普通老师角色
    public static final int ROLE_SUPER_USER = 2;//管理员角色

    //---------------------------------app内空页面及错误提示文案----------------------------------------------------------
    /**
     * 网络问题无法访问
     * An {@link IOException} occurred while communicating to the server.
     */
    public static final String INTERNET_ERROR = "网路连接失败，请检查网络设置！";

    /**
     * 接口访问异常 数据格式有问题
     * An exception was thrown while (de)serializing a body
     */
    public static final String CONVERSION_ERROR = "数据开小差了，再刷新试试看！";

    /**
     * 后台服务有问题
     * A non-200 HTTP status code was received from the server.
     */
    public static final String SERVER_ERROR = "有点小问题，不要着急，程序员哥哥正在抢修！";
}
