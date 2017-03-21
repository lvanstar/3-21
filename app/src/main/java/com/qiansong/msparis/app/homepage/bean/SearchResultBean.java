package com.qiansong.msparis.app.homepage.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kevin on 2017/2/13.
 *
 *  搜索展示商品模型
 */

public class SearchResultBean extends BaseBean implements Serializable {


    /**
     * data : {"total":"1","rows":[{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg","type_id":2,"market_price":199900,"rental_price":199900,"is_favorite":0,"limit_tag":"秋季限定","limit_one_per_package":1,"limit_only_new_vip_user":1}]}
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
         * total : 1
         * rows : [{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg","type_id":2,"market_price":199900,"rental_price":199900,"is_favorite":0,"limit_tag":"秋季限定","limit_one_per_package":1,"limit_only_new_vip_user":1}]
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
             * type_id : 2
             * market_price : 199900
             * rental_price : 199900
             * is_favorite : 0
             * limit_tag : 秋季限定
             * limit_one_per_package : 1
             * limit_only_new_vip_user : 1
             */

            private String id;
            private String spu;
            private String name;
            private String brand;
            private String show_specifications;
            private String cover_image;
            private int type_id;
            private int market_price;
            private int rental_price;
            private int is_favorite;
            private String limit_tag;
            private int limit_one_per_package;
            private int limit_only_new_vip_user;

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

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public int getMarket_price() {
                return market_price;
            }

            public void setMarket_price(int market_price) {
                this.market_price = market_price;
            }

            public int getRental_price() {
                return rental_price;
            }

            public void setRental_price(int rental_price) {
                this.rental_price = rental_price;
            }

            public int getIs_favorite() {
                return is_favorite;
            }

            public void setIs_favorite(int is_favorite) {
                this.is_favorite = is_favorite;
            }

            public String getLimit_tag() {
                return limit_tag;
            }

            public void setLimit_tag(String limit_tag) {
                this.limit_tag = limit_tag;
            }

            public int getLimit_one_per_package() {
                return limit_one_per_package;
            }

            public void setLimit_one_per_package(int limit_one_per_package) {
                this.limit_one_per_package = limit_one_per_package;
            }

            public int getLimit_only_new_vip_user() {
                return limit_only_new_vip_user;
            }

            public void setLimit_only_new_vip_user(int limit_only_new_vip_user) {
                this.limit_only_new_vip_user = limit_only_new_vip_user;
            }
        }
    }
}
