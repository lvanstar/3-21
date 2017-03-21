package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ProductsBean;

import java.util.List;

/**
 * Created by lizhaozhao on 2017/3/6.
 *
 *  商品详情tags
 */

public class TagsAdapter extends BaseAdapter{

    private Context context;
    private List<ProductsBean.DataEntity.TagsEntity>datas;

    public TagsAdapter(Context context, List<ProductsBean.DataEntity.TagsEntity>datas){

        this.context=context;
        this.datas=datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=View.inflate(context, R.layout.item_tags,null);
        TextView nameTv= (TextView) convertView.findViewById(R.id.item_tags_name);
        nameTv.setText(datas.get(position).getName());
        return convertView;
    }
}
