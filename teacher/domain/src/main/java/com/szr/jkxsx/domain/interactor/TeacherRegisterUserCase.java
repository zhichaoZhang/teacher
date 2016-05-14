package com.szr.jkxsx.domain.interactor;


import com.szr.jkxsx.domain.UserRepository;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 教师注册交互器
 * <p/>
 * Created by zczhang on 15/10/15.
 */
public class TeacherRegisterUserCase implements BaseUserCase<Boolean> {
    private UserRepository userRepository;
    private String teacherName;
    private String loginName;
    private String password;
    private String mobile;
    private String identity;
    private String avatar;
    private int sex;

    public TeacherRegisterUserCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TeacherRegisterUserCase teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public TeacherRegisterUserCase loginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public TeacherRegisterUserCase password(String password) {
        this.password = password;
        return this;
    }

    public TeacherRegisterUserCase mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public TeacherRegisterUserCase identity(String identity) {
        this.identity = identity;
        return this;
    }

    public TeacherRegisterUserCase avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public TeacherRegisterUserCase sex(int sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public Observable<Boolean> request() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(userRepository.teacherRegister(teacherName, loginName,
                        password, mobile, avatar, identity, sex));
                subscriber.onCompleted();
            }
        });
    }
}
