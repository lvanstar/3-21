package com.qiansong.msparis.app.fulldress.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.GlobalConsts;

/**
 * Created by YECHEN on 2017/2/9.
 * 尺寸adapter
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHodler>{

    private Context context;
    public ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean;
    private int i;
    private int type; //用于区分从何处来
    public ColorAdapter(Context context,ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity data,int i,int type){
        this.context=context;
        this.bean=data;
        this.i=i;
        this.type=type;
    }


    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=View.inflate(context, R.layout.item_color,null);
        MyViewHodler viewHodler=new MyViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler viewholder, final int position) {

        if (bean.getOptions().get(position).select){
            viewholder.color_select.setBackgroundResource(R.drawable.gray_circle_shap);
        }else {
            viewholder.color_select.setBackgroundResource(R.drawable.white_circle_shap);
        }
        ((GradientDrawable)viewholder.color_info.getBackground())
                .setColor(Color.parseColor("#"+bean.getOptions().get(position).getValue()));

        viewholder.color_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getOptions().get(position).select) {
                    bean.getOptions().get(position).select = false;
                } else {
                    bean.getOptions().get(position).select = true;
                }
               updata(bean);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return bean.getOptions().size();
    }


    public void updata(ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity data){
        this.bean=data;
        Intent intent=new Intent();
        intent.putExtra(GlobalConsts.VALUE,"");
        intent.putExtra(GlobalConsts.INIT_DATA,bean);
        intent.putExtra(GlobalConsts.POSTION,i);
        switch (type){
            case 1:
                intent.setAction(GlobalConsts.FILE_DATA);
                context.sendBroadcast(intent);
                break;
            case 2:
                intent.setAction(GlobalConsts.FILE_DATAS);
                context.sendBroadcast(intent);
                break;
            case 3:
                intent.setAction(GlobalConsts.FILE_DATASS);
                context.sendBroadcast(intent);
                break;
            default:
                break;
        }

        notifyDataSetChanged();
    }



    class MyViewHodler extends RecyclerView.ViewHolder{

        private View color_info,color_select;

        public MyViewHodler(View itemView) {
            super(itemView);

            color_info = itemView.findViewById(R.id.color_info);
            color_select = itemView.findViewById(R.id.color_info2);
        }
    }
}
