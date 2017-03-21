package com.qiansong.msparis.app.find.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.find.adapter.PushImageAdapter;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.mine.bean.PushImgBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lizhaozhao on 2017/2/9.
 * <p>
 * 晒图
 */

public class SlideShowActivity extends Activity {
    public static final String CAMERA = "CAMERA";//拍照
    public static final String ALBUM = "ALBUM";//相册

    private static final int PHOTO_GRAPH = 99;// 拍照

    SlideShowActivity context;

    EditText et_text;//输入

    GridView rl_push;//图片展示的gridview
    private List<ImageBean> path_list; //展示给用户看的图片集合
    private List<ImageBean> path_old;//保存历史记录

    Handler handler;
    List<RequestBody> data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);
        context = this;
        init();
        setListeners();
        if (CAMERA.equals(getIntent().getStringExtra(CAMERA))){//相机
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(),"temp.jpg")));
            startActivityForResult(intent, PHOTO_GRAPH);
        }else if (ALBUM.equals(getIntent().getStringExtra(ALBUM))){//相册
            selectPicture();
        }

    }



    PushImageAdapter adapter;
    private void init() {
        path_list=new ArrayList<>();
        path_old=new ArrayList<>();
        data=new ArrayList<>();

        et_text = (EditText) findViewById(R.id.et_text);

        rl_push= (GridView) findViewById(R.id.rl_push);
        adapter = new PushImageAdapter(this, path_list);
        rl_push.setAdapter(adapter);

        handler=new Handler(new Handler.Callback() {//压缩图片完成后的操作
            @Override
            public boolean handleMessage(Message msg) {

                push(data);
                return false;
            }
        });
    }

    private void setListeners() {
        rl_push.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                path_old.clear();
                if (path_list.size()!=0)path_old.addAll(path_list);

                if (position == getDataSize()) {
                    selectPicture();
                } else {

                    List<String> list=new ArrayList<>();
                    for (int i=0;i<path_list.size();i++){
                        list.add(path_list.get(i).path);
                    }
                    path_list.clear();
                    PhotoPreview.builder()
                            .setPhotos((ArrayList<String>) list)
                            .setCurrentItem(position)
                            .start(context);
                }
            }
        });

        findViewById(R.id.release).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (path_list.size()==0){
                    if ("".equals(et_text.getText().toString())){
                        ContentUtil.makeToast(context,"内容不能为空");
                    }else {
//                        to_release();
                    }

                }else {
                    if (path_list.size()!=0) {
                        upload();
                    }
                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void address(View view){
        Intent intent = new Intent(context, PoiActivity.class);
        startActivityForResult(intent, 2);
    }

    private int getDataSize()
    {
        return path_list == null ? 0 : path_list.size();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PhotoPicker.REQUEST_CODE||requestCode == PhotoPreview.REQUEST_CODE
                && resultCode == RESULT_OK ) {//相册

            List<String> pathList=new ArrayList<>();
            if (data != null) {
                pathList = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS) == null ?
                        (data.getStringArrayListExtra("SELECTED_PHOTOS") == null ? new ArrayList<String>()
                                : data.getStringArrayListExtra("SELECTED_PHOTOS"))
                        : data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            for (int n=0;n<pathList.size();n++){
                 path_list.add(new ImageBean(pathList.get(n)));
            }
            if (pathList.size()==0)path_list.addAll(path_old);
            adapter.updatas(path_list);
        } else if (requestCode == PHOTO_GRAPH&&resultCode==RESULT_OK) {// 拍照
            // 设置文件保存路径
            File picture = new File(Environment.getExternalStorageDirectory()
                    + "/temp.jpg");
            String uriPath=Uri.fromFile(picture).getPath();
            path_list.add(new ImageBean(uriPath));
        }else if (resultCode==3){
            ((TextView)findViewById(R.id.slideshow_address)).setText(data.getStringExtra("back"));
//            lat_select=data.getDoubleExtra("lat",0);
//            lng_select=data.getDoubleExtra("lng",0);
        }
    }

    //去相册选择图片
    private void selectPicture(){


        List<String> list=new ArrayList<>();
        for (int i=0;i<path_list.size();i++){
                list.add(path_list.get(i).path);
        }
        path_list.clear();

        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setSelected((ArrayList<String>) list)
                .setPreviewEnabled(false)
                .start(context, PhotoPicker.REQUEST_CODE);
    }
    //上传
    private void upload(){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   yasuo_long();
               }
           }).start();
    }



    //上传请求接口
    private void push(final List<RequestBody> data){
        for (int i=0;i<data.size();i++){
            if (data.get(i)==null){
                data.remove(i);
            }
        }
        Call<PushImgBean> call = HttpServiceClient.getInstance().pushimg(buData(data,0),buData(data, 1),
                buData(data, 2),buData(data, 3),buData(data, 4),buData(data, 5),buData(data, 6),
                buData(data, 7),buData(data, 8));
        call.enqueue(new retrofit2.Callback<PushImgBean>() {
            @Override
            public void onResponse(Call<PushImgBean> call, Response<PushImgBean> response) {
                if (response.isSuccessful() && response.body().getStatus().equals("ok")) {
                    Eutil.makeLog("图片上传成功");
                    for (int i=0;i<response.body().getData().size();i++) {

                    }
                }
            }

            @Override
            public void onFailure(Call<PushImgBean> call, Throwable t) {

            }
        });
    }

    //压缩图片
    private void yasuo_long(){
        data.clear();
        for (int f=0;f<path_list.size();f++) {
            File file = new File(path_list.get(f).path);
            if (file.getName().contains(".gif")||file.getName().contains(".GIF")){
                final RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                data.add(requestBody);
            }else {
                Eutil.makeLog("压缩前大小：" + file.length() / 1024 + "KB");
                File newFile = new Compressor.Builder(this)
                        .setQuality(75)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .build()
                        .compressToFile(file);
                Eutil.makeLog("path：" + newFile.getPath());

             final RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), newFile);
             Eutil.makeLog("压缩后大小："+newFile.length()/1024+"KB");

            data.add(requestBody);
            }
            if (f==(path_list.size()-1))handler.sendEmptyMessage(0);
        }
    }

    //补全参数
    private RequestBody buData(List<RequestBody> data,int i){
        if (data.size()<(i+1))
            return null;
        return data.get(i);
    }
    //图片实体类
   public class ImageBean{
        public String path;//路径
        public boolean isStartCamera=false;//是否是第一次照相跳过来的

        public ImageBean(String path) {
            this.path = path;
        }
    }
}
