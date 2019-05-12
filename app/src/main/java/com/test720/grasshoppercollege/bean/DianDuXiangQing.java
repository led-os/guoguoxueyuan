package com.test720.grasshoppercollege.bean;

import java.util.List;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/1/18 0018.
 */

public class DianDuXiangQing {

    /**
     * code : 1
     * msg : 成功
     * data : {"unit":"Unit 3","list":[{"info":{"page_now":"page14","page_path":"Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.jpg","page_voice_path":"Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.mp3","content_name":"发给对方"},"list":[{"id":"5","count":"4","sort":"1","content_zb":"280,316","content_kg":"187,168","content_en":"Point to &quot;hello&quot;!Point to &quot;hi&quot;!Point to &quot;you&quot;!And point to &quot;I&quot;!","content_cn":"指向\u201c你好\u201d！指向\u201c嗨\u201d！指向\u201c你\u201d！指向\u201c我\u201d！","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+1.mp3"},{"id":"6","count":"4","sort":"2","content_zb":"108,680","content_kg":"146,89","content_en":"Good morning,boys and ","content_cn":"早上好，男孩和女孩。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+2.mp3"},{"id":"7","count":"4","sort":"3","content_zb":"247,773","content_kg":"155,85","content_en":"Good morning,Ms Smart.","content_cn":"早上好，斯玛特女士。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+3.mp3"},{"id":"8","count":"4","sort":"4","content_zb":"423,714","content_kg":"172,72","content_en":"Sit down,please.","content_cn":"请坐。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+4.mp3"}]}]}
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
         * unit : Unit 3
         * list : [{"info":{"page_now":"page14","page_path":"Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.jpg","page_voice_path":"Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.mp3","content_name":"发给对方"},"list":[{"id":"5","count":"4","sort":"1","content_zb":"280,316","content_kg":"187,168","content_en":"Point to &quot;hello&quot;!Point to &quot;hi&quot;!Point to &quot;you&quot;!And point to &quot;I&quot;!","content_cn":"指向\u201c你好\u201d！指向\u201c嗨\u201d！指向\u201c你\u201d！指向\u201c我\u201d！","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+1.mp3"},{"id":"6","count":"4","sort":"2","content_zb":"108,680","content_kg":"146,89","content_en":"Good morning,boys and ","content_cn":"早上好，男孩和女孩。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+2.mp3"},{"id":"7","count":"4","sort":"3","content_zb":"247,773","content_kg":"155,85","content_en":"Good morning,Ms Smart.","content_cn":"早上好，斯玛特女士。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+3.mp3"},{"id":"8","count":"4","sort":"4","content_zb":"423,714","content_kg":"172,72","content_en":"Sit down,please.","content_cn":"请坐。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+4.mp3"}]}]
         */

        private String unit;
        private List<ListBeanX> list;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * info : {"page_now":"page14","page_path":"Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.jpg","page_voice_path":"Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.mp3","content_name":"发给对方"}
             * list : [{"id":"5","count":"4","sort":"1","content_zb":"280,316","content_kg":"187,168","content_en":"Point to &quot;hello&quot;!Point to &quot;hi&quot;!Point to &quot;you&quot;!And point to &quot;I&quot;!","content_cn":"指向\u201c你好\u201d！指向\u201c嗨\u201d！指向\u201c你\u201d！指向\u201c我\u201d！","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+1.mp3"},{"id":"6","count":"4","sort":"2","content_zb":"108,680","content_kg":"146,89","content_en":"Good morning,boys and ","content_cn":"早上好，男孩和女孩。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+2.mp3"},{"id":"7","count":"4","sort":"3","content_zb":"247,773","content_kg":"155,85","content_en":"Good morning,Ms Smart.","content_cn":"早上好，斯玛特女士。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+3.mp3"},{"id":"8","count":"4","sort":"4","content_zb":"423,714","content_kg":"172,72","content_en":"Sit down,please.","content_cn":"请坐。","page_id":"10","voice_path":"Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+4.mp3"}]
             */

            private InfoBean info;
            private List<ListBean> list;

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class InfoBean {
                /**
                 * page_now : page14
                 * page_path : Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.jpg
                 * page_voice_path : Uploads/reading1/g1+renjiaoban+s/pic-voice/u3/g1+renjiaoban+s+u3+14.mp3
                 * content_name : 发给对方
                 */

                private String page_now;
                private String page_path;
                private String page_voice_path;
                private String content_name;

                public String getPage_now() {
                    return page_now;
                }

                public void setPage_now(String page_now) {
                    this.page_now = page_now;
                }

                public String getPage_path() {
                    return page_path;
                }

                public void setPage_path(String page_path) {
                    this.page_path = page_path;
                }

                public String getPage_voice_path() {
                    return page_voice_path;
                }

                public void setPage_voice_path(String page_voice_path) {
                    this.page_voice_path = page_voice_path;
                }

                public String getContent_name() {
                    return content_name;
                }

                public void setContent_name(String content_name) {
                    this.content_name = content_name;
                }
            }

            public static class ListBean {
                /**
                 * id : 5
                 * count : 4
                 * sort : 1
                 * content_zb : 280,316
                 * content_kg : 187,168
                 * content_en : Point to &quot;hello&quot;!Point to &quot;hi&quot;!Point to &quot;you&quot;!And point to &quot;I&quot;!
                 * content_cn : 指向“你好”！指向“嗨”！指向“你”！指向“我”！
                 * page_id : 10
                 * voice_path : Uploads/reading1/g3+waiyanshe+s/voice/u3/g3+waiyanshe+s+u3+14+1.mp3
                 */

                private String id;
                private String count;
                private String sort;
                private String content_zb;
                private String content_kg;
                private String content_en;
                private String content_cn;
                private String page_id;
                private String voice_path;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getContent_zb() {
                    return content_zb;
                }

                public void setContent_zb(String content_zb) {
                    this.content_zb = content_zb;
                }

                public String getContent_kg() {
                    return content_kg;
                }

                public void setContent_kg(String content_kg) {
                    this.content_kg = content_kg;
                }

                public String getContent_en() {
                    return content_en;
                }

                public void setContent_en(String content_en) {
                    this.content_en = content_en;
                }

                public String getContent_cn() {
                    return content_cn;
                }

                public void setContent_cn(String content_cn) {
                    this.content_cn = content_cn;
                }

                public String getPage_id() {
                    return page_id;
                }

                public void setPage_id(String page_id) {
                    this.page_id = page_id;
                }

                public String getVoice_path() {
                    return voice_path;
                }

                public void setVoice_path(String voice_path) {
                    this.voice_path = voice_path;
                }
            }
        }
    }
}
