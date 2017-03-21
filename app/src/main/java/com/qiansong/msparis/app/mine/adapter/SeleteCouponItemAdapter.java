package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.bean.BuyCardBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 选择优惠券列表
 */
public class SeleteCouponItemAdapter extends BaseAdapter {
    public Context context;

    public List<BuyCardBean.DataBean.RowsBeanX.RowsBean> list = null;

    //设置是哪个选中
    public  int index = -1;

    public SeleteCouponItemAdapter(Context context) {
        this.context = context;
    }

    public void setCheckIndex(int index){
        this.index = index;
        notifyDataSetChanged();
    }
    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<BuyCardBean.DataBean.RowsBeanX.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return 5;
//        return list == null ? 0 : list.size();
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
        SeleteCouponItemAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_selete_coupon_item, null);
            viewHolder = new SeleteCouponItemAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (SeleteCouponItemAdapter.ViewHolder) convertView.getTag();
        }

        if(index == position){
            viewHolder.selete_check.setVisibility(View.VISIBLE);
        }else{
            viewHolder.selete_check.setVisibility(View.INVISIBLE);
        }

//        //设置值
//        viewHolder.buy_item_price.setText("￥"+list.get(position).getPrice());
//        viewHolder.buy_item_oldprice.setText("￥"+list.get(position).getMarket_price());
//        viewHolder.buy_item_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);


        return convertView;
    }


    public class ViewHolder {
        public ImageView selete_check;
        public TextView selete_title,selete_title2,coupon_title1_y, coupon_title2_y, coupon_title3_y, coupon_title4_y;

        public ViewHolder(View view) {
            selete_check = (ImageView) view.findViewById(R.id.selete_check);
            selete_title = (TextView) view.findViewById(R.id.selete_title);
            selete_title2 = (TextView) view.findViewById(R.id.selete_title2);
            coupon_title1_y = (TextView) view.findViewById(R.id.coupon_title1_y);
            coupon_title2_y = (TextView) view.findViewById(R.id.coupon_title2_y);
            coupon_title3_y = (TextView) view.findViewById(R.id.coupon_title3_y);
            coupon_title4_y = (TextView) view.findViewById(R.id.coupon_title4_y);

        }
    }

}
