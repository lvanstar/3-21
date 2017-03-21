package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

/**
 * Created by kevin on 2017/3/13.
 *
 *  购买卡确认订单页面
 */

public class PriceCardBean extends BaseBean{

    /**
     * data : {"card":31800,"original_total":61800,"deposit":30000,"coupon":1000,"member":1000,"total":59800}
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
         * card : 31800
         * original_total : 61800
         * deposit : 30000
         * coupon : 1000
         * member : 1000
         * total : 59800
         */

        private int card;
        private int original_total;
        private int deposit;
        private int coupon;
        private int member;
        private int total;

        public int getCard() {
            return card;
        }

        public void setCard(int card) {
            this.card = card;
        }

        public int getOriginal_total() {
            return original_total;
        }

        public void setOriginal_total(int original_total) {
            this.original_total = original_total;
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

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
