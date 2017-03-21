package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 * 品牌详情
 */

public class BrandDetailsBean {

    /**
     * status : ok
     * data : {"id":"11","name":"KATE SPADE","banner":[["http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x","http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x"]]}
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
         * id : 11
         * name : KATE SPADE
         * banner : [["http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x","http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x"]]
         */

        private String id;
        private String name;
        private List<List<String>> banner;

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

        public List<List<String>> getBanner() {
            return banner;
        }

        public void setBanner(List<List<String>> banner) {
            this.banner = banner;
        }
    }
}
