package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.mine.bean.PrivilegeBean;
import com.qiansong.msparis.app.mine.bean.RenewCardBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 所有特权
 */
public class PrivileqesItemAdapter extends BaseAdapter {
    public Context context;

    public List<PrivilegeBean.DataBean.RowsBeanX.RowsBean> list = null;
    public  int index = 0;
    public PrivileqesItemAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<PrivilegeBean.DataBean.RowsBeanX.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 设置选中位置
     *
     * @param index
     */
    public  void  setIndex(int index){
        this.index = index;
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
        PrivileqesItemAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_privileges_item, null);
            viewHolder = new PrivileqesItemAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (PrivileqesItemAdapter.ViewHolder) convertView.getTag();
        }
        ExclusiveUtils.setImageUrl(viewHolder.member_item_iv, list.get(position).getIcon(), -1);
        viewHolder.item_renew_name.setText(list.get(position).getName());


        return convertView;
    }


    public class ViewHolder {
        SimpleDraweeView member_item_iv;
        TextView item_renew_name;

        public ViewHolder(View view) {
            member_item_iv = (SimpleDraweeView) view.findViewById(R.id.member_item_iv);
            item_renew_name = (TextView) view.findViewById(R.id.member_item_title);

        }
    }

}
