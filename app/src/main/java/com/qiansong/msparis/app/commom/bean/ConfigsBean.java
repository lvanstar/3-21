package com.qiansong.msparis.app.commom.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lizhaozhao on 2017/2/23.
 *
 *  基本配置类
 */

public class ConfigsBean extends BaseBean implements Serializable{


    /**
     * data : {"brand":{"updated_at":1487591771,"brands":[{"id":"1","name":"ALICE+OLIVIA","code":"alice_olivia","logo":"http://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/alice_olivia.jpg?imageMogr2/strip","pic":["http://file.msparis.com/o_1ap75jbeq1eurg98017131c9ng.jpg"],"sort":1,"is_daily":1,"is_dress":0}]},"product_filter":{"updated_at":1487591771,"specifications":[{"id":1,"name":"尺寸","options":[{"id":"1","name":"L","value":null},{"id":"2","name":"L","value":null}]},{"id":2,"name":"颜色","options":[{"id":"1","name":"红色","value":"ff0000"},{"id":"2","name":"蓝色","value":"00ff00"}]}],"tags":[{"id":1,"name":"场合","options":[{"id":"1","name":"休闲"},{"id":"2","name":"时尚"}]},{"id":2,"name":"服装类型","options":[{"id":"1","name":"连衣裙"},{"id":"2","name":"外套"}]}]},"filter_panel":{"updated_at":1487591771,"daily_panel":["date","s1","s2","t1","t2"],"dress_panel":["s1","s2","t1","t2","date"],"daily_tags_panel":["date","s1"],"dress_tags_panel":["date","s1"]},"sort":{"updated_at":1487591771,"daily_sort":[{"key":"hot","name":"热门"},{"key":"new","name":"上新"}],"dress_sort":[{"key":"hot","name":"热门"},{"key":"new","name":"上新"},{"key":"price-ace","name":"价格从低到高"},{"key":"price-desc","name":"加个从高到低"}]},"send_cities":{"updated_at":1487591771,"send_cities":[{"key":"110000","name":"北京","cities":{"key":"110100","name":"北京市","regions":[{"key":"110115","name":"大兴区","fullname":"北京 北京市 大兴区"},{"key":"110109","name":"门头沟区","fullname":"北京 北京市 门头沟区"}]}}]},"booking_cities":{"updated_at":1487591771,"booking_cities":[{"key":"110000","name":"北京","cities":{"key":"110100","name":"北京市","shops":[{"id":"1","name":"三里屯店","address":"朝阳区工体北路8号三里屯SOHO公寓16号1205","memo":"由太古里沿南三里屯路南行,经过通盈中心后右转100米","start_time":"10:00","end_time":"22:00","link_tel":"010-12345678","link_man":"张小姐"}]}}]}}
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
         * brand : {"updated_at":1487591771,"brands":[{"id":"1","name":"ALICE+OLIVIA","code":"alice_olivia","logo":"http://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/alice_olivia.jpg?imageMogr2/strip","pic":["http://file.msparis.com/o_1ap75jbeq1eurg98017131c9ng.jpg"],"sort":1,"is_daily":1,"is_dress":0}]}
         * product_filter : {"updated_at":1487591771,"specifications":[{"id":1,"name":"尺寸","options":[{"id":"1","name":"L","value":null},{"id":"2","name":"L","value":null}]},{"id":2,"name":"颜色","options":[{"id":"1","name":"红色","value":"ff0000"},{"id":"2","name":"蓝色","value":"00ff00"}]}],"tags":[{"id":1,"name":"场合","options":[{"id":"1","name":"休闲"},{"id":"2","name":"时尚"}]},{"id":2,"name":"服装类型","options":[{"id":"1","name":"连衣裙"},{"id":"2","name":"外套"}]}]}
         * filter_panel : {"updated_at":1487591771,"daily_panel":["date","s1","s2","t1","t2"],"dress_panel":["s1","s2","t1","t2","date"],"daily_tags_panel":["date","s1"],"dress_tags_panel":["date","s1"]}
         * sort : {"updated_at":1487591771,"daily_sort":[{"key":"hot","name":"热门"},{"key":"new","name":"上新"}],"dress_sort":[{"key":"hot","name":"热门"},{"key":"new","name":"上新"},{"key":"price-ace","name":"价格从低到高"},{"key":"price-desc","name":"加个从高到低"}]}
         * send_cities : {"updated_at":1487591771,"send_cities":[{"key":"110000","name":"北京","cities":{"key":"110100","name":"北京市","regions":[{"key":"110115","name":"大兴区","fullname":"北京 北京市 大兴区"},{"key":"110109","name":"门头沟区","fullname":"北京 北京市 门头沟区"}]}}]}
         * booking_cities : {"updated_at":1487591771,"booking_cities":[{"key":"110000","name":"北京","cities":{"key":"110100","name":"北京市","shops":[{"id":"1","name":"三里屯店","address":"朝阳区工体北路8号三里屯SOHO公寓16号1205","memo":"由太古里沿南三里屯路南行,经过通盈中心后右转100米","start_time":"10:00","end_time":"22:00","link_tel":"010-12345678","link_man":"张小姐"}]}}]}
         */

