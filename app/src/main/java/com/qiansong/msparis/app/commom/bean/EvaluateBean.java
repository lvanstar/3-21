package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by lizhaozhao on 2017/3/11.
 */

public class EvaluateBean {

    public String access_token;
    public int order_id;
    public int express_vote;
    public String express_remark;
    public int order_split_id;
    public List<DataEntity>order_item;



    public static class DataEntity{

        public int fit_vote;
        public int order_split_item_id;
        public int product_id;
        public String product_remark;
        public int product_vote;
    }
}
