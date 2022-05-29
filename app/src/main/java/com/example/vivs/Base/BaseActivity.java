package com.example.vivs.Base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mBind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mBind = ButterKnife.bind(this);
        initview();
        initevent();
        initPresenter();
    }

    protected abstract void initPresenter() ;

    protected void initevent() {

    }
protected void setOnClickListener(){}
    protected  abstract void initview() ;

    protected abstract int getLayoutResId() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind!=null){
            mBind.unbind();
        }
        this.release();
    }

    protected void release(){

    }
}
