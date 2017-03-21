package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.OrderListBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by lizhaozhao on 2017/2/21.
 *
 * 订单商品详情
 */

public class OrderInfoAdapter extends BaseAdapter {

    private Context context;
    private List<OrderListBean.DataEntity.RowsEntity.OrderDetailEntity>datas;

    public OrderInfoAdapter(Context context,List<OrderListBean.DataEntity.RowsEntity.OrderDetailEntity> datas) {

        this.context = context;
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
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.item_order_info, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.orderInfoNameTv.setText(datas.get(position).getName());
        viewHolder.orderInfoBrandTv.setText(datas.get(position).getBrand_name());
        viewHolder.orderInfoPriceTv.setText("¥"+datas.get(position).getPrice());
        viewHolder.orderInfoCodeTv.setText(datas.get(position).getSpecification());
        viewHolder.simpleDraweeView.setImageURI(Uri.parse(datas.get(position).getImage_url()));

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.order_info_name_Tv)
        TextView orderInfoNameTv;
        @InjectView(R.id.order_info_brand_Tv)
        TextView orderInfoBrandTv;
        @InjectView(R.id.order_info_code_Tv)
        TextView orderInfoCodeTv;
        @InjectView(R.id.order_info_price_Tv)
        TextView orderInfoPriceTv;
        @InjectView(R.id.item_order_draweeView)
        SimpleDraweeView simpleDraweeView;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
