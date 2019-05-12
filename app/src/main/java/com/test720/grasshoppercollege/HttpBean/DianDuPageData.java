package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/23.
 */

public class DianDuPageData {

    /**
     * code : 1
     * msg : 成功
     * data : {"page":{"page":"1","voice_page":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/g5+renjiaoban+x+u1+1.mp3","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/g5+renjiaoban+x+u1+1.jpg","experience":"1","tourist":"1"},"content":[{"cn":"麦克","en":"Mike","zb":"86,171","gk":"164,157","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+1.mp3"},{"cn":"吴一凡","en":"WuYiFan","zb":"92,418","gk":"155,193","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+2.mp3"},{"cn":"萨拉","en":"Sarah","zb":"101,677","gk":"145,191","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+3.mp3"},{"cn":"佐娒","en":"zoom","zb":"109,945","gk":"111,79","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+4.mp3"},{"cn":"约翰","en":"John","zb":"289,177","gk":"175,157","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+5.mp3"},{"cn":"罗宾","en":"Robin","zb":"309,423","gk":"143,201","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+6.mp3"},{"cn":"奥列夫","en":"olover","zb":"309,677","gk":"159,183","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+7.mp3"},{"cn":"陈洁","en":"ChenJie","zb":"517,155","gk":"143,195","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+8.mp3"},{"cn":"艾米","en":"Amy","zb":"521,415","gk":"175,175","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+9.mp3"},{"cn":"张鹏","en":"ZhangPeng ","zb":"509,673","gk":"177,181","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+10.mp3"},{"cn":"兹普","en":"zip","zb":"473,1081","gk":"155,179","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+11.mp3"}]}
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
         * page : {"page":"1","voice_page":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/g5+renjiaoban+x+u1+1.mp3","pic":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/g5+renjiaoban+x+u1+1.jpg","experience":"1","tourist":"1"}
         * content : [{"cn":"麦克","en":"Mike","zb":"86,171","gk":"164,157","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+1.mp3"},{"cn":"吴一凡","en":"WuYiFan","zb":"92,418","gk":"155,193","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+2.mp3"},{"cn":"萨拉","en":"Sarah","zb":"101,677","gk":"145,191","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+3.mp3"},{"cn":"佐娒","en":"zoom","zb":"109,945","gk":"111,79","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+4.mp3"},{"cn":"约翰","en":"John","zb":"289,177","gk":"175,157","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+5.mp3"},{"cn":"罗宾","en":"Robin","zb":"309,423","gk":"143,201","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+6.mp3"},{"cn":"奥列夫","en":"olover","zb":"309,677","gk":"159,183","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+7.mp3"},{"cn":"陈洁","en":"ChenJie","zb":"517,155","gk":"143,195","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+8.mp3"},{"cn":"艾米","en":"Amy","zb":"521,415","gk":"175,175","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+9.mp3"},{"cn":"张鹏","en":"ZhangPeng ","zb":"509,673","gk":"177,181","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+10.mp3"},{"cn":"兹普","en":"zip","zb":"473,1081","gk":"155,179","voice":"https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+11.mp3"}]
         */

        private PageBean page;
        private List<ContentBean> content;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class PageBean {
            /**
             * page : 1
             * voice_page : https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/g5+renjiaoban+x+u1+1.mp3
             * pic : https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/g5+renjiaoban+x+u1+1.jpg
             * experience : 1
             * tourist : 1
             */

            private String page;
            private String voice_page;
            private String pic;
            private String experience;
            private String tourist;

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getVoice_page() {
                return voice_page;
            }

            public void setVoice_page(String voice_page) {
                this.voice_page = voice_page;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getExperience() {
                return experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }

            public String getTourist() {
                return tourist;
            }

            public void setTourist(String tourist) {
                this.tourist = tourist;
            }
        }

        public static class ContentBean {
            /**
             * cn : 麦克
             * en : Mike
             * zb : 86,171
             * gk : 164,157
             * voice : https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban+g5+x+en/Unit1/PartA/voice/g5+renjiaoban+x+u1+1+1.mp3
             */

            private String cn;
            private String en;
            private String zb;
            private String gk;
            private String voice;

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

            public String getZb() {
                return zb;
            }

            public void setZb(String zb) {
                this.zb = zb;
            }

            public String getGk() {
                return gk;
            }

            public void setGk(String gk) {
                this.gk = gk;
            }

            public String getVoice() {
                return voice;
            }

            public void setVoice(String voice) {
                this.voice = voice;
            }
        }
    }
}
