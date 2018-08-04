package com.example.akundu.android_mysql_nodejs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.akundu.android_mysql_nodejs.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void Employee(View view) {
        startActivity(new Intent(this, EmployeeActivity.class));
    }

    public void SalaryChart(View view) {
        startActivity(new Intent(this, SalaryChartActivity.class));
    }

    public void Country(View view) {
        startActivity(new Intent(this, CountryActivity.class));
    }

    public void Car(View view) {
        startActivity(new Intent(this, CarActivity.class));
    }

    public void AddVehicle(View view) {
        startActivity(new Intent(this, AddVehicleActivity.class));
    }
}
