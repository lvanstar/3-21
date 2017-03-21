package com.qiansong.msparis.app.mine.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.bean.UserBean;

import java.util.List;


/**
 * Created by Kevin on 2017/02/14.
 * <p>
 * 我的信息  尺码
 */
public class UserSizeAdapter extends BaseAdapter {
    public Context context;

    public List<UserBean.DataBean.UserSizeBean> list = null;



    public ReturnSize returnSize;

    public ReturnSize getReturnSize() {
        return returnSize;
    }

    public void setReturnSize(ReturnSize returnSize) {
        this.returnSize = returnSize;
    }
    public UserSizeAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<UserBean.DataBean.UserSizeBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface ReturnSize {
        public void returnSize(String  id, boolean ischeck);
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
        UserSizeAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_user_size, null);
            viewHolder = new UserSizeAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (UserSizeAdapter.ViewHolder) convertView.getTag();
        }

        //设置值
        viewHolder.size.setText(list.get(position).getName());
        viewHolder.size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    returnSize.returnSize(list.get(position).getId()+"",true);
                }else{
                    returnSize.returnSize(list.get(position).getId()+"",false);
                }
            }
        });

        // 1.选择 0未选择
        if (list.get(position).getIs_selected() == 1) {
            viewHolder.size.setChecked(true);
        } else if (list.get(position).getIs_selected() == 0) {
            viewHolder.size.setChecked(false);
        }

        return convertView;
    }


    public class ViewHolder {
        CheckBox size;

        public ViewHolder(View view) {
            size = (CheckBox) view.findViewById(R.id.item_user_size);
        }
    }

}
