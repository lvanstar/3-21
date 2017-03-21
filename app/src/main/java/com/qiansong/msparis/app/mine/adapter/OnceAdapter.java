package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.OnceJsonBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.homepage.bean.DayNewBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 * 我的衣橱-曾经拥有
 */

public class OnceAdapter extends BaseAdapter{
    private Context context;
    public List<OnceJsonBean.DataBean.RowsBean> data;
    public OnceAdapter(Context context,List<OnceJsonBean.DataBean.RowsBean> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_once,null);
            viewholder=new ViewHolder(convertView);
            convertView.setTag(viewholder);
        }else {
            viewholder= (ViewHolder) convertView.getTag();
        }
        viewholder.market_price.setText(data.get(position).getBase_discount()+"折购买 ￥"+data.get(position).getMarket_price());
        ExclusiveUtils.setImageUrl(viewholder.image_url,data.get(position).getImage_url(),-1);
        if (2==data.get(position).getType_id()){
            viewholder.type_id.setVisibility(View.VISIBLE);
        }else {
            viewholder.type_id.setVisibility(View.GONE);
        }
        return convertView;
    }
    public void updata(List<OnceJsonBean.DataBean.RowsBean> data){
        this.data=data;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        SimpleDraweeView image_url;
        TextView market_price;
              ImageView type_id;
        public ViewHolder(View view){
            image_url= (SimpleDraweeView) view.findViewById(R.id.image_url);
            market_price= (TextView) view.findViewById(R.id.market_price);
            type_id= (ImageView) view.findViewById(R.id.type_id);
        }
    }
}
