package com.example.robin.coordinatorlayouttest.bean;

import java.util.List;

/**
 * 作者: Robin
 * 时间: 2017/2/3 16:51
 * 邮箱: guaren2005@126.com
 */

public class User {

    /**
     * limit : 40
     * totalProperty : 14
     * start : 0
     * items : [{"id0":4,"node_id":"554E05F0-6E90-41F9-8D2E-17DA7186966F","auth_id":null,"authname":"发起人","node_pre_id":"6148F864-48E8-4143-B40F-E542BF1329E2","node_type":0,"staff_name":"test","coop_id":"3B0A15E0-A124-42EF-86C5-248A671D118E","title":"11111111111111111111","creatime":{"date":18,"day":3,"hours":19,"minutes":39,"month":2,"nanos":967000000,"seconds":48,"time":1426678788967,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":18,"day":3,"hours":19,"minutes":39,"month":2,"nanos":80000000,"seconds":49,"time":1426678789080,"timezoneOffset":-480,"year":115},"content":"阿萨德发生的发生sfdgdfg&nbsp;","contentype":0,"file_id":null},{"id0":6,"node_id":"C51D6CC5-FC15-44CB-B926-42FEFC1962E8","auth_id":"9237B575-6B92-4EC8-9FC5-E29619B2DB06","authname":"审核","node_pre_id":"554E05F0-6E90-41F9-8D2E-17DA7186966F","node_type":1,"staff_name":"test","coop_id":"3B0A15E0-A124-42EF-86C5-248A671D118E","title":"11111111111111111111","creatime":{"date":18,"day":3,"hours":19,"minutes":39,"month":2,"nanos":967000000,"seconds":48,"time":1426678788967,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":18,"day":3,"hours":19,"minutes":39,"month":2,"nanos":80000000,"seconds":49,"time":1426678789080,"timezoneOffset":-480,"year":115},"content":"阿萨德发生的发生sfdgdfg&nbsp;","contentype":0,"file_id":null},{"id0":2,"node_id":"BF1AFDF8-2943-4FD5-B42A-130B5CE007A9","auth_id":null,"authname":"发起人","node_pre_id":"6148F864-48E8-4143-B40F-E542BF1329E2","node_type":0,"staff_name":"test","coop_id":"829EBC6A-38BB-41E1-A3EA-526D8799126D","title":"test4","creatime":{"date":18,"day":3,"hours":19,"minutes":38,"month":2,"nanos":827000000,"seconds":21,"time":1426678701827,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":18,"day":3,"hours":19,"minutes":38,"month":2,"nanos":940000000,"seconds":21,"time":1426678701940,"timezoneOffset":-480,"year":115},"content":"阿萨德发生的发生333","contentype":0,"file_id":null},{"id0":14,"node_id":"BC1BF759-5438-4D34-B646-F699C701EB70","auth_id":null,"authname":"发起人","node_pre_id":"6148F864-48E8-4143-B40F-E542BF1329E2","node_type":0,"staff_name":"test","coop_id":"EBA27906-F522-4CB9-B831-FEA4FFDA71F2","title":"test3","creatime":{"date":18,"day":3,"hours":19,"minutes":11,"month":2,"nanos":577000000,"seconds":51,"time":1426677111577,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":18,"day":3,"hours":19,"minutes":11,"month":2,"nanos":693000000,"seconds":51,"time":1426677111693,"timezoneOffset":-480,"year":115},"content":"阿萨德发生的发生","contentype":0,"file_id":null},{"id0":8,"node_id":"536E741A-C360-43B5-A363-58F79B0B617B","auth_id":null,"authname":"发起人","node_pre_id":"6148F864-48E8-4143-B40F-E542BF1329E2","node_type":0,"staff_name":"test","coop_id":"1A60B970-93B9-440F-A3DA-D20FE089A972","title":"test2","creatime":{"date":18,"day":3,"hours":19,"minutes":11,"month":2,"nanos":993000000,"seconds":23,"time":1426677083993,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":18,"day":3,"hours":19,"minutes":11,"month":2,"nanos":90000000,"seconds":24,"time":1426677084090,"timezoneOffset":-480,"year":115},"content":"阿萨德发生的发生","contentype":0,"file_id":null},{"id0":5,"node_id":"E79F0D9B-4368-42A8-896C-25E9EB5A54D0","auth_id":null,"authname":"发起人","node_pre_id":"6148F864-48E8-4143-B40F-E542BF1329E2","node_type":0,"staff_name":"test","coop_id":"EBA35BCA-6E29-45B1-B863-B6D04A6C9692","title":"test","creatime":{"date":18,"day":3,"hours":19,"minutes":1,"month":2,"nanos":320000000,"seconds":30,"time":1426676490320,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":18,"day":3,"hours":19,"minutes":1,"month":2,"nanos":533000000,"seconds":30,"time":1426676490533,"timezoneOffset":-480,"year":115},"content":"test\u200b","contentype":0,"file_id":null},{"id0":3,"node_id":"73BB52D0-1623-4BC9-8848-147D134049F7","auth_id":null,"authname":"发起人","node_pre_id":"DEA5C80F-E011-453C-8366-BB88567E3EA9","node_type":0,"staff_name":"test","coop_id":"DEA5C80F-E011-453C-8366-BB88567E3EA9","title":"水电费","creatime":{"date":12,"day":4,"hours":14,"minutes":50,"month":2,"nanos":497000000,"seconds":46,"time":1426143046497,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":12,"day":4,"hours":14,"minutes":50,"month":2,"nanos":643000000,"seconds":46,"time":1426143046643,"timezoneOffset":-480,"year":115},"content":"阿斯顿发斯蒂芬","contentype":0,"file_id":null},{"id0":7,"node_id":"B0F5A8EE-77B9-4128-9A22-430F702AE09F","auth_id":null,"authname":"发起人","node_pre_id":"76AB1F1F-2A57-466B-84D4-0A6A6D679D6E","node_type":0,"staff_name":"test","coop_id":"76AB1F1F-2A57-466B-84D4-0A6A6D679D6E","title":"ceshi ","creatime":{"date":10,"day":2,"hours":17,"minutes":45,"month":2,"nanos":463000000,"seconds":55,"time":1425980755463,"timezoneOffset":-480,"year":115},"initiator":"test","status":2,"priority":0,"sentime":{"date":12,"day":4,"hours":10,"minutes":30,"month":2,"nanos":423000000,"seconds":2,"time":1426127402423,"timezoneOffset":-480,"year":115},"content":"content_test","contentype":0,"file_id":null},{"id0":12,"node_id":"FB3D2AD9-BB08-45D9-A088-E7508808B5C6","auth_id":null,"authname":"发起人","node_pre_id":"901899F4-4F42-4427-9A7C-04E21E583F5C","node_type":0,"staff_name":"test","coop_id":"901899F4-4F42-4427-9A7C-04E21E583F5C","title":"123123","creatime":{"date":3,"day":2,"hours":12,"minutes":5,"month":1,"nanos":510000000,"seconds":20,"time":1422936320510,"timezoneOffset":-480,"year":115},"initiator":"test","status":1,"priority":0,"sentime":{"date":3,"day":2,"hours":12,"minutes":5,"month":1,"nanos":597000000,"seconds":20,"time":1422936320597,"timezoneOffset":-480,"year":115},"content":"","contentype":0,"file_id":null},{"id0":13,"node_id":"52B5F1AB-5981-4CA1-AEB2-E75557E1781C","auth_id":"9237B575-6B92-4EC8-9FC5-E29619B2DB06","authname":"审核","node_pre_id":"FB3D2AD9-BB08-45D9-A088-E7508808B5C6","node_type":1,"staff_name":"test","coop_id":"901899F4-4F42-4427-9A7C-04E21E583F5C","title":"123123","creatime":{"date":3,"day":2,"hours":12,"minutes":5,"month":1,"nanos":510000000,"seconds":20,"time":1422936320510,"timezoneOffset":-480,"year":115},"initiator":"test","status":1,"priority":0,"sentime":{"date":3,"day":2,"hours":12,"minutes":5,"month":1,"nanos":597000000,"seconds":20,"time":1422936320597,"timezoneOffset":-480,"year":115},"content":"","contentype":0,"file_id":null},{"id0":1,"node_id":"FDF4BD90-2EF1-4E96-9944-0E1B920DA549","auth_id":"2ECD3713-EBDB-46F0-BB58-C8870581E8B9","authname":"协同","node_pre_id":"458BAC24-F45F-4030-AC95-65D39C5A0B42","node_type":1,"staff_name":"test","coop_id":"24D389E9-2D9C-4AB5-93D8-B4A85A46905C","title":"test","creatime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":693000000,"seconds":40,"time":1408932280693,"timezoneOffset":-480,"year":114},"initiator":"test","status":1,"priority":0,"sentime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":700000000,"seconds":40,"time":1408932280700,"timezoneOffset":-480,"year":114},"content":"","contentype":0,"file_id":null},{"id0":9,"node_id":"458BAC24-F45F-4030-AC95-65D39C5A0B42","auth_id":null,"authname":"发起人","node_pre_id":"24D389E9-2D9C-4AB5-93D8-B4A85A46905C","node_type":0,"staff_name":"test","coop_id":"24D389E9-2D9C-4AB5-93D8-B4A85A46905C","title":"test","creatime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":693000000,"seconds":40,"time":1408932280693,"timezoneOffset":-480,"year":114},"initiator":"test","status":1,"priority":0,"sentime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":700000000,"seconds":40,"time":1408932280700,"timezoneOffset":-480,"year":114},"content":"","contentype":0,"file_id":null},{"id0":10,"node_id":"2C30C5E2-68C6-449B-A017-D57B9C510714","auth_id":"2ECD3713-EBDB-46F0-BB58-C8870581E8B9","authname":"发起人","node_pre_id":"D0788BF3-11AC-482D-B8BC-DC08CE0249E1","node_type":1,"staff_name":"test","coop_id":"24D389E9-2D9C-4AB5-93D8-B4A85A46905C","title":"test","creatime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":693000000,"seconds":40,"time":1408932280693,"timezoneOffset":-480,"year":114},"initiator":"test","status":1,"priority":0,"sentime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":700000000,"seconds":40,"time":1408932280700,"timezoneOffset":-480,"year":114},"content":"","contentype":0,"file_id":null},{"id0":11,"node_id":"D0788BF3-11AC-482D-B8BC-DC08CE0249E1","auth_id":"2ECD3713-EBDB-46F0-BB58-C8870581E8B9","authname":"发起人","node_pre_id":"FDF4BD90-2EF1-4E96-9944-0E1B920DA549","node_type":1,"staff_name":"test","coop_id":"24D389E9-2D9C-4AB5-93D8-B4A85A46905C","title":"test","creatime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":693000000,"seconds":40,"time":1408932280693,"timezoneOffset":-480,"year":114},"initiator":"test","status":1,"priority":0,"sentime":{"date":25,"day":1,"hours":10,"minutes":4,"month":7,"nanos":700000000,"seconds":40,"time":1408932280700,"timezoneOffset":-480,"year":114},"content":"","contentype":0,"file_id":null}]
     * success : true
     */

