package com.qiansong.msparis.app.wardrobe.selfview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.GlobalConsts;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/16.
 * <p>
 * 左侧从上往下弹出视图
 */

public class TopLeftPopup extends PopupWindow {

    private Context context;
    private LayoutInflater inflater = null;
    private View myMenuView;
    private ListView lv;
//    private  ArrayList<RecommendBean>list;
    TopLeftPopupAdapter adapter;
    private List<ConfigsBean.DataEntity.SortEntity.DressSortEntity>datas;
    private int type;

    public TopLeftPopup(Context context, List<ConfigsBean.DataEntity.SortEntity.DressSortEntity>datas,int type) {
        this.context = context;
        this.datas=datas;
        this.type=type;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myMenuView = inflater.inflate(R.layout.top_left_pop, null);

        lv = (ListView) myMenuView.findViewById(R.id.top_left_pop_lv);
        setPopup();
        setListeners();
    }


    private void setPopup() {

        // 设置AccessoryPopup的view
        this.setContentView(myMenuView);
        // 设置AccessoryPopup弹出窗体的宽度
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体的高度
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置AccessoryPopup弹出窗体可点击
        this.setFocusable(true);
        this.setAnimationStyle(R.style.AnimTopLeft);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context, 0.5f);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);




//       list=new ArrayList<>();
//        String[] material_string = new String[]{"推荐", "热门", "上新", "租金从高到低", "租金从低到高"};
//        boolean[] material_b = new boolean[]{true, false, false, false, false};
//
//        for (int i=0;i<material_string.length;i++){
//            list.add(new RecommendBean(material_string[i],material_b[i]));
//        }
        adapter=new TopLeftPopupAdapter(context,datas);
        lv.setAdapter(adapter);

    }


    private void setListeners(){

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    datas.get(position).select=true;
                    for (int i=0;i<datas.size();i++){
                        if(position!=i){
                            datas.get(i).select=false;
                        }
                    }
                adapter.update(datas);

                Intent intent=new Intent();
                intent.putExtra(GlobalConsts.VALUE,datas.get(position).getKey());
                switch (type){
                    case 1:
                        intent.setAction(GlobalConsts.FILE_DATA);
                        context.sendBroadcast(intent);
                        break;
                    case 2:
                        intent.setAction(GlobalConsts.FILE_DATAS);
                        context.sendBroadcast(intent);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                dismiss();

            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


    /**
     * 显示弹窗界面
     *
     * @param view
     */
    public void show(View view) {

        showAsDropDown(view, 0, 0);
    }


    public class TopLeftPopupAdapter extends BaseAdapter {

        private Context context;
        private List<ConfigsBean.DataEntity.SortEntity.DressSortEntity> list;

        public TopLeftPopupAdapter(Context context, List<ConfigsBean.DataEntity.SortEntity.DressSortEntity> list) {

            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_top_left_pop, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.itemTopLeftPopValue.setText(list.get(position).getName());
            if(list.size()-1==position){
                viewHolder.view.setVisibility(View.GONE);
            }else {
                viewHolder.view.setVisibility(View.VISIBLE);
            }


            if(list.get(position).select){
                viewHolder.itemTopLeftPopIv.setVisibility(View.VISIBLE);
                viewHolder.itemTopLeftPopValue.setTextColor(context.getResources().getColor(R.color.white));
            }else {
                viewHolder.itemTopLeftPopIv.setVisibility(View.GONE);
                viewHolder.itemTopLeftPopValue.setTextColor(context.getResources().getColor(R.color.font23));
            }



            return convertView;
        }


        public void update(List<ConfigsBean.DataEntity.SortEntity.DressSortEntity> list){
            this.list=list;
            notifyDataSetChanged();
        }



        }

        class ViewHolder {
            @InjectView(R.id.item_top_left_pop_value)
            TextView itemTopLeftPopValue;
            @InjectView(R.id.item_top_left_pop_iv)
            ImageView itemTopLeftPopIv;
            @InjectView(R.id.top_left_pop_view)
            View view;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
    }
}
