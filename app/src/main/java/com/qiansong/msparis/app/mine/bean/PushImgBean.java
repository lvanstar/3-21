package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/2.
 */

public class PushImgBean  extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uri : /apidoc/uploads/20170227/7cfccbf5896776682b2201ceaaec476d.jpeg
         * url : http://api.test.msparis.com/apidoc/uploads/20170227/7cfccbf5896776682b2201ceaaec476d.jpeg
         */

        private String uri;
        private String url;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
