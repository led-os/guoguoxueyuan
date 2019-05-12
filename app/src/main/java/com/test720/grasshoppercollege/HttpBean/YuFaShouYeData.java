package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/24.
 */

public class YuFaShouYeData {


    /**
     * code : 1
     * msg : 成功
     * data : [[{"book_id":"1","name":"名词","pic":"","sort":"1"},{"book_id":"2","name":"动词","pic":"","sort":"5"}],[{"book_id":"3","name":"形容词","pic":"","sort":"1"},{"book_id":"4","name":"动词","pic":"","sort":"5"}]]
     */

    private int code;
    private String msg;   private List<List<DataBean>> data;


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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * book_id : 1
         * name : 名词
         * pic :
         * sort : 1
         */

        private String book_id;
        private String name;
        private String pic;
        private String sort;
        private String background;

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
