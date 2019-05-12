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
 * Created by 水东流 on 2018/8/15.
 */

public class HuiBenListData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"name":"0","number_tin":"0","number_lu":"0"},"book":[{"book_id":"1","name":"Number","pic":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg","click_count":"0","permissions":"1","points":"0","open_up":1,"pay":false,"title":"","content":""},{"book_id":"2","name":"Ball","pic":"https://www.hzggedu.com/ggxy/Uploads/math_animation/pic/2018-06-https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg","click_count":"0","permissions":"1","points":"0","open_up":1,"pay":false,"title":"","content":""}]}
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
         * info : {"name":"0","number_tin":"0","number_lu":"0"}
         * book : [{"book_id":"1","name":"Number","pic":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg","click_count":"0","permissions":"1","points":"0","open_up":1,"pay":false,"title":"","content":""},{"book_id":"2","name":"Ball","pic":"https://www.hzggedu.com/ggxy/Uploads/math_animation/pic/2018-06-https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg","click_count":"0","permissions":"1","points":"0","open_up":1,"pay":false,"title":"","content":""}]
         */

        private InfoBean info;
        private List<BookBean> book;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<BookBean> getBook() {
            return book;
        }

        public void setBook(List<BookBean> book) {
            this.book = book;
        }

        public static class InfoBean {
            /**
             * name : 0
             * number_tin : 0
             * number_lu : 0
             */

            private String name;
            private String number_tin;
            private String number_lu;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNumber_tin() {
                return number_tin;
            }

            public void setNumber_tin(String number_tin) {
                this.number_tin = number_tin;
            }

            public String getNumber_lu() {
                return number_lu;
            }

            public void setNumber_lu(String number_lu) {
                this.number_lu = number_lu;
            }
        }

        public static class BookBean {
            /**
             * book_id : 1
             * name : Number
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-07-06/5b3f046643c76.jpg
             * click_count : 0
             * permissions : 1
             * points : 0
             * open_up : 1
             * pay : false
             * title :
             * content :
             */

            private String book_id;
            private String name;
            private String pic;
            private String click_count;
            private String permissions;
            private String points;
            private int open_up;
            private boolean pay;
            private String title;
            private String content;

            public String getBook_id() {
                return book_id;
            }

            public void setBook_id(String book_id) {
                this.book_id = book_id;
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

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
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
}
