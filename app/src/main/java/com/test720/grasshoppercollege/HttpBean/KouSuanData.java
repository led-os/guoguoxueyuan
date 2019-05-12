package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/2.
 */

public class KouSuanData {

    /**
     * code : 1
     * msg : 成功
     * data : {"time":"50","count":"20","list":[{"question_id":"8","question":"","question_content_type":"1","question_content":"10 + 40 = ","subscript":[""],"answer":["50"],"desc":"","corr_id":"0","status":"0"},{"question_id":"9","question":"","question_content_type":"1","question_content":"10 + 50 = ","subscript":[""],"answer":["60"],"desc":"","corr_id":"0","status":"0"},{"question_id":"10","question":"","question_content_type":"1","question_content":"10 + 60 = ","subscript":[""],"answer":["70"],"desc":"","corr_id":"0","status":"0"},{"question_id":"11","question":"","question_content_type":"2","question_content":"https://www.hzggedu.com/ggxydemo/Uploads/math_mental_arithmetic/question/01.png","subscript":[""],"answer":["1","6"],"desc":"","corr_id":"0","status":"0"},{"question_id":"12","question":"","question_content_type":"2","question_content":"https://www.hzggedu.com/ggxydemo/Uploads/math_mental_arithmetic/question/02.png","subscript":[""],"answer":["5"],"desc":"","corr_id":"0","status":"0"},{"question_id":"13","question":"","question_content_type":"1","question_content":"10 + 60 + 50 = ","subscript":[""],"answer":["70"],"desc":"","corr_id":"0","status":"0"}]}
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
         * time : 50
         * count : 20
         * list : [{"question_id":"8","question":"","question_content_type":"1","question_content":"10 + 40 = ","subscript":[""],"answer":["50"],"desc":"","corr_id":"0","status":"0"},{"question_id":"9","question":"","question_content_type":"1","question_content":"10 + 50 = ","subscript":[""],"answer":["60"],"desc":"","corr_id":"0","status":"0"},{"question_id":"10","question":"","question_content_type":"1","question_content":"10 + 60 = ","subscript":[""],"answer":["70"],"desc":"","corr_id":"0","status":"0"},{"question_id":"11","question":"","question_content_type":"2","question_content":"https://www.hzggedu.com/ggxydemo/Uploads/math_mental_arithmetic/question/01.png","subscript":[""],"answer":["1","6"],"desc":"","corr_id":"0","status":"0"},{"question_id":"12","question":"","question_content_type":"2","question_content":"https://www.hzggedu.com/ggxydemo/Uploads/math_mental_arithmetic/question/02.png","subscript":[""],"answer":["5"],"desc":"","corr_id":"0","status":"0"},{"question_id":"13","question":"","question_content_type":"1","question_content":"10 + 60 + 50 = ","subscript":[""],"answer":["70"],"desc":"","corr_id":"0","status":"0"}]
         */

        private String time;
        private String count;
        private List<ListBean> list;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * question_id : 8
             * question :
             * question_content_type : 1
             * question_content : 10 + 40 =
             * subscript : [""]
             * answer : ["50"]
             * desc :
             * corr_id : 0
             * status : 0
             */

            private String question_id;
            private String question;
            private String question_content_type;
            private String question_content;
            private String desc;
            private String corr_id;
            private String status;
            private List<String> subscript;
            private List<String> answer;

            public String getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(String question_id) {
                this.question_id = question_id;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getQuestion_content_type() {
                return question_content_type;
            }

            public void setQuestion_content_type(String question_content_type) {
                this.question_content_type = question_content_type;
            }

            public String getQuestion_content() {
                return question_content;
            }

            public void setQuestion_content(String question_content) {
                this.question_content = question_content;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getCorr_id() {
                return corr_id;
            }

            public void setCorr_id(String corr_id) {
                this.corr_id = corr_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<String> getSubscript() {
                return subscript;
            }

            public void setSubscript(List<String> subscript) {
                this.subscript = subscript;
            }

            public List<String> getAnswer() {
                return answer;
            }

            public void setAnswer(List<String> answer) {
                this.answer = answer;
            }
        }
    }
}
