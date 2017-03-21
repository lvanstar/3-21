package com.qiansong.msparis.app.fulldress.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.fulldress.activity.AppointmentActivity;
import com.qiansong.msparis.app.fulldress.bean.LookingTimeBean;
import com.qiansong.msparis.app.fulldress.activity.AppointmentTimeActivity;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 预约时间 item
 */
public class AppointmentItemAdapter extends RecyclerView.Adapter<AppointmentItemAdapter.ViewHolder> {
    public Context context;
    public String date = "";

    public List<LookingTimeBean.DataBean.RowsBean> list = null;

    private int index = -1;

    public AppointmentItemAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<LookingTimeBean.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public  void  setDate(String date){
        this.date = date;
    }

    public void setIndex(int position){
        this.index = position;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public AppointmentItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_appointment_list_item, null);
        return new AppointmentItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointmentItemAdapter.ViewHolder holder, final int position) {


        //设置值
        holder.item_appointment_text.setText(list.get(position).getTime());
        if (position == index) {
            holder.item_appointment_text.setBackgroundResource(R.drawable.mine_yq_b);
        } else {
            holder.item_appointment_text.setBackgroundResource(0);
        }
        holder.item_appointment_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();
                Intent intent = new Intent();
                intent.setClass(context,AppointmentActivity.class);
                intent.putExtra("time",list.get(position).getTime());
                intent.putExtra("date",date);
                Log.i("kevin",date);
                ((AppointmentTimeActivity)context).setResult(10001,intent);
                ((AppointmentTimeActivity)context).finish();
            }
        });

        //是否被预定了  0否 1是
        if(list.get(position).getIs_be_booked() ==1 ){
            holder.item_appointment_text.setClickable(false);
            holder.item_appointment_text.setTextColor(Color.parseColor("#8d8e94"));
        }else{
            holder.item_appointment_text.setClickable(true);
            holder.item_appointment_text.setTextColor(Color.parseColor("#282828"));
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_appointment_text;

        public ViewHolder(View view) {
            super(view);
            item_appointment_text = (TextView) view.findViewById(R.id.item_appointment_text);
        }
    }

}
