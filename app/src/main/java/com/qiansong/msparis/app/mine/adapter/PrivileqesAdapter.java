package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.bean.OldCouponBean;
import com.qiansong.msparis.app.mine.bean.PrivilegeBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/23.
 * 全部特权
 */

  public class PrivileqesAdapter extends RecyclerView.Adapter<PrivileqesAdapter.ViewHolder> {
    private Context mContext;
    private List<PrivilegeBean.DataBean.RowsBeanX>  data =null;
    private PrivileqesItemAdapter adapter ;

    public PrivileqesAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setData(List<PrivilegeBean.DataBean.RowsBeanX> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public PrivileqesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_privileges, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        adapter = new PrivileqesItemAdapter(mContext);
        viewHolder.item_privileges_gridView.setAdapter(adapter);

        viewHolder.item_privileges_title.setText(data.get(position).getName());
        if(data.get(position).getRows().size()>0){
            adapter.setData(data.get(position).getRows());
        }
        //设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(viewHolder.item_privileges_gridView,4);

    }

     class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  item_privileges_title;
        public GridView item_privileges_gridView;

        public ViewHolder(View view) {
            super(view);
            item_privileges_title = (TextView) view.findViewById(R.id.item_privileges_title);
            item_privileges_gridView = (GridView) view.findViewById(R.id.item_privileges_gridView);
        }
    }

}
