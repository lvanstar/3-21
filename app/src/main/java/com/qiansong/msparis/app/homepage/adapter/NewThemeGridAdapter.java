package com.qiansong.msparis.app.homepage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.HomePageBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */

public class NewThemeGridAdapter extends BaseAdapter {
    private Context context;
    public List<HomePageBean.DataBean.TopicsBean.ProductsBean> data;
    public NewThemeGridAdapter(Context context,List<HomePageBean.DataBean.TopicsBean.ProductsBean> data){
        this.context=context;
        this.data=data;
    }

    public NewThemeGridAdapter(Context context) {
        this.context = context;
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
            convertView=View.inflate(context, R.layout.item_new_theme_grid,null);
            viewholder=new ViewHolder(convertView);
            convertView.setTag(viewholder);
        }else {
            viewholder= (ViewHolder) convertView.getTag();
        }
        viewholder.name.setText(data.get(position).getName());
        viewholder.show_specifications.setText(data.get(position).getShow_specifications());
        Glide.with(context).load(data.get(position).getCover_image()).into(viewholder.cover_image);
        return convertView;
    }
    public void updata(List<HomePageBean.DataBean.TopicsBean.ProductsBean> data){
        this.data=data;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        ImageView cover_image;
        TextView name,show_specifications;
        public ViewHolder(View view){
            cover_image = (ImageView)view.findViewById(R.id.cover_image);
            name= (TextView) view.findViewById(R.id.name);
            show_specifications= (TextView) view.findViewById(R.id.show_specifications);

        }
    }
}
