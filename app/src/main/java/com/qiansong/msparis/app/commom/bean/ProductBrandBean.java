package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 * 同品牌商品列表信息
 */

public class ProductBrandBean {

    /**
     * status : ok
     * data : {"total":"1","rows":[{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg","is_favorite":0}]}
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
         * rows : [{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg","is_favorite":0}]
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
             * spu : PN123456
             * name : 浅金项链饰边粗花呢套装
             * brand : tahari
             * show_specifications : S/M/L/XL
             * cover_image : http://static.msparis.com/image/product/F/W/FW022W-1.jpg
             * is_favorite : 0
             */

            private String id;
            private String spu;
            private String name;
            private String brand;
            private String show_specifications;
            private String cover_image;
            private int is_favorite;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSpu() {
                return spu;
            }

            public void setSpu(String spu) {
                this.spu = spu;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getShow_specifications() {
                return show_specifications;
            }

            public void setShow_specifications(String show_specifications) {
                this.show_specifications = show_specifications;
            }

            public String getCover_image() {
                return cover_image;
            }

            public void setCover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public int getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }
        }
    }
}
