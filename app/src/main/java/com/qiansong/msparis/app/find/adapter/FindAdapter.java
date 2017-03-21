package com.qiansong.msparis.app.find.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.FindListBean;
import com.qiansong.msparis.app.find.view.MultiImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/3/17.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {

    private Context context;
    private List<FindListBean.DataEntity.RowsEntity>datas;


    public FindAdapter(Context context, List<FindListBean.DataEntity.RowsEntity>datas){
        this.context=context;
        this.datas=datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item_find,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FindListBean.DataEntity.RowsEntity entity=datas.get(position);

        holder.itemFindHeadIv.setImageURI(Uri.parse(entity.getUser().getHead_portrait()));
        holder.itemFindNameTv.setText(entity.getUser().getNickname());
        holder.itemFindCommentTv.setText(entity.getComment_num()+"");
        holder.itemFindAttionTv.setText(entity.getLike_num()+"");
        holder.itemFindContentTv.setText(entity.getContent());
        holder.itemFindTimeTv.setText(entity.getCreated_at());
        holder.itemFindAddressTv.setText(entity.getCity()+"*"+entity.getAddress());
        List<String>list=new ArrayList<>();
        for (int i=0;i<entity.getImages().size();i++){
            list.add(entity.getImages().get(i).getSrc());
        }
        holder.itemFindMultiImage.setList(list);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.item_find_headIv)
        SimpleDraweeView itemFindHeadIv;
        @InjectView(R.id.item_find_nameTv)
        TextView itemFindNameTv;
        @InjectView(R.id.item_find_collectTv)
        TextView itemFindCollectTv;
        @InjectView(R.id.item_find_commentTv)
        TextView itemFindCommentTv;
        @InjectView(R.id.item_find_attionTv)
        TextView itemFindAttionTv;
        @InjectView(R.id.item_find_contentTv)
        TextView itemFindContentTv;
        @InjectView(R.id.item_find_multiImage)
        MultiImageView itemFindMultiImage;
        @InjectView(R.id.item_find_timeTv)
        TextView itemFindTimeTv;
        @InjectView(R.id.item_find_addressTv)
        TextView itemFindAddressTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
