package com.qiansong.msparis.app.homepage.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.homepage.activity.SearchActivity;
import com.qiansong.msparis.app.homepage.bean.SearchHistoryBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/11.
 *
 * 搜索历史记录
 */

public class SearchHistoryAdapter extends BaseAdapter{
    public SearchActivity activity;

    public List<SearchHistoryBean> list = null;
    public Callback callback;


    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface  Callback{
        public void returnData(String str);
    }

    public SearchHistoryAdapter(SearchActivity activity){
        this.activity = activity;
    }

    /**
     * 设置数据
     * @param list
     */
    public void setData(List<SearchHistoryBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SearchHistoryAdapter.ViewHolder viewHolder = null;

        if(convertView == null) {
            convertView = View.inflate(activity.getBaseContext(), R.layout.item_search_history, null);
            viewHolder = new SearchHistoryAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (SearchHistoryAdapter.ViewHolder) convertView.getTag();
        }

        //设置值
        viewHolder.search_history_text.setText(list.get(position).data);
        viewHolder.search_history_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.returnData(list.get(position).data);
            }
        });


        return convertView;
    }

    private class ViewHolder{
        public TextView search_history_text;
        public RelativeLayout search_history_delete;
        public  ViewHolder(View  view){
            search_history_text = (TextView) view.findViewById(R.id.search_history_text);
            search_history_delete = (RelativeLayout) view.findViewById(R.id.search_history_delete);
        }
    }
}
