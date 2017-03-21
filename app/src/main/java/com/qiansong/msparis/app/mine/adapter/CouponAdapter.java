package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.bean.CouponBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/23.
 *  优惠券适配器
 */

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder>{
    private Context mContext;
    private List<CouponBean.DataBean.RowsBean> data;

    public CouponAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return 20;
//        return data != null ? data.size() : 0;
    }

    public void setData(List<CouponBean.DataBean.RowsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public CouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_coupon, parent, false);
            return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CouponAdapter.ViewHolder viewHolder, int position) {


    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout coupon_ly_y;
        public TextView coupon_title_y, coupon_title1_y, coupon_title2_y, coupon_title3_y, coupon_title4_y;

        public ViewHolder(View view) {
            super(view);
            coupon_ly_y = (LinearLayout) view.findViewById(R.id.coupon_ly_y);

            coupon_title_y = (TextView) view.findViewById(R.id.coupon_title_y);
            coupon_title1_y = (TextView) view.findViewById(R.id.coupon_title1_y);
            coupon_title2_y = (TextView) view.findViewById(R.id.coupon_title2_y);
            coupon_title3_y = (TextView) view.findViewById(R.id.coupon_title3_y);
            coupon_title4_y = (TextView) view.findViewById(R.id.coupon_title4_y);
        }
    }


}
