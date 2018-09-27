package com.example.akundu.android_mysql_nodejs.classicmodelsdb.employee;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.akundu.android_mysql_nodejs.MyApplication;
import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.classicmodelsdb.adapter.FoldingCellListAdapter;
import com.example.akundu.android_mysql_nodejs.classicmodelsdb.pojo.Customer;
import com.example.akundu.android_mysql_nodejs.network.ApiResponseListener;
import com.example.akundu.android_mysql_nodejs.network.NetworkTask;
import com.example.akundu.android_mysql_nodejs.pojo.Response;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.akundu.android_mysql_nodejs.network.API.SHOW_ALL_CUSTOMER;

public class CustomerListActivity extends AppCompatActivity implements ApiResponseListener {

    //RecyclerView customerList;
    ListView customerList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        customerList = findViewById(R.id.customerList);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        new NetworkTask(this, SHOW_ALL_CUSTOMER).execute("http://" + MyApplication.serverIP + ":3000/classicmodels/login_as_employee/showAllCustomer");
    }

    @Override
    public void onResponse(Response response) {
        String stringResponse = response.response;
        List<Customer> customers = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(stringResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                Customer customer = new Customer();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                customer.customerNumber = jsonObject.optInt("customerNumber");
                customer.customerName = jsonObject.optString("customerName");
                customer.contactLastName = jsonObject.optString("contactLastName");
                customer.contactFirstName = jsonObject.optString("contactFirstName");
                customer.phone = jsonObject.optString("phone");
                customer.addressLine1 = jsonObject.optString("addressLine1");
                customer.addressLine2 = jsonObject.optString("addressLine2");
                customer.city = jsonObject.optString("city");
                customer.state = jsonObject.optString("state");
                customer.postalCode = jsonObject.optString("postalCode");
                customer.country = jsonObject.optString("country");
                customer.salesRepEmployeeNumber = jsonObject.optInt("salesRepEmployeeNumber");
                customer.creditLimit = jsonObject.optInt("creditLimit");

                customers.add(customer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("msg", "" + e);
        }
        progressDialog.dismiss();
        //CustomerListAdapter customerListAdapter = new CustomerListAdapter(this,customers);
        //customerList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //customerList.setAdapter(customerListAdapter);
        final FoldingCellListAdapter foldingCellListAdapter = new FoldingCellListAdapter(this, customers);
        customerList.setAdapter(foldingCellListAdapter);
        // set on click event listener to list view
        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                foldingCellListAdapter.registerToggle(pos);
            }
        });
    }
}
