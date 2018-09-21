package com.example.akundu.android_mysql_nodejs.classicmodelsdb.employee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.akundu.android_mysql_nodejs.MyApplication;
import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.classicmodelsdb.adapter.CustomerOrderListAdapter;
import com.example.akundu.android_mysql_nodejs.network.ApiResponseListener;
import com.example.akundu.android_mysql_nodejs.network.NetworkTask;
import com.example.akundu.android_mysql_nodejs.pojo.CustomerOrder;
import com.example.akundu.android_mysql_nodejs.pojo.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.akundu.android_mysql_nodejs.network.API.SHOW_SELECTED_CUSTOMER_ORDER_DETAILS;
import static com.example.akundu.android_mysql_nodejs.network.RequestType.GET;

public class CustomerOrdersActivity extends AppCompatActivity implements ApiResponseListener {

    RecyclerView customerOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        customerOrderList = findViewById(R.id.customerOrderList);
        Bundle bundle = getIntent().getExtras();
        int customerNumber = bundle.getInt("customerNumber");
        new NetworkTask(this, SHOW_SELECTED_CUSTOMER_ORDER_DETAILS, GET).execute("http://" + MyApplication.serverIP + ":3000/classicmodels/login_as_employee/showSelectedCustomerOrderDetails?customerNumber=" + customerNumber);
    }

    @Override
    public void onResponse(Response response) {
        String stringResponse = response.response;
        ArrayList<CustomerOrder> customerOrders = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(stringResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int orderNumber = jsonObject.optInt("orderNumber");
                String productCode = jsonObject.optString("productCode");
                int quantityOrdered = jsonObject.optInt("quantityOrdered");
                double priceEach = jsonObject.optDouble("priceEach");
                int orderLineNumber = jsonObject.optInt("orderLineNumber");
                String productName = jsonObject.optString("productName");
                String productLine = jsonObject.optString("productLine");
                String productVendor = jsonObject.optString("productVendor");
                String image = jsonObject.optString("image");

                CustomerOrder customerOrder = new CustomerOrder(
                        orderNumber, productCode, quantityOrdered, priceEach, orderLineNumber,
                        productName, productLine, productVendor, image
                );

                customerOrders.add(customerOrder);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomerOrderListAdapter customerOrderListAdapter = new CustomerOrderListAdapter(this, customerOrders);
        customerOrderList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        customerOrderList.setAdapter(customerOrderListAdapter);
    }
}
