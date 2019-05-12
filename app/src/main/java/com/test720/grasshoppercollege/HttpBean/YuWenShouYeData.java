package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/19.
 */

public class YuWenShouYeData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"video_id":"5","name":"111111111","pic":"https://www.hzggedu.com/ggxy/Uploads/chinese_pinyin/pic/2018-06-15/5b231b477fed6.jpg","permissions":"3","state":"2","open_up":0,"pay":false,"title":"语言故事","content":"和女女好滴好"},{"video_id":"3","name":"vxc","pic":"https://www.hzggedu.com/ggxy/Uploads/1.jpg","permissions":"2","state":"1","open_up":1,"pay":false,"title":"语言故事","content":"和女女好滴好"},{"video_id":"1","name":"哈哈哈","pic":"https://www.hzggedu.com/ggxy/Uploads/1.jpg","permissions":"1","state":"1","open_up":1,"pay":false,"title":"语言故事","content":"和女女好滴好"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * video_id : 5
         * name : 111111111
         * pic : https://www.hzggedu.com/ggxy/Uploads/chinese_pinyin/pic/2018-06-15/5b231b477fed6.jpg
         * permissions : 3
         * state : 2
         * open_up : 0
         * pay : false
         * title : 语言故事
         * content : 和女女好滴好
         */

        private String video_id;
        private String name;
        private String pic;
        private String permissions;
        private String state;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;
        private String points;

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPermissions() {
            return permissions;
        }

        public void setPermissions(String permissions) {
            this.permissions = permissions;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getOpen_up() {
            return open_up;
        }

        public void setOpen_up(int open_up) {
            this.open_up = open_up;
        }

        public boolean isPay() {
            return pay;
        }

        public void setPay(boolean pay) {
            this.pay = pay;
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
