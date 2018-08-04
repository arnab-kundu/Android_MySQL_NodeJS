package com.example.akundu.android_mysql_nodejs.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;

import com.example.akundu.android_mysql_nodejs.MyApplication;
import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.adapter.ActvAdapter;
import com.example.akundu.android_mysql_nodejs.network.API;
import com.example.akundu.android_mysql_nodejs.network.ApiResponseListener;
import com.example.akundu.android_mysql_nodejs.network.NetworkTask;
import com.example.akundu.android_mysql_nodejs.network.Parser;
import com.example.akundu.android_mysql_nodejs.pojo.Employee;
import com.example.akundu.android_mysql_nodejs.pojo.Response;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SalaryChartActivity extends AppCompatActivity implements ApiResponseListener, ActvAdapter.ItemSelectListener {

    AutoCompleteTextView actv;
    BarChart mChart;
    private ArrayList<Employee> employees;
    private ActvAdapter actvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_chart);

        employees = new ArrayList<>();

        actv = findViewById(R.id.actv);
        actvAdapter = new ActvAdapter(this, R.layout.row_actv, employees, this);
        actv.setAdapter(actvAdapter);
        actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 7 && s.length() > 0) //SP setting. I am not allowing to hit server with more than 7 latter.
                    new NetworkTask(SalaryChartActivity.this, API.EMP_NO).execute("http://" + MyApplication.serverIP + ":3000/employee?first_name=" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mChart = findViewById(R.id.salary_chart);

    }

    @Override
    public void onResponse(Response res) {
        //Log.d("msg", response);
        if (res.api_name == API.EMP_NO) {
            new Parser().parseEmployeeNameAndNo(res.response, employees);
            actvAdapter.notifyDataSetChanged();
        }
        if (res.api_name == API.EMP_SALARY) {
            ArrayList<Employee> employees = new Parser().parseEmployeeSalary(res.response);
            setData(employees);
        }
    }

    @Override
    public void getSelectedItem(Employee employee) {
        actv.setText(employee.getName());
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(actv.getWindowToken(), 0);
        new NetworkTask(SalaryChartActivity.this, API.EMP_SALARY).execute("http://" + MyApplication.serverIP + ":3000/employee?salary_chart_of_emp_no=" + employee.getEmp_no());
    }


    private void setData(ArrayList<Employee> data) {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < data.size(); i++) {
            yVals1.add(new BarEntry(i, data.get(i).getSalary()));
        }
        BarDataSet set1;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);

            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");
            set1.setDrawIcons(true);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);


            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData barData = new BarData(dataSets);
            barData.setValueTextSize(10f);

            barData.setBarWidth(0.9f);


            mChart.setData(barData);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        }
    }

}
