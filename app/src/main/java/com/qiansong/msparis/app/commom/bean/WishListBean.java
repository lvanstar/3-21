package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by ye on 2017/3/11.
 * 心愿单
 */

public class WishListBean {

    /**
     * status : ok
     * data : {"rows":[{"id":1,"spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","limit_tag":"","is_favorite":"1","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg"}],"total":1}
     */

    private String status;
    public DataBean data;

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
         * rows : [{"id":1,"spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","limit_tag":"","is_favorite":"1","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg"}]
         * total : 1
         */

        private int total;
        public List<RowsBean> rows;

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
             * spu : PN123456
             * name : 浅金项链饰边粗花呢套装
             * brand : tahari
             * limit_tag :
             * is_favorite : 1
             * show_specifications : S/M/L/XL
             * cover_image : http://static.msparis.com/image/product/F/W/FW022W-1.jpg
             */

            private int id;
            private String spu;
            private String name;
            private String brand;
            private String limit_tag;
            private String is_favorite;
            private String show_specifications;
            private String cover_image;
            public boolean is_guanzhu=true;//是否关注
            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public String getLimit_tag() {
                return limit_tag;
            }

            public void setLimit_tag(String limit_tag) {
                this.limit_tag = limit_tag;
            }

            public String getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(String is_favorite) {
                this.is_favorite = is_favorite;
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
        }
    }
}
