package com.qiansong.msparis.app.wardrobe.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.app.commom.bean.OnePackagesBean;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.wardrobe.selfview.MenuDialogYe;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.wardrobe.fragment.OneShoppingBagFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */

public class ShoppingBagAdapter extends BaseAdapter {
    private Context context;
    public List<OnePackagesBean.DataBean.PackageItemsBean> data;

    public ShoppingBagAdapter(Context context, List<OnePackagesBean.DataBean.PackageItemsBean> data) {

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
            convertView = View.inflate(context, R.layout.item_shopbag, null);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        if (data.get(position).isEmpty) {
            viewholder.add.setVisibility(View.VISIBLE);
            viewholder.content.setVisibility(View.GONE);
            viewholder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity) context).setResult(31);
                    ((Activity) context).finish();
                }
            });
        } else {
            viewholder.add.setVisibility(View.GONE);
            viewholder.content.setVisibility(View.VISIBLE);
            if (data.get(position).isIs_invalid()) {//失效判断
                viewholder.mengban.setAlpha(0.5f);
                viewholder.shixiao.setVisibility(View.VISIBLE);
                data.get(position).isSelect = false;
            } else {
                viewholder.shixiao.setVisibility(View.GONE);
            }
            if (data.get(position).isSelect) {
                viewholder.select.setImageResource(R.mipmap.select_1);
            } else {
                viewholder.select.setImageResource(R.mipmap.select_0);
            }
            ExclusiveUtils.setImageUrl(viewholder.image, data.get(position).getImage(), -1);
            viewholder.name.setText(data.get(position).getName());
            viewholder.brand.setText(data.get(position).getBrand());
            viewholder.specification.setText(data.get(position).getSpecification());
            viewholder.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.get(position).isIs_invalid()) return;
                    if (data.get(position).isSelect) {
                        data.get(position).isSelect = false;
                    } else {
                        data.get(position).isSelect = true;
                    }
                    notifyDataSetChanged();
                    if (OneShoppingBagFragment.money != null) {
                        int k = 0;
                        for (int m = 0; m < data.size(); m++) {
                            if (data.get(position).isSelect) {
                                k++;
                            }
                        }

                        OneShoppingBagFragment.money.setText(Eutil.getMoney(k) + "");
                    }
                }
            });

            viewholder.content.setFocusable(true);
            viewholder.content.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new MenuDialogYe(context, R.style.registDialog, R.layout.dialog_shop_bag, "", new MenuDialogYe.ButtonClickListener() {
                        @Override
                        public void onButtonClick(int i) {
                            if (i == 0) {//移入心愿单
                             Eutil.addWishList(data.get(position).getId());
                            } else if (i == 1) {//删除

                            }
                        }
                    }).show();
                    return false;
                }
            });
        }
        return convertView;
    }


    public void updata(List<OnePackagesBean.DataBean.PackageItemsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private ImageView select;
        RelativeLayout content;
        LinearLayout add;
        SimpleDraweeView image;
        TextView name, brand, specification;
        RelativeLayout shixiao;
        View mengban;

        public ViewHolder(View view) {
            mengban = view.findViewById(R.id.mengban);
            shixiao = (RelativeLayout) view.findViewById(R.id.shixiao);
            name = (TextView) view.findViewById(R.id.name);
            brand = (TextView) view.findViewById(R.id.brand);
            specification = (TextView) view.findViewById(R.id.specification);
            image = (SimpleDraweeView) view.findViewById(R.id.image);
            select = (ImageView) view.findViewById(R.id.select);
            content = (RelativeLayout) view.findViewById(R.id.content);
            add = (LinearLayout) view.findViewById(R.id.add);
        }
    }
}
