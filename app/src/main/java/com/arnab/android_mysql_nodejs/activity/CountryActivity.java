package com.arnab.android_mysql_nodejs.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arnab.android_mysql_nodejs.MyApplication;
import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.adapter.ListAdapter;
import com.arnab.android_mysql_nodejs.network.Parser;
import com.arnab.android_mysql_nodejs.pojo.Country;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryActivity extends AppCompatActivity {

    ListView listView;
    EditText et_filter;
    String url = "http://" + MyApplication.serverIP + ":3000/country";
    ArrayList<Country> countries = new ArrayList<>();
    ListAdapter listAdapter;
    TextView tv_filter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        listView = findViewById(R.id.list);
        et_filter = findViewById(R.id.et_filter);
        et_filter.setEnabled(false);

        tv_filter = findViewById(R.id.tv_filter);

        listAdapter = new ListAdapter(this, R.layout.row, countries);


        listView.setAdapter(listAdapter);

        new NetworkTask().execute(url);

        et_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new NetworkTask().execute(url);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void countryCodeClick(View view) {
        url = "http://" + MyApplication.serverIP + ":3000/country?countrycode=";
        tv_filter.setText("Filtered by Country Code");
        et_filter.setEnabled(true);
    }

    public void englishNameClick(View view) {
        url = "http://" + MyApplication.serverIP + ":3000/country?englishname=";
        tv_filter.setText("Filtered by English Name");
        et_filter.setEnabled(true);

    }

    public void frenchNameClick(View view) {
        url = "http://" + MyApplication.serverIP + ":3000/country?frenchname=";
        tv_filter.setText("Filtered by French Name");
        et_filter.setEnabled(true);

    }

   /* public void requestClicked(View view) {
        new NetworkTask().execute(url);
    }*/


    class NetworkTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0] + et_filter.getText().toString());
                Log.d("msg Request:", "" + url);
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");

                return scanner.hasNext() ? scanner.next() : "";
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("msg Response:", s);
            new Parser().countryData(s, countries);
            listAdapter.notifyDataSetChanged();
        }
    }
}
