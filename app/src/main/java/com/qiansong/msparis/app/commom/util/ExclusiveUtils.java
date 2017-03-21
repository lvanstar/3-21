package com.qiansong.msparis.app.commom.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.qiansong.msparis.app.commom.selfview.showimage.ImagePagerActivity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;



/**
 * Created by ye on 2016/4/19.
 */
public class ExclusiveUtils {
    //设置网络图片 type 1=小 2=中
    public static void setImageUrl(SimpleDraweeView imageview, String url, int type){
        if (imageview==null||url==null)return;
        if (url.contains("https:")){
            type=-1;
        }
        if (url.contains(".gif")||url.contains(".GIF")){
//            Glide.with(MyApplication.getApp()).load(url).asGif().into(imageview);

            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(
                    Uri.parse(url)).build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request).setAutoPlayAnimations(true)
                    .build();
            imageview.setController(controller);
            return;
        }

        String URL="";
        switch (type){
            case 1:
                URL=url+"plist2x";
                break;
            case 2:
                URL=url+"plist3x";
                break;
            default:
                URL=url;
                break;
        }


        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(URL))
                .setLocalThumbnailPreviewsEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(imageview.getController())
                .build();
        imageview.setController(controller);
    }
    /**
     * 显示或隐藏输入法  true 显示 false 隐藏---------------------------------------------------------------------------------------------
     */
    private void onFocusChange(boolean hasFocus, final EditText view) {
        final boolean isFocus = hasFocus;
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                if (isFocus) {
                    // 显示输入法
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    // 隐藏输入法
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }, 100);
    }

    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public static Date toDate(long sdate) {



        try {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormater.get().parse(format.format(sdate));
        } catch (ParseException e) {
            return null;
        }
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static String dateToStrLong(long dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }



   //判断网络是否连接
   public static boolean isNetworkAvailable(Context context) {
       ConnectivityManager connectivity = (ConnectivityManager) context
               .getSystemService(Context.CONNECTIVITY_SERVICE);
       if (connectivity == null) {
           return false;
       } else {
           NetworkInfo[] info = connectivity.getAllNetworkInfo();
           if (info != null) {
               for (int i = 0; i < info.length; i++) {
                   if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                       NetworkInfo netWorkInfo = info[i];
                       if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                           return true;
                       } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                           return true;
                       }
                   }
               }
           }
       }
       return false;
   }

    //得到图片二进制
    public static byte[] getImage(final String URL_STRING) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] data = null;
                try {
                    //建立URL
                    URL url = new URL(URL_STRING);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);

                    InputStream input = conn.getInputStream();
                    data = readInputStream(input);
                    input.close();

                    System.out.println("下载完毕！");
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               // return data;
            }
        }).start();
       return null;
    }
    public static byte[] readInputStream(InputStream input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output.toByteArray();
    }


    //得到图片二进制
    public static byte[] path2Bytes(String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapFactory.decodeFile(path).compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    //设置字体风格  eg:"fonts/STXINGKA.TTF"
    public static void setFengGe(Context context, TextView tv, String ttf){
        Typeface fontFace = Typeface.createFromAsset(context.getAssets(),ttf);
        tv.setTypeface(fontFace);
    }

    //获取通知栏高度
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }



    //保存图片
    public static void saveImage(final Activity activity, final String url_){
        //开启子线程
        new Thread(){
            public void run() {
                try {

                    URL url = new URL(url_);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(6 * 1000);  // 注意要设置超时，设置时间不要超过10秒，避免被android系统回收
                    if (conn.getResponseCode() != 200) throw new RuntimeException("请求url失败");
                    InputStream inSream = conn.getInputStream();
                    //把图片保存到项目的根目录
                    File file=new File(Environment.getExternalStorageDirectory()+"/Android/"+ BitmapImageUtil.getTempFileName()+".jpg");
                    readAsFile(inSream,file );
                    //-----------发广播更新相册-----------------------------------
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    activity.sendBroadcast(intent);
                    //----------------------------------------------------------
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    public static void readAsFile(InputStream inSream, File file) throws Exception {
        FileOutputStream outStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len = -1;
        while( (len = inSream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inSream.close();
    }





    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }
    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }




    /**
     * @param displayWidth 需要显示的宽度
     * @param displayHeight 需要显示的高度
     * @return Bitmap
     */
    public static Bitmap decodeBitmap(Bitmap bmp, int displayWidth, int displayHeight) {
        return Bitmap.createScaledBitmap(bmp, displayWidth, displayHeight, true);
    }



    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10*10000000; //超时时间
    private static final String CHARSET = "utf-8"; //设置编码
    public static final String SUCCESS="1"; public static final String FAILURE="0";
    /** * android上传文件到服务器
     * @param file 需要上传的文件
     * @param RequestURL 请求的rul
     * @return 返回响应的内容
     */
    public static String uploadFile(File file, String RequestURL) {
        String BOUNDARY = UUID.randomUUID().toString(); //边界标识 随机生成
         String PREFIX = "--" , LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; //内容类型
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); conn.setReadTimeout(TIME_OUT); conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            conn.setRequestMethod("POST"); //请求方式
            conn.setRequestProperty("Charset", CHARSET);
            //设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            if(file!=null) {
                /** * 当文件不为空，把文件包装并且上传 */
                OutputStream outputSteam=conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY); sb.append(LINE_END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+file.getName()+"\""+LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len=is.read(bytes))!=-1)
                {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功
                 * 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                Log.e(TAG, "response code:"+res);
                if(res==200)
                {
                    return SUCCESS;
                }
            }
        } catch (MalformedURLException e)
        { e.printStackTrace(); }
        catch (IOException e)
        { e.printStackTrace(); }
        return FAILURE;
    }

    /**
     * 将16进制 转换成10进制
     *
     * @param str
     * @return
     */
    public static String print10(String str) {

        StringBuffer buff = new StringBuffer();
        String array[] = str.split(" ");
        for (int i = 0; i < array.length; i++) {
            int num = Integer.parseInt(array[i], 16);
            buff.append(String.valueOf((char) num));
        }
        return buff.toString();
    }

    /**
     * byte转16进制
     *
     * @param b
     * @return
     */
    public static String byte2HexStr(byte[] b) {

        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }



    //跳到大图查看页
    public static void toShowBigImages(Context context, ArrayList<String> uils, int nowIndex){
        context.startActivity(new Intent(context, ImagePagerActivity.class)
                .putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,uils)
                .putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX,nowIndex));
    }


}
