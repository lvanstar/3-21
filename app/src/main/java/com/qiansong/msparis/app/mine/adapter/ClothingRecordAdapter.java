package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.app.commom.bean.ClothingRecordBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.homepage.bean.DayNewBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.homepage.util.Eutil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 * 还衣记录
 */

public class ClothingRecordAdapter extends BaseAdapter{
    private Context context;
    public List<ClothingRecordBean.DataBean.RowsBean> data;

    public ClothingRecordAdapter(Context context, List<ClothingRecordBean.DataBean.RowsBean> data) {
        this.context = context;
        this.data = data;
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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_clothing_record, null);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.express_no.setText("快递编号:"+data.get(position).getExpress_no());
        viewholder.express_company.setText("快递公司:"+data.get(position).getExpress_company());
        //待收件、已收件、已完成
        if ("已完成".equals(data.get(position).getExpress_status())){
            viewholder.express_status.setText(Eutil.getHighlight("#999999","快递状态:"+data.get(position).getExpress_status(),data.get(position).getExpress_status()));
        }else {
            viewholder.express_status.setText(Eutil.getHighlight("#8A2293","快递状态:"+data.get(position).getExpress_status(),data.get(position).getExpress_status()));
        }

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewholder.recyclerview_horizontal.setLayoutManager(linearLayoutManager);
        //设置适配器
        GalleryAdapter mAdapter = new GalleryAdapter(context, data.get(position).getExpress_items());
        viewholder.recyclerview_horizontal.setAdapter(mAdapter);
        viewholder.recyclerview_horizontal.setFocusable(false);
        return convertView;
    }


    public void updata(List<ClothingRecordBean.DataBean.RowsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private RecyclerView recyclerview_horizontal;
        TextView express_no,express_company,express_status;
        public ViewHolder(View view) {
            recyclerview_horizontal = (RecyclerView) view.findViewById(R.id.recyclerview_horizontal);
            express_no= (TextView) view.findViewById(R.id.express_no);
            express_company= (TextView) view.findViewById(R.id.express_company);
            express_status= (TextView) view.findViewById(R.id.express_status);
        }
    }

    public class GalleryAdapter extends
            RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private List<ClothingRecordBean.DataBean.RowsBean.ExpressItemsBean> mDatas;

        public GalleryAdapter(Context context, List<ClothingRecordBean.DataBean.RowsBean.ExpressItemsBean> datats) {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
            }

            SimpleDraweeView image_url;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.item_clothing_record_sku,
                    viewGroup, false);
            GalleryAdapter.ViewHolder viewHolder = new GalleryAdapter.ViewHolder(view);
            viewHolder.image_url= (SimpleDraweeView) view.findViewById(R.id.image_url);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final GalleryAdapter.ViewHolder viewHolder, final int i) {
            ExclusiveUtils.setImageUrl(viewHolder.image_url,mDatas.get(i).getImage_url(),-1);
        }

    }
}
