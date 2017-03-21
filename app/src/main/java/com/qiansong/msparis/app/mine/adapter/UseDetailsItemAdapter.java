package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.bean.UseDetailsBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/9.
 * <p>
 * 查看明细
 */

public class UseDetailsItemAdapter extends RecyclerView.Adapter<UseDetailsItemAdapter.ViewHolder> {

    public Context context;

    public List<UseDetailsBean.DataBean.RowsBean>  list = null;


    public UseDetailsItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<UseDetailsBean.DataBean.RowsBean> sizeList) {
        this.list = sizeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_intgegral, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UseDetailsItemAdapter.ViewHolder holder, final int position) {

        holder.intgegral_item_title.setText(list.get(position).getRemark());
        holder.intgegral_item_time.setText(list.get(position).getCreated_at());
        holder.intgegral_item_num.setText(list.get(position).getReward_day());
        holder.intgegral_item_oldnum.setVisibility(View.GONE);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView intgegral_item_title, intgegral_item_num, intgegral_item_time, intgegral_item_oldnum;

        public ViewHolder(View view) {
            super(view);
            intgegral_item_title = (TextView) view.findViewById(R.id.intgegral_item_title);
            intgegral_item_num = (TextView) view.findViewById(R.id.intgegral_item_num);
            intgegral_item_time = (TextView) view.findViewById(R.id.intgegral_item_time);
            intgegral_item_oldnum = (TextView) view.findViewById(R.id.intgegral_item_oldnum);
        }
    }
}
