package com.qiansong.msparis.app.fulldress.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;

/**
 * Created by YECHEN on 2017/2/9.
 * 尺寸adapter
 */

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.MyViewHodler>{

    private Context context;
    public ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean;
    private int i;
    private int type;

    public SizeAdapter(Context context,ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean,int i,int type){
        this.context=context;
        this.bean=bean;
        this.i=i;
        this.type=type;
    }



    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=View.inflate(context, R.layout.item_choose,null);
        MyViewHodler viewHolder=new MyViewHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHodler viewholder, final int position) {

            viewholder.select.setText(bean.getOptions().get(position).getName());
            viewholder.unselect.setText(bean.getOptions().get(position).getName());
            if (bean.getOptions().get(position).select){
                viewholder.select.setVisibility(View.VISIBLE);
                viewholder.unselect.setVisibility(View.GONE);
            }else {
                viewholder.select.setVisibility(View.GONE);
                viewholder.unselect.setVisibility(View.VISIBLE);
            }


            viewholder.line.setOnClickListener(new View.OnClickListener() {
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
//        else if(2==type){
//            viewholder.select.setText(data.getColor().get(position).getName());
//            viewholder.unselect.setText(data.getColor().get(position).getName());
//            if (data.getColor().get(position).select){
//                viewholder.select.setVisibility(View.VISIBLE);
//                viewholder.unselect.setVisibility(View.GONE);
//            }else {
//                viewholder.select.setVisibility(View.GONE);
//                viewholder.unselect.setVisibility(View.VISIBLE);
//            }
//
//
//            viewholder.line.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (data.getColor().get(position).select) {
//                        data.getColor().get(position).select = false;
//                    } else {
//                        data.getColor().get(position).select = true;
//                    }
//                    updata(data);
//                }
//            });
//        }else if(3==type){
//            viewholder.select.setText(data.getScene().get(position).getName());
//            viewholder.unselect.setText(data.getScene().get(position).getName());
//            if (data.getScene().get(position).select){
//                viewholder.select.setVisibility(View.VISIBLE);
//                viewholder.unselect.setVisibility(View.GONE);
//            }else {
//                viewholder.select.setVisibility(View.GONE);
//                viewholder.unselect.setVisibility(View.VISIBLE);
//            }
//
//
//            viewholder.line.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (data.getScene().get(position).select) {
//                        data.getScene().get(position).select = false;
//                    } else {
//                        data.getScene().get(position).select = true;
//                    }
//                    updata(data);
//                }
//            });
//        }




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
        ContentUtil.makeLog("lzz","执行");
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


  public static class  MyViewHodler extends RecyclerView.ViewHolder{

        private TextView select,unselect;
        private View line;

        public MyViewHodler(View itemView) {
            super(itemView);

            //未选中
            unselect= (TextView) itemView.findViewById(R.id.unselect);
            //选中
            select= (TextView) itemView.findViewById(R.id.select);
            line=itemView.findViewById(R.id.item_choose_ll);
        }
    }
}
