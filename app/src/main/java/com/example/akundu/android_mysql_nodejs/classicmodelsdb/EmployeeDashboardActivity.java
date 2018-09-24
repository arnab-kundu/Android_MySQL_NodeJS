package com.example.akundu.android_mysql_nodejs.classicmodelsdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.classicmodelsdb.employee.CustomerListActivity;
import com.example.akundu.android_mysql_nodejs.network.Header;

import java.util.ArrayList;

public class EmployeeDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);


        ArrayList<Header> headers = new ArrayList<>();

        headers.add(new Header("", ""));
        ArrayList<String[]> arrayList = new ArrayList<>();
        arrayList.add(new String[]{"Arnab", "Kundu"});
        arrayList.add(new String[]{"Prime", "aasdasds"});
        arrayList.add(new String[]{"asd", "asas"});

        for (String[] arrayLists :
                arrayList) {
            Log.d("msg", arrayLists[0] + " " + arrayLists[1]);
        }

        for (Header header :
                headers) {
            Log.d("msg", header.key + " " + header.value);
        }
    }

    public void customer_list(View view) {
        startActivity(new Intent(this, CustomerListActivity.class));
    }
}
