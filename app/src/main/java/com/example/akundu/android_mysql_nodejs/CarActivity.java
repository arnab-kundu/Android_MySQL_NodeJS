package com.example.akundu.android_mysql_nodejs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.akundu.android_mysql_nodejs.adapter.CarListAdapter;
import com.example.akundu.android_mysql_nodejs.network.API;
import com.example.akundu.android_mysql_nodejs.network.ApiResponseListener;
import com.example.akundu.android_mysql_nodejs.network.NetworkTask;
import com.example.akundu.android_mysql_nodejs.network.Parser;
import com.example.akundu.android_mysql_nodejs.pojo.Car;
import com.example.akundu.android_mysql_nodejs.pojo.Response;
import com.example.akundu.android_mysql_nodejs.R;

import java.util.ArrayList;

public class CarActivity extends AppCompatActivity implements ApiResponseListener {

    ListView car_listView;
    ArrayList<Car> cars = new ArrayList<>();
    CarListAdapter carListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        car_listView = findViewById(R.id.car_listView);
        carListAdapter = new CarListAdapter(this, R.layout.row_car, cars);
        car_listView.setAdapter(carListAdapter);
        new NetworkTask(this, API.CAR).execute("http://" + MyApplication.serverIP + ":3000/cars");
    }

    @Override
    public void onResponse(Response response) {
        new Parser().parseCarData(cars,response.response);
        carListAdapter.notifyDataSetChanged();
    }
}
