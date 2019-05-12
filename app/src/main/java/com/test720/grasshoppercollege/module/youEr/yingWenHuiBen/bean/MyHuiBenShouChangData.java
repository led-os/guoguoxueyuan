package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean;

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
 * 作者：水东流 编于 2018/8/27
 */
public class MyHuiBenShouChangData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"collection_id":"7","book_id":"1","status":"1","nickname":"虚空恶犬二千","pic":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg","name":"Number","permissions":"1","points":"0","open_up":1,"pay":false,"title":"","content":""},{"collection_id":"3","book_id":"1","status":"1","nickname":"","pic":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg","name":"Number","permissions":"1","points":"0","open_up":1,"pay":false,"title":"","content":""}]
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
         * collection_id : 7
         * book_id : 1
         * status : 1
         * nickname : 虚空恶犬二千
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg
         * name : Number
         * permissions : 1
         * points : 0
         * open_up : 1
         * pay : false
         * title :
         * content :
         */

        private String collection_id;
        private String book_id;
        private String status;
        private String nickname;
        private String pic;
        private String name;
        private String permissions;
        private String points;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getCollection_id() {
            return collection_id;
        }

        public void setCollection_id(String collection_id) {
            this.collection_id = collection_id;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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
