package com.example.akundu.android_mysql_nodejs.activity;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.example.akundu.android_mysql_nodejs.MyApplication
import com.example.akundu.android_mysql_nodejs.R
import com.example.akundu.android_mysql_nodejs.network.API
import com.example.akundu.android_mysql_nodejs.network.ApiResponseListener
import com.example.akundu.android_mysql_nodejs.network.NetworkTask
import com.example.akundu.android_mysql_nodejs.network.RequestType
import com.example.akundu.android_mysql_nodejs.pojo.Response
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_vehicle.*

class AddVehicleActivity : AppCompatActivity(), ApiResponseListener {

    val arrayList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)
        arrayList.add("Select")
        arrayList.add("Motorcycles")
        arrayList.add("Ships")
        arrayList.add("Planes")
        arrayList.add("Trains")
        arrayList.add("Trucks and Buses")
        arrayList.add("Classic Cars")
        arrayList.add("Vintage Cars")

        val s = arrayOf<String>("Select", "Motorcycles", "Ships", "Planes", "Trains", "Trucks and Buses", "Classic Cars", "Vintage Cars")


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, s)
        productLine.adapter = adapter

    }

    override fun onResponse(response: Response?) {
        Log.d("msg", response?.response)
    }


    fun Add(view: View) {
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


        val requestBody: JsonObject = JsonObject()
        requestBody.addProperty("productName", productName.text.toString())
        requestBody.addProperty("productCode", productCode.text.toString())
        requestBody.addProperty("productLine", productLine.selectedItem.toString())
        requestBody.addProperty("productVendor", productVendor.text.toString())
        requestBody.addProperty("productDescription", productDescription.text.toString())
        requestBody.addProperty("quantityInStock", quantityInStock.text.toString())
        requestBody.addProperty("buyPrice", buyPrice.text.toString())
        requestBody.addProperty("MSRP", MSRP.text.toString())
        Log.d("msg", requestBody.toString())
        NetworkTask(this@AddVehicleActivity, API.EMP_DETAILS, RequestType.POST)
                .execute("http://" + MyApplication.serverIP + ":3000/add_new_vehicle", requestBody.toString())
    }
}
