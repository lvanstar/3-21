package com.qiansong.msparis.app.commom.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/3/14.
 * 微信预支付下单
 */

public class WeixinPreBean {

    /**
     * status : ok
     * data : {"appid":"wx5097f181a2ee5017","partnerid":"wx5097f181a2ee5017","noncestr":"rnlkdraesmf7kcugffq1l8bb9s9y6u8r","prepayid":"wx2017031415315702880f6ade0061683354","package":"Sign=WXPay","timestamp":1489476718,"sign":"09B7F790A9D1D1B08187F13C1D5704CE"}
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
         * appid : wx5097f181a2ee5017
         * partnerid : wx5097f181a2ee5017
         * noncestr : rnlkdraesmf7kcugffq1l8bb9s9y6u8r
         * prepayid : wx2017031415315702880f6ade0061683354
         * package : Sign=WXPay
         * timestamp : 1489476718
         * sign : 09B7F790A9D1D1B08187F13C1D5704CE
         */

        private String appid;
        private String partnerid;
        private String noncestr;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
