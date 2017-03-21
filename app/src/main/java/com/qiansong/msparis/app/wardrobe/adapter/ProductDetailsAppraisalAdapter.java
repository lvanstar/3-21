package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiansong.msparis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2017/2/9.
 *
 * 商品详情的评价列表
 */

public class ProductDetailsAppraisalAdapter extends BaseAdapter{

    /** 布局填充器 **/
    private LayoutInflater inflater;
    public  Context context;



    public ProductDetailsAppraisalAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_wardrobe_tv_item,null);

            viewHolder.tv_violet = (TextView) convertView.findViewById(R.id.tv_violet);
            viewHolder.tv_grays = (TextView) convertView.findViewById(R.id.tv_grays);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return null;
    }

    private  class ViewHolder{
        /** 选中textview **/
        TextView tv_violet;
        /** 未选中 **/
        TextView tv_grays;
    }
}
