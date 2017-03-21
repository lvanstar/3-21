package com.qiansong.msparis.app.homepage.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.homepage.activity.SearchActivity;

import java.util.List;

/**
 * Created by kevin on 2017/2/11.
 *
 * 搜索结果
 */

public class SearchAdapter extends BaseAdapter{
    public SearchActivity activity;

    public List<String>  list = null;

    public SearchAdapter(SearchActivity activity){
        this.activity = activity;
    }

    /**
     * 设置数据
     * @param list
     */
    public void setData(List<String>  list){
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
        SearchAdapter.ViewHolder viewHolder = null;

        if(convertView == null) {
            convertView = View.inflate(activity.getBaseContext(), R.layout.item_search, null);
            viewHolder = new SearchAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (SearchAdapter.ViewHolder) convertView.getTag();
        }

        //设置值
        viewHolder.search_text.setText(list.get(position));


        return convertView;
    }

    private class ViewHolder{
        public TextView search_text;
        public  ViewHolder(View  view){
            search_text = (TextView) view.findViewById(R.id.search_text);
        }
    }
}
