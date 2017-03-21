package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qiansong.msparis.R;


/**
 * Created by Administrator on 2016/6/6.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private Context context;

    public TimelineAdapter(Context context){
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.item_logistics,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        if(getItemCount()-1==position){
//            holder.xian.setVisibility(View.GONE);
//        }
//        if(position==0){
//            holder.iv1.setVisibility(View.GONE);
//            holder.iv2.setVisibility(View.VISIBLE);
//            holder.info.setTextColor(context.getResources().getColor(R.color.refresh_color));
//        }else {
//
//            holder.iv1.setVisibility(View.VISIBLE);
//            holder.iv2.setVisibility(View.GONE);
//            holder.info.setTextColor(context.getResources().getColor(R.color.text04));
//        }



    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view) {
            super(view);
        }
    }





}
