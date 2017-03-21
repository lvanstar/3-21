package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 * 首页
 */

public class HomePageBean {


    /**
     * status : ok
     * data : {"banner":[{"id":1,"image_src":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x","image_src2x":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x","image_src3x":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!960x","link_type":"url","link_params":"http://m.msparis.com/pass?hmsr=m&hmpl=2016_Pass&hmcu=&hmkw=&hmci="}],"feature":[{"name":"往返包邮","type":"url","params":"http://www.msparis.com"},{"name":"喜欢才买","type":"url","params":"http://www.msparis.com"},{"name":"无限换穿","type":"url","params":"http://www.msparis.com"},{"name":"海外大牌","type":"url","params":"http://www.msparis.com"}],"new_arrivals":[{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg"}],"banner_rookie":[{"id":1,"image_src":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x","image_src2x":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x","image_src3x":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!960x","link_type":"url","link_params":"http://m.msparis.com/pass?hmsr=m&hmpl=2016_Pass&hmcu=&hmkw=&hmci="}],"product_commend":[{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg"}],"topics":[{"id":1,"title":"测试标题","image_src":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!960x","link_type":"url","link_params":"http://m.msparis.com/pass?hmsr=m&hmpl=2016_Pass&hmcu=&hmkw=&hmci=","products":[{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg","is_favorite":0},{"id":"2","spu":"PN123456","name":"立体毛呢短外套","brand":"MICHAEL KORS","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/M/K/MK148B-1.jpg","is_favorite":1}]}]}
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
        private List<BannerBean> banner;
        private List<FeatureBean> feature;
        private List<NewArrivalsBean> new_arrivals;
        private List<BannerRookieBean> banner_rookie;
        private List<ProductCommendBean> product_commend;
        private List<TopicsBean> topics;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<FeatureBean> getFeature() {
            return feature;
        }

        public void setFeature(List<FeatureBean> feature) {
            this.feature = feature;
        }

        public List<NewArrivalsBean> getNew_arrivals() {
            return new_arrivals;
        }

        public void setNew_arrivals(List<NewArrivalsBean> new_arrivals) {
            this.new_arrivals = new_arrivals;
        }

        public List<BannerRookieBean> getBanner_rookie() {
            return banner_rookie;
        }

        public void setBanner_rookie(List<BannerRookieBean> banner_rookie) {
            this.banner_rookie = banner_rookie;
        }

        public List<ProductCommendBean> getProduct_commend() {
            return product_commend;
        }

        public void setProduct_commend(List<ProductCommendBean> product_commend) {
            this.product_commend = product_commend;
        }

        public List<TopicsBean> getTopics() {
            return topics;
        }

        public void setTopics(List<TopicsBean> topics) {
            this.topics = topics;
        }

        public static class BannerBean {
            /**
             * id : 1
             * image_src : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x
             * image_src2x : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x
             * image_src3x : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!960x
             * link_type : url
             * link_params : http://m.msparis.com/pass?hmsr=m&hmpl=2016_Pass&hmcu=&hmkw=&hmci=
             */

            private int id;
            private String image_src;
            private String image_src2x;
            private String image_src3x;
            private String link_type;
            private String link_params;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_src() {
                return image_src;
            }

            public void setImage_src(String image_src) {
                this.image_src = image_src;
            }

            public String getImage_src2x() {
                return image_src2x;
            }

            public void setImage_src2x(String image_src2x) {
                this.image_src2x = image_src2x;
            }

            public String getImage_src3x() {
                return image_src3x;
            }

            public void setImage_src3x(String image_src3x) {
                this.image_src3x = image_src3x;
            }

            public String getLink_type() {
                return link_type;
            }

            public void setLink_type(String link_type) {
                this.link_type = link_type;
            }

            public String getLink_params() {
                return link_params;
            }

            public void setLink_params(String link_params) {
                this.link_params = link_params;
            }
        }

        public static class FeatureBean {
            /**
             * name : 往返包邮
             * type : url
             * params : http://www.msparis.com
             */

            private String name;
            private String type;
            private String params;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
        }

        public static class NewArrivalsBean {
            /**
             * id : 1
             * spu : PN123456
             * name : 浅金项链饰边粗花呢套装
             * brand : tahari
             * show_specifications : S/M/L/XL
             * cover_image : http://static.msparis.com/image/product/F/W/FW022W-1.jpg
             */

            private String id;
            private String spu;
            private String name;
            private String brand;
            private String show_specifications;
            private String cover_image;

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
        }

        public static class BannerRookieBean {
            /**
             * id : 1
             * image_src : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x
             * image_src2x : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x
             * image_src3x : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!960x
             * link_type : url
             * link_params : http://m.msparis.com/pass?hmsr=m&hmpl=2016_Pass&hmcu=&hmkw=&hmci=
             */

            private int id;
            private String image_src;
            private String image_src2x;
            private String image_src3x;
            private String link_type;
            private String link_params;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_src() {
                return image_src;
            }

            public void setImage_src(String image_src) {
                this.image_src = image_src;
            }

            public String getImage_src2x() {
                return image_src2x;
            }

            public void setImage_src2x(String image_src2x) {
                this.image_src2x = image_src2x;
            }

            public String getImage_src3x() {
                return image_src3x;
            }

            public void setImage_src3x(String image_src3x) {
                this.image_src3x = image_src3x;
            }

            public String getLink_type() {
                return link_type;
            }

            public void setLink_type(String link_type) {
                this.link_type = link_type;
            }

            public String getLink_params() {
                return link_params;
            }

            public void setLink_params(String link_params) {
                this.link_params = link_params;
            }
        }

        public static class ProductCommendBean {
            /**
             * id : 1
             * spu : PN123456
             * name : 浅金项链饰边粗花呢套装
             * brand : tahari
             * show_specifications : S/M/L/XL
             * cover_image : http://static.msparis.com/image/product/F/W/FW022W-1.jpg
             */

            private String id;
            private String spu;
            private String name;
            private String brand;
            private String show_specifications;
            private String cover_image;

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
        }

        public static class TopicsBean {
            /**
             * id : 1
             * title : 测试标题
             * image_src : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!960x
             * link_type : url
             * link_params : http://m.msparis.com/pass?hmsr=m&hmpl=2016_Pass&hmcu=&hmkw=&hmci=
             * products : [{"id":"1","spu":"PN123456","name":"浅金项链饰边粗花呢套装","brand":"tahari","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/F/W/FW022W-1.jpg","is_favorite":0},{"id":"2","spu":"PN123456","name":"立体毛呢短外套","brand":"MICHAEL KORS","show_specifications":"S/M/L/XL","cover_image":"http://static.msparis.com/image/product/M/K/MK148B-1.jpg","is_favorite":1}]
             */

            private int id;
            private String title;
            private String image_src;
            private String link_type;
            private String link_params;
            private List<ProductsBean> products;

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

            public String getImage_src() {
                return image_src;
            }

            public void setImage_src(String image_src) {
                this.image_src = image_src;
            }

            public String getLink_type() {
                return link_type;
            }

            public void setLink_type(String link_type) {
                this.link_type = link_type;
            }

            public String getLink_params() {
                return link_params;
            }

            public void setLink_params(String link_params) {
                this.link_params = link_params;
            }

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class ProductsBean {
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
}
