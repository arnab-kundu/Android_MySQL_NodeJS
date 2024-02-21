package com.arnab.android_mysql_nodejs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.pojo.Country;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Country> item;

    public ListAdapter(@NonNull Context context, int resource, ArrayList<Country> item) {
        super(context, resource, item);
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row, parent,false);
        TextView countryid = (TextView) convertView.findViewById(R.id.countryid);
        TextView countrycode = (TextView) convertView.findViewById(R.id.countrycode);
        TextView englishname = (TextView) convertView.findViewById(R.id.englishname);
        TextView frenchname = (TextView) convertView.findViewById(R.id.frenchname);
        countryid.setText(""+item.get(position).getCountryId());
        countrycode.setText(item.get(position).getCountryCode());
        englishname.setText(item.get(position).getEnglishname());
        frenchname.setText(item.get(position).getFrenchname());
        return convertView;
    }

    @Override
    public int getCount() {
        return item.size();
    }
}
