package com.test720.grasshoppercollege.module.youEr.baoBao.fragment;

import java.util.List;

/**
 * Created by 水东流 on 2018/8/1.
 */

public class LianXiData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":[{"con_id":"1","question":"下列哪一个是","content":"苹果","select_a":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aab6954.jpg","select_b":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aabbea0.jpg","select_c":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aac080d.jpg","answer":"A","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/fallingstar.mp3","select_a_b":"Uploads/child_baby/baby_1/select/2018-10-12/5bc037aab6954.jpg","select_b_b":"Uploads/child_baby/baby_1/select/2018-10-12/5bc037aabbea0.jpg","select_c_b":"Uploads/child_baby/baby_1/select/2018-10-12/5bc037aac080d.jpg","voice_b":"Uploads/child_baby/baby_1/select/fallingstar.mp3"},{"con_id":"2","question":"下列哪一个是","content":"香蕉","select_a":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/5bc03808c7298.jpg","select_b":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/5bc03808cc843.jpg","select_c":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/5bc03808d0983.jpg","answer":"B","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/g5+renjiaoban+x+u1+2.mp3","select_a_b":"Uploads/child_baby/baby_1/select/5bc03808c7298.jpg","select_b_b":"Uploads/child_baby/baby_1/select/5bc03808cc843.jpg","select_c_b":"Uploads/child_baby/baby_1/select/5bc03808d0983.jpg","voice_b":"Uploads/child_baby/baby_1/select/g5+renjiaoban+x+u1+2.mp3"}],"zip_path":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1.zip","zip_name":"baby_1.zip"}
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
         * info : [{"con_id":"1","question":"下列哪一个是","content":"苹果","select_a":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aab6954.jpg","select_b":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aabbea0.jpg","select_c":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aac080d.jpg","answer":"A","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/fallingstar.mp3","select_a_b":"Uploads/child_baby/baby_1/select/2018-10-12/5bc037aab6954.jpg","select_b_b":"Uploads/child_baby/baby_1/select/2018-10-12/5bc037aabbea0.jpg","select_c_b":"Uploads/child_baby/baby_1/select/2018-10-12/5bc037aac080d.jpg","voice_b":"Uploads/child_baby/baby_1/select/fallingstar.mp3"},{"con_id":"2","question":"下列哪一个是","content":"香蕉","select_a":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/5bc03808c7298.jpg","select_b":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/5bc03808cc843.jpg","select_c":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/5bc03808d0983.jpg","answer":"B","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/g5+renjiaoban+x+u1+2.mp3","select_a_b":"Uploads/child_baby/baby_1/select/5bc03808c7298.jpg","select_b_b":"Uploads/child_baby/baby_1/select/5bc03808cc843.jpg","select_c_b":"Uploads/child_baby/baby_1/select/5bc03808d0983.jpg","voice_b":"Uploads/child_baby/baby_1/select/g5+renjiaoban+x+u1+2.mp3"}]
         * zip_path : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1.zip
         * zip_name : baby_1.zip
         */

        private String zip_path;
        private String version_number;
        private String zip_name;
        private String name;
        private String path;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion_number() {
            return version_number;
        }

        public void setVersion_number(String version_number) {
            this.version_number = version_number;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        private List<InfoBean> info;

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

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * con_id : 1
             * question : 下列哪一个是
             * content : 苹果
             * select_a : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aab6954.jpg
             * select_b : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aabbea0.jpg
             * select_c : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/2018-10-12/5bc037aac080d.jpg
             * answer : A
             * voice : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/select/fallingstar.mp3
             * select_a_b : Uploads/child_baby/baby_1/select/2018-10-12/5bc037aab6954.jpg
             * select_b_b : Uploads/child_baby/baby_1/select/2018-10-12/5bc037aabbea0.jpg
             * select_c_b : Uploads/child_baby/baby_1/select/2018-10-12/5bc037aac080d.jpg
             * voice_b : Uploads/child_baby/baby_1/select/fallingstar.mp3
             */

            private String con_id;
            private String question;
            private String content;
            private String select_a;
            private String select_b;
            private String select_c;
            private String answer;
            private String voice;
            private String select_a_b;
            private String select_b_b;
            private String select_c_b;
            private String voice_b;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getVoice() {
                return voice;
            }

            public void setVoice(String voice) {
                this.voice = voice;
            }

            public String getSelect_a_b() {
                return select_a_b;
            }

            public void setSelect_a_b(String select_a_b) {
                this.select_a_b = select_a_b;
            }

            public String getSelect_b_b() {
                return select_b_b;
            }

            public void setSelect_b_b(String select_b_b) {
                this.select_b_b = select_b_b;
            }

            public String getSelect_c_b() {
                return select_c_b;
            }

            public void setSelect_c_b(String select_c_b) {
                this.select_c_b = select_c_b;
            }

            public String getVoice_b() {
                return voice_b;
            }

            public void setVoice_b(String voice_b) {
                this.voice_b = voice_b;
            }
        }
    }
}
