package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.activity.MyOrderDetailsActivity;
import com.qiansong.msparis.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/21.
 * <p>
 * 租赁适配数据
 */

public class LeaseOrderAdapter extends RecyclerView.Adapter<LeaseOrderAdapter.ViewHodler> {



    private Context context;

    public LeaseOrderAdapter(Context context) {

        this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_lease_order, null);
        ViewHodler viewHodler = new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {

//        OrderInfoAdapter adapter=new OrderInfoAdapter(context);
//        holder.leaseOrderInfoLv.setAdapter(adapter);
//        ListUtils.setListViewHeightBasedOnChildrens(holder.leaseOrderInfoLv);

        holder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MyOrderDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        @InjectView(R.id.lease_order_code_Tv)
        TextView leaseOrderCodeTv;
        @InjectView(R.id.lease_order_status_Tv)
        TextView leaseOrderStatusTv;
        @InjectView(R.id.lease_order_startTime_Tv)
        TextView leaseOrderStartTimeTv;
        @InjectView(R.id.lease_order_endTime_Tv)
        TextView leaseOrderEndTimeTv;
        @InjectView(R.id.lease_order_info_lv)
        ListView leaseOrderInfoLv;
        @InjectView(R.id.lease_order_mumber_Tv)
        TextView leaseOrderMumberTv;
        @InjectView(R.id.lease_order_mumberPrice_Tv)
        TextView leaseOrderMumberPriceTv;
        @InjectView(R.id.lease_order_info_Tv)
        TextView leaseOrderInfoTv;
        @InjectView(R.id.lease_order_buy_Tv)
        TextView leaseOrderBuyTv;
        @InjectView(R.id.lease_order_buy_Rl)
        RelativeLayout leaseOrderBuyRl;
        @InjectView(R.id.lease_order_Ll)
        View line;
        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }



}
