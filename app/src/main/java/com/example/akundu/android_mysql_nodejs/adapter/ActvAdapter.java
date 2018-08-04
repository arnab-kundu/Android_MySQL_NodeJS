package com.example.akundu.android_mysql_nodejs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class ActvAdapter extends ArrayAdapter {

    private ArrayList<Employee> employees;
    private Context context;

    List<Employee> mList, filteredPeople, mListAll;
    ItemSelectListener itemSelectListener;

    public ActvAdapter(@NonNull Context context, int resource, ArrayList<Employee> employees, ItemSelectListener itemSelectListener) {
        super(context, resource, employees);
        this.context = context;
        this.employees = employees;
        this.itemSelectListener = itemSelectListener;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return employees.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_actv, parent, false);
        LinearLayout ll_row = convertView.findViewById(R.id.ll_row);
        if (position % 2 == 0)
            ll_row.setBackgroundColor(Color.parseColor("#afd7ff"));

        TextView tv_emp_name = convertView.findViewById(R.id.tv_emp_name);
        TextView tv_emp_id = convertView.findViewById(R.id.tv_emp_id);
        tv_emp_name.setText(employees.get(position).getName());
        tv_emp_id.setText("ID: " + employees.get(position).getEmp_no());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("msg", employees.get(position).getEmp_no() + "");
                itemSelectListener.getSelectedItem(employees.get(position));
            }
        });
        return convertView;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }


    Filter nameFilter = new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Employee> filteredList = (List<Employee>) results.values;

            if (results != null && results.count > 0) {
                clear();
                for (Employee people : filteredList) {
                    add(people);
                }
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                filteredPeople.clear();
                for (Employee people : mListAll) {
                    if (people.getName().contains(constraint)) {
                        filteredPeople.add(people);
                    }
                }
                filterResults.values = filteredPeople;
                filterResults.count = filteredPeople.size();
            }
            return filterResults;
        }
    };

    public interface ItemSelectListener {
        void getSelectedItem(Employee employee);
    }
}
