package com.qiansong.msparis.app.find.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.CommentsBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.find.bean.CommentsAllBean;
import com.qiansong.msparis.app.mine.activity.UpdateAddressActivity;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.wardrobe.activity.MakeOrderActivity;

import java.util.List;

import static com.qiansong.msparis.app.commom.bean.CommentsBean.*;

/**
 * Created by kevin on 2017/2/9.
 * <p>
 * 所有评价适配器
 */

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.ViewHolder> {

    public Context context;

    public List<CommentsAllBean.DataBean.RowsBean>  list = null;

    public EvaluateAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CommentsAllBean.DataBean.RowsBean>  sizeList) {
        this.list = sizeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public EvaluateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_find_evaluate,parent, false);
        return new EvaluateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EvaluateAdapter.ViewHolder viewHolder, final int position) {
        //设置值
        ExclusiveUtils.setImageUrl(viewHolder.find_image, list.get(position).getUser().getHead_portrait(), -1);
        viewHolder.find_evaluate_messages.setText(list.get(position).getContent());
        viewHolder.find_evaluate_date.setText(list.get(position).getCreated_at());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView find_image;
        public TextView find_evaluate_messages, find_evaluate_date;

        public ViewHolder(View view) {
            super(view);
            find_image = (SimpleDraweeView) view.findViewById(R.id.find_evaluate_image);
            find_evaluate_messages = (TextView) view.findViewById(R.id.find_evaluate_messages);
            find_evaluate_date = (TextView) view.findViewById(R.id.find_evaluate_date);
        }
    }
}
