package com.szr.jkxsx.teacher.app.dependency.presenter;



import com.szr.jkxsx.teacher.presenters.LoginPresenter;
import com.szr.jkxsx.teacher.presenters.TeacherRegisterPresenter;

import dagger.Component;

/**
 * 界面控制器presenter提供组件
 * <p/>
 * Created by zcZhang on 15/6/15.
 */
@Component(modules = PresenterModule.class)
public interface PresenterComponent {

    LoginPresenter loginPresenter();

    TeacherRegisterPresenter teacherRegisterPresenter();
}
