package com.qiansong.msparis.app.fulldress.adapter;

/**
 * Created by yechen on 2017/3/7.
 * 礼服 确认订单
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ShoppingCartBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;

public class MakeOrderFulldressAdapter extends BaseAdapter{
    private Context context;
    private ShoppingCartBean bean;
    public MakeOrderFulldressAdapter(Context context,ShoppingCartBean bean){
        this.context=context;
        this.bean=bean;
    }
    @Override
    public int getCount() {
        return bean.getData().get(0).getItems()!=null?bean.getData().get(0).getItems().size():0;
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().get(0).getItems().get(position);
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
        ExclusiveUtils.setImageUrl(viewholder.image,bean.getData().get(0).getItems().get(position).getImage(),-1);
        viewholder.name.setText(bean.getData().get(0).getItems().get(position).getName());
        viewholder.brand.setText(bean.getData().get(0).getItems().get(position).getBrand());
        viewholder.specification.setText(bean.getData().get(0).getItems().get(position).getSpecification());
        return convertView;
    }
    public void updata(ShoppingCartBean bean){
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
