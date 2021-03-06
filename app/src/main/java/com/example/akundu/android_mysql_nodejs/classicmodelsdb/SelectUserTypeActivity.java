package com.example.akundu.android_mysql_nodejs.classicmodelsdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.SplashTestActivity;
import com.example.akundu.android_mysql_nodejs.TestActivity;

public class SelectUserTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
    }

    public void employee_login(View view) {
        startActivity(new Intent(this, EmployeeDashboardActivity.class));
    }

    public void customer_login(View view) {
        //Toast.makeText(this, "Work in progress", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TestActivity.class));
    }

    public void splash(View view) {
        startActivity(new Intent(this,SplashTestActivity.class));
    }
}
