package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.mine.bean.IntegralBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/9.
 * <p>
 * 积分适配器
 */

public class IntgegralItemAdapter extends RecyclerView.Adapter<IntgegralItemAdapter.ViewHolder> {

    public Context context;

    public List<IntegralBean.DataBean.RowsBean> list = null;


    public IntgegralItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<IntegralBean.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() :0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_intgegral, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IntgegralItemAdapter.ViewHolder holder, final int position) {

        holder.intgegral_item_title.setText(list.get(position).getRemark());
        //1.奖励 2.扣除
        if(list.get(position).getType() == 1){
            holder.intgegral_item_num.setText("+"+list.get(position).getBonus_points()+"");
            holder.intgegral_item_num.setTextColor(Color.parseColor("#f5359b"));
        }else if(list.get(position).getType() == 2){
            holder.intgegral_item_num.setText("-"+list.get(position).getBonus_points()+"");
            holder.intgegral_item_num.setTextColor(Color.parseColor("#3F51B5"));
        }
        holder.intgegral_item_time.setText(list.get(position).getCreated_at());
        holder.intgegral_item_oldnum.setText(list.get(position).getRemain_points()+"");
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
