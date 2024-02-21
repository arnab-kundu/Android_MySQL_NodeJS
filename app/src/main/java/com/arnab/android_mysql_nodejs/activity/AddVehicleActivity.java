package com.arnab.android_mysql_nodejs.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arnab.android_mysql_nodejs.MyApplication;
import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.network.API;
import com.arnab.android_mysql_nodejs.network.ApiResponseListener;
import com.arnab.android_mysql_nodejs.network.NetworkTask;
import com.arnab.android_mysql_nodejs.network.RequestType;
import com.arnab.android_mysql_nodejs.pojo.Response;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class AddVehicleActivity extends AppCompatActivity implements ApiResponseListener {

    ArrayList<String> arrayList = new ArrayList();

    Spinner productLine;
    EditText productName;
    EditText productCode;
    EditText productVendor;
    EditText productDescription;
    EditText quantityInStock;
    EditText buyPrice;
    EditText MSRP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        productLine = findViewById(R.id.productLine);
        productName = findViewById(R.id.productName);
        productCode = findViewById(R.id.productCode);
        productVendor = findViewById(R.id.productVendor);
        productDescription = findViewById(R.id.productDescription);
        quantityInStock = findViewById(R.id.quantityInStock);
        buyPrice = findViewById(R.id.buyPrice);
        MSRP = findViewById(R.id.MSRP);

        arrayList.add("Select");
        arrayList.add("Motorcycles");
        arrayList.add("Ships");
        arrayList.add("Planes");
        arrayList.add("Trains");
        arrayList.add("Trucks and Buses");
        arrayList.add("Classic Cars");
        arrayList.add("Vintage Cars");

        String[] s = new String[]{"Select", "Motorcycles", "Ships", "Planes", "Trains", "Trucks and Buses", "Classic Cars", "Vintage Cars"};

        ArrayAdapter adapter = new ArrayAdapter(AddVehicleActivity.this, android.R.layout.simple_spinner_dropdown_item, s);
        productLine.setAdapter(adapter);

    }

    public void Add(View view) {
        /*NetworkTask(this@AddVehicleActivity, API.EMP_DETAILS)
                .execute("http://" + MyApplication.serverIP + ":3000/add_new_vehicle?" +
                        "productName=" + productName.text +
                        "&productCode=" + productCode.text +
                        "&productLine=" + productLine.selectedItem +
                        "&productVendor=" + productVendor.text +
                        "&productDescription=" + productDescription.text +
                        "&quantityInStock=" + quantityInStock.text +
                        "&buyPrice=" + buyPrice.text +
                        "&MSRP=" + MSRP.text)
*/


        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("productName", productName.getText().toString());
        requestBody.addProperty("productCode", productCode.getText().toString());
        requestBody.addProperty("productLine", productLine.getSelectedItem().toString());
        requestBody.addProperty("productVendor", productVendor.getText().toString());
        requestBody.addProperty("productDescription", productDescription.getText().toString());
        requestBody.addProperty("quantityInStock", quantityInStock.getText().toString());
        requestBody.addProperty("buyPrice", buyPrice.getText().toString());
        requestBody.addProperty("MSRP", MSRP.getText().toString());
        Log.d("msg", requestBody.toString());
        new NetworkTask(AddVehicleActivity.this, API.EMP_DETAILS, RequestType.POST)
                .execute("http://" + MyApplication.serverIP + ":3000/add_new_vehicle", requestBody.toString());
    }


    @Override
    public void onResponse(Response response) {
        Log.d("msg", response.response);
    }


}
