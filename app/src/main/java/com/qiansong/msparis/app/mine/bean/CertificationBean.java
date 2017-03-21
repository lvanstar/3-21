package com.qiansong.msparis.app.mine.bean;

/**
 * Created by Administrator on 2017/3/4.
 * 实名认证信息
 */

public class CertificationBean {


    /**
     * status : ok
     * data : {"real_name":"","id_number":""}
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
         * real_name :
         * id_number :
         */

        private String real_name;
        private String id_number;

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }
    }
}
