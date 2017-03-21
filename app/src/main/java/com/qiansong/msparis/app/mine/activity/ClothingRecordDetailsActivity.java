package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.homepage.util.ListUtils;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 还衣记录详情
 */
public class ClothingRecordDetailsActivity extends BaseActivity {
    ClothingRecordDetailsActivity context;
    List<?> data;
    GridView record_clothes;
    PullToRefreshView refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_record_details);
        context=this;
        data=new ArrayList<>();

        setTitleBar();
        init();
        setListener();
    }
   //监听
    private void setListener() {
        refresh.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(PullToRefreshView view) {

                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onHeaderRefreshComplete();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==22){//更新运单号
            Eutil.editNumber("","",new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    //刷新UI
                    ContentUtil.makeToast(context,"更新成功");
                    return false;
                }
            }));
        }
    }

    //编辑运单
    public void editWaybill(View view){
         startActivity(new Intent(context,EditWaybillNumberActivity.class));
    }
    //填充
    private void init() {
        refresh= (PullToRefreshView) findViewById(R.id.refresh);
        record_clothes= (GridView) findViewById(R.id.record_clothes);
        record_clothes.setAdapter(new ClothingRecordDetailsAdapter());
        record_clothes.setFocusable(false);
        ListUtils.setGridViewHeightBasedOnChildren(record_clothes);
    }
    //sku
   class ClothingRecordDetailsAdapter extends BaseAdapter{

       @Override
       public int getCount() {
//        return data!=null?data.size():0;
           return 5;
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
               convertView=View.inflate(context, R.layout.item_record_detail_sku,null);
               viewholder=new ViewHolder(convertView);
               convertView.setTag(viewholder);
           }else {
               viewholder= (ViewHolder) convertView.getTag();
           }

           return convertView;
       }
       private class ViewHolder{
//           private GridView new_theme;
           public ViewHolder(View view){
//               new_theme = (GridView)view.findViewById(R.id.new_theme);
           }
       }
   }
    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("还衣详情");//设置标题
        titleBar.setTitleColor(context.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
        titleBar.setRightTitle("更改运单");
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
                editWaybill(view);
            }
        });
    }
}
