package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

/**
 * Created by kevin on 2017/2/24.
 *  我的钱包
 */

public class MyWallet  extends BaseBean {


    /**
     * data : {"coupon_number":"3","deposit":"500"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * coupon_number : 3
         * deposit : 500
         */

        private String coupon_number;
        private String deposit;

        public String getCoupon_number() {
            return coupon_number;
        }

        public void setCoupon_number(String coupon_number) {
            this.coupon_number = coupon_number;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }
    }
}
