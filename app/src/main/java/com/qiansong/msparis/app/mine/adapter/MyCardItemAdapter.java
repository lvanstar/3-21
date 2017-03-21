package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.mine.bean.MyCardBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 我的女神卡 item
 */
public class MyCardItemAdapter extends BaseAdapter {
    public Context context;

    public List<MyCardBean.DataBean.RowsBean.CardRoleBean> list = null;

    public MyCardItemAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<MyCardBean.DataBean.RowsBean.CardRoleBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyCardItemAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_member, null);
            viewHolder = new MyCardItemAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (MyCardItemAdapter.ViewHolder) convertView.getTag();
        }

        ExclusiveUtils.setImageUrl(viewHolder.member_item_iv, list.get(position).getIcon(), -1);
        //设置值
        viewHolder.member_item_title.setText(list.get(position).getName());

        return convertView;
    }


    public class ViewHolder {
        TextView member_item_title;
        SimpleDraweeView member_item_iv;

        public ViewHolder(View view) {
            member_item_title = (TextView) view.findViewById(R.id.member_item_title);
            member_item_iv = (SimpleDraweeView) view.findViewById(R.id.member_item_iv);
        }
    }

}
