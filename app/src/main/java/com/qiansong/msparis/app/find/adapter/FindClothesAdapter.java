package com.qiansong.msparis.app.find.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.find.bean.FindDetailItemBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 *  美衣列表
 */
public class FindClothesAdapter extends BaseAdapter {
    public Context context;

    public List<FindDetailItemBean.DataBean.ClothesBean> list = null;

    public FindClothesAdapter(Context context){
        this.context = context;
    }
    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<FindDetailItemBean.DataBean.ClothesBean> list) {
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
        FindClothesAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_find_meiyi, null);
            viewHolder = new FindClothesAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (FindClothesAdapter.ViewHolder) convertView.getTag();
        }

        ExclusiveUtils.setImageUrl(viewHolder.find_image, list.get(position).getCover_img(), -1);

        return convertView;
    }


    public class ViewHolder {
        SimpleDraweeView find_image;

        public ViewHolder(View view) {
            find_image = (SimpleDraweeView) view.findViewById(R.id.find_image);
        }
    }

}
