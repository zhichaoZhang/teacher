package com.szr.jkxsx.teacher.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.view.fragment.CommonWebFragment;

import timber.log.Timber;

/**
 * 通用webview
 * <p/>
 * Created by zcZhang on 15/4/30.
 */
public class CommonWebViewActivity extends SingleFragmentActivity implements SingleFragmentActivity.ChildFragmentManager {
    private CommonWebFragment commonWebFragment;
    private String url;
    private boolean isCloseAllActivity = false;
    private int enterAnim = 0;
    private int exitAnim = 0;

    public static Intent getCallingIntent(Context context, String url) {
        Intent intent = new Intent();
        intent.setClass(context, CommonWebViewActivity.class);
        intent.putExtra(CommonWebFragment.KEY_ARGU_URL, url);
        return intent;
    }

    /**
     * @param context
     * @param url
     * @param isCloseAllActivity
     * @return
     */
    public static Intent getCallingIntent(Context context, String url, boolean isCloseAllActivity) {
        Intent intent = new Intent();
        intent.setClass(context, CommonWebViewActivity.class);
        intent.putExtra(CommonWebFragment.KEY_ARGU_URL, url);
        intent.putExtra("is_close_all_activity", isCloseAllActivity);
        return intent;
    }

    public static Intent getCallingIntent(Context context, String url, boolean isCloseAllActivity, int enterAnim, int exitAnim) {
        Intent intent = new Intent();
        intent.setClass(context, CommonWebViewActivity.class);
        intent.putExtra(CommonWebFragment.KEY_ARGU_URL, url);
        intent.putExtra("is_close_all_activity", isCloseAllActivity);
        intent.putExtra("enter_anim", enterAnim);
        intent.putExtra("exit_anim", exitAnim);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            url = getIntent().getStringExtra(CommonWebFragment.KEY_ARGU_URL);
        } catch (Exception e) {
            Toast.makeText(this, "系统错误", Toast.LENGTH_SHORT).show();
            finish();
        }
        isCloseAllActivity = getIntent().getBooleanExtra("is_close_all_activity", false);
        enterAnim = getIntent().getIntExtra("enter_anim", 0);
        exitAnim = getIntent().getIntExtra("exit_anim", 0);
        if (exitAnim != 0 && enterAnim != 0) {
            overridePendingTransition(enterAnim, R.anim.fragments_transition_out);
        }
        Timber.i("跳转链接------->" + url);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        if (exitAnim != 0 && enterAnim != 0) {
            overridePendingTransition(R.anim.fragments_transition_in, exitAnim);
        }
    }

    @Override
    protected Fragment createFragment() {
        commonWebFragment = CommonWebFragment.newInstance(url, isCloseAllActivity);
        return commonWebFragment;
    }

    @Override
    public void changeFragment(Fragment fragment, boolean isAddBackStack, int enterAnim, int exitAnim) {
        showFragment(fragment, isAddBackStack, enterAnim, exitAnim);
    }

    @Override
    public void backFragment() {
        back2LastFragmentIfExist();
    }

    @Override
    public void closeActivity() {
        this.finish();
    }
}
