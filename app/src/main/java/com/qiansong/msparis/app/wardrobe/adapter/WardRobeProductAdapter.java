package com.qiansong.msparis.app.wardrobe.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.util.AccountUtil;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.wardrobe.activity.ProductDetailsActivity;
import com.qiansong.msparis.app.commom.bean.ProductBean;
import com.qiansong.msparis.app.commom.util.GlobalConsts;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Kevin on 2017/02/09.
 * <p>
 * 日常衣橱的商品列表adapter
 */
public class WardRobeProductAdapter extends RecyclerView.Adapter<WardRobeProductAdapter.ViewHolder> {

    private Context context;
    private List<ProductBean.DataEntity.RowsEntity> data;

    public WardRobeProductAdapter(Context context, List<ProductBean.DataEntity.RowsEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_sku, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ExclusiveUtils.setImageUrl(holder.iv,data.get(position).getCover_image(),0);
        holder.skuNameTv.setText(data.get(position).getName());
        holder.skuSizeTv.setText(data.get(position).getShow_specifications());

        holder.xiandingTv.setText(data.get(position).getLimit_tag());
        holder.view.setVisibility(View.GONE);


        holder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AccountUtil.showLoginView(context))return;
                HttpServiceClient.getInstance().wish(MyApplication.token,data.get(position).getId()).enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

                        if(response.isSuccessful()){
                            if("ok".equals(response.body().getStatus())){
//                                ContentUtil.makeToast(INSTANCE,dataEntity.getIs_favorite()==0?"收藏成功":"取消收藏");
                                holder.zan.setImageResource(data.get(position).getIs_favorite()==0?R.mipmap.icon_yes:R.mipmap.icon_no);
                                data.get(position).setIs_favorite(data.get(position).getIs_favorite()==0?1:0);
                                updateData(data);
                            }else {
                                ContentUtil.makeToast(context,response.body().getError().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                        ContentUtil.makeToast(context,"网络错误");
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA, new String[]{data.get(position).getId(),"1"});
                context.startActivity(intent);
            }
        });
        holder.zan.setImageResource(data.get(position).getIs_favorite()==0?R.mipmap.icon_no:R.mipmap.icon_yes);
        holder.skuVipTv.setVisibility(data.get(position).getType_id()==1?View.GONE:View.VISIBLE);
        holder.xiandingTv.setVisibility("".equals(data.get(position).getLimit_tag())?View.GONE:View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.fullDress_zan)
        ImageView zan;
        @InjectView(R.id.sku_vip_Tv)
        ImageView skuVipTv;
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
        @InjectView(R.id.sku_Rl)
        View view;
        @InjectView(R.id.sku_xianding_Tv)
        TextView xiandingTv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this,view);
        }
    }

    public void updateData(List<ProductBean.DataEntity.RowsEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
