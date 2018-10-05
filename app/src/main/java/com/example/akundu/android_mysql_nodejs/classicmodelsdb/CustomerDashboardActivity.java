package com.example.akundu.android_mysql_nodejs.classicmodelsdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.akundu.android_mysql_nodejs.R;
import com.ramotion.foldingcell.FoldingCell;

public class CustomerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);


        final FoldingCell foldingCell = findViewById(R.id.folding_cell);
        foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
            }
        });
    }
}