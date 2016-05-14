package com.szr.jkxsx.teacher.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseActivity;

import java.util.List;

import timber.log.Timber;

/**
 * 单一fragmment的activity容器
 * <p/>
 * Created by zcZhang on 15/4/30.
 */
public abstract class SingleFragmentActivity extends BaseActivity {
    FragmentManager fragmentManager;
    Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.rl_fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            showFragment(fragment, false, 0, 0);
        }
    }

    public void showFragment(Fragment fragment, boolean isAddBackStack, int enterAnim, int exitAnim) {
        try {
            if (fragment == null) {
                return;
            }
            Timber.i("showFragment----->" + fragment.getClass());
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (enterAnim != 0 && exitAnim != 0) {
                Timber.i("带有切换动画---------");
                transaction.setCustomAnimations(enterAnim, exitAnim);
            }
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
            transaction.add(R.id.rl_fragment_container, fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(null);
            }
            lastFragment = fragment;
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFragment(Fragment fragment, boolean isAddBackStack, int enterAnim, int exitAnim, int popIn, int popExit) {
        try {
            if (fragment == null) {
                return;
            }
            Timber.i("showFragment----->" + fragment.getClass());
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (enterAnim != 0 && exitAnim != 0 && popIn != 0 && popExit != 0) {
                Timber.i("带有切换动画---------");
                transaction.setCustomAnimations(enterAnim, exitAnim, popIn, popExit);
            } else {
                transaction.setCustomAnimations(R.anim.activity_animation_right_left, R.anim.fragments_transition_out, R.anim.fragments_transition_in, R.anim.activity_animation_left_right);
            }
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
            transaction.add(R.id.rl_fragment_container, fragment);
            if (isAddBackStack) {
                transaction.addToBackStack(null);
            }
            lastFragment = fragment;
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void back2LastFragmentIfExist() {
        fragmentManager.popBackStack();
        changeLastFragment();
    }

    protected abstract Fragment createFragment();

    public interface ChildFragmentManager {
        void changeFragment(Fragment fragment, boolean isAddBackStack, int enterAnim, int exitAnim);

        void backFragment();

        void closeActivity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            changeLastFragment();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void changeLastFragment() {
        List<Fragment> backStackFragmentList = fragmentManager.getFragments();
        if (backStackFragmentList != null) {
            int backStackSize = backStackFragmentList.size();
            if (backStackSize > 0) {
                if (backStackSize - 2 >= 0) {
                    lastFragment = backStackFragmentList.get(backStackSize - 2);
                    Timber.i("lastFragment----->" + lastFragment.getClass());
                }
            }
        }
    }
}
