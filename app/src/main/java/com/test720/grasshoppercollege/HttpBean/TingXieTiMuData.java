package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/6/26.
 */

public class TingXieTiMuData {

    /**
     * code : 1
     * msg : 成功
     * data : {"voice":"https://www.hzggedu.com/ggxy/Uploads/reading_small/renjiaoban+g3+x+en/Unit1/PartC/g3+renjiaoban+x+u1+11.mp3","word_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-25/5b30e0a3a5445.jpg","title":"","content":""}
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
         * voice : https://www.hzggedu.com/ggxy/Uploads/reading_small/renjiaoban+g3+x+en/Unit1/PartC/g3+renjiaoban+x+u1+11.mp3
         * word_pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-25/5b30e0a3a5445.jpg
         * title :
         * content :
         */

        private String voice;
        private String word_pic;
        private String title;
        private String content;
        private String points;

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getVoice() {
            return voice;
        }

        public void setVoice(String voice) {
            this.voice = voice;
        }

        public String getWord_pic() {
            return word_pic;
        }

        public void setWord_pic(String word_pic) {
            this.word_pic = word_pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
