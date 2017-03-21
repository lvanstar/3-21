package com.qiansong.msparis.app.commom.util;

import android.content.Context;

import com.google.gson.Gson;
import com.qiansong.msparis.app.commom.bean.QiYuBean;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.ProductDetail;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2017/2/14.
 */

public class QiYuUtil {
    /**
     * 启动七鱼客服
     */
    public static void  StartQiYu(Context context, ProductDetail detail){
        String title = "派派客服";
        ConsultSource source = new ConsultSource(null,null,null);
        if(detail!=null ){
            source.productDetail = detail;
        }
        Unicorn.openServiceActivity(
                context, // 上下文
                title, // 聊天窗口的标题
                source // 咨询的发起来源，包括发起咨询的url，title，描述信息等
        );
        //设置用户信息
        YSFUserInfo userInfo = new YSFUserInfo();
        userInfo.userId=TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, "");
        userInfo.data=QiYuUtil.ReturnJson();
        Unicorn.setUserInfo(userInfo);
    }

    /**
     * 七鱼传递用户信息json
     * @return
     */
    public  static String ReturnJson(){
        String str="";
        List<QiYuBean> list = new ArrayList<QiYuBean>();
        QiYuBean qiYuBean = new QiYuBean();
        qiYuBean.key="real_name";
        qiYuBean.value="曹磊";
        QiYuBean qiYuBean1 = new QiYuBean();
        qiYuBean1.key="mobile_phone";
        qiYuBean1.value="15111296292";

        list.add(qiYuBean);
        list.add(qiYuBean1);

        Gson gson = new Gson();
        str = gson.toJson(list);
        System.out.println(str);
        return str;
    }
}
