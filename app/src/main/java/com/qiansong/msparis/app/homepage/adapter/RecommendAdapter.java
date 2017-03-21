package com.qiansong.msparis.app.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.wardrobe.activity.ProductDetailsActivity;
import com.qiansong.msparis.app.commom.bean.HomePageBean;

import java.util.List;

/**
 * Created by yechen on 2017/2/10.
 * 首页小编推荐
 */


public class RecommendAdapter extends
        RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<HomePageBean.DataBean.ProductCommendBean> mDatas;
    Context context;
    public RecommendAdapter(Context context, List<HomePageBean.DataBean.ProductCommendBean> datats) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView cover_image;
        TextView name,show_specifications;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_homepage_daynew,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.name = (TextView) view.findViewById(R.id.name);
        viewHolder.show_specifications = (TextView) view.findViewById(R.id.show_specifications);
        viewHolder.cover_image = (ImageView) view.findViewById(R.id.cover_image);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(mDatas.get(i).getName());
        viewHolder.show_specifications.setText(mDatas.get(i).getShow_specifications());
        Glide.with(context).load(mDatas.get(i).getCover_image()).into(viewHolder.cover_image);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,ProductDetailsActivity.class));
            }
        });
    }

}
//public class DayNewAdapter extends BaseAdapter {
//    private Context context;
//    public List<DayNewBean> data;
//    public DayNewAdapter(Context context,List<DayNewBean> data){
//        this.context=context;
//        this.data=data;
//    }
//    @Override
//    public int getCount() {
//        return data!=null?data.size():0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final ViewHolder viewholder;
//        if(convertView==null){
//            convertView=View.inflate(context, R.layout.item_homepage_daynew,null);
//            viewholder=new ViewHolder(convertView);
//            convertView.setTag(viewholder);
//        }else {
//            viewholder= (ViewHolder) convertView.getTag();
//        }
//
//        return convertView;
//    }
//    public void updata(List<DayNewBean> data){
//        this.data=data;
//        notifyDataSetChanged();
//    }
//
//    private class ViewHolder{
////        private View color_info;
//        public ViewHolder(View view){
////            color_info = view.findViewById(R.id.color_info);
//        }
//    }
//}
