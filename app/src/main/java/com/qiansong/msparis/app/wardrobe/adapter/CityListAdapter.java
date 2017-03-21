package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.CityBean;

import java.util.List;


/**
 * Created by enjoytouch-ad02 on 2015/4/1.
 */
public class CityListAdapter extends BaseAdapter {
    private List<CityBean> beans;
    private final Context mContext;



    public CityListAdapter(Context context, List<CityBean>  list) {
        mContext = context;
        beans = list;

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)        //
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_location, null);
            holder = new ViewHolder();
            holder.alpha = (TextView) convertView.findViewById(R.id.tvLetter);     //item的首字母
            holder.name = (TextView) convertView.findViewById(R.id.address);       //城市名
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        int section = getSectionForPosition(position);
        //�����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
        if(position == getPositionForSection(section)){
            holder.alpha.setVisibility(View.VISIBLE);
            holder.alpha.setText(beans.get(position).getPingyin());
        }else{
            holder.alpha.setVisibility(View.GONE);
        }
        holder.name.setText(beans.get(position).getName());     //设置item的城市名

        return convertView;
    }

    /**
     * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
     */
    public int getSectionForPosition(int position) {
        return beans.get(position).getPingyin().charAt(0);
    }

    /**
     * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = beans.get(i).getPingyin();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }


    class ViewHolder {
        TextView alpha;     //字母
        TextView name;      //城市名
    }


}
