package com.example.com.application.util.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 异常处理类
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/wanAndroid/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler instance = new CrashHandler();

    public static CrashHandler getInstance() {
        return instance;
    }

    private Context mContext;

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mContext = context.getApplicationContext();
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        dumpToSdCard(e);
        uploadService(e);
        Process.killProcess(Process.myPid());
    }

    private void dumpToSdCard(Throwable e) {

        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (DEBUG) {
                Log.d(TAG, "sdcard unmounted");
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        long currentTimeMillis = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currentTimeMillis));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.print(time);
            dumpPhoneInfo(pw);
            pw.println();
            e.printStackTrace(pw);
            pw.close();
        } catch (Exception e1) {
            Log.e(TAG, "dump crash info fail");
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App version: " + packageInfo.versionName + "_" + packageInfo.versionCode);
        pw.println("OS Version:"+ Build.VERSION.RELEASE+"_"+Build.VERSION.SDK_INT);
        pw.println("Vendor:"+Build.MANUFACTURER); // 手机制造商
        pw.println("Model:"+Build.MODEL);// 手机型号
        pw.println("CPU ABI:"+Build.CPU_ABI);//CPU架构
    }

    /**
     * 上传给服务器
     *
     * @param e
     */
    private void uploadService(Throwable e) {

    }
}
