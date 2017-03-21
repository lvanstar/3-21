package com.qiansong.msparis.app.commom.bean;

/**
 * Created by Administrator on 2017/3/9.
 * 确认订单价格
 */

public class PriceBean {

    /**
     * status : ok
     * data : {"grid":10000,"more_time":3000,"more_time_count":1,"deposit":30000,"coupon":1000,"member":1000,"express":0,"total":31000}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * grid : 10000
         * more_time : 3000
         * more_time_count : 1
         * deposit : 30000
         * coupon : 1000
         * member : 1000
         * express : 0
         * total : 31000
         */

        private int grid;
        private int more_time;
        private int more_time_count;
        private int deposit;
        private int coupon;
        private int member;
        private int express;
        private int total;

        public int getGrid() {
            return grid;
        }

        public void setGrid(int grid) {
            this.grid = grid;
        }

        public int getMore_time() {
            return more_time;
        }

        public void setMore_time(int more_time) {
            this.more_time = more_time;
        }

        public int getMore_time_count() {
            return more_time_count;
        }

        public void setMore_time_count(int more_time_count) {
            this.more_time_count = more_time_count;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public int getMember() {
            return member;
        }

        public void setMember(int member) {
            this.member = member;
        }

        public int getExpress() {
            return express;
        }

        public void setExpress(int express) {
            this.express = express;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
