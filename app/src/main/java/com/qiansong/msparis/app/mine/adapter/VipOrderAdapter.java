package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.app.commom.bean.CardListBean;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/21.
 * <p>
 * 租赁适配数据
 */

public class VipOrderAdapter extends RecyclerView.Adapter<VipOrderAdapter.ViewHodler> {



    private Context context;
    private List<CardListBean.DataEntity.RowsEntity>datas;

    public VipOrderAdapter(Context context, List<CardListBean.DataEntity.RowsEntity>datas) {

        this.context = context;
        this.datas=datas;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_vip_order, null);
        ViewHodler viewHodler = new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {

        VipOrderInfoAdapter adapter = new VipOrderInfoAdapter(context,datas.get(position).getOrder_detail());
        holder.vipOrderInfoLv.setAdapter(adapter);
        ListUtils.setListViewHeightBasedOnChildrens(holder.vipOrderInfoLv);

        holder.vipOrderBuyTv.setText("订单编号:"+datas.get(position).getOrder_number());
        holder.vipOrderInfoTv.setText("总计"+datas.get(position).getProduct_num()+"件美衣");
        holder.vipOrderPriceTv.setText("合计¥"+datas.get(position).getTotal_sale());

    }

    public void mergeData(List<CardListBean.DataEntity.RowsEntity>datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHodler extends RecyclerView.ViewHolder {
        @InjectView(R.id.vip_order_code_Tv)
        TextView vipOrderCodeTv;
        @InjectView(R.id.vip_order_status_Tv)
        TextView vipOrderStatusTv;
        @InjectView(R.id.vip_order_info_lv)
        ListView vipOrderInfoLv;
        @InjectView(R.id.vip_order_mumberPrice_Tv)
        TextView vipOrderMumberPriceTv;
        @InjectView(R.id.vip_order_info_Tv)
        TextView vipOrderInfoTv;
        @InjectView(R.id.vip_order_buy_Tv)
        TextView vipOrderBuyTv;
        @InjectView(R.id.vip_order_buy_Rl)
        RelativeLayout vipOrderBuyRl;
        @InjectView(R.id.vip_order_price_Tv)
        TextView vipOrderPriceTv;

        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


}
