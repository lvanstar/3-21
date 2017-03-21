package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kevin on 2017/2/24.
 *
 *  收货地址
 */

public class AddressBean  extends BaseBean implements Serializable{


    /**
     * data : {"rows":[{"id":12267,"region_name":"上海上海市松江区","address_detail":"我希望有个如你一般的人。","contact_name":"丫头","contact_mobile":"15921815744","region_code":"123"},{"id":12268,"region_name":"四川省甘孜藏族自治州稻城县","address_detail":"我希望有个如你一般的人。如山间清爽的风，如古城温暖的光。从清晨到夜晚，由山野到书房。只要最后是你，就好。","contact_name":"阿尔卑斯","contact_mobile":"15914746767","region_code":"123"},{"id":12270,"region_name":"广西壮族自治区钦州市浦北县","address_detail":"人生如只如初见，何事秋风悲画扇。","contact_name":"桃子","contact_mobile":"15478784546","region_code":"123"}],"total":3}
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
         * rows : [{"id":12267,"region_name":"上海上海市松江区","address_detail":"我希望有个如你一般的人。","contact_name":"丫头","contact_mobile":"15921815744","region_code":"123"},{"id":12268,"region_name":"四川省甘孜藏族自治州稻城县","address_detail":"我希望有个如你一般的人。如山间清爽的风，如古城温暖的光。从清晨到夜晚，由山野到书房。只要最后是你，就好。","contact_name":"阿尔卑斯","contact_mobile":"15914746767","region_code":"123"},{"id":12270,"region_name":"广西壮族自治区钦州市浦北县","address_detail":"人生如只如初见，何事秋风悲画扇。","contact_name":"桃子","contact_mobile":"15478784546","region_code":"123"}]
         * total : 3
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

        public static class RowsBean implements Serializable{
            /**
             * id : 12267
             * region_name : 上海上海市松江区
             * address_detail : 我希望有个如你一般的人。
             * contact_name : 丫头
             * contact_mobile : 15921815744
             * region_code : 123
             */

            private int id;
            private String region_name;
            private String address_detail;
            private String contact_name;
            private String contact_mobile;
            private String region_code;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public String getAddress_detail() {
                return address_detail;
            }

            public void setAddress_detail(String address_detail) {
                this.address_detail = address_detail;
            }

            public String getContact_name() {
                return contact_name;
            }

            public void setContact_name(String contact_name) {
                this.contact_name = contact_name;
            }

            public String getContact_mobile() {
                return contact_mobile;
            }

            public void setContact_mobile(String contact_mobile) {
                this.contact_mobile = contact_mobile;
            }

            public String getRegion_code() {
                return region_code;
            }

            public void setRegion_code(String region_code) {
                this.region_code = region_code;
            }
        }
    }
}
