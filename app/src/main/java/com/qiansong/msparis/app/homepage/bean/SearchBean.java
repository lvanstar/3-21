package com.qiansong.msparis.app.homepage.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kevin on 2017/2/11.
 *
 *  搜索后台联想结果页
 */

public class SearchBean extends BaseBean implements Serializable {


    /**
     * data : {"total":3,"row":["衣服","大衣","连衣裙"]}
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
         * total : 3
         * row : ["衣服","大衣","连衣裙"]
         */

        private int total;
        private List<String> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<String> getRow() {
            return rows;
        }

        public void setRow(List<String> row) {
            this.rows = row;
        }
    }
}
