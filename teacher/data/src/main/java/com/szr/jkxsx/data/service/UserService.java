package com.szr.jkxsx.data.service;

import com.szr.jkxsx.domain.entities.ResponseDataWrapper;

import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * 用户相关网络接口
 *
 * Created by zczhang on 15/10/11.
 */
public interface UserService {

    /**
     * 用户登陆接口
     * @param userName
     * @param userPwd
     * @return
     */
    @FormUrlEncoded
    @POST("/SizeruiManager/login/teacherLogin")
    Response userLogin(@Field("id") String userName, @Field("password") String userPwd);

    /**
     * 教师注册接口
     * @param teacherName
     * @param loginName
     * @param password
     * @param mobile
     * @param avatar
     * @param identity
     * @param sex
     * @return
     */
    @FormUrlEncoded
    @POST("/sizerui_manager/teacher/teacher_register")
    ResponseDataWrapper teacherRegister(@Field("name") String teacherName, @Field("user_name") String loginName,
                                        @Field("password") String password, @Field("mobile") String mobile,
                                        @Field("avatar") String avatar, @Field("identity") String identity,
                                        @Field("sex") int sex);
}
