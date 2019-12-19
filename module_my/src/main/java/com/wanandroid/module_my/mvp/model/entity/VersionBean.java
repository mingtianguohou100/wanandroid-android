package com.wanandroid.module_my.mvp.model.entity;

public class VersionBean {

    /**
     * message : 成功
     * retCode : 200
     * data : {"id":2,"version":"1.4.9","address":"http://www.pigcome.com/app-release.apk","context":"界面优化,针对某些BUG修复","type":0}
     */

    private String message;
    private int retCode;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", version='" + version + '\'' +
                    ", address='" + address + '\'' +
                    ", context='" + context + '\'' +
                    ", type=" + type +
                    '}';
        }

        /**
         * id : 2
         * version : 1.4.9
         * address : http://www.pigcome.com/app-release.apk
         * context : 界面优化,针对某些BUG修复
         * type : 0
         */

        private int id;
        private String version;
        private String address;
        private String context;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
