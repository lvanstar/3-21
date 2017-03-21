package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/10.
 *  所有特权
 */

public class PrivilegeBean extends BaseBean {

    /**
     * data : {"rows":[{"name":"普通会员","highlight":1,"rows":[{"id":1,"name":"特权名称1","icon":"http://api.test.msparis.com/uploads/20170228/1488254501639.png"}]},{"name":"高级","highlight":0,"rows":[]},{"name":"高级1","highlight":0,"rows":[]},{"name":"高级12","highlight":0,"rows":[]},{"name":"高级24","highlight":0,"rows":[]},{"name":"等级1","highlight":0,"rows":[]}],"total":6}
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
         * rows : [{"name":"普通会员","highlight":1,"rows":[{"id":1,"name":"特权名称1","icon":"http://api.test.msparis.com/uploads/20170228/1488254501639.png"}]},{"name":"高级","highlight":0,"rows":[]},{"name":"高级1","highlight":0,"rows":[]},{"name":"高级12","highlight":0,"rows":[]},{"name":"高级24","highlight":0,"rows":[]},{"name":"等级1","highlight":0,"rows":[]}]
         * total : 6
         */

        private int total;
        private List<RowsBeanX> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBeanX> getRows() {
            return rows;
        }

        public void setRows(List<RowsBeanX> rows) {
            this.rows = rows;
        }

        public static class RowsBeanX {
            /**
             * name : 普通会员
             * highlight : 1
             * rows : [{"id":1,"name":"特权名称1","icon":"http://api.test.msparis.com/uploads/20170228/1488254501639.png"}]
             */

            private String name;
            private int highlight;
            private List<RowsBean> rows;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getHighlight() {
                return highlight;
            }

            public void setHighlight(int highlight) {
                this.highlight = highlight;
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
                 * name : 特权名称1
                 * icon : http://api.test.msparis.com/uploads/20170228/1488254501639.png
                 */

                private int id;
                private String name;
                private String icon;

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

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }
    }
}
