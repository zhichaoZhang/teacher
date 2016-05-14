package com.szr.jkxsx.teacher.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseFragment;
import com.szr.jkxsx.teacher.utils.IntentHelper;
import com.szr.jkxsx.teacher.view.activity.SingleFragmentActivity;

import java.util.concurrent.TimeUnit;

import butterknife.InjectView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * 欢迎界面
 *
 * Created by zczhang on 15/12/6.
 */
public class WelcomeFragment extends BaseFragment{
    @InjectView(R.id.dv_wel)
    SimpleDraweeView dvWel;

    private Subscription subscription;

    public static WelcomeFragment newInstance() {
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        return welcomeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    @Override
    public void initialize() {
        //延迟3秒跳转到登陆界面
        subscription = Observable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread()
        ).map(new Func1<Long, Object>() {
            @Override
            public Object call(Long aLong) {
                IntentHelper.getInstance().startLoginActivity(getContext());
                if(getContext() instanceof SingleFragmentActivity.ChildFragmentManager) {
                    ((SingleFragmentActivity.ChildFragmentManager) getContext()).closeActivity();
                }
                return null;
            }
        }).subscribe();
    }
}
