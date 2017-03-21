package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.CardListBean;
import com.qiansong.msparis.app.commom.util.CalendarUtil.CalendarUtils;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by lizhaozhao on 2017/2/21.
 * <p>
 * 订单商品详情
 */

public class VipOrderInfoAdapter extends BaseAdapter {

    private Context context;
    private List<CardListBean.DataEntity.RowsEntity.OrderDetailEntity>datas;

    public VipOrderInfoAdapter(Context context, List<CardListBean.DataEntity.RowsEntity.OrderDetailEntity>datas) {

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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_vip_order_info, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ExclusiveUtils.setImageUrl(viewHolder.vipOrderInfoIv,datas.get(position).getCover_img(),1);
        viewHolder.vipOrderInfoPrice.setText("¥"+datas.get(position).getPrice());

        return convertView;
    }




    class ViewHolder {
        @InjectView(R.id.vip_order_info_iv)
        SimpleDraweeView vipOrderInfoIv;
        @InjectView(R.id.vip_order_info_name)
        TextView vipOrderInfoName;
        @InjectView(R.id.vip_order_info_price)
        TextView vipOrderInfoPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
