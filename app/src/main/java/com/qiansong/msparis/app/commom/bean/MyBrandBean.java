package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */

public class MyBrandBean {

    /**
     * status : ok
     * data : {"rows":[{"id":1,"name":"BCBG MAXAZRIA","logo":"http://api.test.msparis.comhttp://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/bcbg_maxazria.jpg?imageMogr2/strip"},{"id":81,"name":"ALICE'S PIG","logo":"http://api.test.msparis.com"},{"id":221,"name":"AIEXIA ADMOR","logo":"http://api.test.msparis.com"},{"id":87,"name":"AQ/AQ","logo":"http://api.test.msparis.com"},{"id":76,"name":"ARROGANT CAT","logo":"http://api.test.msparis.com"},{"id":39,"name":"ALEXANDER WANG","logo":"http://api.test.msparis.comhttp://file.msparis.com/o_1ap7h4hbke636oqa09mgl1oq3g.jpg"},{"id":37,"name":"ASOS","logo":"http://api.test.msparis.comhttp://file.msparis.com/o_1ap7guufonkdgl51crv1dft796l.jpg"},{"id":35,"name":"AQUA","logo":"http://api.test.msparis.com"},{"id":34,"name":"AWAYLEE","logo":"http://api.test.msparis.comhttp://file.msparis.com/o_1ap7h6r8mgbt1n6ssjug4dtd1g.jpg"},{"id":218,"name":"CLASSIQUES ENTIER","logo":"http://api.test.msparis.com"}],"total":17}
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
         * rows : [{"id":1,"name":"BCBG MAXAZRIA","logo":"http://api.test.msparis.comhttp://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/bcbg_maxazria.jpg?imageMogr2/strip"},{"id":81,"name":"ALICE'S PIG","logo":"http://api.test.msparis.com"},{"id":221,"name":"AIEXIA ADMOR","logo":"http://api.test.msparis.com"},{"id":87,"name":"AQ/AQ","logo":"http://api.test.msparis.com"},{"id":76,"name":"ARROGANT CAT","logo":"http://api.test.msparis.com"},{"id":39,"name":"ALEXANDER WANG","logo":"http://api.test.msparis.comhttp://file.msparis.com/o_1ap7h4hbke636oqa09mgl1oq3g.jpg"},{"id":37,"name":"ASOS","logo":"http://api.test.msparis.comhttp://file.msparis.com/o_1ap7guufonkdgl51crv1dft796l.jpg"},{"id":35,"name":"AQUA","logo":"http://api.test.msparis.com"},{"id":34,"name":"AWAYLEE","logo":"http://api.test.msparis.comhttp://file.msparis.com/o_1ap7h6r8mgbt1n6ssjug4dtd1g.jpg"},{"id":218,"name":"CLASSIQUES ENTIER","logo":"http://api.test.msparis.com"}]
         * total : 17
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
             * name : BCBG MAXAZRIA
             * logo : http://api.test.msparis.comhttp://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/bcbg_maxazria.jpg?imageMogr2/strip
             */

            private int id;
            private String name;
            private String logo;
            public boolean is_dingyue=true;
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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }
    }
}
