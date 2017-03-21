package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by lizhaozhao on 2017/3/15.
 *
 *   消息数据
 */

public class MessageBean extends BaseBean{

    /**
     * data : {"rows":[{"content":"还衣提醒2","type":3,"created_at":"2017-03-14 23:17:40"},{"content":"物流信息","type":2,"created_at":"2017-03-14 23:16:41"},{"content":"系统消息","type":1,"created_at":"2017-03-14 23:16:15"}],"total":3}
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
         * rows : [{"content":"还衣提醒2","type":3,"created_at":"2017-03-14 23:17:40"},{"content":"物流信息","type":2,"created_at":"2017-03-14 23:16:41"},{"content":"系统消息","type":1,"created_at":"2017-03-14 23:16:15"}]
         * total : 3
         */

        private int total;
        private List<RowsEntity> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public static class RowsEntity {
            /**
             * content : 还衣提醒2
             * type : 3
             * created_at : 2017-03-14 23:17:40
             */

            private String content;
            private int type;
            private String created_at;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
