package com.szr.jkxsx.domain.interactor;

import com.szr.jkxsx.domain.UserRepository;
import com.szr.jkxsx.domain.entities.LogonReturnInfo;

import rx.Observable;
import rx.Subscriber;

/**
 * 用户登陆交互器
 *
 * Created by zczhang on 15/10/11.
 */
public class LoginUserCase implements BaseUserCase<LogonReturnInfo> {
    private String userName;
    private String userPwd;
    private UserRepository userRepository;

    public LoginUserCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginUserCase userName(String userName) {
        this.userName = userName;
        return this;
    }

    public LoginUserCase userPwd(String userPwd) {
        this.userPwd = userPwd;
        return this;
    }

    @Override
    public Observable<LogonReturnInfo> request() {
        return Observable.create(new Observable.OnSubscribe<LogonReturnInfo>() {
            @Override
            public void call(Subscriber<? super LogonReturnInfo> subscriber) {
                subscriber.onNext(userRepository.login(userName, userPwd));
                subscriber.onCompleted();
            }
        });
    }
}
