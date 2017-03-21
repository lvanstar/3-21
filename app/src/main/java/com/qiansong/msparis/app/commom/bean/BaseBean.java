package com.qiansong.msparis.app.commom.bean;

/**
 * Created by lizhaozhao on 16/4/26.
 */
public class BaseBean {
    /**
     * status : ok
     */

    private String status;
    private ErrorBean error;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }
}
