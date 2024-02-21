package com.arnab.android_mysql_nodejs.network;


import com.arnab.android_mysql_nodejs.pojo.Response;

public interface ApiResponseListener {
    void onResponse(Response response);
}
