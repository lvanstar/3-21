package com.qiansong.msparis.app.fulldress.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

/**
 * Created by kevin on 2017/3/3.
 *  预约详情
 */

public class AppointmentBean extends BaseBean {

    /**
     * data : {"id":3326,"name":"蔡振喜 先生","mobile":"15000059840","city_name":"上海","num":2,"date":"2017-03-01","start_time":"15:00","remark":"测试数据","store_name":"九江路点","type":1}
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
         * id : 3326
         * name : 蔡振喜 先生
         * mobile : 15000059840
         * city_name : 上海
         * num : 2
         * date : 2017-03-01
         * start_time : 15:00
         * remark : 测试数据
         * store_name : 九江路点
         * type : 1
         */

        private int id;
        private String name;
        private String mobile;
        private String city_name;
        private int num;
        private String date;
        private String start_time;
        private String remark;
        private String store_name;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
