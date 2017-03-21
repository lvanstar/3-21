package com.qiansong.msparis.app.homepage.view.sortlistview;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class FriendListBean {
    public String total;
    public String status;
    public List<DataBean> data;

    public FriendListBean(String total, String status, List<DataBean> data) {
        this.total = total;
        this.status = status;
        this.data = data;
    }

    public static class DataBean {
        public String nick_name;
        public String sortLetters;

        public DataBean(String nick_name, String sortLetters) {
            this.nick_name = nick_name;
            this.sortLetters = sortLetters;
        }
    }
}
