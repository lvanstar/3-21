package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.bean.WishListBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.bean.DayNewBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.homepage.util.Eutil;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/20.
 * 我的衣橱-曾经拥有
 */

public class WishAdapter extends BaseAdapter{
    private Context context;
    public List<WishListBean.DataBean.RowsBean> data;
    public WishAdapter(Context context,List<WishListBean.DataBean.RowsBean> data){
        this.context=context;
        this.data=data;
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
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_product_brand,null);
            viewholder=new ViewHolder(convertView);
            convertView.setTag(viewholder);
        }else {
            viewholder= (ViewHolder) convertView.getTag();
        }
        viewholder.name.setText(data.get(position).getName());
        viewholder.size.setText(data.get(position).getShow_specifications());
        if (!data.get(position).is_guanzhu){
            viewholder.zan.setImageResource(R.mipmap.icon_no);
        }else {
            viewholder.zan.setImageResource(R.mipmap.icon_yes);
        }
        Eutil.toBigThenSmallAnimation(viewholder.zan);

        viewholder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianzan(position,data.get(position).getId()+"");
                if (data.get(position).is_guanzhu){
                    data.get(position).is_guanzhu=false;
                }else {
                    data.get(position).is_guanzhu=true;
                }
                notifyDataSetChanged();
            }
        });
        //"http://static.msparis.com/image/product/F/W/FW022W-1.jpg"
        ExclusiveUtils.setImageUrl(viewholder.sku_img,data.get(position).getCover_image(),-1);
        return convertView;
    }
    private void dianzan(final int position, String id){
        Map<String,String> map=new Hashtable<>();
        map.put("access_token",MyApplication.token);
        map.put("id",id);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
       if (!data.get(position).is_guanzhu) {
           HttpServiceClient.getInstance().to_wish(body).enqueue(new Callback<BaseBean>() {
               @Override
               public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                   if (response.isSuccessful() && "ok".equals(response.body().getStatus())) {
                       ContentUtil.makeToast(context, "收藏成功");
                   } else {
                       if (data.get(position).is_guanzhu) {
                           data.get(position).is_guanzhu = false;
                       } else {
                           data.get(position).is_guanzhu = true;
                       }
                       notifyDataSetChanged();
                   }
               }

               @Override
               public void onFailure(Call<BaseBean> call, Throwable t) {
                   if (data.get(position).is_guanzhu) {
                       data.get(position).is_guanzhu = false;
                   } else {
                       data.get(position).is_guanzhu = true;
                   }
                   notifyDataSetChanged();
               }
           });
       }else {
           HttpServiceClient.getInstance().to_no_wish(body).enqueue(new Callback<BaseBean>() {
               @Override
               public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                   if (response.isSuccessful() && "ok".equals(response.body().getStatus())) {
                       ContentUtil.makeToast(context, "取消收藏成功");
                   } else {
                       if (data.get(position).is_guanzhu) {
                           data.get(position).is_guanzhu = false;
                       } else {
                           data.get(position).is_guanzhu = true;
                       }
                       notifyDataSetChanged();
                   }
               }

               @Override
               public void onFailure(Call<BaseBean> call, Throwable t) {
                   if (data.get(position).is_guanzhu) {
                       data.get(position).is_guanzhu = false;
                   } else {
                       data.get(position).is_guanzhu = true;
                   }
                   notifyDataSetChanged();
               }
           });
       }
    }
    public void updata(List<WishListBean.DataBean.RowsBean> data){
        this.data=data;
        notifyDataSetChanged();
    }

    private class ViewHolder{
        private SimpleDraweeView sku_img;
        ImageView zan;
        TextView name,size;
        public ViewHolder(View view){
            sku_img = (SimpleDraweeView)view.findViewById(R.id.sku_img);
            zan= (ImageView) view.findViewById(R.id.zan);
            name= (TextView) view.findViewById(R.id.name);
            size= (TextView) view.findViewById(R.id.size);
        }
    }
}
