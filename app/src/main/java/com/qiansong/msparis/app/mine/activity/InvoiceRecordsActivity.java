package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.HomePageBean;
import com.qiansong.msparis.app.commom.bean.UserInvoicesBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.activity.BrandDetailsActivity;
import com.qiansong.msparis.app.homepage.adapter.NewThemeAdapter;
import com.qiansong.msparis.app.homepage.adapter.NewThemeGridAdapter;
import com.qiansong.msparis.app.homepage.util.ListUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 开票记录 - yechen
 */
public class InvoiceRecordsActivity extends BaseActivity {
    InvoiceRecordsActivity context;
    ListView record_list;

    UserInvoicesBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_records);
        context=this;
        setTitleBar();
        init();
    }

    /**
     * 数据
     */
    private void init() {
        record_list= (ListView) findViewById(R.id.record_list);
        HttpServiceClient.getInstance().user_invoices(MyApplication.token,null,null).enqueue(new Callback<UserInvoicesBean>() {
            @Override
            public void onResponse(Call<UserInvoicesBean> call, Response<UserInvoicesBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    record_list.setAdapter(new InvoiceRecordsAdapter());
                }
            }

            @Override
            public void onFailure(Call<UserInvoicesBean> call, Throwable t) {

            }
        });
    }

    /**
     * 开票记录
     */
    class InvoiceRecordsAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return bean!=null?bean.getData().getRows().size():0;
        }

        @Override
        public Object getItem(int position) {
            return bean.getData().getRows().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewholder;
            if(convertView==null){
                convertView=View.inflate(context, R.layout.item_invoice_record,null);
                viewholder=new ViewHolder(convertView);
                convertView.setTag(viewholder);
            }else {
                viewholder= (ViewHolder) convertView.getTag();
            }
            viewholder.status.setText("2".equals(bean.getData().getRows().get(position).getStatus())?"已开票":
                    ("1".equals(bean.getData().getRows().get(position).getStatus())?"已提交":""));

            return convertView;
        }

        private class ViewHolder{
            TextView created_at,status;
            public ViewHolder(View view){
                created_at= (TextView) view.findViewById(R.id.created_at);
                status= (TextView) view.findViewById(R.id.status);
            }
        }
    }

    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("开票记录");//设置标题
        titleBar.setTitleColor(this.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
//        titleBar.setRightImageResource(R.mipmap.ic_launcher);//设置右边图标
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));//设置背景
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
            }
        });
    }
}
