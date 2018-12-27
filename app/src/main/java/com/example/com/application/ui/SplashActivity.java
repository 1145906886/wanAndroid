package com.example.com.application.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.com.application.R;
import com.example.com.application.base.BaseActivity;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
