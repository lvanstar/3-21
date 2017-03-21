package com.qiansong.msparis.app.commom.selfview.CalendarView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.util.CalendarUtil.CalendarDay;
import com.qiansong.msparis.app.wardrobe.activity.LocationActivity;
import com.qiansong.msparis.app.wardrobe.selfview.CalendarView;


/**
 * Created by lizhaozhao on 2017/3/10 03.
 * Description:
 */

public class CalendarLayout extends FrameLayout {
    private TextView mResetV;
    public static TextView mConfirmV;
    private View mPreV;
    private View mNexV;
    private static TextView mTvLocation, mTvYearMonth;
    public static CalendarPageOne mCalendarPageOne;
    private boolean mIsTop, mIsBottom;
    private ViewPager mPager;
    private View address;
    private static int[] duration;//确认选择结果

    public CalendarLayout(Context context) {
        this(context, null);
    }

    public CalendarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_calendar_pager_container, this, true);
        mPreV = findViewById(R.id.pre);
        mNexV = findViewById(R.id.nex);
        mTvLocation = (TextView) findViewById(R.id.tv_location);
        mTvYearMonth = (TextView) findViewById(R.id.tv_year_month);
        mConfirmV = (TextView) findViewById(R.id.btn_confirm);
        mResetV = (TextView) findViewById(R.id.btn_reset);
        address=findViewById(R.id.calendar_pager_address);


        ///////////////////////////////初始化viewpager///////////////////////////////////////////
        mPager = (ViewPager) findViewById(R.id.vp);
        mPager.setOffscreenPageLimit(2);
        //配置日期范围
        CalendarDay today = CalendarDay.today();
        //最小日期为当天减0年
        CalendarDay minDay = CalendarDay.from(today.getYear() - 0, today.getMonth(), today.getDay());
        //最大日期为当天加200年
        CalendarDay maxDay = CalendarDay.from(today.getYear() + 200, today.getMonth(), today.getDay());
        mCalendarPageOne = new CalendarPageOne(getContext(), minDay, maxDay);
        mCalendarPageOne.bindViewPager(mPager);
        /////////////////////////////////////////////////////////////////////////////////////////
        setListeners(context);
    }

    public void setListeners(final Context context) {
        /**
         * 上月
         */
        mPreV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mPager.getCurrentItem();
                mPager.setCurrentItem(position - 1, true);
            }
        });

        /**
         * 下月
         */
        mNexV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mPager.getCurrentItem();
                mPager.setCurrentItem(position + 1, true);
            }
        });

        /**
         * 边界判断
         * 标题月份改变
         */
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                determineTopBottom(position);
                mTvYearMonth.setText(mCalendarPageOne.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //重置
        mResetV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarPageOne.cleanSelected();
                mConfirmV.setTextColor(Color.BLACK);
                mConfirmV.setBackgroundResource(R.drawable.gray_shap);
                mConfirmV.setEnabled(false);
            }
        });

        mConfirmV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                duration = mCalendarPageOne.getSelectedRange();

            }
        });

        address.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LocationActivity.class);
                ((Activity)context).startActivityForResult(intent,66);
            }
        });
    }

    /**
     * 边界判断
     */
    private void determineTopBottom(int position) {
        boolean alreadyTop = position == 0;
        if (alreadyTop != mIsTop) {
            mIsTop = alreadyTop;
            mPreV.setEnabled(!mIsTop);
        }
        boolean alreadyBottom = position == mCalendarPageOne.getCount() - 1;
        if (alreadyBottom != mIsBottom) {
            mIsBottom = alreadyBottom;
            mNexV.setEnabled(!mIsBottom);
        }
    }

    /**
     * 地区
     */
    public  static void setLocation(String cityName) {
        mTvLocation.setText(cityName+"   >");
    }

    /**
     * @param data 租赁信息
     */
    public void setRentalData(RentalMonitor.DataEntity data) {
        mCalendarPageOne.bindData(data);
        determineTopBottom(mPager.getCurrentItem());
    }


    /**
     * 回调数据
     */
//    public interface OnCheckedClickListener{
//       void onClick(int [] args);
//    }
//
//    public static void setOnListener(OnCheckedClickListener listener){
//        listener.onClick(duration);
//    }

}
