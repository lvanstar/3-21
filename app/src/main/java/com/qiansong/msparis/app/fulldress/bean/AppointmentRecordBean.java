package com.qiansong.msparis.app.fulldress.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/14.
 *
 * 预约记录列表
 */

public class AppointmentRecordBean  extends BaseBean {


    /**
     * data : {"total":"5","rows":[{"id":"1","name":"李女士","mobile":"13312345678","city_name":"上海","store_name":"普陀店","remark":"测试","type":"1","date":"2017 02-22","start_time":"10:15","num":"3"}]}
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
         * total : 5
         * rows : [{"id":"1","name":"李女士","mobile":"13312345678","city_name":"上海","store_name":"普陀店","remark":"测试","type":"1","date":"2017 02-22","start_time":"10:15","num":"3"}]
         */

        private String total;
        private List<RowsBean> rows;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 1
             * name : 李女士
             * mobile : 13312345678
             * city_name : 上海
             * store_name : 普陀店
             * remark : 测试
             * type : 1
             * date : 2017 02-22
             * start_time : 10:15
             * num : 3
             */

            private String id;
            private String name;
            private String mobile;
            private String city_name;
            private String store_name;
            private String remark;
            private String type;
            private String date;
            private String start_time;
            private String num;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