    private int limit;
    private String totalProperty;
    private int start;
    private boolean success;
    private List<ItemsBean> items;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getTotalProperty() {
        return totalProperty;
    }

    public void setTotalProperty(String totalProperty) {
        this.totalProperty = totalProperty;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    private static class ItemsBean {
        /**
         * id0 : 4
         * node_id : 554E05F0-6E90-41F9-8D2E-17DA7186966F
         * auth_id : null
         * authname : 发起人
         * node_pre_id : 6148F864-48E8-4143-B40F-E542BF1329E2
         * node_type : 0
         * staff_name : test
         * coop_id : 3B0A15E0-A124-42EF-86C5-248A671D118E
         * title : 11111111111111111111
         * creatime : {"date":18,"day":3,"hours":19,"minutes":39,"month":2,"nanos":967000000,"seconds":48,"time":1426678788967,"timezoneOffset":-480,"year":115}
         * initiator : test
         * status : 2
         * priority : 0
         * sentime : {"date":18,"day":3,"hours":19,"minutes":39,"month":2,"nanos":80000000,"seconds":49,"time":1426678789080,"timezoneOffset":-480,"year":115}
         * content : 阿萨德发生的发生sfdgdfg&nbsp;
         * contentype : 0
         * file_id : null
         */

        private int id0;
        private String node_id;
        private Object auth_id;
        private String authname;
        private String node_pre_id;
        private int node_type;
        private String staff_name;
        private String coop_id;
        private String title;
        private CreatimeBean creatime;
        private String initiator;
        private int status;
        private int priority;
        private SentimeBean sentime;
        private String content;
        private int contentype;
        private Object file_id;

        public int getId0() {
            return id0;
        }

        public void setId0(int id0) {
            this.id0 = id0;
        }

        public String getNode_id() {
            return node_id;
        }

        public void setNode_id(String node_id) {
            this.node_id = node_id;
        }

        public Object getAuth_id() {
            return auth_id;
        }

        public void setAuth_id(Object auth_id) {
            this.auth_id = auth_id;
        }

        public String getAuthname() {
            return authname;
        }

        public void setAuthname(String authname) {
            this.authname = authname;
        }

        public String getNode_pre_id() {
            return node_pre_id;
        }

        public void setNode_pre_id(String node_pre_id) {
            this.node_pre_id = node_pre_id;
        }

        public int getNode_type() {
            return node_type;
        }

        public void setNode_type(int node_type) {
            this.node_type = node_type;
        }

        public String getStaff_name() {
            return staff_name;
        }

        public void setStaff_name(String staff_name) {
            this.staff_name = staff_name;
        }

        public String getCoop_id() {
            return coop_id;
        }

        public void setCoop_id(String coop_id) {
            this.coop_id = coop_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public CreatimeBean getCreatime() {
            return creatime;
        }

        public void setCreatime(CreatimeBean creatime) {
            this.creatime = creatime;
        }

        public String getInitiator() {
            return initiator;
        }

        public void setInitiator(String initiator) {
            this.initiator = initiator;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public SentimeBean getSentime() {
            return sentime;
        }

        public void setSentime(SentimeBean sentime) {
            this.sentime = sentime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getContentype() {
            return contentype;
        }

        public void setContentype(int contentype) {
            this.contentype = contentype;
        }

        public Object getFile_id() {
            return file_id;
        }

        public void setFile_id(Object file_id) {
            this.file_id = file_id;
        }

        public static class CreatimeBean {
            /**
             * date : 18
             * day : 3
             * hours : 19
             * minutes : 39
             * month : 2
             * nanos : 967000000
             * seconds : 48
             * time : 1426678788967
             * timezoneOffset : -480
             * year : 115
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class SentimeBean {
            /**
             * date : 18
             * day : 3
             * hours : 19
             * minutes : 39
             * month : 2
             * nanos : 80000000
             * seconds : 49
             * time : 1426678789080
             * timezoneOffset : -480
             * year : 115
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
