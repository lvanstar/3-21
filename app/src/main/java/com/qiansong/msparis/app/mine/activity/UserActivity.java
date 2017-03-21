package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.mine.adapter.UserSizeAdapter;
import com.qiansong.msparis.app.mine.bean.PushImgBean;
import com.qiansong.msparis.app.mine.bean.UserBean;
import com.qiansong.msparis.app.mine.util.GlideLoader;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.DateUtil;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;


/**
 * coalei
 * <p>
 * 个人信息
 */
public class UserActivity extends BaseActivity {

    @InjectView(R.id.user_image)
    SimpleDraweeView userImage;
    @InjectView(R.id.user_image_layout)
    LinearLayout userImageLayout;
    @InjectView(R.id.user_name)
    EditText userName;
    @InjectView(R.id.user_sex)
    TextView userSex;
    @InjectView(R.id.user_sex_layout)
    LinearLayout userSexLayout;
    @InjectView(R.id.user_date)
    TextView userDate;
    @InjectView(R.id.user_date_layout)
    LinearLayout userDateLayout;
    @InjectView(R.id.user_height)
    EditText userHeight;
//    @InjectView(R.id.user_size_xs)
//    CheckBox userSizeXs;
//    @InjectView(R.id.user_size_s)
//    CheckBox userSizeS;
//    @InjectView(R.id.user_size_m)
//    CheckBox userSizeM;
//    @InjectView(R.id.user_size_l)
//    CheckBox userSizeL;
//    @InjectView(R.id.user_size_xl)
//    CheckBox userSizeXl;
//    @InjectView(R.id.user_size_other)
//    CheckBox userSizeOther;
    @InjectView(R.id.user_button)
    TextView userButton;
    @InjectView(R.id.user_list)
    GridView userList;


    private ETitleBar titleBar;
    //用户tonken
    private String token = "";
    public UserBean userBean = null;
    private BaseBean baseBean = null ;

    /**
     * 性别
     **/
    //f.女性 m.男性
    public OptionsPickerView sexOptions;
    private String sex = "";
    private ArrayList<String> sexList = new ArrayList<>();
    private TimePickerView pvTime;
    //尺码
    private List<String> size = new ArrayList<>();

