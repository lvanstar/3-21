package com.qiansong.msparis.app.homepage.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.homepage.bean.SearchResultBean;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.wardrobe.activity.ProductDetailsActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Kevin on 2017/02/13.
 *
 *  搜索页商品展示适配器
 */
public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> {
    private Context context;
    private List<SearchResultBean.DataBean.RowsBean> data;

    public SearchProductAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<SearchResultBean.DataBean.RowsBean> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_sku,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        ExclusiveUtils.setImageUrl(holder.iv,data.get(position).getCover_image(),0);
//        holder.skuNameTv.setText(data.get(position).getName());
//        holder.skuSizeTv.setText("￥"+data.get(position).getShow_specifications());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProductDetailsActivity.class);
//                intent.putExtra(GlobalConsts.INIT_DATA, data.get(position).getId());
//                context.startActivity(intent);
//            }
//        });
//        holder.zan.setFillColor(data.get(position).getIs_favorite()==0?Color.parseColor("#f9f9f9"):Color.parseColor("#CBB6D8"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA, data.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.zan.setImageResource(data.get(position).getIs_favorite()==0?R.mipmap.icon_no:R.mipmap.icon_yes);

    }

    @Override
    public int getItemCount() {
        return data== null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView zan;
        @InjectView(R.id.sku_vip_Tv)
        TextView skuVipTv;
        @InjectView(R.id.sku_name_Tv)
        TextView skuNameTv;
        @InjectView(R.id.sku_size_Tv)
        TextView skuSizeTv;
        @InjectView(R.id.sku_price01_Tv)
        TextView skuPrice01Tv;
        @InjectView(R.id.sku_price02_Tv)
        TextView skuPrice02Tv;
        @InjectView(R.id.sku_iv)
        SimpleDraweeView iv;

        public ViewHolder(View view) {
            super(view);
//            sku_img= (SimpleDraweeView) view.findViewById(R.id.sku_img);
            zan = (ImageView) view.findViewById(R.id.zan);
            ButterKnife.inject(this,view);
        }
    }
}
