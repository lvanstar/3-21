package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 * 品牌馆
 */

public class BrandHomeBean {

    /**
     * status : ok
     * data : {"banner":[{"image_daily":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x","image_dress":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x"}],"brand_commend":[{"id":"2","logo":"http://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/alice_olivia.jpg?imageMogr2/strip","name":"ALICE+OLIVIA"}],"favorite_ids":"1,2,3,4"}
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
         * banner : [{"image_daily":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x","image_dress":"http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x"}]
         * brand_commend : [{"id":"2","logo":"http://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/alice_olivia.jpg?imageMogr2/strip","name":"ALICE+OLIVIA"}]
         * favorite_ids : 1,2,3,4
         */

        private String favorite_ids;
        private BannerBean banner;
        private List<BrandCommendBean> brand_commend;

        public String getFavorite_ids() {
            return favorite_ids;
        }

        public void setFavorite_ids(String favorite_ids) {
            this.favorite_ids = favorite_ids;
        }

        public BannerBean getBanner() {
            return banner;
        }

        public void setBanner(BannerBean banner) {
            this.banner = banner;
        }

        public List<BrandCommendBean> getBrand_commend() {
            return brand_commend;
        }

        public void setBrand_commend(List<BrandCommendBean> brand_commend) {
            this.brand_commend = brand_commend;
        }

        public static class BannerBean {
            /**
             * image_daily : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!320x
             * image_dress : http://file.msparis.com/o_1b6j0d2qu1rtb8i8ad3dj15tnl.jpg!640x
             */

            private String image_daily;
            private String image_dress;

            public String getImage_daily() {
                return image_daily;
            }

            public void setImage_daily(String image_daily) {
                this.image_daily = image_daily;
            }

            public String getImage_dress() {
                return image_dress;
            }

            public void setImage_dress(String image_dress) {
                this.image_dress = image_dress;
            }
        }

        public static class BrandCommendBean {
            /**
             * id : 2
             * logo : http://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/alice_olivia.jpg?imageMogr2/strip
             * name : ALICE+OLIVIA
             */

            private String id;
            private String logo;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
