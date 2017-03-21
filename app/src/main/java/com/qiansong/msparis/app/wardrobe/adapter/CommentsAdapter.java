package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.CommentsBean;
import com.qiansong.msparis.app.commom.util.DateUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/13.
 * <p>
 * 评论内容 item
 */

public class CommentsAdapter extends BaseAdapter {

    private Context context;
    List<CommentsBean.DataEntity.RowsEntity>data;

    public CommentsAdapter(Context context,List<CommentsBean.DataEntity.RowsEntity>data) {

        this.context = context;
        this.data=data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_comments, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemCommentsName.setText(data.get(position).getNickname());
        viewHolder.itemCommentsTime.setText(DateUtil.getTimeRange(data.get(position).getCreated_at()));
        viewHolder.itemCommentsValue.setText(data.get(position).getComment());
        for (int i=0;i<data.get(position).getStars();i++){
            viewHolder.star.setImageResource(context.getResources().getIdentifier("star"+data.get(position).getStars(),"mipmap", context.getPackageName()));
        }

        return convertView;
    }




    class ViewHolder {
//        @InjectView(R.id.item_comments_head)
//        SimpleDraweeView itemCommentsHead;
        @InjectView(R.id.item_comments_name)
        TextView itemCommentsName;
        @InjectView(R.id.item_comments_time)
        TextView itemCommentsTime;
        @InjectView(R.id.item_comments_ll)
        LinearLayout itemCommentsLl;
        @InjectView(R.id.item_comments_value)
        TextView itemCommentsValue;
        @InjectView(R.id.comments_satr)
        ImageView star;
        @InjectView(R.id.item_comments_size)
        TextView sizeTv;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);

        }
    }
}
