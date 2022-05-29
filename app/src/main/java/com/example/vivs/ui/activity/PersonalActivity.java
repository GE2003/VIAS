package com.example.vivs.ui.activity;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import com.example.vivs.Base.BaseActivity;
import com.example.vivs.R;

public class PersonalActivity extends BaseActivity {
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initview() {

    }

    @Override
    protected int getLayoutResId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
        getWindow().setEnterTransition(transition);
        return R.layout.activity_personal;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
