package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ProductsBean;

/**
 * Created by YECHEN on 2017/2/9.
 * 尺寸adapter
 */

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolder>{

    private Context context;
    public ProductsBean.DataEntity.SpecificationsEntity data;
    public ProductSizeAdapter(Context context, ProductsBean.DataEntity.SpecificationsEntity data){
        this.context=context;
        this.data=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_details_choose, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, final int position) {

        viewholder.select.setText(data.getOptions().get(position).getName());
        viewholder.unselect.setText(data.getOptions().get(position).getName());
        viewholder.out.setText(data.getOptions().get(position).getName());
//        if(data.get(0).getOptions().get(position).isOut){
//            viewholder.select.setVisibility(View.GONE);
//            viewholder.unselect.setVisibility(View.GONE);
//            viewholder.out.setVisibility(View.VISIBLE);
//            viewholder.out.setText(data.get(0).getOptions().get(position).getName());
//            ContentUtil.makeLog("lzz","11111111");
//
//        }else {
//            viewholder.out.setVisibility(View.GONE);
        if (data.getOptions().get(position).select){
            viewholder.select.setVisibility(View.VISIBLE);
            viewholder.unselect.setVisibility(View.GONE);
        }else {
            viewholder.select.setVisibility(View.GONE);
            viewholder.unselect.setVisibility(View.VISIBLE);
        }
//        }

        viewholder.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.getOptions().get(position).select=true;
                for (int i=0;i<data.getOptions().size();i++){
                    if(i!=position){
                        data.getOptions().get(i).select=false;
                    }
                }
//                ProductDetailsActivity.setUpData();
                updata(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.getOptions().size();
    }

    public void updata(ProductsBean.DataEntity.SpecificationsEntity data){
        this.data=data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        private TextView select,unselect,out;
         private View line;
        public ViewHolder(View view){
            super(view);
            //未选中
            unselect= (TextView) view.findViewById(R.id.unselect);
            //选中
            select= (TextView) view.findViewById(R.id.select);
            //缺少型号
            out= (TextView) view.findViewById(R.id.out);
            line=view.findViewById(R.id.item_details_choose_ll);

        }
    }
}
