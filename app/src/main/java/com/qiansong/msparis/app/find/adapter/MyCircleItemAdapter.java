package com.qiansong.msparis.app.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.find.bean.CommentsAllBean;
import com.qiansong.msparis.app.find.bean.MyCircleBean;
import com.qiansong.msparis.app.find.view.MultiImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2017/2/9.
 * <p>
 * 我的图片
 */

public class MyCircleItemAdapter extends RecyclerView.Adapter<MyCircleItemAdapter.ViewHolder> {

    public Context context;

    public List<MyCircleBean.DataBean.RowsBean>  list = null;

    public MyCircleItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MyCircleBean.DataBean.RowsBean>  sizeList) {
        this.list = sizeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public MyCircleItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_find_my_circle, null);
        return new MyCircleItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCircleItemAdapter.ViewHolder viewHolder, final int position) {
        //设置值
        viewHolder.find_text.setText(list.get(position).getContent());
        //设置图片
        if(list.get(position).getImages() != null && list.get(position).getImages().size() > 0){
            List<String> lists = new ArrayList<>();
            for (int i = 0; i < list.get(position).getImages().size(); i++) {
                lists.add(list.get(position).getImages().get(i).getSrc());
            }
            viewHolder.find_image_list.setList(lists);
        }
        viewHolder.find_image_start.setText(list.get(position).getLike_num());
        viewHolder.find_image_liu_yan.setText(list.get(position).getComment_num());
        viewHolder.find_image_time.setText(list.get(position).getCreated_at());
        //地址可能没有  地址图标消失
        if(list.get(position).getAddress() !=null && list.get(position).getAddress().length() > 0){
            viewHolder.find_image_address.setText(list.get(position).getAddress());
            viewHolder.find_image_image.setVisibility(View.VISIBLE);
        }else{
            viewHolder.find_image_image.setVisibility(View.GONE);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView find_text, find_image_start,find_image_liu_yan,find_image_time,find_image_address;
        public ImageView find_image_image;
        MultiImageView find_image_list;

        public ViewHolder(View view) {
            super(view);
            find_text = (TextView) view.findViewById(R.id.find_text);
            find_image_start = (TextView) view.findViewById(R.id.find_image_start);
            find_image_liu_yan = (TextView) view.findViewById(R.id.find_image_liuyan);
            find_image_time = (TextView) view.findViewById(R.id.find_image_time);
            find_image_address = (TextView) view.findViewById(R.id.find_image_address);
            find_image_image = (ImageView) view.findViewById(R.id.find_image_image);
            find_image_list = (MultiImageView) view.findViewById(R.id.find_image_list);
        }
    }
}
