package com.szr.jkxsx.domain.common;

/**
 * Created by zczhang on 16/2/3.
 */
public class Constants {
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


    //关于用户角色的常量
    public static final int ROLE_TEACHER = 1;//普通老师角色
    public static final int ROLE_SUPER_USER = 2;//管理员角色
}
