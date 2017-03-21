package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.model.IPickerViewData;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.iosdialog.widget.AlertDialog;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.mine.bean.ProvinceBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.util.AddressDialog;

import java.util.ArrayList;
import java.util.HashMap;
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
 * coalei
 * <p>
 * 修改地址页面
 */
public class UpdateAddressActivity extends BaseActivity implements View.OnClickListener {


    /**
     * 姓名
     */
    @InjectView(R.id.addresss_name)
    EditText addresssName;
    /**
     * 手机号码
     */
    @InjectView(R.id.addresss_phone)
    EditText addresssPhone;
    /**
     * 所在地区
     */
    @InjectView(R.id.addresss_region)
    TextView addresssRegion;
    /**
     * 详细地区
     */
    @InjectView(R.id.addresss_detail_address)
    EditText addresssDetailAddress;
    /**
     * 保存
     */
    @InjectView(R.id.address_submit)
    TextView addressSubmit;

    public ETitleBar titleBar;

    //用户tonken
    private String token = "";
    private BaseBean bean = null;
    private String code = "";
    private AddressBean.DataBean.RowsBean address = null;

    private AddressDialog addressDialog = null;
    /**
     * 地区选择器
     */
//    public  ConfigsBean.DataEntity.SendCitiesEntityX address;
    public OptionsPickerView pvOptions;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<IPickerViewData>>> options3Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        //最好等数据加载完毕再初始化并显示，以免数据量大的时候，还未加载完毕就显示，造成APP崩溃
//        initOptionData();
//        initOptionPicker();
    }

    /**
     * 加载页面
     */
    public void initView() {
        //获取列表传递过来的
        address = (AddressBean.DataBean.RowsBean) getIntent().getSerializableExtra(GlobalConsts.ADDRESS_INTENT);
        //添加
        if (address == null) {
            titleBar.setTitle("添加收货地址");
            titleBar.setRightLayoutClickable(false);
        } else if (address != null) {
            titleBar.setTitle("编辑收货地址");
            titleBar.setRightTitle("删除");
            titleBar.setRightTitleVisibility(View.VISIBLE);
            addresssName.setText(address.getContact_name());
            addresssName.setSelection(address.getContact_name().length());
            addresssPhone.setText(address.getContact_mobile());
            addresssRegion.setText(address.getRegion_name());
            addresssDetailAddress.setText(address.getAddress_detail());
            code = address.getRegion_code();
            addresssName.clearFocus();
            addresssName.setSelected(false);
        }
    }

    /**
     * 网络请求  添加或者修改
     * id: 添加是不需要设置的
     */
    public void requestData(String id,
                            String contact_name,
                            String region_code,
                            String contact_mobile,
                            String region_name,
                            String address_detail) {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);

        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        //修改
        if (address != null) {
            map.put("id", id);
        }
        map.put("contact_name", contact_name);
        map.put("region_code", region_code);
        map.put("contact_mobile", contact_mobile);
        map.put("region_name", region_name);
        map.put("address_detail", address_detail);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        //获取收货地址
        HttpServiceClient.getInstance().address_update(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                if (address == null) {
                                    ToastUtil.showAnimToast("添加收货地址成功");
                                } else if (address != null) {
                                    ToastUtil.showAnimToast("修改收货地址成功");
                                }
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        } else {
                            ToastUtil.showMessage("网络连接失败");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });
    }


    /**
     * 删除地址
     *
     * @param id
     */
    public void requestData(String id) {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //获取收货地址
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("id", id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        HttpServiceClient.getInstance().address_delete(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                ToastUtil.showAnimToast("删除成功");
                                UpdateAddressActivity.this.finish();
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        } else {
                            ToastUtil.showMessage("网络连接失败");
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
     * 点击事件
     */
    @OnClick({R.id.address_submit, R.id.addresss_region})
    public void onClick(View view) {
        switch (view.getId()) {
            //地址
            case R.id.addresss_region:
                addressDialog = new AddressDialog(this);
                addressDialog.setDataListener(new AddressDialog.DataListener() {
                    @Override
                    public void AddressCode(String codes, String address) {
                        code = codes;
                        addresssRegion.setText(address);
                    }
                });
                break;
            //保存
            case R.id.address_submit:
//                code = ""
                if (addresssName.getText().toString().length() > 0) {
                    if (addresssPhone.getText().toString().length() > 0) {
                        if (code != null && code.length() > 0) {
                            if (addresssDetailAddress.getText().toString().length() > 0) {
                                //添加
                                if (address == null) {
                                    //添加
                                    requestData(null,
                                            addresssName.getText().toString().trim(),
                                            code,
                                            addresssPhone.getText().toString().trim(),
                                            addresssRegion.getText().toString().trim(),
                                            addresssDetailAddress.getText().toString().trim());
                                } else if (address != null) {
                                    //修改
                                    requestData(address.getId() + "",
                                            addresssName.getText().toString().trim(),
                                            code,
                                            addresssPhone.getText().toString().trim(),
                                            addresssRegion.getText().toString().trim(),
                                            addresssDetailAddress.getText().toString().trim());
                                }
                            } else {
                                ToastUtil.showAnimToast("请输入详细地址");
                            }
                        } else {
                            ToastUtil.showAnimToast("请选择地址");
                        }
                    } else {
                        ToastUtil.showAnimToast("请输入手机号");
                    }
                } else {
                    ToastUtil.showAnimToast("请输入姓名");
                }
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("编辑收货地址");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                UpdateAddressActivity.super.onBackPressed();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
                new AlertDialog(UpdateAddressActivity.this).builder().setTitle("删除地址")
                        .setHasTitleMsg("是否确定删除该收发货地址？")
                        .setCancelable(false)
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestData(address.getId() + "");
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pvOptions != null && pvOptions.isShowing()) {
                pvOptions.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
