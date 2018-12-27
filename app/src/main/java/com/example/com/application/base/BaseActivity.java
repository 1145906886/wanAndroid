package com.example.com.application.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.com.application.util.davik.AppDavikActivityUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private AppDavikActivityUtil appDavikActivityUtil = new AppDavikActivityUtil();
    protected BaseActivity activity;
    public MyApplication context;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        appDavikActivityUtil.addActivity(this);
        context = MyApplication.getInstance();
        activity = this;
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        appDavikActivityUtil.removeActivity(this);
        unbinder.unbind();
        super.onDestroy();
    }
}
