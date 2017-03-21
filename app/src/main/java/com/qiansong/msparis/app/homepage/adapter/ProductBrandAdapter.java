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
import com.qiansong.msparis.app.commom.bean.ProductBrandBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.fulldress.activity.FullDressActivity;

import java.util.List;



/**
 * Created by Administrator on 2017/2/27.
 * 同品牌商品列表
 */
public class ProductBrandAdapter extends RecyclerView.Adapter<ProductBrandAdapter.ViewHolder> {
    private Context context;
    private List<ProductBrandBean.DataBean.RowsBean> data;

    public ProductBrandAdapter(Context context, List<ProductBrandBean.DataBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }



    @Override
    public ProductBrandAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_product_brand,null);
        return new ProductBrandAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductBrandAdapter.ViewHolder holder, final int position) {
        ExclusiveUtils.setImageUrl(holder.sku_img,data.get(position).getCover_image(),-1);
        holder.name.setText(data.get(position).getName());
        holder.size.setText(data.get(position).getShow_specifications());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullDressActivity.class);
                context.startActivity(intent);
            }
        });
        holder.zan.setImageResource(data.get(position).getIs_favorite()==0?R.mipmap.icon_no:R.mipmap.icon_yes);
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sku_img;
        TextView name,size;
        ImageView zan;
        public ViewHolder(View view){
            super(view);
            sku_img= (SimpleDraweeView) view.findViewById(R.id.sku_img);
            name= (TextView) view.findViewById(R.id.name);
            size= (TextView) view.findViewById(R.id.size);
            zan= (ImageView) view.findViewById(R.id.zan);
        }
    }
    public void updateData(List<ProductBrandBean.DataBean.RowsBean>data){
        this.data=data;
        notifyDataSetChanged();
    }
}
