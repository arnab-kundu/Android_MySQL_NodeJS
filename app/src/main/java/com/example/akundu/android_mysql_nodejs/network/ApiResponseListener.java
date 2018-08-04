package com.example.akundu.android_mysql_nodejs.network;

import com.example.akundu.android_mysql_nodejs.activity.AddVehicleActivity;
import com.example.akundu.android_mysql_nodejs.pojo.Response;

import org.jetbrains.annotations.NotNull;

public interface ApiResponseListener {
    void onResponse(Response response);
}
