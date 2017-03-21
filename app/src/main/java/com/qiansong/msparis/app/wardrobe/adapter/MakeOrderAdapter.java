package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.OnePackagesBean;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;

/**
 * Created by yechen on 2017/2/16.
 * 常服 - 确认订单
 */

public class MakeOrderAdapter extends BaseAdapter{
    private Context context;
    private OnePackagesBean bean;
    public MakeOrderAdapter(Context context,OnePackagesBean bean){
        this.context=context;
        this.bean=bean;
    }
    @Override
    public int getCount() {
        return bean.getData().getPackage_items()!=null?bean.getData().getPackage_items().size():0;
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getPackage_items().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_make_order,null);
            viewholder=new ViewHolder(convertView);
            convertView.setTag(viewholder);
        }else {
            viewholder= (ViewHolder) convertView.getTag();
        }
        ExclusiveUtils.setImageUrl(viewholder.image,bean.getData().getPackage_items().get(position).getImage(),-1);
        viewholder.name.setText(bean.getData().getPackage_items().get(position).getName());
        viewholder.brand.setText(bean.getData().getPackage_items().get(position).getBrand());
        viewholder.specification.setText(bean.getData().getPackage_items().get(position).getSpecification());
        return convertView;
    }
    public void updata(OnePackagesBean bean){
        this.bean=bean;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        SimpleDraweeView image;
        TextView name,brand,specification;

        public ViewHolder(View view) {
            name= (TextView) view.findViewById(R.id.name);
            brand= (TextView) view.findViewById(R.id.brand);
            specification= (TextView) view.findViewById(R.id.specification);
            image= (SimpleDraweeView) view.findViewById(R.id.image);
        }
    }
}
