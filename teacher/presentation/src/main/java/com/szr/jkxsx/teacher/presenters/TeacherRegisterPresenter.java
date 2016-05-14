package com.szr.jkxsx.teacher.presenters;

import com.szr.jkxsx.domain.exception.MyThrowableAction1;
import com.szr.jkxsx.domain.exception.RequestException;
import com.szr.jkxsx.domain.interactor.TeacherRegisterUserCase;
import com.szr.jkxsx.teacher.utils.TextUtils;
import com.szr.jkxsx.teacher.views.TeacherRegisterView;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 教师注册业务逻辑实现
 *
 * Created by zczhang on 15/10/17.
 */
public class TeacherRegisterPresenter implements BasePresenter<TeacherRegisterView> {
    private TeacherRegisterView mView;
    private TeacherRegisterUserCase teacherRegisterUserCase;
    private Subscription subscription;
    private CompositeSubscription compositeSubscription;

    public TeacherRegisterPresenter(TeacherRegisterUserCase teacherRegisterUserCase) {
        this.teacherRegisterUserCase = teacherRegisterUserCase;
    }

    public void register(String teacherName, String loginName, String password, String confirmPassword,
                         final String mobile, String identity, String avatar, int sex) {
        if(checkRegisterInfo(teacherName, loginName, password, confirmPassword, mobile, identity, avatar, sex)) {
            mView.showLoading("正在提交注册信息,请稍后!", false);
            subscription = teacherRegisterUserCase.teacherName(teacherName)
                    .loginName(loginName).password(password).mobile(mobile).identity(identity).avatar(avatar).sex(sex)
                    .request()
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            mView.hideLoading();
                            if(aBoolean) {
                                mView.onMessage("注册成功!");

                            }
                        }
                    }, new MyThrowableAction1<Throwable>() {
                        @Override
                        public void call(Throwable t) {
                            super.call(t);
                            mView.hideLoading();
                            if(t != null && t instanceof RequestException) {
                                mView.onMessage(((RequestException)t).getErrorMsg());
                            }
                        }
                    });
            compositeSubscription.add(subscription);
        }

    }

    @Override
    public void setView(TeacherRegisterView view) {
        this.mView = view;
    }

    @Override
    public void onCreate() {
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        compositeSubscription.unsubscribe();
        compositeSubscription = null;
    }

    private boolean checkRegisterInfo(String teacherName, String loginName, String password, String confirmPassword,
                                      String mobile, String identity, String avatar, int sex) {
        if(TextUtils.isEmpty(teacherName)) {
            mView.onMessage("教师姓名不能为空!");
            return false;
        }

        if(TextUtils.isEmpty(loginName)) {
            mView.onMessage("登陆用户名称不能为空!");
            return false;
        }

        if(TextUtils.isEmpty(password)) {
            mView.onMessage("登陆密码不能为空!");
            return false;
        }

        if(TextUtils.isEmpty(confirmPassword)) {
            mView.onMessage("请输入确认密码!");
            return false;
        }

        if(!password.equals(confirmPassword)) {
            mView.onMessage("两次密码输入不一致,请重新输入!");
            return false;
        }

        if(TextUtils.isEmpty(mobile)) {
            mView.onMessage("手机号不能为空!");
            return false;
        }

        if(!mobile.startsWith("1")) {
            mView.onMessage("请输入合法的手机号!");
            return false;
        }

        if(TextUtils.isEmpty(identity)) {
            mView.onMessage("身份证号不能为空!");
            return false;
        }

        if(TextUtils.isEmpty(avatar)) {
            mView.onMessage("请选择用户头像!");
            return true;
        }

        return true;
    }
}
