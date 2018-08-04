package com.example.akundu.android_mysql_nodejs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.pojo.Employee;

import java.util.ArrayList;

public class EmployeeListAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Employee> item;

    public EmployeeListAdapter(@NonNull Context context, int resource, ArrayList<Employee> item) {
        super(context, resource, item);
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_emp_detalis, parent, false);
        LinearLayout ll_row = convertView.findViewById(R.id.ll_row);
        if (position % 2 == 0)
            ll_row.setBackgroundColor(Color.parseColor("#E3afd7ff"));
        else
            ll_row.setBackgroundColor(Color.parseColor("#E3ffdf91"));
        TextView emp_no = (TextView) convertView.findViewById(R.id.emp_no);
        TextView birth_date = (TextView) convertView.findViewById(R.id.birth_date);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView gender = (TextView) convertView.findViewById(R.id.gender);
        TextView hire_date = (TextView) convertView.findViewById(R.id.hire_date);
        TextView dept_no = (TextView) convertView.findViewById(R.id.dept_no);
        TextView dept_name = (TextView) convertView.findViewById(R.id.dept_name);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView salary = (TextView) convertView.findViewById(R.id.salary);
        TextView Managers = (TextView) convertView.findViewById(R.id.Managers);
        emp_no.setText("" + item.get(position).getEmp_no());
        birth_date.setText(item.get(position).getBirth_date());
        name.setText(item.get(position).getName());
        gender.setText(item.get(position).getGender());
        hire_date.setText(item.get(position).getHire_date());
        dept_no.setText(item.get(position).getDept_no());
        dept_name.setText(item.get(position).getDept_name());
        title.setText(item.get(position).getTitle());
        salary.setText("" + item.get(position).getSalary());
        Managers.setText(item.get(position).getManagers());
        return convertView;
    }

    @Override
    public int getCount() {
        return item.size();
    }
}
