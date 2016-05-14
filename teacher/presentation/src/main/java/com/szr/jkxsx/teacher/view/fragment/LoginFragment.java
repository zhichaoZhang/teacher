package com.szr.jkxsx.teacher.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseFragment;
import com.szr.jkxsx.teacher.app.dependency.presenter.DaggerPresenterComponent;
import com.szr.jkxsx.teacher.presenters.LoginPresenter;
import com.szr.jkxsx.teacher.utils.IntentHelper;
import com.szr.jkxsx.teacher.views.LoginView;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * 登陆界面
 * <p/>
 * Created by zczhang on 15/10/11.
 */
public class LoginFragment extends BaseFragment implements LoginView {
    @InjectView(R.id.et_login_name)
    EditText etLoginName;//用户名输入框
    @InjectView(R.id.et_password)
    EditText etPassword;//密码输入框
    @InjectView(R.id.iv_owl)
    ImageView ivOwl;//猫头鹰图片
    private LoginPresenter mPresenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initialize() {
        mPresenter = DaggerPresenterComponent.builder().build().loginPresenter();
        mPresenter.setView(this);
        mPresenter.onCreate();
    }

    @OnClick(R.id.bt_login)
    void clickLoginBtn() {
        IntentHelper.getInstance().startStudentsActivity(getContext());
        mPresenter.login(etLoginName.getText().toString(), etPassword.getText().toString());
    }

    @OnFocusChange(R.id.et_login_name)
    void onUnFocusChanged(boolean focused) {
        if(focused) {
            ivOwl.setImageResource(R.drawable.img_login_un_inputting);
        }
    }

    @OnFocusChange(R.id.et_password)
    void onPwFocusChanged(boolean focused) {
        if(focused) {
            ivOwl.setImageResource(R.drawable.img_login_pw_inputting);
        }
    }

    @OnClick(R.id.tv_login_forget_password)
    void clickForgetPwd() {
        IntentHelper.getInstance().startSuperManagerActivity(getContext());
    }
}
