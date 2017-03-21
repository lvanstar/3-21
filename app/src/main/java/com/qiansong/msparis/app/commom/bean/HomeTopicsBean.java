package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class HomeTopicsBean {

    /**
     * status : ok
     * data : {"total":1,"rows":[{"id":1,"title":"CMS_TEST_001","type":"url","params":"http://www.msparis.com","image_src":""},{"id":2,"title":"欧文女OK小处女了我","type":"url","params":"http://www.msparis.com","image_src":""}]}
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
         * total : 1
         * rows : [{"id":1,"title":"CMS_TEST_001","type":"url","params":"http://www.msparis.com","image_src":""},{"id":2,"title":"欧文女OK小处女了我","type":"url","params":"http://www.msparis.com","image_src":""}]
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
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
             * title : CMS_TEST_001
             * type : url
             * params : http://www.msparis.com
             * image_src :
             */

            private int id;
            private String title;
            private String type;
            private String params;
            private String image_src;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getParams() {
                return params;
            }

            public void setParams(String params) {
                this.params = params;
            }

            public String getImage_src() {
                return image_src;
            }

            public void setImage_src(String image_src) {
                this.image_src = image_src;
            }
        }
    }
}
