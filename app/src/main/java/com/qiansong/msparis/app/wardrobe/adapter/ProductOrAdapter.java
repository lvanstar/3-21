package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qiansong.msparis.app.commom.bean.ProductsBean;
import com.qiansong.msparis.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/3/6.
 */

public class ProductOrAdapter extends RecyclerView.Adapter<ProductOrAdapter.MyViewHodler>{

    private Context context;
    private List<ProductsBean.DataEntity.SpecificationsEntity>datas;

    public ProductOrAdapter(Context context,List<ProductsBean.DataEntity.SpecificationsEntity>datas){

        this.context=context;
        this.datas=datas;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_deatils_recycler, null);
        MyViewHodler viewHolder = new MyViewHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        ProductSizeAdapter adapter=new ProductSizeAdapter(context,datas.get(position));
        holder.recyclerView.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        @InjectView(R.id.item_details_recyler)
        RecyclerView recyclerView;
        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
