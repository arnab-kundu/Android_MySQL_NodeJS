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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.pojo.Car;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class CarListAdapter extends ArrayAdapter {

    private ArrayList<Car> cars;
    private Context context;

    public CarListAdapter(@NonNull Context context, int resource, ArrayList<Car> cars) {
        super(context, resource, cars);
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_car, parent, false);
        ImageView car_image = convertView.findViewById(R.id.car_image);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productLine = convertView.findViewById(R.id.productLine);
        TextView buyPrice = convertView.findViewById(R.id.buyPrice);
        TextView quantityInStock = convertView.findViewById(R.id.quantityInStock);
        TextView productDescription = convertView.findViewById(R.id.productDescription);
        productName.setText(cars.get(position).getProductName() + " (" + cars.get(position).getProductCode() + ")");
        productLine.setText(cars.get(position).getProductLine());
        buyPrice.setText("$ " + cars.get(position).getBuyPrice());
        productDescription.setText(cars.get(position).getProductDescription());
        if (cars.get(position).getQuantityInStock() < 200)
            ((TextView)convertView.findViewById(R.id.quantityInStock)).setTextColor(Color.RED);
        else
            ((TextView)convertView.findViewById(R.id.quantityInStock)).setTextColor(Color.parseColor("#32c42f"));
        quantityInStock.setText(cars.get(position).getQuantityInStock() + " Items remaining in stock.");
        Ion.with(context)
                .load(cars.get(position).getImage())
                .intoImageView(car_image);
        //Log.d("msg", cars.get(position).getImage());

        return convertView;
    }

    @Override
    public int getCount() {
        return cars.size();
    }


}
