package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by yechen on 2017/3/10.
 * 还依记录 列表
 */

public class ClothingRecordBean {

    /**
     * status : ok
     * data : {"total":100,"rows":[{"express_no":"RM201709022210223489","express_company":"顺丰","express_status":"待收件","express_items":[{"image_url":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240"},{"image_url":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240"}]}]}
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
         * total : 100
         * rows : [{"express_no":"RM201709022210223489","express_company":"顺丰","express_status":"待收件","express_items":[{"image_url":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240"},{"image_url":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240"}]}]
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
             * express_no : RM201709022210223489
             * express_company : 顺丰
             * express_status : 待收件
             * express_items : [{"image_url":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240"},{"image_url":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240"}]
             */

            private String express_no;
            private String express_company;
            private String express_status;
            private List<ExpressItemsBean> express_items;

            public String getExpress_no() {
                return express_no;
            }

            public void setExpress_no(String express_no) {
                this.express_no = express_no;
            }

            public String getExpress_company() {
                return express_company;
            }

            public void setExpress_company(String express_company) {
                this.express_company = express_company;
            }

            public String getExpress_status() {
                return express_status;
            }

            public void setExpress_status(String express_status) {
                this.express_status = express_status;
            }

            public List<ExpressItemsBean> getExpress_items() {
                return express_items;
            }

            public void setExpress_items(List<ExpressItemsBean> express_items) {
                this.express_items = express_items;
            }

            public static class ExpressItemsBean {
                /**
                 * image_url : http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240
                 */

                private String image_url;

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }
            }
        }
    }
}
