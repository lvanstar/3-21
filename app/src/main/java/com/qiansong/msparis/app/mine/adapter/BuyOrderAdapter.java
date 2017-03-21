package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.OrderListBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.activity.EvaluateActivity;
import com.qiansong.msparis.app.mine.activity.LogisticsActivity;
import com.qiansong.msparis.app.mine.activity.MyOrderDetailsActivity;
import com.qiansong.msparis.app.wardrobe.activity.PayActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.data;

/**
 * Created by lizhaozhao on 2017/2/21.
 * <p>
 * 租赁适配数据
 */

public class BuyOrderAdapter extends RecyclerView.Adapter<BuyOrderAdapter.ViewHodler> {



    private Context context;
    private List<OrderListBean.DataEntity.RowsEntity>datas;
    public OptionsPickerView sexOptions;

    public BuyOrderAdapter(Context context,List<OrderListBean.DataEntity.RowsEntity>datas) {

        this.datas=datas;
        this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_buy_order, null);
        ViewHodler viewHodler = new ViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(final ViewHodler holder, final int position) {

        OrderInfoAdapter adapter = new OrderInfoAdapter(context,datas.get(position).getOrder_detail());
        holder.buyOrderInfoLv.setAdapter(adapter);
        ListUtils.setListViewHeightBasedOnChildrens(holder.buyOrderInfoLv);

        holder.buyOrderCodeTv.setText("订单编号:"+datas.get(position).getOrder_no());
        holder.buyOrderTimeTv.setText("租赁日期:"+datas.get(position).getLease_period());
        holder.buyOrderCountTv.setText("服装格子增项X"+datas.get(position).getClothing_lattice_num());
        holder.buyOrderCountPriceTv.setText("¥"+datas.get(position).getClothing_lattice_price());
        holder.buyOrderMumberPriceTv.setText("总计"+datas.get(position).getProduct_num()+"件服装,"+datas.get(position).getClothing_lattice_num()+
        "个服装格子增项,合计¥"+datas.get(position).getTotal_sale()+"(含运费:¥"+datas.get(position).getAmount_shipping()+")");

        //订单状态判断 1.待支付 2.已支付 3.部分发货 4.已发货 5.待归还 6. 部分归还7.已完成 8.超时 9.取消 10.部分退货 11整单退货 12.已关闭 13.已收货
        switch (datas.get(position).getStatus()){

            case "1":
                holder.buyOrderStatusTv.setText("待付款");
                holder.buyOrderStatusTv.setBackgroundResource(R.drawable.violet_round_shap);
                holder.leaseOrderBuyRl.setVisibility(View.VISIBLE);
                holder.leaseOrderBuyTv.setText("去付款");
                holder.leaseOrderBuyTv.setTextColor(Color.WHITE);
                holder.leaseOrderBuyTv.setBackgroundColor(Color.BLACK);

                holder.leaseOrderBuyTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, PayActivity.class);
                        intent.putExtra("PayActivity",datas.get(position).getOrder_no());
                        context.startActivity(intent);
                    }
                });
                break;
            case "2":
                holder.buyOrderStatusTv.setText("已支付");
                holder.buyOrderStatusTv.setBackgroundResource(R.drawable.violet_round_shap);
                holder.leaseOrderBuyRl.setVisibility(View.VISIBLE);
                holder.leaseOrderBuyTv.setText("取消订单");
                holder.leaseOrderBuyTv.setTextColor(Color.BLACK);
                holder.leaseOrderBuyTv.setBackgroundResource(R.drawable.gray_shap);

                holder.leaseOrderBuyTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCancelOrder(holder);
                    }
                });
                break;
            case "4":
                holder.buyOrderStatusTv.setText("已发货");
                holder.buyOrderStatusTv.setBackgroundResource(R.drawable.violet_round_shap);
                holder.leaseOrderBuyRl.setVisibility(View.VISIBLE);
                holder.leaseOrderBuyTv.setText("查看物流");
                holder.leaseOrderBuyTv.setTextColor(Color.WHITE);
                holder.leaseOrderBuyTv.setBackgroundColor(Color.BLACK);

                holder.leaseOrderBuyTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, LogisticsActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;

            case "7":
                holder.buyOrderStatusTv.setText("已完成");
                holder.buyOrderStatusTv.setBackgroundResource(R.drawable.violet_round_shap);
                holder.leaseOrderBuyRl.setVisibility(View.VISIBLE);
                holder.leaseOrderBuyTv.setText("去评价");
                holder.leaseOrderBuyTv.setTextColor(Color.WHITE);
                holder.leaseOrderBuyTv.setBackgroundColor(Color.BLACK);

                holder.leaseOrderBuyTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, EvaluateActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;

            case "8":