    //图片上传
    private PushImgBean pushImgBean= null;
    //图片路径
    private String imageUri = "";
    private String Path;
    private List<String> pathList=new ArrayList<>();
    private UserSizeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);//得到选中的图片集合
            pushImage(pathList.get(0));
        }
    }
    /**
     * 加载页面
     */
    public void initView() {

        //设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(userList,6);

        adapter = new UserSizeAdapter(this);
        userList.setAdapter(adapter);


        sexList.add("女");
        sexList.add("男");
        sexOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                userSex.setText(sexList.get(options1).toString());
                if ("男".equals(sexList.get(options1).toString())) {
                    sex = "m";
                } else if ("女".equals(sexList.get(options1).toString())) {
                    sex = "f";
                }
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("选择性别")
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setSubCalSize(18)//确定取消按钮大小
                .setLineSpacingMultiplier(1.8f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)//设置滚轮文字大小
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .build();
        sexOptions.setPicker(sexList);


        //时间选择器
        Calendar calendar = Calendar.getInstance();
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                userDate.setText(DateUtil.getStringTime(date));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//default is all
                .setTitleText("选择出生日期")
                .setOutSideCancelable(false)// default is true
                .setRange(calendar.get(Calendar.YEAR) - 70, calendar.get(Calendar.YEAR))//default 1900-2100 years *//*
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setLineSpacingMultiplier(1.8f)//设置两横线之间的间隔倍数
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .build();

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        HttpServiceClient.getInstance().getUser(token)
                .enqueue(new Callback<UserBean>() {
                    @Override
                    public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            userBean = response.body();
                            if (userBean != null) {
                                if ("ok".equals(userBean.getStatus())) {
                                    initData();
                                } else {

                                }
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<UserBean> call, Throwable t) {

                    }
                });

    }
    /**
     *
     * 上传图片
     *
     */
    public  void  pushImage(String imagePath){
        dialog.show();
        ContentUtil.makeLog("lzz","image:"+imagePath);
        final File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        HttpServiceClient.getInstance().pushImage(requestBody).enqueue(new Callback<PushImgBean>() {
            @Override
            public void onResponse(Call<PushImgBean> call, Response<PushImgBean> response) {

                if(response.isSuccessful()){

                    if("ok".equals(response.body().getStatus())){
                        imageUri = pushImgBean.getData().get(0).getUri();
                        ToastUtil.showMessage("上传成功");
                        //设置会员头像信息
                        ExclusiveUtils.setImageUrl(userImage, pushImgBean.getData().get(0).getUrl(), -1);

                    }else {
                        ContentUtil.makeLog("lzz",response.body().getError().getMessage());
                    }
                }else {
                    ContentUtil.makeLog("lzz","222222");
                }
            }

            @Override
            public void onFailure(Call<PushImgBean> call, Throwable t) {

                ContentUtil.makeLog("lzz","111111"+t.toString());
            }
        });
//        Compressor.getDefault(UserActivity.this)
//                .compressToFileAsObservable(file)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<File>() {
//                    @Override
//                    public void call(File file) {
//                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
//                        HttpServiceClient.getInstance().pushImage(requestBody)
//                                .enqueue(new Callback<PushImgBean>() {
//                                    @Override
//                                    public void onResponse(Call<PushImgBean> call, Response<PushImgBean> response) {
//                                        dialog.cancel();
//                                        if (response.isSuccessful()) {
//                                            pushImgBean = response.body();
//                                            if (pushImgBean != null) {
//                                                if ("ok".equals(pushImgBean.getStatus())) {
//                                                    imageUri = pushImgBean.getData().get(0).getUri();
//                                                    ToastUtil.showMessage("上传成功");
//                                                    //设置会员头像信息
//                                                    ExclusiveUtils.setImageUrl(userImage, pushImgBean.getData().get(0).getUrl(), -1);
//                                                } else {
//                                                    ToastUtil.showMessage(pushImgBean.getError().getMessage());
//                                                }
//                                            }
//                                        } else {
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<PushImgBean> call, Throwable t) {
//
//                                    }
//                                });
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        ToastUtil.showMessage("上传失败,请检查网络");
//                    }
//                });

    }

    /**
     * 修改用户信息
     */
    public void updataUserRequestData(String access_token,
                            String nickname,
                            String gender,
                            String dob,
                            String fit_height,
                            String head_portrait,
                            String size) {
        dialog.show();
        Map<String,Object> map=new HashMap<>();
        map.put("access_token",access_token);
        map.put("nickname",nickname);
        map.put("gender",gender);
        map.put("dob",dob);
        map.put("fit_height",fit_height);
        map.put("head_portrait",head_portrait);
        map.put("size",size);

        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),JsonUtil.toJson(map));
        HttpServiceClient.getInstance().updateUser(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if (baseBean != null) {
                                if ("ok".equals(baseBean.getStatus())) {
                                    ToastUtil.showMessage("修改成功");
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
        size.clear();
        adapter.setData(userBean.getData().getUser_size());
        adapter.setReturnSize(new UserSizeAdapter.ReturnSize() {
            @Override
            public void returnSize(String  id, boolean ischeck) {
                if(ischeck){
                    if(! size.contains(id)){
                        size.add(id);
                    }
                }else{
                    if(size.contains(id)){
                        size.remove(id);
                    }
                }
            }
        });

        /** 设置会员的信息***/
        ExclusiveUtils.setImageUrl(userImage, userBean.getData().getHead_portrait(), -1);
        userName.setText(userBean.getData().getNickname());

        if ("m".equals(userBean.getData().getGender())) {
            userSex.setText("男");
            sex = "m";
        } else if ("f".equals(userBean.getData().getGender())) {
            userSex.setText("女");
            sex = "f";
        }

        userDate.setText(userBean.getData().getDob());
        userHeight.setText(userBean.getData().getFit_height() + "");
    }


    /**
     * 点击事件
     *R.id.user_size_xs, R.id.user_size_s, R.id.user_size_m, R.id.user_size_l, R.id.user_size_xl, R.id.user_size_other,
     * @param view
     */
    @OnClick({R.id.user_image_layout, R.id.user_sex_layout, R.id.user_date_layout,R.id.user_button})
    public void onClick(View view) {
        switch (view.getId()) {
            //更换图片
            case R.id.user_image_layout:
                selectPicture();
                break;
            //选择性别
            case R.id.user_sex_layout:
                sexOptions.show();
                break;
            //选择生日
            case R.id.user_date_layout:
                pvTime.show();
                break;
            //提交用户信息
            case R.id.user_button:
                StringBuffer sizeStr =  new StringBuffer();
                for (int i = 0; i <size.size() ; i++) {
                    if(i==0){
                        sizeStr.append(size.get(i));
                    }else{
                        sizeStr.append(","+size.get(i));
                    }
                }
                if(imageUri.length()>0){
                    updataUserRequestData(token,userName.getText().toString(),sex,userDate.getText().toString(),userHeight.getText().toString(),imageUri,sizeStr.toString());
                }else{
                    updataUserRequestData(token,userName.getText().toString(),sex,userDate.getText().toString(),userHeight.getText().toString(),null,sizeStr.toString());
                }
                break;
        }
    }


    //选择图片
    private void selectPicture(){

        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(getResources().getColor(R.color.black))
                .titleBgColor(getResources().getColor(R.color.black))
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                .titleTextColor(getResources().getColor(R.color.white))
                .crop()//截图
                // 开启单选   （默认为多选）
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/ImageSelector/Pictures")
                .build();


        ImageSelector.open(this, imageConfig);   // 开启图片选择器
        Path=imageConfig.getFilePath();

    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("个人信息");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("添加");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                UserActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(AddressActivity.this, UpdateAddressActivity.class);
//                intent.putExtra(GlobalConsts.ADDRESS_TYPE,GlobalConsts.ADDRESS_TYPE_ADD);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pvTime.isShowing()) {
                pvTime.dismiss();
                return true;
            }
            if (sexOptions.isShowing()) {
                sexOptions.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
