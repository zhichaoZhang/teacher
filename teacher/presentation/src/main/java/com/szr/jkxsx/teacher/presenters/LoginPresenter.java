package com.szr.jkxsx.teacher.presenters;

import com.szr.jkxsx.domain.common.Constants;
import com.szr.jkxsx.domain.entities.LogonReturnInfo;
import com.szr.jkxsx.domain.exception.MyThrowableAction1;
import com.szr.jkxsx.domain.exception.RequestException;
import com.szr.jkxsx.domain.interactor.LoginUserCase;
import com.szr.jkxsx.teacher.utils.TextUtils;
import com.szr.jkxsx.teacher.viewmodel.LoginViewModel;
import com.szr.jkxsx.teacher.viewmodelmapper.LoginViewModelMapper;
import com.szr.jkxsx.teacher.views.LoginView;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * 登陆界面逻辑实现
 * <p/>
 * Created by zczhang on 15/10/8.
 */
public class LoginPresenter implements BasePresenter<LoginView> {
    private LoginView mView;
    private LoginUserCase loginUserCase;
    private Subscription subscription;
    private CompositeSubscription compositeSubscription;
    private LoginViewModelMapper loginViewModelMapper;

    public LoginPresenter(LoginUserCase loginUserCase, LoginViewModelMapper loginViewModelMapper) {
        this.loginUserCase = loginUserCase;
        this.loginViewModelMapper = loginViewModelMapper;
    }

    public void login(String userName, String userPwd) {
        if (checkLoginInfo(userName, userPwd)) {
//            mView.showLoading(mView.getContext().getString(R.string.login_toast_logining), false);
            subscription = loginUserCase.userName(userName).userPwd(userPwd)
                    .request()
                    .map(new Func1<LogonReturnInfo, LoginViewModel>() {
                        @Override
                        public LoginViewModel call(LogonReturnInfo logonReturnInfo) {
                            return loginViewModelMapper.transform(logonReturnInfo);
                        }
                    })
                    .subscribe(new Action1<LoginViewModel>() {
                        @Override
                        public void call(LoginViewModel logonReturnInfo) {
                            mView.hideLoading();
                            if (logonReturnInfo.role == Constants.ROLE_TEACHER) {
//                                IntentHelper.getInstance().startTeacherRegisterActivity(mView.getContext());
                            } else if (logonReturnInfo.role == Constants.ROLE_SUPER_USER) {
//                                IntentHelper.getInstance().startSuperManagerActivity(mView.getContext());
                            } else {
                                mView.onMessage("unknown user role");
                            }
                        }
                    }, new MyThrowableAction1<Throwable>() {
                        @Override
                        public void call(Throwable t) {
                            super.call(t);
                            Timber.i("t----->" + t.toString());
                            mView.hideLoading();
                            mView.onMessage(((RequestException) t).getErrorMsg());
                            if (t != null && t instanceof RequestException && mView != null) {
                            }
                        }
                    });
            compositeSubscription.add(subscription);
        }
    }

    @Override
    public void setView(LoginView view) {
        this.mView = view;
    }

    @Override
    public void onCreate() {
        compositeSubscription = new CompositeSubscription();

    }

    @Override
    public void onDestroy() {
        compositeSubscription.unsubscribe();
    }

    private boolean checkLoginInfo(String loginName, String password) {
        if (TextUtils.isEmpty(loginName)) {
//            mView.onMessage(mView.getContext().getString(R.string.login_toast_user_name_empty));
            return false;
        }

        if (TextUtils.isEmpty(password)) {
//            mView.onMessage(mView.getContext().getString(R.string.login_toast_password_empty));
            return false;
        }

        return true;
    }
}
