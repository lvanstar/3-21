package com.qiansong.msparis.app.mine.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.util.kankan.wheel.widget.OnWheelChangedListener;
import com.qiansong.msparis.app.mine.util.kankan.wheel.widget.WheelView;
import com.qiansong.msparis.app.mine.util.kankan.wheel.widget.adapters.ArrayWheelAdapter;

import java.util.List;

/**
 * Created by kevin on 2017/3/4.
 * <p>
 * 地址选择器
 */

public class AddressDialog extends Dialog implements View.OnClickListener,OnWheelChangedListener {
    public Context context;
    public TextView addressDialogExit;
    public TextView addressDialogTitle;
    public TextView addressDialogSubmit;
    public WheelView mViewProvince;
    public WheelView mViewCity;
    public WheelView mViewDistrict;
    public DataListener dataListener;


    protected String[] mProvinceDatas;
    protected String[] mCityDatas;
    protected String[] mAreaDatas;

    int pid=0;
    int cid=0;
    int aid=0;

    String regionName ="";
    String regionCode ="";

    private List<ConfigsBean.DataEntity.SendCitiesEntityX.SendCitiesEntity> provinceList = MyApplication.getSendEntity().getSend_cities();


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            pid=newValue;
            updateCity();
            updateArea();
        } else if (wheel == mViewCity) {
            cid=newValue;
            updateArea();
        } else if (wheel == mViewDistrict) {
            aid=newValue;
        }
    }

    //接口
    public interface  DataListener{
        public void AddressCode(String  code ,String  address);
    }
    public DataListener getDataListener() {
        return dataListener;
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public AddressDialog(Context context) {
        super(context, R.style.AddressDialogStyle);
        this.setContentView(R.layout.address_wheel_view);
        this.context = context;
        initView();

        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay();
        lp.width = (int) (d.getWidth());
        lp.y = 0;
        lp.x = 0;
        dialogWindow.setAttributes(lp);
        show();
    }
    public  void initView(){
        addressDialogExit = (TextView) findViewById(R.id.address_dialog_exit);
        addressDialogTitle = (TextView) findViewById(R.id.address_dialog_title);
        addressDialogSubmit = (TextView) findViewById(R.id.address_dialog_submit);
        mViewProvince = (WheelView) findViewById(R.id.address_dialog_wheel_1);
        mViewCity = (WheelView) findViewById(R.id.address_dialog_wheel_2);
        mViewDistrict = (WheelView) findViewById(R.id.address_dialog_wheel_3);
        addressDialogExit.setOnClickListener(this);
        addressDialogSubmit.setOnClickListener(this);

        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);

        //判断有没有地址
        if(provinceList != null && provinceList.size() > 0){
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i <provinceList.size(); i++) {
                mProvinceDatas[i]=provinceList.get(i).getName();
            }
            setUpData();
        }else{
            ToastUtil.showMessage("网络连接错误");
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //关闭
            case R.id.address_dialog_exit:
                this.cancel();
                break;
            //确定
            case R.id.address_dialog_submit:
                regionName=provinceList.get(pid).getName()+""+provinceList.get(pid).getCities().get(cid).getName();
                if(provinceList.get(pid).getCities().get(cid).getName()!=null && provinceList.get(pid).getCities().get(cid).getRegions() != null && provinceList.get(pid).getCities().get(cid).getName()!=null && provinceList.get(pid).getCities().get(cid).getRegions().size()>0){
                    regionName+=""+provinceList.get(pid).getCities().get(cid).getRegions().get(aid).getName();
                    regionCode=provinceList.get(pid).getCities().get(cid).getRegions().get(aid).getKey();
                }else{
                    regionCode=provinceList.get(pid).getCities().get(cid).getKey();
                }
                ToastUtil.showMessage(regionName+"------"+regionCode);
                this.cancel();
                dataListener.AddressCode(regionCode,regionName);
                break;
        }
    }
    private void setUpData() {
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCity();
        updateArea();

        mViewProvince.setCurrentItem(pid);
        mViewCity.setCurrentItem(cid);
        mViewDistrict.setCurrentItem(aid);
    }
    private void updateCity() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCityDatas = new String[provinceList.get(pCurrent).getCities().size()];
        for (int i = 0; i <provinceList.get(pCurrent).getCities().size(); i++) {
            mCityDatas[i] = provinceList.get(pCurrent).getCities().get(i).getName();
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, mCityDatas));
        mViewCity.setCurrentItem(0);
    }
    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateArea() {
        int pCurrent = mViewProvince.getCurrentItem();
        int cCurrent =mViewCity.getCurrentItem();
        if(provinceList.get(pCurrent).getCities().get(cCurrent).getRegions() == null || provinceList.get(pCurrent).getCities().get(cCurrent).getRegions().size() ==0){
            mAreaDatas = new String[] {""};
        } else {
            mAreaDatas = new String[provinceList.get(pCurrent).getCities().get(cCurrent).getRegions().size()];
            for (int i = 0; i < provinceList.get(pCurrent).getCities().get(cCurrent).getRegions().size(); i++) {
                mAreaDatas[i] = provinceList.get(pCurrent).getCities().get(cCurrent).getRegions().get(i).getName();
            }
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(context, mAreaDatas));
        mViewDistrict.setCurrentItem(0);
    }
}
