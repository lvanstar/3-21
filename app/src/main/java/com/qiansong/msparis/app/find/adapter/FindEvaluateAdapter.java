package com.qiansong.msparis.app.find.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.find.bean.FindDetailItemBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * 评级列表
 */
public class FindEvaluateAdapter extends BaseAdapter {
    public Context context;

    public List<FindDetailItemBean.DataBean.CommentsBean> list = null;

    public FindEvaluateAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<FindDetailItemBean.DataBean.CommentsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FindEvaluateAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_find_evaluate, null);
            viewHolder = new FindEvaluateAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (FindEvaluateAdapter.ViewHolder) convertView.getTag();
        }

        ExclusiveUtils.setImageUrl(viewHolder.find_image, list.get(position).getUser().getHead_portrait(), -1);
        viewHolder.find_evaluate_messages.setText(list.get(position).getContent());
        viewHolder.find_evaluate_date.setText(list.get(position).getCreated_at());

        return convertView;
    }


    public class ViewHolder {
        SimpleDraweeView find_image;
        TextView find_evaluate_messages, find_evaluate_date;

        public ViewHolder(View view) {
            find_image = (SimpleDraweeView) view.findViewById(R.id.find_evaluate_image);
            find_evaluate_messages = (TextView) view.findViewById(R.id.find_evaluate_messages);
            find_evaluate_date = (TextView) view.findViewById(R.id.find_evaluate_date);
        }
    }

}
