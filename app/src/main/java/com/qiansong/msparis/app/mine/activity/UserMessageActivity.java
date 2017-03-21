package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.DateUtil;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.UserSizeAdapter;
import com.qiansong.msparis.app.mine.bean.PushImgBean;
import com.qiansong.msparis.app.mine.bean.UserBean;
import com.qiansong.msparis.app.mine.util.GlideLoader;
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
import rx.schedulers.Schedulers;

/**
 * kevin .cao
 * <p>
 * 补全用户信息
 */
public class UserMessageActivity extends BaseActivity {


    @InjectView(R.id.title_bar)
    ETitleBar titleBar;
    @InjectView(R.id.user_image)
    SimpleDraweeView userImage;
    @InjectView(R.id.user_radio1)
    RadioButton userRadio1;
    @InjectView(R.id.user_radio2)
    RadioButton userRadio2;
    @InjectView(R.id.user_radio_group)
    RadioGroup userRadioGroup;
    @InjectView(R.id.user_name)
    EditText userName;
    @InjectView(R.id.user_time)
    TextView userTime;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.user_height)
    EditText userHeight;
    @InjectView(R.id.editText)
    TextView editText;
    @InjectView(R.id.user_button)
    TextView userButton;
    @InjectView(R.id.user_list)
    GridView userList;
    //用户tonken
    private String token = "";
    public BaseBean userBean = null;
    private UserSizeAdapter adapter;

    /**
     * 性别 f.女性 m.男性
     **/
    private String sex = "f";
    //出生日期
    private String userdata="";
    //尺码
    private List<String> size = new ArrayList<>();
    private TimePickerView pvTime;
    //图片上传
    private PushImgBean pushImgBean= null;
    //图片路径
    private String imageUri = "";
    private String Path;
    private List<String> pathList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
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

        List<ConfigsBean.DataEntity.UserSizeEntity.UserSizesEntity> sizesEntities = MyApplication.getUserSizeEntity().getUser_size();
        List<UserBean.DataBean.UserSizeBean> list = new ArrayList<>();
        for (int i = 0; i < sizesEntities.size(); i++) {
            UserBean.DataBean.UserSizeBean bean = new UserBean.DataBean.UserSizeBean();
            bean.setId(sizesEntities.get(i).getId());
            bean.setName(sizesEntities.get(i).getName());
            list.add(bean);
        }
        size.clear();
        adapter.setData(list);
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



        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //默认为女性
        userRadio1.setChecked(true);

        //时间选择器
        Calendar calendar = Calendar.getInstance();
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                userTime.setText(DateUtil.getStringTime(date));
                userdata = DateUtil.getStringTime(date);
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//default is all
                .setTitleText("选择出生日期")
                .setOutSideCancelable(false)// default is true
                .setRange(calendar.get(Calendar.YEAR) - 70, calendar.get(Calendar.YEAR))//default 1900-2100 years *//*
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setLineSpacingMultiplier(1.8f)//设置两横线之间的间隔倍数
                .setLabel("","","","","","") //设置空字符串以隐藏单位提示   hide label
                .build();

    }
    /**
     *
     * 上传图片
     *
     */
    public  void  pushImage(String path){
        dialog.show();
        final File file = new File(path);
        Compressor.getDefault(this)
                .compressToFileAsObservable(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("file/jpeg"), file);
        HttpServiceClient.getInstance().pushImage(requestBody)
                .enqueue(new Callback<PushImgBean>() {
                    @Override
                    public void onResponse(Call<PushImgBean> call, Response<PushImgBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            pushImgBean = response.body();
                            if (pushImgBean != null) {
                                if ("ok".equals(pushImgBean.getStatus())) {
                                    imageUri = pushImgBean.getData().get(0).getUri();
                                    ToastUtil.showMessage("上传成功");
                                    //设置会员头像信息
                                    ExclusiveUtils.setImageUrl(userImage, pushImgBean.getData().get(0).getUrl(), -1);
                                } else {
                                    ToastUtil.showMessage(pushImgBean.getError().getMessage());
                                }
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<PushImgBean> call, Throwable t) {

                    }
                });
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtil.showMessage("上传失败,请检查网络");
                    }
                });

    }

    /**
     * 网络请求
     */
    public void requestData(String access_token,
                            String nickname,
                            String gender,
                            String dob,
                            String fit_height,
                            String head_portrait,
                            String size) {
        dialog.show();
        Map<String,String>map=new HashMap<>();
        map.put("access_token",access_token);
        map.put("nickname",nickname);
        map.put("gender",gender);
        map.put("dob",dob);
        map.put("fit_height",fit_height);
        map.put("head_portrait",head_portrait);
        map.put("size",size);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),map.toString());
        HttpServiceClient.getInstance().updateUser(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            userBean = response.body();
                            if (userBean != null) {
                                if ("ok".equals(userBean.getStatus())) {
                                    initData();
                                } else {
                                    ToastUtil.showMessage(userBean.getError().getMessage());
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
        ToastUtil.showMessage("修改成功，OK");
        finish();
    }


    /**
     *
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.user_image, R.id.user_radio1, R.id.user_radio2, R.id.user_time,R.id.user_button})
    public void onClick(View view) {
        switch (view.getId()) {
            //图片
            case R.id.user_image:
                selectPicture();
                break;
            //女士
            case R.id.user_radio1:
                sex = "f";
                break;
            //男士
            case R.id.user_radio2:
                sex = "m";
                break;
            //出生日期
            case R.id.user_time:
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
                    requestData(token,userName.getText().toString(),sex,userdata,userHeight.getText().toString(),imageUri,sizeStr.toString());

                }else{
                    requestData(token,userName.getText().toString(),sex,userdata,userHeight.getText().toString(),null,sizeStr.toString());
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
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("个人信息");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutVisibility(View.GONE);
        titleBar.setRightTitle("跳过");
        titleBar.setRightTitleVisibility(View.VISIBLE);
//        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
//            @Override
//            public void onClick(View view) {
//                UserMessageActivity.super.onBackPressed();
//            }
//        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(UserMessageActivity.this, UpdateAddressActivity.class);
//                startActivity(intent);
                UserMessageActivity.super.onBackPressed();
            }
        });
    }

}
