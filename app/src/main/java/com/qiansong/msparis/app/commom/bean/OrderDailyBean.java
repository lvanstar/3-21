package com.qiansong.msparis.app.commom.bean;

/**
 * Created by Administrator on 2017/3/9.
 * 创建租赁订单
 */

public class OrderDailyBean {


    /**
     * status : ok
     * data : {"order_id":"2","order_no":"P2017020514223012","pay_amount":"30900"}
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
         * order_id : 2
         * order_no : P2017020514223012
         * pay_amount : 30900
         */

        private String order_id;
        private String order_no;
        private String pay_amount;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }
    }
}
