package com.test720.grasshoppercollege.HttpBean;

import com.test720.grasshoppercollege.bean.BookListItemData;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/16.
 */

public class KeWenBookListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"book_version":"人教版","info":[{"book_id":"2","book_version":"人教版","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0},{"book_id":"1","book_version":"人教版","class":"三年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0}]},{"book_version":"新起点","info":[{"book_id":"16","book_version":"新起点","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0},{"book_id":"15","book_version":"新起点","class":"三年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0}]}],"experience_time":"10","points":"5"}
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
         * list : [{"book_version":"人教版","info":[{"book_id":"2","book_version":"人教版","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0},{"book_id":"1","book_version":"人教版","class":"三年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0}]},{"book_version":"新起点","info":[{"book_id":"16","book_version":"新起点","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0},{"book_id":"15","book_version":"新起点","class":"三年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0}]}]
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
             * book_version : 人教版
             * info : [{"book_id":"2","book_version":"人教版","class":"三年级","book_type":"英语","status":"上册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0},{"book_id":"1","book_version":"人教版","class":"三年级","book_type":"英语","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg","is_pay":0,"is_reading":0,"is_add":0}]
             */

            private String book_version;
            private List<BookListItemData> info;

            public String getBook_version() {
                return book_version;
            }

            public void setBook_version(String book_version) {
                this.book_version = book_version;
            }

            public List<BookListItemData> getInfo() {
                return info;
            }

            public void setInfo(List<BookListItemData> info) {
                this.info = info;
            }


        }
    }
}
