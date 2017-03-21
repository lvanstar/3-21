package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.mine.bean.BuyCardBean;
import com.qiansong.msparis.app.mine.bean.RenewCardBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 续费女神卡m
 */
public class RenewCardAdapter extends BaseAdapter {
    public Context context;

    public List<RenewCardBean> list = null;
    public  int index = 0;
    public RenewCardAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<RenewCardBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 设置选中位置
     *
     * @param index
     */
    public  void  setIndex(int index){
        this.index = index;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RenewCardAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_renew_card, null);
            viewHolder = new RenewCardAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (RenewCardAdapter.ViewHolder) convertView.getTag();
        }

        if(position == index){
            viewHolder.item_renew_check.setBackgroundResource(R.drawable.mine_checkbox_true);
        }else{
            viewHolder.item_renew_check.setBackgroundResource(R.drawable.mine_checkbox_false);
        }

//        //设置值
//        viewHolder.buy_item_price.setText("￥"+list.get(position).getPrice());
//        viewHolder.buy_item_oldprice.setText("￥"+list.get(position).getMarket_price());
//        viewHolder.buy_item_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);


        return convertView;
    }


    public class ViewHolder {
        TextView item_renew_name, item_renew_title,item_renew_price;
        ImageView item_renew_check;

        public ViewHolder(View view) {
            item_renew_name = (TextView) view.findViewById(R.id.item_renew_name);
            item_renew_title = (TextView) view.findViewById(R.id.item_renew_title);
            item_renew_price = (TextView) view.findViewById(R.id.item_renew_price);
            item_renew_check = (ImageView) view.findViewById(R.id.item_renew_check);

        }
    }

}
