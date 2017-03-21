package com.qiansong.msparis.app.wardrobe.selfview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.util.DisplayUtil;
import com.qiansong.msparis.app.wardrobe.adapter.MyShoppingBagAdapter;

import java.util.List;

/**
 * Created by lizhaozhao on 2017/2/13.
 *
 * 购物袋弹出视图
 */

public class ShoppingBagView extends PopupWindow{

    private Context context;
    private LayoutInflater inflater = null;
    private View myMenuView,cancel,line;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private OnClickListener listener;
    private List<PackagesBean.DataBean.UserPackageBean>datas;
    public static MyShoppingBagAdapter myShoppingBagAdapter;


    public ShoppingBagView(Context context, List<PackagesBean.DataBean.UserPackageBean>datas,OnClickListener listener){
        this.context=context;
        this.listener=listener;
        this.datas=datas;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myMenuView = inflater.inflate(R.layout.shopping_bag, null);


        setPopup();
        setViews();
        setListeners();

    }


    private void setViews(){
        linearLayout= (LinearLayout) myMenuView.findViewById(R.id.shopping_bag_Ll);
        recyclerView= (RecyclerView) myMenuView.findViewById(R.id.shopping_bag_recyler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        myShoppingBagAdapter=new MyShoppingBagAdapter(context,datas,listener);
        recyclerView.setAdapter(myShoppingBagAdapter);

        if(datas.size()>4){
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            linearParams.height = DisplayUtil.dip2px(context,65*4);// 控件的高强制设成20
            linearLayout.setLayoutParams(linearParams);
        }

    }

    private void setPopup(){

        // 设置AccessoryPopup的view
        this.setContentView(myMenuView);
        // 设置AccessoryPopup弹出窗体的宽度
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体的高度
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置AccessoryPopup弹出窗体可点击
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.update();
        cancel=myMenuView.findViewById(R.id.shopping_bag_cancel);
        line=myMenuView.findViewById(R.id.shopping_bag_ll);
        float pix = context.getResources().getDisplayMetrics().density;
        Animation a = new TranslateAnimation(0, 0, (int) (161 * pix + 0.5f), 0);
        a.setDuration(150);
        a.setInterpolator(new LinearInterpolator());
        line.startAnimation(a);
        final Animation b = new TranslateAnimation(0, 0, 0, (int) (161 * pix + 0.5f));
        b.setDuration(150);
        b.setInterpolator(new LinearInterpolator());

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);

            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


    private void setListeners(){


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 显示弹窗界面
     *
     * @param view
     */
    public void show(View view) {
        backgroundAlpha((Activity) context,0.5f);
        showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    public interface OnClickListener{

        void OnClick(int postion);

    }
}
