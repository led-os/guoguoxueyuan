package com.test720.grasshoppercollege.module.guShiCi;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class SiCiXiangQingData {

    /**
     * code : 1
     * msg : 成功
     * data : {"poetry_id":"1","name":"李白白","video_path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","voice":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.mp3","desc_web":"https://www.hzggedu.com/ggxydemo/small.php/ChineseAncientPoetry/descWeb/id/1","pay":false,"collection":"0"}
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
         * poetry_id : 1
         * name : 李白白
         * video_path : https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
         * voice : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.mp3
         * desc_web : https://www.hzggedu.com/ggxydemo/small.php/ChineseAncientPoetry/descWeb/id/1
         * pay : false
         * collection : 0
         */

        private String poetry_id;
        private String name;
        private String video_path;
        private String pic;
        private String voice;
        private String desc_web;
        private String web_url;
        private boolean pay;
        private String collection;

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

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

        public String getVideo_path() {
            return video_path;
        }

        public void setVideo_path(String video_path) {
            this.video_path = video_path;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getVoice() {
            return voice;
        }

        public void setVoice(String voice) {
            this.voice = voice;
        }

        public String getDesc_web() {
            return desc_web;
        }

        public void setDesc_web(String desc_web) {
            this.desc_web = desc_web;
        }

        public boolean isPay() {
            return pay;
        }

        public void setPay(boolean pay) {
            this.pay = pay;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }
    }
}
