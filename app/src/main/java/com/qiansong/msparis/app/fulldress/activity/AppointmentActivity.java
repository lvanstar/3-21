package com.qiansong.msparis.app.fulldress.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.cao
 * <p>
 * 预约试穿
 */
public class AppointmentActivity extends BaseActivity {

    @InjectView(R.id.appointment_text1)
    TextView appointmentText1;
    @InjectView(R.id.appointment_layout1)
    LinearLayout appointmentLayout1;
    @InjectView(R.id.appointment_text2)
    TextView appointmentText2;
    @InjectView(R.id.appointment_layout2)
    LinearLayout appointmentLayout2;
    @InjectView(R.id.appointment_text3)
    TextView appointmentText3;
    @InjectView(R.id.appointment_layout3)
    LinearLayout appointmentLayout3;
    @InjectView(R.id.appointment_text4)
    TextView appointmentText4;
    @InjectView(R.id.appointment_layout4)
    LinearLayout appointmentLayout4;
    @InjectView(R.id.appointment_text5)
    TextView appointmentText5;
    @InjectView(R.id.appointment_layout5)
    LinearLayout appointmentLayout5;
    @InjectView(R.id.appointment_text6)
    EditText appointmentText6;
    @InjectView(R.id.user_radio1)
    RadioButton userRadio1;
    @InjectView(R.id.user_radio2)
    RadioButton userRadio2;
    @InjectView(R.id.user_radio_group)
    RadioGroup userRadioGroup;
    @InjectView(R.id.appointment_text7)
    EditText appointmentText7;
    @InjectView(R.id.appointment_text8)
    EditText appointmentText8;
    @InjectView(R.id.appointment_submit)
    TextView appointmentSubmit;
    private ETitleBar titleBar;


    public OptionsPickerView cityPickerView;
    public OptionsPickerView addressPickerView;
    public OptionsPickerView typePickerView;
    public OptionsPickerView numPickerView;


    private ArrayList<String> cityList = new ArrayList<>();
    private ArrayList<String> addressList = new ArrayList<>();
    private ArrayList<String> typeList = new ArrayList<>();
    private ArrayList<String> numList = new ArrayList<>();

    //获取城市数据
    List<ConfigsBean.DataEntity.BookingCitiesEntityX.BookingCitiesEntity> booking_cities = null;
    List<ConfigsBean.DataEntity.BookingCitiesEntityX.BookingCitiesEntity.CitiesEntityX.ShopsEntity> shops = null;

    //f.女性 m.男性 默认为女性
    private String sex = "f";
    //用户tonken
    private String token = "";
    private BaseBean baseBean = null ;

