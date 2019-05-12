package com.test720.grasshoppercollege.HttpBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/22.
 */

public class CiDaiNeiRongData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"book_version":"人教版","class":"一年级","status":"下册","voice":"https://www.hzggedu.com/ggxy/Uploads/heros theme.mp3"},"list":[{"unit":"Unit 1","unit_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3","page":[{"page":"1","page_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3"}]},{"unit":"Unit 2","unit_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3","page":[{"page":"2","page_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3"}]}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * info : {"book_version":"人教版","class":"一年级","status":"下册","voice":"https://www.hzggedu.com/ggxy/Uploads/heros theme.mp3"}
         * list : [{"unit":"Unit 1","unit_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3","page":[{"page":"1","page_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3"}]},{"unit":"Unit 2","unit_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3","page":[{"page":"2","page_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3"}]}]
         */

        private InfoBean info;
        private List<ListBean> list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * book_version : 人教版
             * class : 一年级
             * status : 下册
             * voice : https://www.hzggedu.com/ggxy/Uploads/heros theme.mp3
             */

            private String book_version;
            @SerializedName("class")
            private String classX;
            private String status;
            private String voice;
            private String alias;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getBook_version() {
                return book_version;
            }

            public void setBook_version(String book_version) {
                this.book_version = book_version;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getVoice() {
                return voice;
            }

            public void setVoice(String voice) {
                this.voice = voice;
            }
        }

        public static class ListBean {
            /**
             * unit : Unit 1
             * unit_voice : https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3
             * page : [{"page":"1","page_voice":"https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3"}]
             */

            private String unit;
            private String unit_voice;
            private List<PageBean> page;

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUnit_voice() {
                return unit_voice;
            }

            public void setUnit_voice(String unit_voice) {
                this.unit_voice = unit_voice;
            }

            public List<PageBean> getPage() {
                return page;
            }

            public void setPage(List<PageBean> page) {
                this.page = page;
            }

            public static class PageBean {
                /**
                 * page : 1
                 * page_voice : https://www.hzggedu.com/ggxy/Uploads/fallingstar.mp3
                 */

                private String page;
                private String page_voice;

                public String getPage() {
                    return page;
                }

                public void setPage(String page) {
                    this.page = page;
                }

                public String getPage_voice() {
                    return page_voice;
                }

                public void setPage_voice(String page_voice) {
                    this.page_voice = page_voice;
                }
            }
        }
    }
}
