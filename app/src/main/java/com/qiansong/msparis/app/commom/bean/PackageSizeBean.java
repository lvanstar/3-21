package com.qiansong.msparis.app.commom.bean;

/**
 * Created by lizhaozhao on 2017/3/8.
 *
 *  购物车剩余商品数量
 */

public class PackageSizeBean extends BaseBean {

    /**
     * data : {"package_amount":0,"package_item_amount":0,"cart_item_amount":0}
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
         * package_amount : 0
         * package_item_amount : 0
         * cart_item_amount : 0
         */

        private int package_amount;
        private int package_item_amount;
        private int cart_item_amount;

        public int getPackage_amount() {
            return package_amount;
        }

        public void setPackage_amount(int package_amount) {
            this.package_amount = package_amount;
        }

        public int getPackage_item_amount() {
            return package_item_amount;
        }

        public void setPackage_item_amount(int package_item_amount) {
            this.package_item_amount = package_item_amount;
        }

        public int getCart_item_amount() {
            return cart_item_amount;
        }

        public void setCart_item_amount(int cart_item_amount) {
            this.cart_item_amount = cart_item_amount;
        }
    }
}