    //选择的地址
    public ConfigsBean.DataEntity.BookingCitiesEntityX.BookingCitiesEntity.CitiesEntityX city  = null ;
    //选择的门店地址
    public ConfigsBean.DataEntity.BookingCitiesEntityX.BookingCitiesEntity.CitiesEntityX.ShopsEntity shop = null ;
    //预约类型   1.礼服 2.婚纱 3.化妆
    public int typeOption = 0;
    //人数
    public int numOption = 0;
    //某一天
    public String  date = "";
    //时间
    public String  time = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001 && data != null ) {
            time = data.getStringExtra("time");
            date = data.getStringExtra("date");
            appointmentText5.setText(date+"  "+time);
        }
    }

    /**
     * 加载页面
     */
    public void initView() {
        //初始化OptionsPickerView数据
        setOptionsPickerView();

        //默认选中女士
        userRadio1.setChecked(true);


    }

    /**
     * 网络请求
     */
    public void requestData(String access_token,
                            String name,
                            String mobile,
                            String gender,
                            String city_code,
                            String store,
                            String city_name,
                            String type,
                            String date,
                            String start_time,
                            String num,
                            String channel,
                            String remark) {
        Map<String,Object> map=new HashMap<>();
        map.put("access_token",token);
        map.put("name",name);
        map.put("mobile",mobile);
        map.put("gender",gender);
        map.put("city_code",city_code);
        map.put("store",store);
        map.put("city_name",city_name);
        map.put("type",type);
        map.put("date",date);
        map.put("start_time",start_time);
        map.put("num",num);
        map.put("channel",channel);
        map.put("remark",remark);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));



        HttpServiceClient.getInstance().create_booking_info(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if (baseBean != null) {
                                if ("ok".equals(baseBean.getStatus())) {
                                    ToastUtil.showMessage("预约成功");
                                } else {
                                    ToastUtil.showMessage(baseBean.getError().getMessage());
                                }
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });
    }

    /**
     * 填充数据
     */
    public void initData() {


    }


    /**
     *
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.appointment_layout1, R.id.appointment_layout2, R.id.appointment_layout3, R.id.appointment_layout4, R.id.appointment_layout5, R.id.user_radio1, R.id.user_radio2, R.id.appointment_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            //所在城市
            case R.id.appointment_layout1:
                cityPickerView.show();
                break;
            //门店
            case R.id.appointment_layout2:
                if (appointmentText1.getText().length() != 0) {

                    //      设置门店
                    addressList.clear();
                    shops = city.getShops();
                    for (int i = 0; i < shops.size(); i++) {
                        addressList.add(shops.get(i).getName());
                    }
                    addressPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            AppointmentActivity.this.shop = shops.get(options1);
                            appointmentText2.setText(addressList.get(options1).toString());
                        }
                    }).setSubmitText("确定")
                            .setCancelText("取消")
                            .setTitleText("选择门店")
                            .setOutSideCancelable(false)//点击外部dismiss, default true
                            .setSubCalSize(16)//确定取消按钮大小
                            .setLineSpacingMultiplier(1.8f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                            .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                            .setContentTextSize(18)//设置滚轮文字大小
                            .setSelectOptions(0, 0, 0)  //设置默认选中项
                            .build();
                    addressPickerView.setPicker(addressList);

                    addressPickerView.show();
                } else {
                    ToastUtil.showMessage("请先选择城市");
                }
                break;
            //预约类型
            case R.id.appointment_layout3:
                if (appointmentText1.getText().length() != 0) {
                    if (appointmentText2.getText().length() != 0) {
                        typePickerView.show();
                    } else {
                        ToastUtil.showMessage("请先选择门店");
                    }
                } else {
                    ToastUtil.showMessage("请先选择城市");
                }
                break;
            //试衣人数
            case R.id.appointment_layout4:
                if (appointmentText1.getText().length() != 0) {
                    if (appointmentText2.getText().length() != 0) {
                        if (appointmentText3.getText().length() != 0) {
                            numPickerView.show();
                        } else {
                            ToastUtil.showMessage("请先选择预约类型");
                        }
                    } else {
                        ToastUtil.showMessage("请先选择门店");
                    }
                } else {
                    ToastUtil.showMessage("请先选择城市");
                }
                break;
            //预约时间
            case R.id.appointment_layout5:

                if (appointmentText1.getText().length() != 0) {
                    if (appointmentText2.getText().length() != 0) {
                        if (appointmentText3.getText().length() != 0) {
                            if (appointmentText4.getText().length() != 0) {
                                Intent intent = new Intent();
                                intent.setClass(AppointmentActivity.this, AppointmentTimeActivity.class);
                                intent.putExtra("type",typeOption+"");
                                intent.putExtra("store_id",shop.getId());
                                startActivityForResult(intent, 10001);
                            } else {
                                ToastUtil.showMessage("请先选择试衣人数");
                            }
                        } else {
                            ToastUtil.showMessage("请先选择预约类型");
                        }
                    } else {
                        ToastUtil.showMessage("请先选择门店");
                    }
                } else {
                    ToastUtil.showMessage("请先选择城市");
                }

                break;
            //女士
            case R.id.user_radio1:
                sex = "f";
                break;
            //男士
            case R.id.user_radio2:
                sex = "m";
                break;
            //提交
            case R.id.appointment_submit:
                if (appointmentText1.getText().length() != 0) {
                    if (appointmentText2.getText().length() != 0) {
                        if (appointmentText3.getText().length() != 0) {
                            if (appointmentText4.getText().length() != 0) {
                                if (appointmentText5.getText().length() != 0) {
                                    if (appointmentText6.getText().length() != 0) {
                                        if (appointmentText7.getText().length() != 0) {
                                            submitData();
                                        } else {
                                            ToastUtil.showMessage("请填写手机号码");
                                        }
                                    } else {
                                        ToastUtil.showMessage("请选择联系人");
                                    }
                                } else {
                                    ToastUtil.showMessage("请选择预约时间");
                                }
                            } else {
                                ToastUtil.showMessage("请选择试衣人数");
                            }
                        } else {
                            ToastUtil.showMessage("请选择预约类型");
                        }
                    } else {
                        ToastUtil.showMessage("请选择门店");
                    }
                } else {
                    ToastUtil.showMessage("请选择城市");
                }
                break;
        }
    }

    //准备数据请求接口
    public  void submitData(){
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        requestData(token,
                appointmentText6.getText().toString(),
                appointmentText7.getText().toString(),
                sex,
                city.getKey(),
                shop.getId(),
                city.getName(),
                typeOption+"",
                date,//日期
                time,
                numOption+"",
                "android",
                appointmentText8.getText().toString());

    }
    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("预约试穿");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setRightTitle("预约历史");
        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                AppointmentActivity.super.onBackPressed();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(AppointmentActivity.this, AppointmentRecordActivity.class);
//                intent.setClass(AppointmentActivity.this, AppointmentTimeActivity.class);
                startActivity(intent);

            }
        });
    }

    public void setOptionsPickerView(){
        //获取城市数据
        booking_cities =MyApplication.getBookingEntity().getBooking_cities();
        for (int i = 0; i <booking_cities.size(); i++) {
            cityList.add(booking_cities.get(i).getName());
        }
        cityPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                AppointmentActivity.this.city = booking_cities.get(options1).getCities().get(0);

                appointmentText1.setText(cityList.get(options1).toString());
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("选择城市")
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setSubCalSize(16)//确定取消按钮大小
                .setLineSpacingMultiplier(1.8f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)//设置滚轮文字大小
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .build();
        cityPickerView.setPicker(cityList);






        typeList.add("礼服");
        typeList.add("婚纱");
        typeList.add("化妆");
        typePickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                AppointmentActivity.this.typeOption = options1+1;
                appointmentText3.setText(typeList.get(options1).toString());
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("选择类型")
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setSubCalSize(16)//确定取消按钮大小
                .setLineSpacingMultiplier(1.8f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)//设置滚轮文字大小
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .build();
        typePickerView.setPicker(typeList);

        numList.add("1");
        numList.add("2");
        numList.add("3");
        numList.add("4");
        numList.add("5");
        numList.add("6");
        numPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                AppointmentActivity.this.numOption = options1+1;
                appointmentText4.setText(numList.get(options1).toString());
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("选择人数")
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setSubCalSize(16)//确定取消按钮大小
                .setLineSpacingMultiplier(1.8f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)//设置滚轮文字大小
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .build();
        numPickerView.setPicker(numList);

    }

    public  void  buyCityGetStore(){

    }

}
