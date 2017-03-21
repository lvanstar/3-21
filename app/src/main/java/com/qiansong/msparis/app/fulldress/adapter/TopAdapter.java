package com.qiansong.msparis.app.fulldress.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/3/2.
 * <p>
 * 头部适配器
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.MyViewHodler> {


    private Context context;
    private List<ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity> datas;
    private int type;

    public TopAdapter(Context context, List<ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity> datas,int type) {

        this.context = context;
        this.datas = datas;
        this.type=type;

    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_top, null);
        MyViewHodler viewHolder = new MyViewHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {

        holder.itemTopTitle.setText(datas.get(position).getName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.itemTopRecyler.setLayoutManager(linearLayoutManager);

        // 0表示不是颜色 1是
        if("1".equals(datas.get(position).is_color)){

            ColorAdapter colorAdapter=new ColorAdapter(context,datas.get(position),position,type);
            holder.itemTopRecyler.setAdapter(colorAdapter);
        }else {
            SizeAdapter sizeAdapter=new SizeAdapter(context,datas.get(position),position,type);
            holder.itemTopRecyler.setAdapter(sizeAdapter);

        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_top_title)
        TextView itemTopTitle;
        @InjectView(R.id.item_top_recyler)
        RecyclerView itemTopRecyler;
        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }


}
