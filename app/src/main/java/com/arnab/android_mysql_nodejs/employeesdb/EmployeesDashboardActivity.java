package com.arnab.android_mysql_nodejs.employeesdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.activity.EmployeeActivity;

public class EmployeesDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_dashboard);
    }

    public void Employee(View view) {
        startActivity(new Intent(this, EmployeeActivity.class));
    }

    public void DepartmentFilter(View view) {
        startActivity(new Intent(this, EmployeeByDepartmentsActivity.class));
    }
}
