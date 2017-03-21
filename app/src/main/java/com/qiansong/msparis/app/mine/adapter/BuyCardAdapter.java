package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.bean.BuyCardBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.mine.activity.MemberCardDetailsActivity;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 *
 *  购买女神卡
 */
public class BuyCardAdapter extends RecyclerView.Adapter<BuyCardAdapter.ViewHolder> {
    private Context context;
    private  BuyCardItemAdapter adapter;
    private  List<BuyCardBean.DataBean.RowsBeanX> data = null;

    public BuyCardAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<BuyCardBean.DataBean.RowsBeanX> data){
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return data!=null ? data.size() : 0 ;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_fragment_buy_card,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {



        holder.title.setText(data.get(position).getType());
        adapter = new BuyCardItemAdapter(context);
        adapter.setData(data.get(position).getRows());
        holder.buyList.setAdapter(adapter);

        //设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(holder.buyList,2);
        holder.buyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, MemberCardDetailsActivity.class);
                context.startActivity(intent);
            }
        });

        holder.buyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionid, long id) {
                Intent intent = new Intent();
                intent.setClass(context, MemberCardDetailsActivity.class);
                intent.putExtra(GlobalConsts.MEMBERCARD_ID,data.get(position).getRows().get(positionid).getId()+"");
                context. startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        GridView buyList;
        public ViewHolder(View view){
            super(view);
            title= (TextView) view.findViewById(R.id.iem_cart_title);
            buyList = (GridView) view.findViewById(R.id.iem_cart_list);
//            ListUtils.setGridViewHeightBasedOnChildren(buyList,3);
        }
    }

}
