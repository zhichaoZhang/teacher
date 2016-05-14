package com.szr.jkxsx.data.service;

import com.szr.jkxsx.domain.entities.ResponseDataWrapper;
import com.szr.jkxsx.domain.entities.Student;

import retrofit.http.Field;
import retrofit.http.POST;

/**
 * 学生相关接口
 *
 * Created by zczhang on 15/10/15.
 */
public interface StudentService {

    /**
     * 添加学生
     * @return
     */
    @POST("/SizeruiManager/student/addStudent")
    ResponseDataWrapper<Student> postStudent(@Field("name") String stuName, @Field("birthday") String stuBirthday,
                                             @Field("kindergarten") String stuKindergarten, @Field("primarySchool") String stuPrimarySchool,
                                             @Field("grade") String stuGrade, @Field("parentName") String stuParentName,
                                             @Field("tel") String stuTel, @Field("weichatId") String weichatId,
                                             @Field("aoshuLearnYear") String stuAoshuLearnYear, @Field("photoUrl") String stuPhotoUrl,
                                             @Field("schoolId") String schoolId, @Field("nickname") String stuNickName);
}
