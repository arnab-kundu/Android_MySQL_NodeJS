package com.arnab.android_mysql_nodejs.employeesdb;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.arnab.android_mysql_nodejs.MyApplication;
import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.adapter.EmployeeListAdapter;
import com.arnab.android_mysql_nodejs.employeesdb.pojo.Department;
import com.arnab.android_mysql_nodejs.network.API;
import com.arnab.android_mysql_nodejs.network.ApiResponseListener;
import com.arnab.android_mysql_nodejs.network.NetworkTask;
import com.arnab.android_mysql_nodejs.network.Parser;
import com.arnab.android_mysql_nodejs.pojo.Employee;
import com.arnab.android_mysql_nodejs.pojo.Response;

import java.util.ArrayList;

public class EmployeeByDepartmentsActivity extends AppCompatActivity implements ApiResponseListener {

    Spinner spinner_employee_department;
    ListView listView_employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_by_departments);

        spinner_employee_department = findViewById(R.id.spinner_employee_department);
        listView_employee = findViewById(R.id.listView_employee);

        String name1 = "Arnab Kundu";
        name1 = name1.replaceAll("\\s","");
        StringBuilder name = new StringBuilder(name1);

        for (int i = 0; i < name.length(); i++) {
            String ch = String.valueOf(name.charAt(i));
            int count=1;
            for(int j=i+1;j<name.length();j++){
                if(ch.equalsIgnoreCase(String.valueOf(name.charAt(j)))) {
                    count++;
                    name.deleteCharAt(j);
                }
            }
            Log.d("msg1",""+ch+"-"+count);
        }

        new NetworkTask(this, API.EMP_DEPT).execute("http://" + MyApplication.serverIP + ":3000/employeesDB/get_employee_departments");
    }

    @Override
    public void onResponse(Response response) {
        if (response.api_name == API.EMP_DEPT) {
            //Log.d("msg", response.response);
            final ArrayList<Department> departments = new Parser().getDepartments(response.response);
            ArrayList<String> list = new ArrayList<>();

            for (int i = 0; i < departments.size(); i++) {
                list.add(departments.get(i).dept_name);
            }
            spinner_employee_department.setAdapter(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list));
            spinner_employee_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    new NetworkTask(EmployeeByDepartmentsActivity.this, API.EMP_BY_DEPT).execute("http://" + MyApplication.serverIP + ":3000/employeesDB/filter_employees_by_department?dept_no=" + departments.get(position).dept_no);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        if (response.api_name == API.EMP_BY_DEPT) {
            //Log.d("msg", response.response);

            ArrayList<Employee> employees = new ArrayList<>();
            employees = new Parser().getEmployees(response.response);
            EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(this, R.layout.row_emp_detalis, employees);
            listView_employee.setAdapter(employeeListAdapter);

        }
    }
}