        private BrandEntity brand;
        private ProductFilterEntity product_filter;
        private FilterPanelEntity filter_panel;
        private SortEntity sort;
        private SendCitiesEntityX send_cities;
        private BookingCitiesEntityX booking_cities;
        private UserSizeEntity user_size;

        public BrandEntity getBrand() {
            return brand;
        }

        public void setBrand(BrandEntity brand) {
            this.brand = brand;
        }

        public ProductFilterEntity getProduct_filter() {
            return product_filter;
        }

        public void setProduct_filter(ProductFilterEntity product_filter) {
            this.product_filter = product_filter;
        }

        public FilterPanelEntity getFilter_panel() {
            return filter_panel;
        }

        public void setFilter_panel(FilterPanelEntity filter_panel) {
            this.filter_panel = filter_panel;
        }

        public SortEntity getSort() {
            return sort;
        }

        public void setSort(SortEntity sort) {
            this.sort = sort;
        }

        public SendCitiesEntityX getSend_cities() {
            return send_cities;
        }

        public void setSend_cities(SendCitiesEntityX send_cities) {
            this.send_cities = send_cities;
        }

        public BookingCitiesEntityX getBooking_cities() {
            return booking_cities;
        }

        public void setBooking_cities(BookingCitiesEntityX booking_cities) {
            this.booking_cities = booking_cities;
        }

        public UserSizeEntity getUser_size() {
            return user_size;
        }

        public void setUser_size(UserSizeEntity user_size) {
            this.user_size = user_size;
        }

        public static class UserSizeEntity{
            private int updated_at;
            private List<UserSizesEntity>user_size;

            public List<UserSizesEntity> getUser_size() {
                return user_size;
            }

