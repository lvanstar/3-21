package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.bean.DepositBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/9.
 * <p>
 * 我的押金适配器
 */

public class DepositAdapter extends RecyclerView.Adapter<DepositAdapter.ViewHolder> {
    private Context context;
    private List<DepositBean.DataBean.RowsBean> data = null ;

    public DepositAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return data !=null ? data.size() :0 ;
    }

    public void setData(List<DepositBean.DataBean.RowsBean> data) {
        this.data = data ;
        notifyDataSetChanged();
    }

    @Override
    public DepositAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_deposit, null);
        return new DepositAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepositAdapter.ViewHolder viewHolder, final int position) {
        //order：租凭，card：体验卡
        if ("order".equals(data.get(position).getType())) {
            viewHolder.deposit_text1.setText("押金类型：租赁押金");
        }else if ("card".equals(data.get(position).getType())){
            viewHolder.deposit_text1.setText("押金类型：体验卡押金");
        }
        viewHolder.deposit_text2.setText("会员卡号："+data.get(position).getCard_no());
        viewHolder.deposit_text3.setText("押金金额："+data.get(position).getPay_amount());
        viewHolder.deposit_text4.setText("支付日期："+data.get(position).getPaid_at());
        //wxpay：微支付，alipay：支付宝
        if ("wxpay".equals(data.get(position).getPay_method())) {
            viewHolder.deposit_text5.setText("支付方式：微信支付");
        }else if ("alipay".equals(data.get(position).getType())){
            viewHolder.deposit_text5.setText("支付方式：支付宝支付");
        }
        viewHolder.deposit_text6.setText("支付单号："+data.get(position).getOrder_no());
        //1：待申请退款，2：待处理退款，3：已退款
        if(Integer.parseInt(data.get(position).getStatus()) == 1 ){
            viewHolder.deposit_button.setText("待申请退款");
        }else if(Integer.parseInt(data.get(position).getStatus()) == 2 ){
            viewHolder.deposit_button.setText("待处理退款");
        }else if(Integer.parseInt(data.get(position).getStatus()) == 3 ){
            viewHolder.deposit_button.setText("已退款");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deposit_text1,deposit_text2,deposit_text3,deposit_text4,deposit_text5,deposit_text6,deposit_button;
        public ViewHolder(View view) {
            super(view);

            deposit_text1 = (TextView) view.findViewById(R.id.deposit_text1);
            deposit_text2 = (TextView) view.findViewById(R.id.deposit_text2);
            deposit_text3 = (TextView) view.findViewById(R.id.deposit_text3);
            deposit_text4 = (TextView) view.findViewById(R.id.deposit_text4);
            deposit_text5 = (TextView) view.findViewById(R.id.deposit_text5);
            deposit_text6 = (TextView) view.findViewById(R.id.deposit_text6);
            deposit_button = (TextView) view.findViewById(R.id.deposit_button);


        }
    }
}
