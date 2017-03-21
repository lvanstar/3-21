package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.util.DateUtil;
import com.qiansong.msparis.app.wardrobe.selfview.ShoppingBagView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/3/3.
 * <p>
 * 我的购物袋列表
 */

public class MyShoppingBagAdapter extends RecyclerView.Adapter<MyShoppingBagAdapter.ViewHolder> {


    private Context context;
    private List<PackagesBean.DataBean.UserPackageBean> datas;
    private ShoppingBagView.OnClickListener listener;

    public MyShoppingBagAdapter(Context context, List<PackagesBean.DataBean.UserPackageBean> datas, ShoppingBagView.OnClickListener listener) {

        this.context = context;
        this.datas = datas;
        this.listener=listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shopping_bag,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        /**
         * 点击事件
         */
        holder.itemShoppingBagRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(position);
            }
        });

        //显示数量
        holder.itemShoppingBagNumberTv.setText(datas.get(position).getItems_count()+"/"+datas.get(position).getBackage_grid_limit());

        //判断购物袋里是否有商品
        if(!"0".equals(datas.get(position).getItems_count())){

            holder.itemShoppingBagTimeTv.setText(DateUtil.getDate(datas.get(position).getDelivery_date())+"  "+DateUtil.getDate(datas.get(position).getRental_expiry_date()));

        }else {
            holder.itemShoppingBagTimeTv.setText("点击后需要指定起止日期");
        }
        //是否是VIP购物袋
        if(datas.get(position).isCan_buy_vip()){

            holder.itemShoppingBagRl.setBackgroundColor(context.getResources().getColor(R.color.font19));
            holder.itemShoppingBagNumberTv.setBackgroundColor(context.getResources().getColor(R.color.font24));
            holder.itemShoppingBagNameTv.setText("VIP购物袋"+(position+1));
            //商品已满
            if(datas.get(position).getItems_count().equals(datas.get(position).getBackage_grid_limit())||"pause".equals(datas.get(position).getUser_card_paused())){
                holder.itemShoppingBagRl.setBackgroundColor(context.getResources().getColor(R.color.font20));
            }

        }else {
            holder.itemShoppingBagRl.setBackgroundColor(context.getResources().getColor(R.color.font26));
            holder.itemShoppingBagNumberTv.setBackgroundColor(context.getResources().getColor(R.color.violet));
            holder.itemShoppingBagNumberTv.setTextColor(context.getResources().getColor(R.color.white));
            holder.itemShoppingBagNameTv.setText("购物袋"+(position+1));

            //商品已满
            if(datas.get(position).getItems_count().equals(datas.get(position).getBackage_grid_limit())||"pause".equals(datas.get(position).getUser_card_paused())){
                holder.itemShoppingBagRl.setBackgroundColor(context.getResources().getColor(R.color.color_violet));
            }
        }
    }

    public void update(List<PackagesBean.DataBean.UserPackageBean>datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.item_shopping_bag_name_Tv)
        TextView itemShoppingBagNameTv;
        @InjectView(R.id.item_shopping_bag_time_Tv)
        TextView itemShoppingBagTimeTv;
        @InjectView(R.id.item_shopping_bag_Ll)
        LinearLayout itemShoppingBagLl;
        @InjectView(R.id.item_shopping_bag_number_Tv)
        TextView itemShoppingBagNumberTv;
        @InjectView(R.id.item_shopping_bag_Rl)
        RelativeLayout itemShoppingBagRl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
