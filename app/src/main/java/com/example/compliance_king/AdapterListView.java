package com.example.compliance_king;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class AdapterListView extends BaseAdapter {
    private Context mContext;
    LayoutInflater mLayoutInflater;
    private ArrayList<ItemUseProduct> array_product;

    private ViewHolder mViewHolder;

    public AdapterListView(Context mContext, ArrayList<ItemUseProduct> array_product) {
        this.mContext = mContext;
        this.array_product = array_product;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return array_product.size();
    }

    @Override
    public Object getItem(int position) {
        return array_product.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_use_product, null);

        ImageView img = (ImageView)view.findViewById(R.id.product_img);
        TextView name = (TextView)view.findViewById(R.id.product_name);
        TextView date1 = (TextView)view.findViewById(R.id.product_date1);
        TextView date2 = (TextView)view.findViewById(R.id.product_date2);

        img.setImageResource(array_product.get(position).getImg());
        name.setText(array_product.get(position).getName());
        date1.setText(array_product.get(position).getDate1());
        date2.setText(array_product.get(position).getDate2());

        return view;
    }
}
