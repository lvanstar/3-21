package com.qiansong.msparis.app.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.HomePageBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.wardrobe.activity.ProductDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 * 首页那个横的长长的list
 */
public class RookieAdapter extends
        RecyclerView.Adapter<RookieAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<HomePageBean.DataBean.BannerRookieBean> mDatas;
    Context context;
    public RookieAdapter(Context context, List<HomePageBean.DataBean.BannerRookieBean> datats) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public RookieAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        SimpleDraweeView image_src;
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_rookie,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.image_src = (SimpleDraweeView) view.findViewById(R.id.image_src);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        ExclusiveUtils.setImageUrl(viewHolder.image_src,mDatas.get(i).getImage_src2x(),-1);//--------------------------------------------------
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,ProductDetailsActivity.class));
            }
        });
    }

}