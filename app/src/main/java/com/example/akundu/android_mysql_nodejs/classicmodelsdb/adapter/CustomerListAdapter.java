package com.example.akundu.android_mysql_nodejs.classicmodelsdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.classicmodelsdb.employee.CustomerOrdersActivity;
import com.example.akundu.android_mysql_nodejs.classicmodelsdb.pojo.Customer;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {

    private List<Customer> customers;
    private Context context;

    public CustomerListAdapter(Context context, List<Customer> customers) {
        this.customers = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Customer customer = customers.get(position);
        //holder.title.setText("" + customer.customerNumber);
        holder.genre.setText(customer.customerName);
        holder.year.setText(customer.phone);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("values", customers.get(holder.getAdapterPosition()).customerNumber + "");
                Intent intent = new Intent(context, CustomerOrdersActivity.class);
                intent.putExtra("customerNumber", customers.get(holder.getAdapterPosition()).customerNumber);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            relativeLayout = view.findViewById(R.id.root);
            title = view.findViewById(R.id.title);
            genre = view.findViewById(R.id.genre);
            year = view.findViewById(R.id.year);
        }
    }

}