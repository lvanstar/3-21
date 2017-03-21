package com.qiansong.msparis.app.commom.bean;

/**
 * Created by Administrator on 2017/3/13.
 * 支付宝支付的数据进行加密
 */

public class AliBean {


    /**
     * status : ok
     * data : {"parameters":{"_input_charset":"utf-8","body":"MSPARIS女神派 #测试","it_b_pay":"60m","notify_url":"http://api.test.msparis.com/order/alipay-notify","out_trade_no":"2017031715032410000","partner":"2088711709865220","payment_type":1,"seller_id":"paris.xu@icloud.com","service":"mobile.securitypay.pay","subject":"MSPARIS女神派 #测试","total_fee":0.01,"sign":"l4AMXHrq7Hw+qRRiylVVAtyjrW3WlRlWBmLczkYlTfH/M4QEUBj2Iwvj+18d4YqP4izd36va+58XuoiYj8XVNWbRmXKiR4MhsrWnxt4Gm6aC2nPE4mPKaArs0e7unCyGGurQQqYDuhIDUCQeVbCI6/+waD+/LP5PQiANvSYjOhc=","sign_type":"RSA"},"order_spec":"_input_charset=utf-8&body=MSPARIS%E5%A5%B3%E7%A5%9E%E6%B4%BE+%23%E6%B5%8B%E8%AF%95&it_b_pay=60m&notify_url=http%3A%2F%2Fapi.test.msparis.com%2Forder%2Falipay-notify&out_trade_no=2017031715032410000&partner=2088711709865220&payment_type=1&seller_id=paris.xu%40icloud.com&service=mobile.securitypay.pay&subject=MSPARIS%E5%A5%B3%E7%A5%9E%E6%B4%BE+%23%E6%B5%8B%E8%AF%95&total_fee=0.01&sign=l4AMXHrq7Hw%2BqRRiylVVAtyjrW3WlRlWBmLczkYlTfH%2FM4QEUBj2Iwvj%2B18d4YqP4izd36va%2B58XuoiYj8XVNWbRmXKiR4MhsrWnxt4Gm6aC2nPE4mPKaArs0e7unCyGGurQQqYDuhIDUCQeVbCI6%2F%2BwaD%2B%2FLP5PQiANvSYjOhc%3D&sign_type=RSA","sign":"l4AMXHrq7Hw%2BqRRiylVVAtyjrW3WlRlWBmLczkYlTfH%2FM4QEUBj2Iwvj%2B18d4YqP4izd36va%2B58XuoiYj8XVNWbRmXKiR4MhsrWnxt4Gm6aC2nPE4mPKaArs0e7unCyGGurQQqYDuhIDUCQeVbCI6%2F%2BwaD%2B%2FLP5PQiANvSYjOhc%3D","sign_type":"RSA"}
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
         * parameters : {"_input_charset":"utf-8","body":"MSPARIS女神派 #测试","it_b_pay":"60m","notify_url":"http://api.test.msparis.com/order/alipay-notify","out_trade_no":"2017031715032410000","partner":"2088711709865220","payment_type":1,"seller_id":"paris.xu@icloud.com","service":"mobile.securitypay.pay","subject":"MSPARIS女神派 #测试","total_fee":0.01,"sign":"l4AMXHrq7Hw+qRRiylVVAtyjrW3WlRlWBmLczkYlTfH/M4QEUBj2Iwvj+18d4YqP4izd36va+58XuoiYj8XVNWbRmXKiR4MhsrWnxt4Gm6aC2nPE4mPKaArs0e7unCyGGurQQqYDuhIDUCQeVbCI6/+waD+/LP5PQiANvSYjOhc=","sign_type":"RSA"}
         * order_spec : _input_charset=utf-8&body=MSPARIS%E5%A5%B3%E7%A5%9E%E6%B4%BE+%23%E6%B5%8B%E8%AF%95&it_b_pay=60m&notify_url=http%3A%2F%2Fapi.test.msparis.com%2Forder%2Falipay-notify&out_trade_no=2017031715032410000&partner=2088711709865220&payment_type=1&seller_id=paris.xu%40icloud.com&service=mobile.securitypay.pay&subject=MSPARIS%E5%A5%B3%E7%A5%9E%E6%B4%BE+%23%E6%B5%8B%E8%AF%95&total_fee=0.01&sign=l4AMXHrq7Hw%2BqRRiylVVAtyjrW3WlRlWBmLczkYlTfH%2FM4QEUBj2Iwvj%2B18d4YqP4izd36va%2B58XuoiYj8XVNWbRmXKiR4MhsrWnxt4Gm6aC2nPE4mPKaArs0e7unCyGGurQQqYDuhIDUCQeVbCI6%2F%2BwaD%2B%2FLP5PQiANvSYjOhc%3D&sign_type=RSA
         * sign : l4AMXHrq7Hw%2BqRRiylVVAtyjrW3WlRlWBmLczkYlTfH%2FM4QEUBj2Iwvj%2B18d4YqP4izd36va%2B58XuoiYj8XVNWbRmXKiR4MhsrWnxt4Gm6aC2nPE4mPKaArs0e7unCyGGurQQqYDuhIDUCQeVbCI6%2F%2BwaD%2B%2FLP5PQiANvSYjOhc%3D
         * sign_type : RSA
         */

