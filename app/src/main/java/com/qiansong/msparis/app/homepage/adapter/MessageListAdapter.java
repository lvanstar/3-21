package com.qiansong.msparis.app.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.MessageBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/3/15.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    private Context context;
    private List<MessageBean.DataEntity.RowsEntity>datas;

    public MessageListAdapter(Context context, List<MessageBean.DataEntity.RowsEntity>datas){

        this.context=context;
        this.datas=datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_message_list, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemMessageListTimeTv.setText(datas.get(position).getCreated_at());
        holder.itemMessageListValueTv.setText(datas.get(position).getContent());
        switch (datas.get(position).getType()){
            case 1:
                holder.itemMessageListIv.setImageResource(R.mipmap.msg1);
                break;
            case 2:
                holder.itemMessageListIv.setImageResource(R.mipmap.msg2);
                break;
            case 3:
                holder.itemMessageListIv.setImageResource(R.mipmap.msg3);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void mergeData(List<MessageBean.DataEntity.RowsEntity>datas){

        this.datas=datas;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.item_messageListIv)
        ImageView itemMessageListIv;
        @InjectView(R.id.item_messageListValueTv)
        TextView itemMessageListValueTv;
        @InjectView(R.id.item_messageListTimeTv)
        TextView itemMessageListTimeTv;

       public ViewHolder(View view) {
           super(view);
           ButterKnife.inject(this, view);
        }
    }
}
