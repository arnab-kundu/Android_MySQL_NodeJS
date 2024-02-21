package com.arnab.android_mysql_nodejs.pojo;


import com.arnab.android_mysql_nodejs.network.API;

public class Response {
    public String response;
    public API api_name;

    public Response(String response, API api_name) {
        this.response = response;
        this.api_name = api_name;
    }
}
