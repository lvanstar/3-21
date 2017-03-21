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
import com.qiansong.msparis.app.commom.bean.UserWardrobeBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.homepage.bean.DayNewBean;
import com.qiansong.msparis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 * 我的衣橱-预定中
 */

public class BookingAdapter extends BaseAdapter {

    private Context context;
    public List<UserWardrobeBean.DataBean.RowsBean> data;

    public BookingAdapter(Context context, List<UserWardrobeBean.DataBean.RowsBean> data) {
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
            convertView = View.inflate(context, R.layout.item_booking, null);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.time.setText("租赁日期:"+data.get(position).getData());
        viewholder.bag_barcode.setText("请连同环保袋一同归还,编号:"+data.get(position).getBag_barcode());




        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewholder.recyclerview_horizontal.setLayoutManager(linearLayoutManager);
        //设置适配器
        GalleryAdapter mAdapter = new GalleryAdapter(context, data.get(position).getProduct());
        viewholder.recyclerview_horizontal.setAdapter(mAdapter);
        viewholder.recyclerview_horizontal.setFocusable(false);
        return convertView;
    }


    public void updata(List<UserWardrobeBean.DataBean.RowsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private RecyclerView recyclerview_horizontal;
        TextView time,bag_barcode;
        public ViewHolder(View view) {
            recyclerview_horizontal = (RecyclerView) view.findViewById(R.id.recyclerview_horizontal);
            time= (TextView) view.findViewById(R.id.time);
            bag_barcode= (TextView) view.findViewById(R.id.bag_barcode);
        }
    }

    public class GalleryAdapter extends
            RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private List<UserWardrobeBean.DataBean.RowsBean.ProductBean> mDatas;

        public GalleryAdapter(Context context, List<UserWardrobeBean.DataBean.RowsBean.ProductBean> datats) {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
            }
            SimpleDraweeView image_url;
            TextView specification;
            ImageView status,type_id;
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
            View view = mInflater.inflate(R.layout.item_booking_sku,
                    viewGroup, false);
            GalleryAdapter.ViewHolder viewHolder = new GalleryAdapter.ViewHolder(view);
            viewHolder.image_url = (SimpleDraweeView) view.findViewById(R.id.image_url);
            viewHolder.specification= (TextView) view.findViewById(R.id.specification);
            viewHolder.status= (ImageView) view.findViewById(R.id.status);
            viewHolder.type_id= (ImageView) view.findViewById(R.id.type_id);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final GalleryAdapter.ViewHolder viewHolder, final int i) {
            ExclusiveUtils.setImageUrl(viewHolder.image_url,mDatas.get(i).getImage_url(),-1);
            viewHolder.specification.setText(mDatas.get(i).getSpecification());
            if (2==mDatas.get(i).getType_id()){
                viewHolder.type_id.setVisibility(View.VISIBLE);
            }else {
                viewHolder.type_id.setVisibility(View.GONE);
            }
            switch (mDatas.get(i).getStatus()){
                case 1:
                    viewHolder.status.setVisibility(View.VISIBLE);
                    viewHolder.status.setImageResource(R.mipmap.weifahuo);
                    break;
                case 5:
                    viewHolder.status.setVisibility(View.VISIBLE);
                    viewHolder.status.setImageResource(R.mipmap.yituihuo);
                    break;
                case 8:
                    viewHolder.status.setVisibility(View.VISIBLE);
                    viewHolder.status.setImageResource(R.mipmap.yiguihuan);
                    break;
                default:
                    viewHolder.status.setVisibility(View.GONE);
                    break;
            }
        }

    }
}
