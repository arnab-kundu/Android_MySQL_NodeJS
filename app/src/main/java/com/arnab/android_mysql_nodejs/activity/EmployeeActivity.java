package com.arnab.android_mysql_nodejs.activity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.arnab.android_mysql_nodejs.MyApplication;
import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.adapter.EmployeeListAdapter;
import com.arnab.android_mysql_nodejs.network.API;
import com.arnab.android_mysql_nodejs.network.ApiResponseListener;
import com.arnab.android_mysql_nodejs.network.NetworkTask;
import com.arnab.android_mysql_nodejs.network.Parser;
import com.arnab.android_mysql_nodejs.pojo.Employee;
import com.arnab.android_mysql_nodejs.pojo.Response;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity implements ApiResponseListener {


    ArrayList<Employee> employees = new ArrayList<>();
    private ImageView footerView;
    private EmployeeListAdapter employeeListAdapter;
    private boolean IsLoadingFlag = false;
    private int requestCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        ListView emp_listview = findViewById(R.id.emp_listview);
        employeeListAdapter = new EmployeeListAdapter(this, R.layout.row_emp_detalis, employees);
        emp_listview.setAdapter(employeeListAdapter);
        footerView = findViewById(R.id.IVloadinganimation);
        AnimationDrawable frameAnimation = (AnimationDrawable) footerView.getBackground();
        frameAnimation.start();

        emp_listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    footerView.setVisibility(View.VISIBLE);
                }
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    if (!IsLoadingFlag) {
                        IsLoadingFlag = true;
                        new NetworkTask(EmployeeActivity.this, API.EMP_DETAILS).execute("http://" + MyApplication.serverIP + ":3000/employee?record_starting_index=" + requestCount);
                    }
                }
            }
        });
        if (isNetworkAvailable())
            new NetworkTask(this, API.EMP_DETAILS).execute("http://" + MyApplication.serverIP + ":3000/employee?record_starting_index=" + requestCount);
        else {
            //AlertDialog alertDialog = new AlertDialog(this);
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onResponse(Response response) {
        //Log.d("msg Response:", response);
        new Parser().employeeData(response.response, employees);
        employeeListAdapter.notifyDataSetChanged();
        IsLoadingFlag = false;
        requestCount += 10;
        footerView.setVisibility(View.GONE);

    }
}
