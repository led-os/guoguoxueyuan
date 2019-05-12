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
public class MyHuiBenListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":[{"my_id":"26","book_id":"35","pic":"http://cdn1.guoguoxueyuan.com/Uploads/child_book/pic/2018-12-04/5c05e516e2444.jpg","name":"有内容1","status":"1","click_count":"4","thumb_up_count":"0","time":"2018-12-05 19:34:30","version_number":"1.1","zip_path":"http://cdn1.guoguoxueyuan.com/Uploads/child_book/book_35.zip","zip_name":"book_35.zip","path":"book_35"},{"my_id":"18","book_id":"31","pic":"http://cdn1.guoguoxueyuan.com/Uploads/child_book/book/Number.jpg","name":"Number1","status":"0","click_count":"0","thumb_up_count":"0","time":"2018-12-02 20:49:04","version_number":"1.0","zip_path":"http://cdn1.guoguoxueyuan.com/Uploads/child_book/book_31.zip","zip_name":"book_31.zip","path":"book_31"},{"my_id":"20","book_id":"30","pic":"http://cdn1.guoguoxueyuan.com/Uploads/child_book/book/9.jpg","name":"Activity","status":"0","click_count":"0","thumb_up_count":"0","time":"2018-12-04 16:38:06","version_number":"1.0","zip_path":"http://cdn1.guoguoxueyuan.com/Uploads/child_book/book_30.zip","zip_name":"book_30.zip","path":"book_30"}]}
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
        private List<InfoBean> info;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * my_id : 26
             * book_id : 35
             * pic : http://cdn1.guoguoxueyuan.com/Uploads/child_book/pic/2018-12-04/5c05e516e2444.jpg
             * name : 有内容1
             * status : 1
             * click_count : 4
             * thumb_up_count : 0
             * time : 2018-12-05 19:34:30
             * version_number : 1.1
             * zip_path : http://cdn1.guoguoxueyuan.com/Uploads/child_book/book_35.zip
             * zip_name : book_35.zip
             * path : book_35
             */

            private String my_id;
            private String book_id;
            private String pic;
            private String name;
            private String status;
            private String click_count;
            private String thumb_up_count;
            private String time;
            private String version_number;
            private String zip_path;
            private String zip_name;
            private String path;

            public String getMy_id() {
                return my_id;
            }

            public void setMy_id(String my_id) {
                this.my_id = my_id;
            }

            public String getBook_id() {
                return book_id;
            }

            public void setBook_id(String book_id) {
                this.book_id = book_id;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getThumb_up_count() {
                return thumb_up_count;
            }

            public void setThumb_up_count(String thumb_up_count) {
                this.thumb_up_count = thumb_up_count;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getVersion_number() {
                return version_number;
            }

            public void setVersion_number(String version_number) {
                this.version_number = version_number;
            }

            public String getZip_path() {
                return zip_path;
            }

            public void setZip_path(String zip_path) {
                this.zip_path = zip_path;
            }

            public String getZip_name() {
                return zip_name;
            }

            public void setZip_name(String zip_name) {
                this.zip_name = zip_name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }
}
