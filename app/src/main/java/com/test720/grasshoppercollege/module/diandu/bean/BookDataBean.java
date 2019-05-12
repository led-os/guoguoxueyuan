package com.test720.grasshoppercollege.module.diandu.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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
 * 作者：水东流 编于 2018/9/28
 */
public class BookDataBean implements Serializable {
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


        private BookBean book;
        private List<ContentBeanX> content;

        public BookBean getBook() {
            return book;
        }

        public void setBook(BookBean book) {
            this.book = book;
        }

        public List<ContentBeanX> getContent() {
            return content;
        }

        public void setContent(List<ContentBeanX> content) {
            this.content = content;
        }

        public static class BookBean {


            private String book_id;
            private String book_version;
            private String version_number;
            @SerializedName("class")
            private String classX;
            private String status;
            private String pic;
            private String pic_loca;
            private String alias;
            private String zip_path;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
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

            public String getPic_loca() {
                return pic_loca;
            }

            public void setPic_loca(String pic_loca) {
                this.pic_loca = pic_loca;
            }
        }

        public static class ContentBeanX {


            private String unit;
            private List<LessonBean> lesson;

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public List<LessonBean> getLesson() {
                return lesson;
            }

            public void setLesson(List<LessonBean> lesson) {
                this.lesson = lesson;
            }

            public static class LessonBean {
                /**
                 * lesson : Again
                 * page : [{"page":"14","voice_page":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.mp3","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.jpg","experience":"1","tourist":"1","voice_page_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.mp3","pic_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.jpg","content":[{"cn":"听，写和匹配。","en":"Listen,write and match.","zb":"81,863","gk":"627,367","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+5.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+5.mp3"},{"cn":"听录音，圈出四。","en":"Listen and circle four.","zb":"77,647","gk":"637,121","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+4.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+4.mp3"},{"cn":"听录音，圈出三。","en":"Listen and circle three.","zb":"79,517","gk":"639,113","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+3.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+3.mp3"},{"cn":"听并圈出两个。","en":"Listen and circle two.","zb":"77,389","gk":"639,115","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+2.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+2.mp3"},{"cn":"听一听，圈出一个。","en":"Listen and circle one.","zb":"65,259","gk":"637,117","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+1.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+1.mp3"}]},{"page":"15","voice_page":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+15.mp3","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+15.jpg","experience":"1","tourist":"1","voice_page_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+15.mp3","pic_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+15.jpg","content":[{"cn":"我们喜欢","en":"We like to","zb":"27,1229","gk":"141,35","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+16.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+16.mp3"},{"cn":"我的朋友喜欢","en":"My friend likes to ","zb":"31,1183","gk":"169,31","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+15.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+15.mp3"},{"cn":"我喜欢","en":"I like to","zb":"21,1133","gk":"82,42","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+14.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+14.mp3"},{"cn":"在电脑上玩","en":"play on the computer","zb":"371,1017","gk":"125,61","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+13.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+13.mp3"},{"cn":"看一场电影","en":"watch a film","zb":"375,947","gk":"81,57","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+12.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+12.mp3"},{"cn":"看电视","en":"watch TV","zb":"229,1016","gk":"109,45","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+11.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+11.mp3"},{"cn":"种植蔬菜","en":"plant vegetables","zb":"51,1011","gk":"123,65","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+9.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+9.mp3"},{"cn":"去钓鱼","en":"go fishing","zb":"221,947","gk":"111,35","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+10.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+10.mp3"},{"cn":"打篮球","en":"play basketball","zb":"49,935","gk":"125,59","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+8.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+8.mp3"},{"cn":"新的单词是什么？是兄弟姐妹","en":"what is the new word? It's cousin","zb":"25,659","gk":"527,105","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+6.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+6.mp3"},{"cn":"万蓉是我的阿姨","en":"Wanrong is my aunt.","zb":"395,605","gk":"229,49","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+7.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+7.mp3"},{"cn":"我的祖父有一头白发","en":"my grandfather haw white hair.","zb":"31,593","gk":"337,45","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+5.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+5.mp3"},{"cn":"Lynn是Jenny的姐妹","en":"Lynn is Jenny's sister.","zb":"397,441","gk":"259,49","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+4.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+4.mp3"},{"cn":"大超是我的叔叔","en":"Dachao is my uncle.","zb":"27,441","gk":"263,49","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+3.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+3.mp3"},{"cn":"Bob是Jenny的兄弟","en":"Bob is Jenny's brother.","zb":"403,283","gk":"265,43","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+2.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+2.mp3"},{"cn":"我的爸爸有一头黑发","en":"my father has black hair.","zb":"35,275","gk":"293,45","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+1.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+15+1.mp3"}]},{"page":"16","voice_page":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+16.mp3","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+16.jpg","experience":"1","tourist":"1","voice_page_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+16.mp3","pic_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+16.jpg","content":[{"cn":"看和写","en":"Look and write","zb":"63,93","gk":"611,699","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+16+1.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+16+1.mp3"},{"cn":"阅读并遵循说明。","en":"Read and follow the directions.","zb":"77,899","gk":"651,347","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+16+2.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+16+2.mp3"}]},{"page":"17","voice_page":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+17.mp3","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+17.jpg","experience":"1","tourist":"1","voice_page_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+17.mp3","pic_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+17.jpg","content":[{"cn":"做苏珊的家谱。","en":"Make Susan's family tree.","zb":"41,469","gk":"589,613","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+5.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+5.mp3"},{"cn":"哪个是杰夫？","en":"Who is Jeff?","zb":"49,357","gk":"531,68","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+4.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+4.mp3"},{"cn":"乔长得什么样？","en":"What does Joe look like?","zb":"39,268","gk":"534,82","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+3.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+3.mp3"},{"cn":"谁在农场工作？","en":"Who works on a farm?","zb":"43,190","gk":"527,67","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+2.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+2.mp3"},{"cn":"苏珊的父亲是干什么的？","en":"What does Susan's father do?","zb":"44,110","gk":"492,70","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+1.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+17+1.mp3"}]},{"page":"18","voice_page":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+18.mp3","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+18.jpg","experience":"1","tourist":"1","voice_page_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+18.mp3","pic_loca":"jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+18.jpg","content":[{"cn":"这是我的家，我奶奶六十六岁了。她有白色的头发和黑色的眼睛。她喜欢种花。","en":"This is my family.My grandmother is sixty-six years old.She has white hair and black eyes.She likes to plant flowers.","zb":"83,147","gk":"341,177","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+18+1.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+18+1.mp3"}]}]
                 */

