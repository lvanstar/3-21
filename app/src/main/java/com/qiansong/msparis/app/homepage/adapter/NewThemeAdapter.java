package com.qiansong.msparis.app.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.HomePageBean;
import com.qiansong.msparis.app.homepage.activity.BrandDetailsActivity;
import com.qiansong.msparis.app.homepage.util.ListUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */

public class NewThemeAdapter extends BaseAdapter{
    private Context context;
    public List<HomePageBean.DataBean.TopicsBean> data;
    public NewThemeAdapter(Context context,List<HomePageBean.DataBean.TopicsBean> data){
        this.context=context;
        this.data=data;
    }

    public NewThemeAdapter(Context context) {
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
            convertView=View.inflate(context, R.layout.item_new_theme,null);
            viewholder=new ViewHolder(convertView);
            convertView.setTag(viewholder);
        }else {
            viewholder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(data.get(position).getImage_src()).into(viewholder.image_src);

        viewholder.new_theme.setAdapter(new NewThemeGridAdapter(context,data.get(position).getProducts()));
        ListUtils.setGridViewHeightBasedOnChildren(viewholder.new_theme);
        viewholder.new_theme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                context.startActivity(new Intent(context,BrandDetailsActivity.class));
            }
        });
        return convertView;
    }
    public void updata(List<HomePageBean.DataBean.TopicsBean> data){
        this.data=data;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        private GridView new_theme;
        ImageView image_src;
        public ViewHolder(View view){
            new_theme = (GridView)view.findViewById(R.id.new_theme);
            image_src= (ImageView) view.findViewById(R.id.image_src);
        }
    }
}
