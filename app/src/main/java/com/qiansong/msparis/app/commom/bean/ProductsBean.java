package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by lizhaozhao on 2017/2/27.
 *
 * 商品详情
 */

public class ProductsBean extends BaseBean{


    /**
     * data : {"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand_id":"tahari","brand":"tahari","is_favorite":0,"type_id":2,"mode_id":1,"market_price":120000,"rental_price":199900,"limit_tag":"秋季限定","limit_one_per_package":1,"limit_only_new_vip_user":1,"image":["http://static.msparis.com/image/product/F/W/FW022W-1.jpg"],"specifications":[{"id":1,"name":"尺寸","options":[{"id":1,"name":"L"},{"id":2,"name":"XL"}]}],"tags":[{"id":1,"name":"标签1"},{"id":2,"name":"标签2"}],"fabric":"全棉","thickness":"薄","measurement":"100x120","stocks":[{"id":1,"key":"1|2|3","is_enable":1},{"id":1,"key":"1|2|4","is_enable":1},{"id":1,"key":"1|2|5","is_enable":0}]}
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
         * id : 1
         * spu : PN123456
         * name : 浅金项链饰边粗花呢套装
         * brand_id : tahari
         * brand : tahari
         * is_favorite : 0
         * type_id : 2
         * mode_id : 1
         * market_price : 120000
         * rental_price : 199900
         * limit_tag : 秋季限定
         * limit_one_per_package : 1
         * limit_only_new_vip_user : 1
         * image : ["http://static.msparis.com/image/product/F/W/FW022W-1.jpg"]
         * specifications : [{"id":1,"name":"尺寸","options":[{"id":1,"name":"L"},{"id":2,"name":"XL"}]}]
         * tags : [{"id":1,"name":"标签1"},{"id":2,"name":"标签2"}]
         * fabric : 全棉
         * thickness : 薄
         * measurement : 100x120
         * stocks : [{"id":1,"key":"1|2|3","is_enable":1},{"id":1,"key":"1|2|4","is_enable":1},{"id":1,"key":"1|2|5","is_enable":0}]
         */

        private String id;
        private String spu;
        private String name;
        private String brand_id;
        private String brand;
        private int is_favorite;
        private int type_id;
        private int mode_id;
        private int market_price;
        private int rental_price;
        private String limit_tag;
        private int limit_one_per_package;
        private int limit_only_new_vip_user;
        private String fabric;
        private String thickness;
        private String measurement;
        private List<String> image;
        private List<SpecificationsEntity> specifications;
        private List<TagsEntity> tags;
        private List<StocksEntity> stocks;

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

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(int is_favorite) {
            this.is_favorite = is_favorite;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getMode_id() {
            return mode_id;
        }

        public void setMode_id(int mode_id) {
            this.mode_id = mode_id;
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

        public String getFabric() {
            return fabric;
        }

        public void setFabric(String fabric) {
            this.fabric = fabric;
        }

        public String getThickness() {
            return thickness;
        }

        public void setThickness(String thickness) {
            this.thickness = thickness;
        }

        public String getMeasurement() {
            return measurement;
        }

        public void setMeasurement(String measurement) {
            this.measurement = measurement;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public List<SpecificationsEntity> getSpecifications() {
            return specifications;
        }

        public void setSpecifications(List<SpecificationsEntity> specifications) {
            this.specifications = specifications;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public List<StocksEntity> getStocks() {
            return stocks;
        }

        public void setStocks(List<StocksEntity> stocks) {
            this.stocks = stocks;
        }

        public static class SpecificationsEntity {
            /**
             * id : 1
             * name : 尺寸
             * options : [{"id":1,"name":"L"},{"id":2,"name":"XL"}]
             */

            private int id;
            private String name;
            private List<OptionsEntity> options;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<OptionsEntity> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsEntity> options) {
                this.options = options;
            }

            public static class OptionsEntity {
                /**
                 * id : 1
                 * name : L
                 */

                private int id;
                private String name;
                public boolean select;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class TagsEntity {
            /**
             * id : 1
             * name : 标签1
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class StocksEntity {
            /**
             * id : 1
             * key : 1|2|3
             * is_enable : 1
             */

            private int id;
            private String key;
            private int is_enable;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getIs_enable() {
                return is_enable;
            }

            public void setIs_enable(int is_enable) {
                this.is_enable = is_enable;
            }
        }
    }
}
