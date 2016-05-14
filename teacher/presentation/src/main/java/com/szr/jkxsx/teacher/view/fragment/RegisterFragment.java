package com.szr.jkxsx.teacher.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.szr.jkxsx.data.common.Constants;
import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseFragment;
import com.szr.jkxsx.teacher.app.dependency.presenter.DaggerPresenterComponent;
import com.szr.jkxsx.teacher.presenters.TeacherRegisterPresenter;
import com.szr.jkxsx.teacher.view.activity.SingleFragmentActivity;
import com.szr.jkxsx.teacher.views.TeacherRegisterView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 教师注册界面
 * <p/>
 * Created by zczhang on 15/10/15.
 */
public class RegisterFragment extends BaseFragment implements TeacherRegisterView {
    @InjectView(R.id.et_teacher_name)
    EditText etTeacherName;
    @InjectView(R.id.et_login_name)
    EditText etLoginName;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @InjectView(R.id.et_identity)
    EditText etIdentity;
    @InjectView(R.id.et_mobile)
    EditText etMobile;
    @InjectView(R.id.rg_sex)
    RadioGroup rgSex;
    private TeacherRegisterPresenter mPresenter;
    private int sex = Constants.SEX_MAN;//性别,默认为0

    public static RegisterFragment newInstance() {
        RegisterFragment registerFragment = new RegisterFragment();
        return registerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_register, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void initialize() {

    }

    private void init() {
        mPresenter = DaggerPresenterComponent.builder().build().teacherRegisterPresenter();
        mPresenter.setView(this);
        mPresenter.onCreate();
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rt_man:
                        sex = Constants.SEX_MAN;
                        break;
                    case R.id.rt_woman:
                        sex = Constants.SEX_WOMAN;
                        break;
                }
            }
        });
    }

    @OnClick(R.id.btn_confirm)
    void clickConfirmBtn() {
        mPresenter.register(etTeacherName.getText().toString(),etLoginName.getText().toString(),
                etPassword.getText().toString(),etConfirmPassword.getText().toString(), etMobile.getText().toString(),
                etIdentity.getText().toString(), "", sex);
    }

    @Override
    public void closeActivity() {
        if(getContext() instanceof SingleFragmentActivity.ChildFragmentManager) {
            ((SingleFragmentActivity.ChildFragmentManager)getContext()).closeActivity();
        }
    }
}
