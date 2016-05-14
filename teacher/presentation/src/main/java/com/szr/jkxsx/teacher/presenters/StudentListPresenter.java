package com.szr.jkxsx.teacher.presenters;


import com.szr.jkxsx.teacher.views.StudentListView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 学生列表业务逻辑实现
 * <p/>
 * Created by zczhang on 15/10/18.
 */
public class StudentListPresenter implements BasePresenter<StudentListView> {
    private StudentListView mView;
    private Subscription subscription;
    private CompositeSubscription compositeSubscription;

    @Override
    public void setView(StudentListView view) {
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
}
