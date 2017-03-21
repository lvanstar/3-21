package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.mine.bean.BuyCardBean;
import com.qiansong.msparis.R;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 购买女神卡 item
 */
public class BuyCardItemAdapter extends BaseAdapter {
    public Context context;

    public List<BuyCardBean.DataBean.RowsBeanX.RowsBean> list = null;

    public BuyCardItemAdapter(Context context) {
        this.context = context;
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
        BuyCardItemAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_fragment_buy_card_item, null);
            viewHolder = new BuyCardItemAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (BuyCardItemAdapter.ViewHolder) convertView.getTag();
        }

        //设置值
        viewHolder.buy_item_price.setText("￥"+list.get(position).getPrice());
        viewHolder.buy_item_oldprice.setText("￥"+list.get(position).getMarket_price());
        viewHolder.buy_item_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ExclusiveUtils.setImageUrl(viewHolder.buy_item_iv, list.get(position).getCover_img(), -1);

        return convertView;
    }


    public class ViewHolder {
        TextView buy_item_price, buy_item_oldprice;
        ImageView buy_item_image;
        SimpleDraweeView buy_item_iv;

        public ViewHolder(View view) {
            buy_item_price = (TextView) view.findViewById(R.id.buy_item_price);
            buy_item_oldprice = (TextView) view.findViewById(R.id.buy_item_oldprice);
            buy_item_iv = (SimpleDraweeView) view.findViewById(R.id.buy_item_iv);
            buy_item_image = (ImageView) view.findViewById(R.id.buy_item_image);

        }
    }

}
