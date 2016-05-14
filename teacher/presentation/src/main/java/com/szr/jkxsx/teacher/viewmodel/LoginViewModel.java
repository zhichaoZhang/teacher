package com.szr.jkxsx.teacher.viewmodel;

/**
 * Created by zczhang on 16/2/3.
 */
public class LoginViewModel extends BaseViewModel {
    public String jsessionid;//session id
    public int role;//用户角色

    @Override
    public String toString() {
        return "LoginViewModel{" +
                "jsessionid='" + jsessionid + '\'' +
                ", role=" + role +
                '}';
    }
}
