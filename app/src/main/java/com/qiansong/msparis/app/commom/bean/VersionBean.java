package com.qiansong.msparis.app.commom.bean;

/**
 * Created by lizhaozhao on 2017/2/28.
 *
 *  版本检测接口
 */

public class VersionBean extends BaseBean{
    /**
     * data : {"platform":"android","version":"1.1","build":"123456","note":"Android全新改版","update_url":"http://www.msparis.com","forced_update":0}
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * platform : android
         * version : 1.1
         * build : 123456
         * note : Android全新改版
         * update_url : http://www.msparis.com
         * forced_update : 0
         */

        private String platform;
        private String version;
        private String build;
        private String note;
        private String update_url;
        private int forced_update;

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getBuild() {
            return build;
        }

        public void setBuild(String build) {
            this.build = build;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getUpdate_url() {
            return update_url;
        }

        public void setUpdate_url(String update_url) {
            this.update_url = update_url;
        }

        public int getForced_update() {
            return forced_update;
        }

        public void setForced_update(int forced_update) {
            this.forced_update = forced_update;
        }
    }
}
