package com.example.robin.coordinatorlayouttest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者: Robin
 * 时间: 2017/2/3 21:22
 * 邮箱: guaren2005@126.com
 */

public class Content implements Parcelable {

    /**
     * success : true
     * coopData : {"id":"C02F537C-9ECB-497C-AB2F-AA1B84FFC651","creatime":{"date":26,"day":4,"hours":11,"minutes":26,"month":2,"nanos":297000000,"seconds":54,"time":1427340414297,"timezoneOffset":-480,"year":115},"staff_id":"CC551D85-A46C-4FC2-8B02-E8D170BFD2A3","title":"测试：有回复","status":1,"sentime":{"date":26,"day":4,"hours":11,"minutes":26,"month":2,"nanos":330000000,"seconds":54,"time":1427340414330,"timezoneOffset":-480,"year":115},"doc_id":"CB5E831B-76E4-4FA9-BD40-911646E28B5F","type":0,"whendone":{"date":26,"day":4,"hours":11,"minutes":27,"month":2,"nanos":463000000,"seconds":40,"time":1427340460463,"timezoneOffset":-480,"year":115},"initiator":"测试帐号","node_start_id":"1DDB5930-D1DB-4FF0-BDF0-0E03E97B606D","tpl_id":null,"priority":0,"forType":null,"content":"有回复正文。。。","contentype":0,"file_id":null,"node_id":"1DDB5930-D1DB-4FF0-BDF0-0E03E97B606D","auth_id":null,"authname":"发起人","node_container_id":null}
     */

    private boolean success;
    private CoopDataBean coopData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CoopDataBean getCoopData() {
        return coopData;
    }

    public void setCoopData(CoopDataBean coopData) {
        this.coopData = coopData;
    }

    public static class CoopDataBean {
        /**
         * id : C02F537C-9ECB-497C-AB2F-AA1B84FFC651
         * creatime : {"date":26,"day":4,"hours":11,"minutes":26,"month":2,"nanos":297000000,"seconds":54,"time":1427340414297,"timezoneOffset":-480,"year":115}
         * staff_id : CC551D85-A46C-4FC2-8B02-E8D170BFD2A3
         * title : 测试：有回复
         * status : 1
         * sentime : {"date":26,"day":4,"hours":11,"minutes":26,"month":2,"nanos":330000000,"seconds":54,"time":1427340414330,"timezoneOffset":-480,"year":115}
         * doc_id : CB5E831B-76E4-4FA9-BD40-911646E28B5F
         * type : 0
         * whendone : {"date":26,"day":4,"hours":11,"minutes":27,"month":2,"nanos":463000000,"seconds":40,"time":1427340460463,"timezoneOffset":-480,"year":115}
         * initiator : 测试帐号
         * node_start_id : 1DDB5930-D1DB-4FF0-BDF0-0E03E97B606D
         * tpl_id : null
         * priority : 0
         * forType : null
         * content : 有回复正文。。。
         * contentype : 0
         * file_id : null
         * node_id : 1DDB5930-D1DB-4FF0-BDF0-0E03E97B606D
         * auth_id : null
         * authname : 发起人
         * node_container_id : null
         */

        private String id;
        private CreatimeBean creatime;
        private String staff_id;
        private String title;
        private int status;
        private SentimeBean sentime;
        private String doc_id;
        private int type;
        private WhendoneBean whendone;
        private String initiator;
        private String node_start_id;
        private Object tpl_id;
        private int priority;
        private Object forType;
        private String content;
        private int contentype;
        private Object file_id;
        private String node_id;
        private Object auth_id;
        private String authname;
        private Object node_container_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CreatimeBean getCreatime() {
            return creatime;
        }

        public void setCreatime(CreatimeBean creatime) {
            this.creatime = creatime;
        }

        public String getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(String staff_id) {
            this.staff_id = staff_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public SentimeBean getSentime() {
            return sentime;
        }

        public void setSentime(SentimeBean sentime) {
            this.sentime = sentime;
        }

        public String getDoc_id() {
            return doc_id;
        }

        public void setDoc_id(String doc_id) {
            this.doc_id = doc_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public WhendoneBean getWhendone() {
            return whendone;
        }

        public void setWhendone(WhendoneBean whendone) {
            this.whendone = whendone;
        }

        public String getInitiator() {
            return initiator;
        }

        public void setInitiator(String initiator) {
            this.initiator = initiator;
        }

        public String getNode_start_id() {
            return node_start_id;
        }

        public void setNode_start_id(String node_start_id) {
            this.node_start_id = node_start_id;
        }

        public Object getTpl_id() {
            return tpl_id;
        }

        public void setTpl_id(Object tpl_id) {
            this.tpl_id = tpl_id;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Object getForType() {
            return forType;
        }

        public void setForType(Object forType) {
            this.forType = forType;
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

        public Object getNode_container_id() {
            return node_container_id;
        }

        public void setNode_container_id(Object node_container_id) {
            this.node_container_id = node_container_id;
        }

        public static class CreatimeBean {
            /**
             * date : 26
             * day : 4
             * hours : 11
             * minutes : 26
             * month : 2
             * nanos : 297000000
             * seconds : 54
             * time : 1427340414297
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
             * date : 26
             * day : 4
             * hours : 11
             * minutes : 26
             * month : 2
             * nanos : 330000000
             * seconds : 54
             * time : 1427340414330
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

        public static class WhendoneBean {
            /**
             * date : 26
             * day : 4
             * hours : 11
             * minutes : 27
             * month : 2
             * nanos : 463000000
             * seconds : 40
             * time : 1427340460463
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeParcelable((Parcelable) this.coopData, flags);
    }

    public Content() {
    }

    private Content(Parcel in) {
        this.success = in.readByte() != 0;
        this.coopData = in.readParcelable(CoopDataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
