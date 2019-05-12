package com.test720.grasshoppercollege.module.tanXian.bean;

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
 * 作者：水东流 编于 2018/11/10
 */
public class TanXianTiMuBean {


    /**
     * code : 1
     * msg : 成功
     * data : [{"question_id":"28","type":"14","foot":"4","to_fill":[""],"question_type":"1","question":"","question_content_type":"1","question_content":"","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/adventure/question/Christmas in my heart-Sarah Connor.mp3","subscript":[""],"answer":[""],"desc":"","select_a":"","select_b":"","select_c":"","select_d":"","foot_list":[{"question_id":"12","question":"发射点发给发士大夫士大夫的撒","select_a":"1","select_b":"2","select_c":"","select_d":"","answer":"A","desc":"暗室逢灯阿三地方"},{"question_id":"13","question":"g's'd'g","select_a":"1","select_b":"2","select_c":"3","select_d":"4","answer":"A","desc":"根深蒂固撒地方"},{"question_id":"11","question":"阿三发射点发","select_a":"1","select_b":"2","select_c":"","select_d":"","answer":"A","desc":"阿三地方"},{"question_id":"14","question":"复古风德国法国","select_a":"1","select_b":"2","select_c":"","select_d":"","answer":"A","desc":"asdf "}],"question_content_select":[]}]
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
         * question_id : 28
         * type : 14
         * foot : 4
         * to_fill : [""]
         * question_type : 1
         * question :
         * question_content_type : 1
         * question_content :
         * voice : http://www.guoguoxueyuan.com/ggxy/Uploads/adventure/question/Christmas in my heart-Sarah Connor.mp3
         * subscript : [""]
         * answer : [""]
         * desc :
         * select_a :
         * select_b :
         * select_c :
         * select_d :
         * foot_list : [{"question_id":"12","question":"发射点发给发士大夫士大夫的撒","select_a":"1","select_b":"2","select_c":"","select_d":"","answer":"A","desc":"暗室逢灯阿三地方"},{"question_id":"13","question":"g's'd'g","select_a":"1","select_b":"2","select_c":"3","select_d":"4","answer":"A","desc":"根深蒂固撒地方"},{"question_id":"11","question":"阿三发射点发","select_a":"1","select_b":"2","select_c":"","select_d":"","answer":"A","desc":"阿三地方"},{"question_id":"14","question":"复古风德国法国","select_a":"1","select_b":"2","select_c":"","select_d":"","answer":"A","desc":"asdf "}]
         * question_content_select : []
         */

        private String question_id;
        private String type;
        private String foot;
        private String question_type;
        private String question;
        private String question_content_type;
        private String question_content;
        private String voice;
        private String desc;
        private String select_a;
        private String select_b;
        private String select_c;
        private String select_d;
        private List<String> to_fill;
        private List<String> subscript;
        private List<String> answer;
        private List<FootListBean> foot_list;
        private List<String> question_content_select;

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFoot() {
            return foot;
        }

        public void setFoot(String foot) {
            this.foot = foot;
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

        public String getVoice() {
            return voice;
        }

        public void setVoice(String voice) {
            this.voice = voice;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSelect_a() {
            return select_a;
        }

        public void setSelect_a(String select_a) {
            this.select_a = select_a;
        }

        public String getSelect_b() {
            return select_b;
        }

        public void setSelect_b(String select_b) {
            this.select_b = select_b;
        }

        public String getSelect_c() {
            return select_c;
        }

        public void setSelect_c(String select_c) {
            this.select_c = select_c;
        }

        public String getSelect_d() {
            return select_d;
        }

        public void setSelect_d(String select_d) {
            this.select_d = select_d;
        }

        public List<String> getTo_fill() {
            return to_fill;
        }

        public void setTo_fill(List<String> to_fill) {
            this.to_fill = to_fill;
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

        public List<FootListBean> getFoot_list() {
            return foot_list;
        }

        public void setFoot_list(List<FootListBean> foot_list) {
            this.foot_list = foot_list;
        }

        public List<String> getQuestion_content_select() {
            return question_content_select;
        }

        public void setQuestion_content_select(List<String> question_content_select) {
            this.question_content_select = question_content_select;
        }

        public static class FootListBean {
            /**
             * question_id : 12
             * question : 发射点发给发士大夫士大夫的撒
             * select_a : 1
             * select_b : 2
             * select_c :
             * select_d :
             * answer : A
             * desc : 暗室逢灯阿三地方
             */

            private String question_id;
            private String question;
            private String select_a;
            private String select_b;
            private String select_c;
            private String select_d;
            private String answer;
            private String desc;

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

            public String getSelect_a() {
                return select_a;
            }

            public void setSelect_a(String select_a) {
                this.select_a = select_a;
            }

            public String getSelect_b() {
                return select_b;
            }

            public void setSelect_b(String select_b) {
                this.select_b = select_b;
            }

            public String getSelect_c() {
                return select_c;
            }

            public void setSelect_c(String select_c) {
                this.select_c = select_c;
            }

            public String getSelect_d() {
                return select_d;
            }

            public void setSelect_d(String select_d) {
                this.select_d = select_d;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }
    }
}