            public void setUser_size(List<UserSizesEntity> user_size) {
                this.user_size = user_size;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public static class UserSizesEntity{
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
        }

        public static class BrandEntity {
            /**
             * updated_at : 1487591771
             * brands : [{"id":"1","name":"ALICE+OLIVIA","code":"alice_olivia","logo":"http://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/alice_olivia.jpg?imageMogr2/strip","pic":["http://file.msparis.com/o_1ap75jbeq1eurg98017131c9ng.jpg"],"sort":1,"is_daily":1,"is_dress":0}]
             */

            private int updated_at;
            public List<BrandsEntity> brands;

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public List<BrandsEntity> getBrands() {
                return brands;
            }

            public void setBrands(List<BrandsEntity> brands) {
                this.brands = brands;
            }

            public static class BrandsEntity {
                /**
                 * id : 1
                 * name : ALICE+OLIVIA
                 * code : alice_olivia
                 * logo : http://7xaw9u.com2.z0.glb.qiniucdn.com/sort-designer/alice_olivia.jpg?imageMogr2/strip
                 * pic : ["http://file.msparis.com/o_1ap75jbeq1eurg98017131c9ng.jpg"]
                 * sort : 1
                 * is_daily : 1
                 * is_dress : 0
                 */

                private String id;
                private String name;
                private String code;
                private String logo;
                private int sort;
                private int is_daily;
                private int is_dress;
                private List<String> pic;
                public String sort_az;
                public boolean is_dingyue=false;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public int getIs_daily() {
                    return is_daily;
                }

                public void setIs_daily(int is_daily) {
                    this.is_daily = is_daily;
                }

                public int getIs_dress() {
                    return is_dress;
                }

                public void setIs_dress(int is_dress) {
                    this.is_dress = is_dress;
                }

                public List<String> getPic() {
                    return pic;
                }

                public void setPic(List<String> pic) {
                    this.pic = pic;
                }
            }
        }

        public static class ProductFilterEntity implements Serializable{
            /**
             * updated_at : 1487591771
             * specifications : [{"id":1,"name":"尺寸","options":[{"id":"1","name":"L","value":null},{"id":"2","name":"L","value":null}]},{"id":2,"name":"颜色","options":[{"id":"1","name":"红色","value":"ff0000"},{"id":"2","name":"蓝色","value":"00ff00"}]}]
             * tags : [{"id":1,"name":"场合","options":[{"id":"1","name":"休闲"},{"id":"2","name":"时尚"}]},{"id":2,"name":"服装类型","options":[{"id":"1","name":"连衣裙"},{"id":"2","name":"外套"}]}]
             */

            private long updated_at;
            private List<TagsEntity> specifications;
            private List<TagsEntity> tags;

            public long getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public List<TagsEntity> getSpecifications() {
                return specifications;
            }

            public void setSpecifications(List<TagsEntity> specifications) {
                this.specifications = specifications;
            }

            public List<TagsEntity> getTags() {
                return tags;
            }

            public void setTags(List<TagsEntity> tags) {
                this.tags = tags;
            }

            public static class SpecificationsEntity {
                /**
                 * id : 1
                 * name : 尺寸
                 * options : [{"id":"1","name":"L","value":null},{"id":"2","name":"L","value":null}]
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
                     * value : null
                     */

                    private String id;
                    private String name;
                    private Object value;
                    public boolean select;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getValue() {
                        return value;
                    }

                    public void setValue(Object value) {
                        this.value = value;
                    }
                }
            }

            public static class TagsEntity implements Serializable{
                /**
                 * id : 1
                 * name : 场合
                 * options : [{"id":"1","name":"休闲"},{"id":"2","name":"时尚"}]
                 */

                private int id;
                private String name;
                public  String is_color;
                public  String panel;
                private List<OptionsEntityX> options;

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

                public List<OptionsEntityX> getOptions() {
                    return options;
                }

                public void setOptions(List<OptionsEntityX> options) {
                    this.options = options;
                }

                public static class OptionsEntityX implements Serializable{
                    /**
                     * id : 1
                     * name : 休闲
                     */

                    private String id;
                    private String name;
                    private String value;
                    public boolean select;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
        }

        public static class FilterPanelEntity {
            /**
             * updated_at : 1487591771
             * daily_panel : ["date","s1","s2","t1","t2"]
             * dress_panel : ["s1","s2","t1","t2","date"]
             * daily_tags_panel : ["date","s1"]
             * dress_tags_panel : ["date","s1"]
             */

            private int updated_at;
            private List<String> daily_panel;
            private List<String> dress_panel;
            private List<String> daily_tags_panel;
            private List<String> dress_tags_panel;

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public List<String> getDaily_panel() {
                return daily_panel;
            }

            public void setDaily_panel(List<String> daily_panel) {
                this.daily_panel = daily_panel;
            }

            public List<String> getDress_panel() {
                return dress_panel;
            }

            public void setDress_panel(List<String> dress_panel) {
                this.dress_panel = dress_panel;
            }

            public List<String> getDaily_tags_panel() {
                return daily_tags_panel;
            }

            public void setDaily_tags_panel(List<String> daily_tags_panel) {
                this.daily_tags_panel = daily_tags_panel;
            }

            public List<String> getDress_tags_panel() {
                return dress_tags_panel;
            }

            public void setDress_tags_panel(List<String> dress_tags_panel) {
                this.dress_tags_panel = dress_tags_panel;
            }
        }

        public static class SortEntity {
            /**
             * updated_at : 1487591771
             * daily_sort : [{"key":"hot","name":"热门"},{"key":"new","name":"上新"}]
             * dress_sort : [{"key":"hot","name":"热门"},{"key":"new","name":"上新"},{"key":"price-ace","name":"价格从低到高"},{"key":"price-desc","name":"加个从高到低"}]
             */

            private int updated_at;
            private List<DailySortEntity> daily_sort;
            private List<DressSortEntity> dress_sort;

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public List<DailySortEntity> getDaily_sort() {
                return daily_sort;
            }

            public void setDaily_sort(List<DailySortEntity> daily_sort) {
                this.daily_sort = daily_sort;
            }

            public List<DressSortEntity> getDress_sort() {
                return dress_sort;
            }

            public void setDress_sort(List<DressSortEntity> dress_sort) {
                this.dress_sort = dress_sort;
            }

            public static class DailySortEntity {
                /**
                 * key : hot
                 * name : 热门
                 */

                private String key;
                private String name;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class DressSortEntity {
                /**
                 * key : hot
                 * name : 热门
                 */

                private String key;
                private String name;
                public boolean select;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class SendCitiesEntityX {
            /**
             * updated_at : 1487591771
             * send_cities : [{"key":"110000","name":"北京","cities":{"key":"110100","name":"北京市","regions":[{"key":"110115","name":"大兴区","fullname":"北京 北京市 大兴区"},{"key":"110109","name":"门头沟区","fullname":"北京 北京市 门头沟区"}]}}]
             */

            private int updated_at;
            private List<SendCitiesEntity> send_cities;

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public List<SendCitiesEntity> getSend_cities() {
                return send_cities;
            }

            public void setSend_cities(List<SendCitiesEntity> send_cities) {
                this.send_cities = send_cities;
            }

            public static class SendCitiesEntity {
                /**
                 * key : 110000
                 * name : 北京
                 * cities : {"key":"110100","name":"北京市","regions":[{"key":"110115","name":"大兴区","fullname":"北京 北京市 大兴区"},{"key":"110109","name":"门头沟区","fullname":"北京 北京市 门头沟区"}]}
                 */

                private String key;
                private String name;
                private List<CitiesEntity> cities;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<CitiesEntity> getCities() {
                    return cities;
                }

                public void setCities(List<CitiesEntity> cities) {
                    this.cities = cities;
                }

                public static class CitiesEntity {
                    /**
                     * key : 110100
                     * name : 北京市
                     * regions : [{"key":"110115","name":"大兴区","fullname":"北京 北京市 大兴区"},{"key":"110109","name":"门头沟区","fullname":"北京 北京市 门头沟区"}]
                     */

                    private String key;
                    private String name;
                    private List<RegionsEntity> regions;

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public List<RegionsEntity> getRegions() {
                        return regions;
                    }

                    public void setRegions(List<RegionsEntity> regions) {
                        this.regions = regions;
                    }

                    public static class RegionsEntity {
                        /**
                         * key : 110115
                         * name : 大兴区
                         * fullname : 北京 北京市 大兴区
                         */

                        private String key;
                        private String name;
                        private String fullname;

                        public String getKey() {
                            return key;
                        }

                        public void setKey(String key) {
                            this.key = key;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getFullname() {
                            return fullname;
                        }

                        public void setFullname(String fullname) {
                            this.fullname = fullname;
                        }
                    }
                }
            }
        }

        public static class BookingCitiesEntityX {
            /**
             * updated_at : 1487591771
             * booking_cities : [{"key":"110000","name":"北京","cities":{"key":"110100","name":"北京市","shops":[{"id":"1","name":"三里屯店","address":"朝阳区工体北路8号三里屯SOHO公寓16号1205","memo":"由太古里沿南三里屯路南行,经过通盈中心后右转100米","start_time":"10:00","end_time":"22:00","link_tel":"010-12345678","link_man":"张小姐"}]}}]
             */

            private int updated_at;
            private List<BookingCitiesEntity> booking_cities;

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public List<BookingCitiesEntity> getBooking_cities() {
                return booking_cities;
            }

            public void setBooking_cities(List<BookingCitiesEntity> booking_cities) {
                this.booking_cities = booking_cities;
            }

            public static class BookingCitiesEntity {
                /**
                 * key : 110000
                 * name : 北京
                 * cities : {"key":"110100","name":"北京市","shops":[{"id":"1","name":"三里屯店","address":"朝阳区工体北路8号三里屯SOHO公寓16号1205","memo":"由太古里沿南三里屯路南行,经过通盈中心后右转100米","start_time":"10:00","end_time":"22:00","link_tel":"010-12345678","link_man":"张小姐"}]}
                 */

                private String key;
                private String name;
                public String pinyin;
                private List<CitiesEntityX> cities;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<CitiesEntityX> getCities() {
                    return cities;
                }

                public void setCities(List<CitiesEntityX> cities) {
                    this.cities = cities;
                }

                public static class CitiesEntityX {
                    /**
                     * key : 110100
                     * name : 北京市
                     * shops : [{"id":"1","name":"三里屯店","address":"朝阳区工体北路8号三里屯SOHO公寓16号1205","memo":"由太古里沿南三里屯路南行,经过通盈中心后右转100米","start_time":"10:00","end_time":"22:00","link_tel":"010-12345678","link_man":"张小姐"}]
                     */

                    private String key;
                    private String name;
                    public String pinyin;
                    private List<ShopsEntity> shops;

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public List<ShopsEntity> getShops() {
                        return shops;
                    }

                    public void setShops(List<ShopsEntity> shops) {
                        this.shops = shops;
                    }

                    public static class ShopsEntity {
                        /**
                         * id : 1
                         * name : 三里屯店
                         * address : 朝阳区工体北路8号三里屯SOHO公寓16号1205
                         * memo : 由太古里沿南三里屯路南行,经过通盈中心后右转100米
                         * start_time : 10:00
                         * end_time : 22:00
                         * link_tel : 010-12345678
                         * link_man : 张小姐
                         */

                        private String id;
                        private String name;
                        private String address;
                        private String memo;
                        private String start_time;
                        private String end_time;
                        private String link_tel;
                        private String link_man;

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getAddress() {
                            return address;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }

                        public String getMemo() {
                            return memo;
                        }

                        public void setMemo(String memo) {
                            this.memo = memo;
                        }

                        public String getStart_time() {
                            return start_time;
                        }

                        public void setStart_time(String start_time) {
                            this.start_time = start_time;
                        }

                        public String getEnd_time() {
                            return end_time;
                        }

                        public void setEnd_time(String end_time) {
                            this.end_time = end_time;
                        }

                        public String getLink_tel() {
                            return link_tel;
                        }

                        public void setLink_tel(String link_tel) {
                            this.link_tel = link_tel;
                        }

                        public String getLink_man() {
                            return link_man;
                        }

                        public void setLink_man(String link_man) {
                            this.link_man = link_man;
                        }
                    }
                }
            }
        }
    }
}
