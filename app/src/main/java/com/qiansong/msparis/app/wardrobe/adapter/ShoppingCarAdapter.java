package com.qiansong.msparis.app.wardrobe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.app.commom.bean.ShoppingCartBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.wardrobe.selfview.MenuDialogYe;
import com.qiansong.msparis.R;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 * 购物车
 */

public class ShoppingCarAdapter extends BaseAdapter {
    private int now_position=-1;//判断是不是同一个包裹用的
    private Context context;
    public List<ShoppingCartBean.DataBean> data;

    public ShoppingCarAdapter(Context context, List<ShoppingCartBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
            convertView = View.inflate(context, R.layout.item_shopping_cart_top, null);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.time.setText("租赁周期："+ data.get(position).getStarttime()+"-"+ data.get(position).getEndtime());
        viewholder.address.setText("目的地:"+data.get(position).getRegion());
        viewholder.shopping_car_top.setAdapter(new ShoppingCart2Adapter(position));
        ListUtils.setListViewHeightsOmag(viewholder.shopping_car_top);
        return convertView;
    }

    private class ViewHolder {
        LinearLayout  title;
        TextView time,address;
        ListView shopping_car_top;
        public ViewHolder(View view) {
            title = (LinearLayout) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
            address = (TextView) view.findViewById(R.id.address);
            shopping_car_top= (ListView) view.findViewById(R.id.shopping_car_top);
        }
    }
    class ShoppingCart2Adapter extends BaseAdapter{
        private int i;//父级的position
        public ShoppingCart2Adapter(int i){
            this.i=i;
        }
        @Override
        public int getCount() {
            return data == null ? 0 : data.get(i).getItems().size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(i).getItems().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder2 viewholder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_shopcar, null);
                viewholder = new ViewHolder2(convertView);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder2) convertView.getTag();
            }
            if (data.get(i).getItems().get(position).isIs_invalid()){//失效判断
                viewholder.mengban.setAlpha(0.5f);
                viewholder.shixiao.setVisibility(View.VISIBLE);
                data.get(i).getItems().get(position).isSelect=false;
            }else {
                viewholder.shixiao.setVisibility(View.GONE);
            }
            if (data.get(i).getItems().get(position).isSelect) {
                viewholder.select.setImageResource(R.mipmap.select_1);
            } else {
                viewholder.select.setImageResource(R.mipmap.select_0);
            }
            ExclusiveUtils.setImageUrl(viewholder.image,data.get(i).getItems().get(position).getImage(),-1);
            viewholder.name.setText(data.get(i).getItems().get(position).getName());
            viewholder.brand.setText(data.get(i).getItems().get(position).getBrand());
            viewholder.specification.setText(data.get(i).getItems().get(position).getSpecification());
            viewholder.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (data.get(i).getItems().get(position).isSelect) {
                        data.get(i).getItems().get(position).isSelect=false;
                        int s=0;
                        for (int k=0;k< data.get(i).getItems().size();k++){
                            if (data.get(i).getItems().get(k).isSelect)s++;
                        }
                        if (s<=0) now_position=-1;
                    } else {
                        if (now_position!=-1&&now_position!=i){
                            ContentUtil.makeToast(context,"请勾选租赁日期一致的服装进行预定");
                            return;
                        }
                        now_position=i;
                        data.get(i).getItems().get(position).isSelect=true;
                    }
                    notifyDataSetChanged();
                }
            });
            viewholder.content.setFocusable(true);
            viewholder.content.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new MenuDialogYe(context, R.style.registDialog, R.layout.dialog_shop_bag, "", new MenuDialogYe.ButtonClickListener() {
                        @Override
                        public void onButtonClick(int i) {
                            if (i==0){//移入心愿单

                            }else if (i==1){//删除

                            }
                        }
                    }).show();
                    return false;
                }
            });
            return convertView;
        }

        private class ViewHolder2 {
            private ImageView select;
            LinearLayout content;
            SimpleDraweeView image;
            TextView name,brand,specification;
            RelativeLayout shixiao;
            View mengban;
            public ViewHolder2(View view) {
                mengban=view.findViewById(R.id.mengban);
                shixiao= (RelativeLayout) view.findViewById(R.id.shixiao);
                name= (TextView) view.findViewById(R.id.name);
                brand= (TextView) view.findViewById(R.id.brand);
                specification= (TextView) view.findViewById(R.id.specification);
                image= (SimpleDraweeView) view.findViewById(R.id.image);
                select = (ImageView) view.findViewById(R.id.select);
                content = (LinearLayout) view.findViewById(R.id.content);
            }
        }
    }
}
