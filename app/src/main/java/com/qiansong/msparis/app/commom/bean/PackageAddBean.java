package com.qiansong.msparis.app.commom.bean;

/**
 * Created by lizhaozhao on 2017/3/4.
 *
 *  加入购物袋返回 数据
 */

public class PackageAddBean extends BaseBean{


    /**
     * data : {"item_count":2}
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * item_count : 2
         */

        private int item_count;

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }
    }
}
