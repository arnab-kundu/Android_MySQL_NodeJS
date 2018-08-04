package com.example.akundu.android_mysql_nodejs.activity;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.akundu.android_mysql_nodejs.MyApplication
import com.example.akundu.android_mysql_nodejs.R
import com.example.akundu.android_mysql_nodejs.network.API
import com.example.akundu.android_mysql_nodejs.network.ApiResponseListener
import com.example.akundu.android_mysql_nodejs.network.NetworkTask
import com.example.akundu.android_mysql_nodejs.pojo.Response
import kotlinx.android.synthetic.main.activity_add_vehicle.*

class AddVehicleActivity : AppCompatActivity(), ApiResponseListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)
    }

    override fun onResponse(response: Response?) {
        Log.d("msg", response?.response)
    }


    fun Add(view: View) {
        NetworkTask(this@AddVehicleActivity, API.EMP_DETAILS)
                .execute("http://" + MyApplication.serverIP + ":3000/add_new_vehicle?" +
                        "productName=" + productName.text +
                        "&productCode=" + productCode.text +
                        "&productLine=" + productLine.text +
                        "&productVendor=" + productVendor.text +
                        "&productDescription=" + productDescription.text +
                        "&quantityInStock=" + quantityInStock.text +
                        "&buyPrice=" + buyPrice.text +
                        "&MSRP=" + MSRP.text)
    }
}
