package com.qiansong.msparis.app.find.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.find.activity.SlideShowActivity;

import java.util.List;


/**
 * Created by Administrator on 2016/3/29.
 */
public class PushImageAdapter extends BaseAdapter {
    private Context context;
    private List<SlideShowActivity.ImageBean> result;

    public PushImageAdapter(Context context, List<SlideShowActivity.ImageBean> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public int getCount() {
        // 多返回一个用于展示添加图标
        if (result == null) {
            return 1;
        } else if (result.size() == 9) {
            return 9;
        } else {
            return result.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        if (result != null
                && result.size() == 9) {
            return result.get(position);
        } else if (result == null || position - 1 < 0
                || position > result.size()) {
            return null;
        } else {
            return result.get(position - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= View.inflate(context, R.layout.item_image,null);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.image);
        if (isShowAddItem(position)) {
            imageView.setImageResource(R.drawable.btn_add_pic);
        } else {
            Glide.with(context).load(result.get(position).path).centerCrop().into(imageView);
        }

        return convertView;
    }

    public void updatas(List<SlideShowActivity.ImageBean> results){
        this.result = results;
        notifyDataSetChanged();
    }

    private boolean isShowAddItem(int position) {
        int size = result == null ? 0 : result.size();
        return position == size;
    }
}
