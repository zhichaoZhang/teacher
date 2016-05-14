package com.szr.jkxsx.teacher.viewmodelmapper;


import com.szr.jkxsx.domain.entities.LogonReturnInfo;
import com.szr.jkxsx.teacher.viewmodel.LoginViewModel;

/**
 * Created by zczhang on 16/2/3.
 */
public class LoginViewModelMapper implements BaseViewModelMapper<LogonReturnInfo, LoginViewModel> {

    @Override
    public LoginViewModel transform(LogonReturnInfo domainModel) {
        return null;
    }
}