//                holder.buyOrderStatusTv.setText("已超时");
//                holder.buyOrderStatusTv.setBackgroundResource(R.drawable.gray_all_shap);
//                holder.leaseOrderBuyRl.setVisibility(View.GONE);
//                holder.leaseOrderBuyTv.setTextColor(Color.WHITE);
//                holder.leaseOrderBuyTv.setBackgroundColor(Color.BLACK);
                setStatus(holder,"已超时");
                break;
            case "9":
//                holder.buyOrderStatusTv.setText("已取消");
//                holder.buyOrderStatusTv.setBackgroundResource(R.drawable.gray_all_shap);
//                holder.leaseOrderBuyRl.setVisibility(View.GONE);
//                holder.leaseOrderBuyTv.setTextColor(Color.WHITE);
//                holder.leaseOrderBuyTv.setBackgroundColor(Color.BLACK);

                setStatus(holder,"已取消");
                break;
        }


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MyOrderDetailsActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,datas.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    private void setStatus(ViewHodler holder,String status){
        holder.buyOrderStatusTv.setText(status);
        holder.buyOrderStatusTv.setBackgroundResource(R.drawable.gray_all_shap);
        holder.leaseOrderBuyRl.setVisibility(View.GONE);
        holder.leaseOrderBuyTv.setTextColor(Color.WHITE);
        holder.leaseOrderBuyTv.setBackgroundColor(Color.BLACK);
    }

    private void showCancelOrder(final ViewHodler hodler){
        final ArrayList<String>list=new ArrayList<>();
        list.add("我不想买了");
        list.add("信息填写错误,重新购买");
        list.add("卖家缺货");
        list.add("其他原因");
        sexOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                ContentUtil.makeLog("lzz",list.get(options1));
                setStatus(hodler,"已取消");
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("选择取消订单原因")
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setSubCalSize(18)//确定取消按钮大小
                .setLineSpacingMultiplier(1.8f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)//设置滚轮文字大小
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .build();
        sexOptions.setPicker(list);
    }

    public void update(List<OrderListBean.DataEntity.RowsEntity>datas){

        this.datas=datas;
        notifyDataSetChanged();
    }


    class ViewHodler extends RecyclerView.ViewHolder {

        @InjectView(R.id.buy_order_code_Tv)
        TextView buyOrderCodeTv;
        @InjectView(R.id.buy_order_time_Tv)
        TextView buyOrderTimeTv;
        @InjectView(R.id.buy_order_status_Tv)
        TextView buyOrderStatusTv;
        @InjectView(R.id.lease_order_startTime_Tv)
        TextView leaseOrderStartTimeTv;
        @InjectView(R.id.lease_order_endTime_Tv)
        TextView leaseOrderEndTimeTv;
        @InjectView(R.id.buy_order_info_lv)
        ListView buyOrderInfoLv;
        @InjectView(R.id.buy_order_mumberPrice_Tv)
        TextView buyOrderMumberPriceTv;
        @InjectView(R.id.lease_order_buy_Tv)
        TextView leaseOrderBuyTv;
        @InjectView(R.id.lease_order_buy_Rl)
        RelativeLayout leaseOrderBuyRl;
        @InjectView(R.id.item_buy_oeder_count_Tv)
        TextView buyOrderCountTv;
        @InjectView(R.id.item_buy_oeder_countPrice_Tv)
        TextView buyOrderCountPriceTv;
        @InjectView(R.id.lease_order_ll)
        View view;

        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


}
