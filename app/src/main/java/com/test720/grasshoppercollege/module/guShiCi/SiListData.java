package com.test720.grasshoppercollege.module.guShiCi;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class SiListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"poetry_id":"1","name":"李白白","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","status":"0"},{"poetry_id":"2","name":"李白白1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","status":"0"},{"poetry_id":"3","name":"李白白2","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","status":"0"},{"poetry_id":"4","name":"李白白3","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","status":"0"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * poetry_id : 1
             * name : 李白白
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
             * status : 0
             */

            private String poetry_id;
            private String name;
            private String cover;
            private String status;

            public String getPoetry_id() {
                return poetry_id;
            }

            public void setPoetry_id(String poetry_id) {
                this.poetry_id = poetry_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
