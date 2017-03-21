package com.qiansong.msparis.app.fulldress.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.fulldress.bean.AppointmentRecordBean;
import com.qiansong.msparis.app.fulldress.activity.AppointmentRecordDetailsActivity;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 *
 *  预约记录的adapter
 */
public class AppointmentRecordAdapter extends RecyclerView.Adapter<AppointmentRecordAdapter.ViewHolder> {
    private Context context;
    private  List<AppointmentRecordBean.DataBean.RowsBean> list = null;

    public AppointmentRecordAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<AppointmentRecordBean.DataBean.RowsBean> data){
        this.list = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_appointment_record,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.time.setText(list.get(position).getDate() + "  " +list.get(position).getStart_time());
        holder.city.setText(list.get(position).getCity_name());
        holder.road.setText(list.get(position).getStore_name());

        //1礼服 2婚纱 3 化妆
        if(Integer.parseInt(list.get(position).getType()) ==1){
            holder.type.setText("礼服");
        }else if(Integer.parseInt(list.get(position).getType()) ==2){
            holder.type.setText("婚纱");
        }else{
            holder.type.setText("化妆");
        }
        holder.num.setText(list.get(position).getNum());
        holder.name.setText(list.get(position).getName());
        holder.phone.setText(list.get(position).getMobile());
        holder.remarks.setText(list.get(position).getRemark());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AppointmentRecordDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time,city,road,type,num,name,phone,remarks;
        public ViewHolder(View view){
            super(view);
            time= (TextView) view.findViewById(R.id.item_record_time);
            city= (TextView) view.findViewById(R.id.item_record_city);
            road= (TextView) view.findViewById(R.id.item_record_road);
            type= (TextView) view.findViewById(R.id.item_record_type);
            num= (TextView) view.findViewById(R.id.item_record_num);
            name= (TextView) view.findViewById(R.id.item_record_name);
            phone= (TextView) view.findViewById(R.id.item_record_phone);
            remarks= (TextView) view.findViewById(R.id.item_record_remarks);
        }
    }

}
