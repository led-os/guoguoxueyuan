package com.test720.grasshoppercollege.module.youEr.baoBao.fragment;

import java.util.List;

/**
 * Created by 水东流 on 2018/8/1.
 */

public class YueDuData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":[{"con_id":"1","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf120ba14f8.jpg","cn":"苹果","en":"apple","cn_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf11a130a8b.mp3","en_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf11a134b1f.mp3","background_color":"#222222","pic_b":"Uploads/child_baby/baby_1/voice/apple/5bbf120ba14f8.jpg","cn_voice_b":"Uploads/child_baby/baby_1/voice/apple/5bbf11a130a8b.mp3","en_voice_b":"Uploads/child_baby/baby_1/voice/apple/5bbf11a134b1f.mp3"},{"con_id":"2","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/banana/5bbf1229b22cc.jpg","cn":"香蕉","en":"banana","cn_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/banana/5bbf11e44a735.mp3","en_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/banana/5bbf11e44e081.mp3","background_color":"#123456","pic_b":"Uploads/child_baby/baby_1/voice/banana/5bbf1229b22cc.jpg","cn_voice_b":"Uploads/child_baby/baby_1/voice/banana/5bbf11e44a735.mp3","en_voice_b":"Uploads/child_baby/baby_1/voice/banana/5bbf11e44e081.mp3"}],"zip_path":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1.zip","zip_name":"baby_1.zip","path":"Uploads/child_baby/baby_1"}
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
         * info : [{"con_id":"1","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf120ba14f8.jpg","cn":"苹果","en":"apple","cn_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf11a130a8b.mp3","en_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf11a134b1f.mp3","background_color":"#222222","pic_b":"Uploads/child_baby/baby_1/voice/apple/5bbf120ba14f8.jpg","cn_voice_b":"Uploads/child_baby/baby_1/voice/apple/5bbf11a130a8b.mp3","en_voice_b":"Uploads/child_baby/baby_1/voice/apple/5bbf11a134b1f.mp3"},{"con_id":"2","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/banana/5bbf1229b22cc.jpg","cn":"香蕉","en":"banana","cn_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/banana/5bbf11e44a735.mp3","en_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/banana/5bbf11e44e081.mp3","background_color":"#123456","pic_b":"Uploads/child_baby/baby_1/voice/banana/5bbf1229b22cc.jpg","cn_voice_b":"Uploads/child_baby/baby_1/voice/banana/5bbf11e44a735.mp3","en_voice_b":"Uploads/child_baby/baby_1/voice/banana/5bbf11e44e081.mp3"}]
         * zip_path : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1.zip
         * zip_name : baby_1.zip
         * path : Uploads/child_baby/baby_1
         */

        private String zip_path;
        private String version_number;
        private String zip_name;
        private String name;
        private String path;
        private List<InfoBean> info;

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

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * con_id : 1
             * pic : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf120ba14f8.jpg
             * cn : 苹果
             * en : apple
             * cn_voice : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf11a130a8b.mp3
             * en_voice : http://www.guoguoxueyuan.com/ggxy/Uploads/child_baby/baby_1/voice/apple/5bbf11a134b1f.mp3
             * background_color : #222222
             * pic_b : Uploads/child_baby/baby_1/voice/apple/5bbf120ba14f8.jpg
             * cn_voice_b : Uploads/child_baby/baby_1/voice/apple/5bbf11a130a8b.mp3
             * en_voice_b : Uploads/child_baby/baby_1/voice/apple/5bbf11a134b1f.mp3
             */

            private String con_id;
            private String pic;
            private String cn;
            private String en;
            private String cn_voice;
            private String en_voice;
            private String background_color;
            private String pic_b;
            private String cn_voice_b;
            private String en_voice_b;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getCn() {
                return cn;
            }

            public void setCn(String cn) {
                this.cn = cn;
            }

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getCn_voice() {
                return cn_voice;
            }

            public void setCn_voice(String cn_voice) {
                this.cn_voice = cn_voice;
            }

            public String getEn_voice() {
                return en_voice;
            }

            public void setEn_voice(String en_voice) {
                this.en_voice = en_voice;
            }

            public String getBackground_color() {
                return background_color;
            }

            public void setBackground_color(String background_color) {
                this.background_color = background_color;
            }

            public String getPic_b() {
                return pic_b;
            }

            public void setPic_b(String pic_b) {
                this.pic_b = pic_b;
            }

            public String getCn_voice_b() {
                return cn_voice_b;
            }

            public void setCn_voice_b(String cn_voice_b) {
                this.cn_voice_b = cn_voice_b;
            }

            public String getEn_voice_b() {
                return en_voice_b;
            }

            public void setEn_voice_b(String en_voice_b) {
                this.en_voice_b = en_voice_b;
            }
        }
    }
}
