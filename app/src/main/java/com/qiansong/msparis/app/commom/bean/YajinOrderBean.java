package com.qiansong.msparis.app.commom.bean;

/**
 * Created by Administrator on 2017/3/14.
 * 押金认证创建订单
 */

public class YajinOrderBean {

    /**
     * status : ok
     * data : {"id":3,"order_no":"AM14888895255739","title":"押金认证","price":300}
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
         * id : 3
         * order_no : AM14888895255739
         * title : 押金认证
         * price : 300
         */

        private int id;
        private String order_no;
        private String title;
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