                private String lesson;
                private List<PageBean> page;

                public String getLesson() {
                    return lesson;
                }

                public void setLesson(String lesson) {
                    this.lesson = lesson;
                }

                public List<PageBean> getPage() {
                    return page;
                }

                public void setPage(List<PageBean> page) {
                    this.page = page;
                }

                public static class PageBean {
                    /**
                     * page : 14
                     * voice_page : http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.mp3
                     * pic : http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.jpg
                     * experience : 1
                     * tourist : 1
                     * voice_page_loca : jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.mp3
                     * pic_loca : jijiaoban+g5+s+en/Unit1/Again/g5+jijiaoban+s+u1+14.jpg
                     * content : [{"cn":"听，写和匹配。","en":"Listen,write and match.","zb":"81,863","gk":"627,367","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+5.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+5.mp3"},{"cn":"听录音，圈出四。","en":"Listen and circle four.","zb":"77,647","gk":"637,121","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+4.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+4.mp3"},{"cn":"听录音，圈出三。","en":"Listen and circle three.","zb":"79,517","gk":"639,113","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+3.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+3.mp3"},{"cn":"听并圈出两个。","en":"Listen and circle two.","zb":"77,389","gk":"639,115","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+2.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+2.mp3"},{"cn":"听一听，圈出一个。","en":"Listen and circle one.","zb":"65,259","gk":"637,117","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+1.mp3","voice_loca":"jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+1.mp3"}]
                     */

                    private String page;
                    private String voice_page;
                    private String pic;
                    private String experience;
                    private String tourist;
                    private String voice_page_loca;
                    private String pic_loca;
                    private List<ContentBean> content;

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

                    public String getVoice_page_loca() {
                        return voice_page_loca;
                    }

                    public void setVoice_page_loca(String voice_page_loca) {
                        this.voice_page_loca = voice_page_loca;
                    }

                    public String getPic_loca() {
                        return pic_loca;
                    }

                    public void setPic_loca(String pic_loca) {
                        this.pic_loca = pic_loca;
                    }

                    public List<ContentBean> getContent() {
                        return content;
                    }

                    public void setContent(List<ContentBean> content) {
                        this.content = content;
                    }

                    public static class ContentBean {
                        /**
                         * cn : 听，写和匹配。
                         * en : Listen,write and match.
                         * zb : 81,863
                         * gk : 627,367
                         * voice : http://www.guoguoxueyuan.com/ggxy/Uploads/reading/jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+5.mp3
                         * voice_loca : jijiaoban+g5+s+en/Unit1/Again/voice/g5+jijiaoban+s+u1+14+5.mp3
                         */

                        private String cn;
                        private String en;
                        private String zb;
                        private String gk;
                        private String voice;
                        private String voice_loca;

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

                        public String getVoice_loca() {
                            return voice_loca;
                        }

                        public void setVoice_loca(String voice_loca) {
                            this.voice_loca = voice_loca;
                        }
                    }
                }
            }
        }
    }
}
