package com.example.com.application.base;

import android.app.Application;

import com.example.com.application.util.log.CrashHandler;
import com.example.com.application.util.log.LogcatHelper;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        CrashHandler.getInstance().init(this);
        LogcatHelper.getInstance(this).start();
    }

    public static synchronized MyApplication getInstance(){return myApplication;}
}
