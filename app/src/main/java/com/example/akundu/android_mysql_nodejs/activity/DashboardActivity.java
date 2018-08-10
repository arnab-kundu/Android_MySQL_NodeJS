package com.example.akundu.android_mysql_nodejs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.akundu.android_mysql_nodejs.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

/*

        try {
           File file =  getExternalStorageDirectory();
       Log.d("msg", file.getAbsolutePath());
        File myFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

       Log.d("msg", myFile.getAbsolutePath());

       myFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);

        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.append("ARNAB");
        myOutWriter.flush();
        myOutWriter.close();
        fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

      /*  String filename = "abhra.txt";
        String fileContents = "Hello world!";
        FileOutputStream outputStream;

        File file = new File(getFilesDir(), filename);
        Log.d("msg", file.getAbsolutePath());
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/




        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"arnab.txt");
        //File file = new File(getExternalStorageDirectory(),"newfile.txt");
        Log.d("msg",""+file.getAbsolutePath());
        String content = "This is the text content";


        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("msg Done");

        } catch (IOException e) {
            e.printStackTrace();
        }






















    }

    public void Employee(View view) {
        startActivity(new Intent(this, EmployeeActivity.class));
    }

    public void SalaryChart(View view) {
        startActivity(new Intent(this, SalaryChartActivity.class));
    }

    public void Country(View view) {
        startActivity(new Intent(this, CountryActivity.class));
    }

    public void Car(View view) {
        startActivity(new Intent(this, CarActivity.class));
    }

    public void AddVehicle(View view) {
        startActivity(new Intent(this, AddVehicleActivity.class));
    }
}
