package com.szr.jkxsx.teacher.app.dependency.presenter;


import com.szr.jkxsx.domain.interactor.LoginUserCase;
import com.szr.jkxsx.domain.interactor.TeacherRegisterUserCase;
import com.szr.jkxsx.teacher.app.dependency.interact.InteractModule;
import com.szr.jkxsx.teacher.presenters.LoginPresenter;
import com.szr.jkxsx.teacher.presenters.TeacherRegisterPresenter;
import com.szr.jkxsx.teacher.viewmodelmapper.LoginViewModelMapper;

import dagger.Module;
import dagger.Provides;

/**
 * 界面控制器依赖
 * <p/>
 * Created by zcZhang on 15/6/12.
 */
@Module(includes = InteractModule.class)
public class PresenterModule {

    @Provides
    LoginPresenter provideLoginPresenter(LoginUserCase loginUserCase) {
        return new LoginPresenter(loginUserCase, new LoginViewModelMapper());
    }

    @Provides
    TeacherRegisterPresenter provideTeacherRegisterPresenter(TeacherRegisterUserCase teacherRegisterUserCase) {
        return new TeacherRegisterPresenter(teacherRegisterUserCase);
    }
}
