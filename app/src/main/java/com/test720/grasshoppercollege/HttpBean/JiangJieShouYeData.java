package com.test720.grasshoppercollege.HttpBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lenovo on 2018/4/3.
 */

public class JiangJieShouYeData {


    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"book_id":"1","book_version":"人教版","class":"三年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"2","book_version":"人教版","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"3","book_version":"冀教版","class":"一年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"4","book_version":"冀教版","class":"一年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"5","book_version":"人教版","class":"四年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"6","book_version":"人教版","class":"四年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"7","book_version":"北师大版","class":"五年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"8","book_version":"北师大版","class":"五年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"9","book_version":"新起点","class":"六年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"16","book_version":"新起点","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0}],"experience_time":"10","points":"5"}
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
         * list : [{"book_id":"1","book_version":"人教版","class":"三年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"2","book_version":"人教版","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"3","book_version":"冀教版","class":"一年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"4","book_version":"冀教版","class":"一年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"5","book_version":"人教版","class":"四年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"6","book_version":"人教版","class":"四年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"7","book_version":"北师大版","class":"五年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"8","book_version":"北师大版","class":"五年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"9","book_version":"新起点","class":"六年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0},{"book_id":"16","book_version":"新起点","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0}]
         * experience_time : 10
         * points : 5
         */

        private String experience_time;
        private String points;
        private List<ListBean> list;

        public String getExperience_time() {
            return experience_time;
        }

        public void setExperience_time(String experience_time) {
            this.experience_time = experience_time;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * book_id : 1
             * book_version : 人教版
             * class : 三年级
             * book_type : 英语
             * status : 下册
             * pic : https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg
             * is_pay : 0
             */

            private String book_id;
            private String book_version;
            @SerializedName("class")
            private String classX;
            private String book_type;
            private String status;
            private String pic;
            private int is_pay;

            public String getBook_id() {
                return book_id;
            }

            public void setBook_id(String book_id) {
                this.book_id = book_id;
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

            public String getBook_type() {
                return book_type;
            }

            public void setBook_type(String book_type) {
                this.book_type = book_type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getIs_pay() {
                return is_pay;
            }

            public void setIs_pay(int is_pay) {
                this.is_pay = is_pay;
            }
        }
    }
}
