package com.qiansong.msparis.app.wardrobe.selfview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayout;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayoutTwo;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lizhaozhao on 2017/2/13.
 *
 * 日历视图
 */

public class CalendarView extends PopupWindow{

    private Context context;
    private LayoutInflater inflater = null;
    private View myMenuView,line;
    private CalendarLayout calendarLayout;
    private RentalMonitor.DataEntity entity;



    public CalendarView(Context context,RentalMonitor.DataEntity entity,int type){

        this.context=context;
        this.entity=entity;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);




        switch (type){
            case 1:
                myMenuView = inflater.inflate(R.layout.calendar, null);
                setCalendarOne();
                break;
            case 2:
                myMenuView = inflater.inflate(R.layout.calendar2, null);
                setCalendarTwo();
                break;
        }
        setPopup();
        setListeners();
    }



    private void setCalendarOne(){
        calendarLayout= (CalendarLayout) myMenuView.findViewById(R.id.cl);
        calendarLayout.setRentalData(generateData());
    }


    private void setCalendarTwo(){

        CalendarLayoutTwo calendarLayoutTwo= (CalendarLayoutTwo) myMenuView.findViewById(R.id.cl2);

    }





    /**
     * 模拟数据
     */
    private RentalMonitor.DataEntity generateData() {
        RentalMonitor.DataEntity rm = new RentalMonitor.DataEntity();
        rm.setRental_expiry_date(System.currentTimeMillis() / 1000 + 365 * 24 * 3600);
        ArrayList<RentalMonitor.DataEntity.Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RentalMonitor.DataEntity.Schedule schedule = new RentalMonitor.DataEntity.Schedule();
            schedule.setStart_time(System.currentTimeMillis() / 1000 + (1 + i * 10) * 24 * 3600);
            schedule.setEnd_time(System.currentTimeMillis() / 1000 + (15 + i * 10) * 24 * 3600);
            schedules.add(schedule);
        }
        rm.setMax_use_days(5);
        rm.setMin_use_days(2);
        rm.setClean_days(1);
        rm.setSend_express_days(3);
        rm.setReturn_express_days(1);
        rm.setSchedule(schedules);
        return rm;
    }


    private void setPopup(){
        line=myMenuView.findViewById(R.id.calendar_line);
        // 设置AccessoryPopup的view
        this.setContentView(myMenuView);
        // 设置AccessoryPopup弹出窗体的宽度
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体的高度
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置AccessoryPopup弹出窗体可点击
        this.setFocusable(true);
        this.setTouchable(true);
        backgroundAlpha((Activity) context,0.5f);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.update();
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

//        this.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
//
//                    dismiss();
//                    return true;
//                }
//                return false;
//            }
//        });
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



    }


    /**
     * 显示弹窗界面
     *
     * @param view
     */
    public void show(View view) {

        showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
