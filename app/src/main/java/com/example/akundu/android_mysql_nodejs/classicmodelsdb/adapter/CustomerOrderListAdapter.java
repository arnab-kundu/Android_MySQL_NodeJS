package com.example.akundu.android_mysql_nodejs.classicmodelsdb.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.pojo.CustomerOrder;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class CustomerOrderListAdapter extends RecyclerView.Adapter<CustomerOrderListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CustomerOrder> items;

    public CustomerOrderListAdapter(Context context, ArrayList<CustomerOrder> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer_order, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderNumber.setText("Order Number: " + items.get(position).orderNumber);
        holder.productCode.setText("(" + items.get(position).productCode + ")");
        holder.quantityOrdered.setText(items.get(position).quantityOrdered + " x");
        holder.priceEach.setText("@ $" + items.get(position).priceEach);
        holder.productName.setText(items.get(position).productName);
        holder.productLine.setText(items.get(position).productLine);
        holder.productVendor.setText("Product Vendor: " + items.get(position).productVendor);
        Ion.with(context)
                .load(items.get(position).image)
                .intoImageView(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber,
                productCode,
                quantityOrdered,
                priceEach,
                productName,
                productLine,
                productVendor;
        ImageView image;
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/digital.ttf");

        public MyViewHolder(View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            productCode = itemView.findViewById(R.id.productCode);
            quantityOrdered = itemView.findViewById(R.id.quantityOrdered);
            priceEach = itemView.findViewById(R.id.priceEach);
            priceEach.setTypeface(custom_font);
            productName = itemView.findViewById(R.id.productName);
            productLine = itemView.findViewById(R.id.productLine);
            productLine.setTypeface(custom_font);
            productVendor = itemView.findViewById(R.id.productVendor);
            image = itemView.findViewById(R.id.image);
        }
    }
}