package com.qiansong.msparis.app.commom.util;

/**
 * Created by lizhaozhao on 2017/2/24.
 */

 /**
   * @Title: FileHelper.java
   * @Package com.tes.textsd
   * @Description: TODO(用一句话描述该文件做什么)
   * @author Alex.Z
   * @date 2013-2-26 下午5:45:40
   * @version V1.0
   */

         import java.io.BufferedReader;
         import java.io.BufferedWriter;
         import java.io.DataOutputStream;
         import java.io.File;
         import java.io.FileOutputStream;
         import java.io.FileWriter;

         import java.io.FileInputStream;

        import java.io.FileNotFoundException;

         import java.io.IOException;
         import java.io.InputStream;
         import java.io.InputStreamReader;
         import java.io.OutputStreamWriter;
         import java.nio.charset.Charset;


         import android.content.Context;

        import android.os.Environment;

         import org.apache.http.util.EncodingUtils;

         import static javax.xml.transform.OutputKeys.ENCODING;


public class FileHelper {


             private Context context;

             /**
              * SD卡是否存在
              **/

             private boolean hasSD = false;

             /**
              * SD卡的路径
              **/

             private String SDPATH;

             /**
              * 当前程序包的路径
              **/

             private String FILESPATH;

             public static final String FILE_BRAND = "brand.txt";
             public static final String FILE_PRODUCT = "product.txt";
             public static final String FILE_SEND = "send.txt";
             public static final String FILE_BOOKING = "booking.txt";


             public FileHelper(Context context) {

                 this.context = context;

                 hasSD = Environment.getExternalStorageState().equals(

                         android.os.Environment.MEDIA_MOUNTED);

                 SDPATH = Environment.getExternalStorageDirectory().getPath();

                 FILESPATH = this.context.getFilesDir().getPath();

             }


             /**
              * 在SD卡上创建文件
              *
              * @throws IOException
              */

             public File createSDFile(String fileName) throws IOException {

                 File file = new File(SDPATH + "//" + fileName);

                 if (!file.exists()) {

                     file.createNewFile();

                 }

                 return file;

             }


             /**
              * 删除SD卡上的文件
              *
              * @param fileName
              */

             public boolean deleteSDFile(String fileName) {

                 File file = new File(SDPATH + "//" + fileName);

                 if (file == null || !file.exists() || file.isDirectory())

                     return false;

                 return file.delete();

             }

             /**
              * 写入内容到SD卡中的txt文本中
              * str为内容
              */

             public void writeSDFile(String str, String fileName) {

                 try {
                     createSDFile(fileName);
                     FileWriter fw = new FileWriter(SDPATH + "//" + fileName);
                     File f = new File(SDPATH + "//" + fileName);
                     fw.write(str);

                     FileOutputStream os = new FileOutputStream(f);
                     DataOutputStream out = new DataOutputStream(os);
                     out.writeUTF("gbk");
                     out.writeShort(2);
                     out.writeUTF("");
                     System.out.println(out);
                     fw.flush();
                     fw.close();
                     System.out.println(fw);
                 } catch (Exception e) {
                 }
             }

    public  boolean writeToTxt(String content,String fileName) {
        try {
            OutputStreamWriter write = null;
            BufferedWriter out = null;
            if (fileName != null) {
                try {   // new FileOutputStream(fileName, true) 第二个参数表示追加写入
                    createSDFile(fileName);
                    File f = new File(SDPATH + "//" + fileName);
                    write = new OutputStreamWriter(new FileOutputStream(
                            f), Charset.forName("utf-8"));//一定要使用gbk格式
                    out = new BufferedWriter(write, 1024);
                } catch (Exception e) {
                }
            }
            out.write(content);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

             /**
              * 读取SD卡中文本文件
              *
              * @param fileName
              * @return
              */

             public String readSDFile(String fileName) {

                 StringBuffer sb = new StringBuffer();

                 File file = new File(SDPATH + "//" + fileName);

                 try {

                     String str=null;
                     FileInputStream fis = new FileInputStream(file);

                     InputStreamReader input = new InputStreamReader(fis, "UTF-8");
                     BufferedReader reader = new BufferedReader(input);
                     while ((str = reader.readLine()) != null) {
                         sb.append(str);
                     }
//                     int c;
//
//                     while ((c = fis.read()) != -1) {
//
//                         sb.append((char) c);
//
//                     }
//
//                     fis.close();

                 } catch (FileNotFoundException e) {

                     e.printStackTrace();

                 } catch (IOException e) {

                     e.printStackTrace();

                 }

                 return sb.toString();

             }






             public String getFILESPATH() {

                 return FILESPATH;

             }


             public String getSDPATH() {

                 return SDPATH;

             }


             public boolean hasSD() {

                 return hasSD;

             }


    //从assets 文件夹中获取文件并读取数据
    public static String getFromAssets(Context context,String fileName){
        String result = "";
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            //获取文件的字节数
            int lenght = in.available();
            //创建byte数组
            byte[]  buffer = new byte[lenght];
            //将文件中的数据读到byte数组中
            in.read(buffer);
            result = EncodingUtils.getString(buffer, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
         }