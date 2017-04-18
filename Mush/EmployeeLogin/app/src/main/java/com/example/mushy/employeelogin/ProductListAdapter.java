package com.example.mushy.employeelogin;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Mushy on 4/10/2017.
 */

public class ProductListAdapter extends BaseAdapter
{
    private Context mContext;
    private List<Product> mProductList;

    // Constructor



    public ProductListAdapter(Context mContext, List<Product> mProductList)
    {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount()
    {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = View.inflate(mContext, R.layout.item_product_list, null);
        TextView tvName= (TextView) v.findViewById(R.id.tv_name);
        TextView tvDescription= (TextView) v.findViewById(R.id.tv_description);

        // set text for TextView
        tvName.setText(mProductList.get(position).getName());
        tvDescription.setText(mProductList.get(position).getDescription());

        // Depending if working the text will be either BLACK or RED
        if(mProductList.get(position).getDescription().equals("Not Scheduled"))
        {
            tvDescription.setTextColor(Color.RED);
            tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
        }
        else
        {
            tvDescription.setTextColor(Color.BLACK);
            tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        }
        // save product id to tag
        v.setTag(mProductList.get(position).getId());

        return v;
    }
}
