package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class ChengYUTiMuData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"question_id":"10","question_type":"0","question":"","question_content":["","","",""],"question_content1":["就","是","那","地","方","你","是","发","上"],"answer":["天","天","向","上"],"desc":"","type":"31"},{"question_id":"9","question_type":"0","question":"","question_content":["天","天","",""],"question_content1":["就","是","那","地","方","你","是","发","上"],"answer":["天","天","向","上"],"desc":"","type":"31"},{"question_id":"7","question_type":"0","question":"","question_content":["天","","",""],"question_content1":["就","是","那","地","方","你","是","发","上"],"answer":["天","天","向","上"],"desc":"","type":"31"},{"question_id":"2","question_type":"0","question":"","question_content":["天","天","向",""],"question_content1":["就","是","那","地","方","你","是","发","上"],"answer":["天","天","向","上"],"desc":"","type":"31"},{"question_id":"1","question_type":"0","question":"","question_content":["待","字","","中"],"question_content1":["你","能","收","到","甲","方","你","是","否","看","得","开","看","闺","中"],"answer":["待","字","闺","中"],"desc":"","type":"31"}]
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
         * question_id : 10
         * question_type : 0
         * question :
         * question_content : ["","","",""]
         * question_content1 : ["就","是","那","地","方","你","是","发","上"]
         * answer : ["天","天","向","上"]
         * desc :
         * type : 31
         */

        private String question_id;
        private String question_type;
        private String question;
        private String desc;
        private String type;
        private List<String> question_content;
        private List<String> question_content1;
        private List<String> answer;

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getQuestion_type() {
            return question_type;
        }

        public void setQuestion_type(String question_type) {
            this.question_type = question_type;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getQuestion_content() {
            return question_content;
        }

        public void setQuestion_content(List<String> question_content) {
            this.question_content = question_content;
        }

        public List<String> getQuestion_content1() {
            return question_content1;
        }

        public void setQuestion_content1(List<String> question_content1) {
            this.question_content1 = question_content1;
        }

        public List<String> getAnswer() {
            return answer;
        }

        public void setAnswer(List<String> answer) {
            this.answer = answer;
        }
    }
}
