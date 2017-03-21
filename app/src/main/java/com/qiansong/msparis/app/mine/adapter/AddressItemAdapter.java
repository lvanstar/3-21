package com.qiansong.msparis.app.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.mine.activity.AddressActivity;
import com.qiansong.msparis.app.mine.activity.UpdateAddressActivity;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.wardrobe.activity.MakeOrderActivity;

import java.util.List;

/**
 * Created by kevin on 2017/2/9.
 *
 *  地址适配器
 */

public class AddressItemAdapter  extends RecyclerView.Adapter<AddressItemAdapter.ViewHolder>{

    public  Context context;

    public   List<AddressBean.DataBean.RowsBean> list = null;
    public AddressItemAdapter(Context context){
        this.context = context;
    }
    public void setData( List<AddressBean.DataBean.RowsBean> sizeList){
        this.list = sizeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public AddressItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.address_item, null);
        return new AddressItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddressItemAdapter.ViewHolder holder, final int position) {
        //设置值
        holder.address_item_name.setText(list.get(position).getContact_name());
        holder.address_item_phone.setText(list.get(position).getContact_mobile());
        holder.address_item_region.setText(list.get(position).getRegion_name());
        holder.address_item_detail_address.setText(list.get(position).getAddress_detail());
        holder.address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("1".equals(((AddressActivity)context).getIntent().getStringExtra("is_order"))){
                    Intent intent = ((AddressActivity)context).getIntent();
                    intent.putExtra(MakeOrderActivity.intent_key,new Gson().toJson(list.get(position)));
                    ((AddressActivity)context).setResult(66,intent);
                    ((AddressActivity)context).finish();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(context, UpdateAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalConsts.ADDRESS_INTENT,list.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        /**  选中 **/
        public LinearLayout address_layout;
        public  TextView address_item_name,address_item_phone,address_item_region,address_item_detail_address;
        public  ViewHolder(View  view){
            super(view);
            address_layout = (LinearLayout) view.findViewById(R.id.address_layout);
            address_item_name = (TextView) view.findViewById(R.id.address_item_name);
            address_item_phone = (TextView) view.findViewById(R.id.address_item_phone);
            address_item_region = (TextView) view.findViewById(R.id.address_item_region);
            address_item_detail_address = (TextView) view.findViewById(R.id.address_item_detail_address);
        }
    }
}
