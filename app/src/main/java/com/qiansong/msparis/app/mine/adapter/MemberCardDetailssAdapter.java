package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.mine.bean.BuyCarDetailsBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 女神卡详情
 */
public class MemberCardDetailssAdapter extends BaseAdapter {
    public Context context;

    public List<BuyCarDetailsBean.DataBean.CardRoleBean> list = null;

    public MemberCardDetailssAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<BuyCarDetailsBean.DataBean.CardRoleBean>  list) {
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
        MemberCardDetailssAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_member_details, null);
            viewHolder = new MemberCardDetailssAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (MemberCardDetailssAdapter.ViewHolder) convertView.getTag();
        }

        //设置值
        viewHolder.text.setText(list.get(position).getName());

        ExclusiveUtils.setImageUrl(viewHolder.image, list.get(position).getImage_src(), -1);

        return convertView;
    }


    public class ViewHolder {
        TextView text;
        SimpleDraweeView image;

        public ViewHolder(View view) {
            image = (SimpleDraweeView) view.findViewById(R.id.item_details_image);
            text = (TextView) view.findViewById(R.id.item_details_text);

        }
    }

}
