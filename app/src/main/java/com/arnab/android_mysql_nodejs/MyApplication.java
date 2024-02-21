package com.arnab.android_mysql_nodejs;

import android.app.Application;
import android.content.SharedPreferences;

public class MyApplication extends Application {
    public static String serverIP;


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        serverIP = sharedPreferences.getString("serverIP", "");
    }
}
