package com.arnab.android_mysql_nodejs.classicmodelsdb.employee;

import static com.arnab.android_mysql_nodejs.network.API.SHOW_ALL_CUSTOMER;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.arnab.android_mysql_nodejs.MyApplication;
import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.classicmodelsdb.adapter.CustomerListAdapter;
import com.arnab.android_mysql_nodejs.network.ApiResponseListener;
import com.arnab.android_mysql_nodejs.network.NetworkTask;
import com.arnab.android_mysql_nodejs.pojo.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        final List<com.example.akundu.android_mysql_nodejs.classicmodelsdb.pojo.Customer> customers = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(stringResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                com.example.akundu.android_mysql_nodejs.classicmodelsdb.pojo.Customer customer = new com.example.akundu.android_mysql_nodejs.classicmodelsdb.pojo.Customer();
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
        CustomerListAdapter customerListAdapter = new CustomerListAdapter(this,customers);
        //customerList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //customerList.setAdapter(customerListAdapter);
       // final com.example.akundu.android_mysql_nodejs.classicmodelsdb.adapter.FoldingCellListAdapter foldingCellListAdapter = new com.example.akundu.android_mysql_nodejs.classicmodelsdb.adapter.FoldingCellListAdapter(this, customers);
        //customerList.setAdapter(foldingCellListAdapter);
        // set on click event listener to list view
        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                //===((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
              //  foldingCellListAdapter.registerToggle(pos);
            }
        });
        customerList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("values", customers.get(position).customerNumber + "");
                Intent intent = new Intent(CustomerListActivity.this, CustomerOrdersActivity.class);
                intent.putExtra("customerNumber", customers.get(position).customerNumber);
                CustomerListActivity.this.startActivity(intent);
                return true;
            }
        });
    }
}
