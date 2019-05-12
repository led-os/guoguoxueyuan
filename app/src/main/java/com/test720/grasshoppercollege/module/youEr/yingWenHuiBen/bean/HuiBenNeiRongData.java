package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean;

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
 * Created by 水东流 on 2018/8/16.
 */

public class HuiBenNeiRongData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"name":"Number","status":"1","type":"52","get_points":"1","is_points":"0","share_points":"2","is_share":"0","my_id":""},"list":[{"read_id":"1","count":"5","sort":"1","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc05476b026a.jpg","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc05476b3f1c.mp3","cn":"What's this,Mocky?","pic_b":"Uploads/child_book/book_1/5bc05476b026a.jpg","voice_b":"Uploads/child_book/book_1/5bc05476b3f1c.mp3","my_voice":""},{"read_id":"2","count":"5","sort":"2","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc0548721f49.jpg","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc054872a092.jpg","cn":"It's a banana.","pic_b":"Uploads/child_book/book_1/5bc0548721f49.jpg","voice_b":"Uploads/child_book/book_1/5bc054872a092.jpg","my_voice":""}]}
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
         * info : {"name":"Number","status":"1","type":"52","get_points":"1","is_points":"0","share_points":"2","is_share":"0","my_id":""}
         * list : [{"read_id":"1","count":"5","sort":"1","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc05476b026a.jpg","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc05476b3f1c.mp3","cn":"What's this,Mocky?","pic_b":"Uploads/child_book/book_1/5bc05476b026a.jpg","voice_b":"Uploads/child_book/book_1/5bc05476b3f1c.mp3","my_voice":""},{"read_id":"2","count":"5","sort":"2","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc0548721f49.jpg","voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc054872a092.jpg","cn":"It's a banana.","pic_b":"Uploads/child_book/book_1/5bc0548721f49.jpg","voice_b":"Uploads/child_book/book_1/5bc054872a092.jpg","my_voice":""}]
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
             * name : Number
             * status : 1
             * type : 52
             * get_points : 1
             * is_points : 0
             * share_points : 2
             * is_share : 0
             * my_id :
             */

            private String name;
            private String status;
            private String type;
            private String get_points;
            private String is_points;
            private String share_points;
            private String is_share;
            private String my_id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getGet_points() {
                return get_points;
            }

            public void setGet_points(String get_points) {
                this.get_points = get_points;
            }

            public String getIs_points() {
                return is_points;
            }

            public void setIs_points(String is_points) {
                this.is_points = is_points;
            }

            public String getShare_points() {
                return share_points;
            }

            public void setShare_points(String share_points) {
                this.share_points = share_points;
            }

            public String getIs_share() {
                return is_share;
            }

            public void setIs_share(String is_share) {
                this.is_share = is_share;
            }

            public String getMy_id() {
                return my_id;
            }

            public void setMy_id(String my_id) {
                this.my_id = my_id;
            }
        }

        public static class ListBean {
            /**
             * read_id : 1
             * count : 5
             * sort : 1
             * pic : http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc05476b026a.jpg
             * voice : http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1/5bc05476b3f1c.mp3
             * cn : What's this,Mocky?
             * pic_b : Uploads/child_book/book_1/5bc05476b026a.jpg
             * voice_b : Uploads/child_book/book_1/5bc05476b3f1c.mp3
             * my_voice :
             */

            private String read_id;
            private String count;
            private String sort;
            private String pic;
            private String voice;
            private String cn;
            private String pic_b;
            private String voice_b;
            private String my_voice;

            public String getRead_id() {
                return read_id;
            }

            public void setRead_id(String read_id) {
                this.read_id = read_id;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getVoice() {
                return voice;
            }

            public void setVoice(String voice) {
                this.voice = voice;
            }

            public String getCn() {
                return cn;
            }

            public void setCn(String cn) {
                this.cn = cn;
            }

            public String getPic_b() {
                return pic_b;
            }

            public void setPic_b(String pic_b) {
                this.pic_b = pic_b;
            }

            public String getVoice_b() {
                return voice_b;
            }

            public void setVoice_b(String voice_b) {
                this.voice_b = voice_b;
            }

            public String getMy_voice() {
                return my_voice;
            }

            public void setMy_voice(String my_voice) {
                this.my_voice = my_voice;
            }
        }
    }
}
