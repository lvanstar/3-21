package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.bean.OldCouponBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/23.
 * 历史优惠券适配器
 */

  public class OldCouponAdapter extends RecyclerView.Adapter<OldCouponAdapter.ViewHolder> {
    private Context mContext;
    private  List<OldCouponBean.DataBean.RowsBean> data =null;

    public OldCouponAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
//        return 20;
        return data != null ? data.size() : 0;
    }

    public void setData(List<OldCouponBean.DataBean.RowsBean>  data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public OldCouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_coupon2, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        viewHolder.item_coupon2_title.setText();
//        viewHolder.item_coupon2_title.setText();
//        viewHolder.item_coupon2_title.setText();
//        viewHolder.item_coupon2_title.setText();
        // 1已使用 2已过期
//        if(data.get(position).getStatusX() == 1){
//            viewHolder. coupon_image.setBackgroundResource(R.mipmap.oldcoupon_sy);
//        }else if (data.get(position).getStatusX() == 2){
//            viewHolder. coupon_image.setBackgroundResource(R.mipmap.oldcoupon_gq);
//        }
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout coupon_ly_n;
        public TextView  coupon_title_n, coupon_title1_n, coupon_title2_n, coupon_title3_n, coupon_title4_n;
        public ImageView coupon_image;

        public ViewHolder(View view) {
            super(view);
            coupon_ly_n = (LinearLayout) view.findViewById(R.id.coupon_ly_n);
            coupon_title_n = (TextView) view.findViewById(R.id.coupon_title_n);
            coupon_title1_n = (TextView) view.findViewById(R.id.coupon_title1_n);
            coupon_title2_n = (TextView) view.findViewById(R.id.coupon_title2_n);
            coupon_title3_n = (TextView) view.findViewById(R.id.coupon_title3_n);
            coupon_title4_n = (TextView) view.findViewById(R.id.coupon_title4_n);
            coupon_image = (ImageView) view.findViewById(R.id.coupon_image);
        }
    }

}
