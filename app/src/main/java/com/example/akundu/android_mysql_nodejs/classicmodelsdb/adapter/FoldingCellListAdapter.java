package com.example.akundu.android_mysql_nodejs.classicmodelsdb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.classicmodelsdb.pojo.Customer;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
public class FoldingCellListAdapter extends ArrayAdapter<Customer> {

    private List<Customer> items;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellListAdapter(Context context, List<Customer> objects) {
        super(context, 0, objects);
        items = objects;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        //Customer item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.row_folding_cell, parent, false);
            // binding view parts to view holder
            viewHolder.customerName = (TextView) cell.findViewById(R.id.customerName);
            viewHolder.customerName1 = (TextView) cell.findViewById(R.id.customerName1);
            viewHolder.address = (TextView) cell.findViewById(R.id.address);
            viewHolder.city = (TextView) cell.findViewById(R.id.city);
            viewHolder.state = (TextView) cell.findViewById(R.id.state);
            viewHolder.country = (TextView) cell.findViewById(R.id.country);
            viewHolder.postalCode = (TextView) cell.findViewById(R.id.postalCode);
            viewHolder.contactName = (TextView) cell.findViewById(R.id.contactName);
            viewHolder.phone = (TextView) cell.findViewById(R.id.phone);
            viewHolder.creditLimit = (TextView) cell.findViewById(R.id.creditLimit);

            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        // bind data from selected element to view through view holder
        viewHolder.customerName.setText(items.get(position).customerName);
        viewHolder.customerName1.setText(items.get(position).customerName);
        viewHolder.contactName.setText("Name: " + items.get(position).contactFirstName + " " + items.get(position).contactLastName);
        viewHolder.phone.setText("Phone: " + items.get(position).phone);
        viewHolder.creditLimit.setText("Credit Limit: " + items.get(position).creditLimit);
        viewHolder.address.setText(items.get(position).addressLine1 + " " + items.get(position).addressLine2);
        viewHolder.city.setText(items.get(position).city);
        viewHolder.state.setText(items.get(position).state);
        viewHolder.country.setText(items.get(position).country);
        viewHolder.postalCode.setText(String.valueOf(items.get(position).postalCode));

        // set custom btn handler for list item from that item
        /*if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }*/


        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView customerNumber;
        TextView customerName, customerName1;
        TextView contactName;
        TextView phone;
        TextView address;
        TextView city;
        TextView state;
        TextView postalCode;
        TextView country;
        TextView creditLimit;
    }
}
