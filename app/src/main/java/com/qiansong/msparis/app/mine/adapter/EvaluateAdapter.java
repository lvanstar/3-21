package com.qiansong.msparis.app.mine.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.EvaluateBean;
import com.qiansong.msparis.app.commom.bean.OrderDeatilBean;
import com.qiansong.msparis.app.commom.selfview.StarBarView;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.homepage.bean.DayNewBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/2/23.
 * 评价
 */

public class EvaluateAdapter extends BaseAdapter {
    private Context context;
    private List<OrderDeatilBean.DataEntity.OrderDetailEntity>datas;
    private  List<EvaluateBean.DataEntity> dataEntities;

    public EvaluateAdapter(Context context, List<OrderDeatilBean.DataEntity.OrderDetailEntity>datas) {
        this.context = context;
        this.datas = datas;
        dataEntities=new ArrayList<>();
    }


    @Override
    public int getCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_evaluate, null);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        final EvaluateBean.DataEntity entity=new EvaluateBean.DataEntity();

        final CheckBox[]checkBoxes=new CheckBox[]{viewholder.itemEvaluateCheck01,viewholder.itemEvaluateCheck02,viewholder.itemEvaluateCheck03};

        viewholder.itemEvaluateDraweeView.setImageURI(Uri.parse(datas.get(position).getImage_url()));
        viewholder.itemEvaluateBrandTv.setText(datas.get(position).getBrand_name());
        viewholder.itemEvaluateNameTv.setText(datas.get(position).getName());
        viewholder.itemEvaluateCodeTv.setText(datas.get(position).getSpecification());

        for (int i=0;i<checkBoxes.length;i++){
            final int finalI = i;
            checkBoxes[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        ContentUtil.makeLog("lzz","J:"+finalI);
                        for (int j=0;j<checkBoxes.length;j++){
                            if(finalI!=j){
                                checkBoxes[j].setChecked(false);
                            }
                        }

                    }
                }
            });
        }

        viewholder.itemEvaluateEt.getText().toString();


        viewholder.itemEvaluateStarbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entity.product_vote=(int) viewholder.itemEvaluateStarbar.getStarRating();
            }
        });
        dataEntities.add(entity);
        return convertView;
    }

    public void updata(List<OrderDeatilBean.DataEntity.OrderDetailEntity>datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    /**
     * 获取数据
     * @return
     */
    public List<EvaluateBean.DataEntity> getDataEntities(){

        return dataEntities;
    }



   static class ViewHolder {
        @InjectView(R.id.item_evaluate_draweeView)
        SimpleDraweeView itemEvaluateDraweeView;
        @InjectView(R.id.item_evaluate_name_Tv)
        TextView itemEvaluateNameTv;
        @InjectView(R.id.item_evaluate_brand_Tv)
        TextView itemEvaluateBrandTv;
        @InjectView(R.id.item_evaluate_code_Tv)
        TextView itemEvaluateCodeTv;
        @InjectView(R.id.item_evaluate_starbar)
        StarBarView itemEvaluateStarbar;
        @InjectView(R.id.item_evaluate_Check01)
        CheckBox itemEvaluateCheck01;
        @InjectView(R.id.item_evaluate_Check02)
        CheckBox itemEvaluateCheck02;
        @InjectView(R.id.item_evaluate_Check03)
        CheckBox itemEvaluateCheck03;
        @InjectView(R.id.item_evaluate_Et)
        EditText itemEvaluateEt;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
