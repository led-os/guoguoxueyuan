package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean;

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
 * 作者：水东流 编于 2018/9/4
 */
public class BaoBaoZhuanJiListData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"album_id":"1","title":"凯叔 · 三国演义","small_title":"听英雄故事，长少年志气","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","count":"10","price":"120.00"}]
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
         * album_id : 1
         * title : 凯叔 · 三国演义
         * small_title : 听英雄故事，长少年志气
         * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg
         * count : 10
         * price : 120.00
         */

        private String album_id;
        private String title;
        private String small_title;
        private String cover;
        private String count;
        private String price;

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSmall_title() {
            return small_title;
        }

        public void setSmall_title(String small_title) {
            this.small_title = small_title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