        private ParametersBean parameters;
        private String order_spec;
        private String sign;
        private String sign_type;

        public ParametersBean getParameters() {
            return parameters;
        }

        public void setParameters(ParametersBean parameters) {
            this.parameters = parameters;
        }

        public String getOrder_spec() {
            return order_spec;
        }

        public void setOrder_spec(String order_spec) {
            this.order_spec = order_spec;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSign_type() {
            return sign_type;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }

        public static class ParametersBean {
            /**
             * _input_charset : utf-8
             * body : MSPARIS女神派 #测试
             * it_b_pay : 60m
             * notify_url : http://api.test.msparis.com/order/alipay-notify
             * out_trade_no : 2017031715032410000
             * partner : 2088711709865220
             * payment_type : 1
             * seller_id : paris.xu@icloud.com
             * service : mobile.securitypay.pay
             * subject : MSPARIS女神派 #测试
             * total_fee : 0.01
             * sign : l4AMXHrq7Hw+qRRiylVVAtyjrW3WlRlWBmLczkYlTfH/M4QEUBj2Iwvj+18d4YqP4izd36va+58XuoiYj8XVNWbRmXKiR4MhsrWnxt4Gm6aC2nPE4mPKaArs0e7unCyGGurQQqYDuhIDUCQeVbCI6/+waD+/LP5PQiANvSYjOhc=
             * sign_type : RSA
             */

            private String _input_charset;
            private String body;
            private String it_b_pay;
            private String notify_url;
            private String out_trade_no;
            private String partner;
            private int payment_type;
            private String seller_id;
            private String service;
            private String subject;
            private double total_fee;
            private String sign;
            private String sign_type;

            public String get_input_charset() {
                return _input_charset;
            }

            public void set_input_charset(String _input_charset) {
                this._input_charset = _input_charset;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public String getIt_b_pay() {
                return it_b_pay;
            }

            public void setIt_b_pay(String it_b_pay) {
                this.it_b_pay = it_b_pay;
            }

            public String getNotify_url() {
                return notify_url;
            }

            public void setNotify_url(String notify_url) {
                this.notify_url = notify_url;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getPartner() {
                return partner;
            }

            public void setPartner(String partner) {
                this.partner = partner;
            }

            public int getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(int payment_type) {
                this.payment_type = payment_type;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
                this.seller_id = seller_id;
            }

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getSign_type() {
                return sign_type;
            }

            public void setSign_type(String sign_type) {
                this.sign_type = sign_type;
            }
        }
    }
}
