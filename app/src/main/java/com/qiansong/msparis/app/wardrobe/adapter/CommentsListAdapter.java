package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.app.commom.bean.CommentsBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.DateUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/14.
 * <p>
 * 评论列表适配器
 */

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.ViewHolder> {


    private Context context;
    private List<CommentsBean.DataEntity.RowsEntity> data;

    public CommentsListAdapter(Context context, List<CommentsBean.DataEntity.RowsEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_comments, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.itemCommentsName.setText(data.get(position).getNickname());
        viewHolder.itemCommentsTime.setText(DateUtil.getTimeRange(data.get(position).getCreated_at()));
        viewHolder.itemCommentsValue.setText(data.get(position).getComment());
        for (int i=0;i<data.get(position).getStars();i++){
            viewHolder.commentsSatr.setImageResource(context.getResources().getIdentifier("star"+data.get(position).getStars(),"mipmap", context.getPackageName()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_comments_head)
        ImageView itemCommentsHead;
        @InjectView(R.id.item_comments_name)
        TextView itemCommentsName;
        @InjectView(R.id.item_comments_time)
        TextView itemCommentsTime;
        @InjectView(R.id.comments_satr)
        ImageView commentsSatr;
        @InjectView(R.id.item_comments_ll)
        LinearLayout itemCommentsLl;
        @InjectView(R.id.item_comments_value)
        TextView itemCommentsValue;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

    public void updateData(List<CommentsBean.DataEntity.RowsEntity>data){
        this.data=data;
        notifyDataSetChanged();
    }
}
