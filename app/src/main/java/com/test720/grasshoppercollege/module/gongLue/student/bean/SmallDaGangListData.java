package com.test720.grasshoppercollege.module.gongLue.student.bean;

import java.util.List;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/9/6
 */
public class SmallDaGangListData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"micro_class_id":"1","permissions":"4","points":"0","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","state":"1","desc":"不是大部分是的","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","open_up":0,"pay":false,"title":"","content":""},{"micro_class_id":"2","permissions":"4","points":"0","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","state":"1","desc":"不是大部分是的","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","open_up":0,"pay":false,"title":"","content":""}]
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
         * micro_class_id : 1
         * permissions : 4
         * points : 0
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * name : 第一课 哈哈哈
         * state : 1
         * desc : 不是大部分是的
         * path : https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4
         * open_up : 0
         * pay : false
         * title :
         * content :
         */

        private String micro_class_id;
        private String permissions;
        private String points;
        private String pic;
        private String name;
        private String state;
        private String desc;
        private String path;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getMicro_class_id() {
            return micro_class_id;
        }

        public void setMicro_class_id(String micro_class_id) {
            this.micro_class_id = micro_class_id;
        }

        public String getPermissions() {
            return permissions;
        }

        public void setPermissions(String permissions) {
            this.permissions = permissions;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
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
