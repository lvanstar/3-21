package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.bean.CouponBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/23.
 *  选择优惠券
 */

public class SeleteCouponAdapter extends RecyclerView.Adapter<SeleteCouponAdapter.ViewHolder>{
    private Context mContext;
    private List<CouponBean.DataBean.RowsBean> data;
    private  SeleteCouponItemAdapter adapter;

    public SeleteCouponAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return 2;
//        return data != null ? data.size() : 0;
    }

    public void setData(List<CouponBean.DataBean.RowsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public SeleteCouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_selete_coupon, parent, false);
            return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SeleteCouponAdapter.ViewHolder viewHolder, int position) {

        adapter = new SeleteCouponItemAdapter(mContext);
        viewHolder.select_gridview.setAdapter(adapter);
        ListUtils.setGridViewHeightBasedOnChildren(viewHolder.select_gridview,1);

        viewHolder.select_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionid, long id) {
//                Intent intent = new Intent();
//                intent.setClass(context, MemberCardDetailsActivity.class);
//                intent.putExtra(GlobalConsts.MEMBERCARD_ID,data.get(position).getRows().get(positionid).getId()+"");
//                context. startActivity(intent);
                adapter.setCheckIndex(positionid);

            }
        });

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView select_title1, select_title2, select_title3;
        public GridView select_gridview;

        public ViewHolder(View view) {
            super(view);
            select_gridview = (GridView) view.findViewById(R.id.select_gridview);
            select_title1 = (TextView) view.findViewById(R.id.select_title1);
            select_title2 = (TextView) view.findViewById(R.id.select_title2);
            select_title3 = (TextView) view.findViewById(R.id.select_title3);

        }
    }


}
