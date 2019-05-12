package com.test720.grasshoppercollege.module.peiYin.bean;

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
 * 作者：水东流 编于 2018/8/29
 */
public class ZhuaJiData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"name":"哈哈哈","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png","level":"3"},"list":[{"con_id":"1","video_name":"围追堵截1","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"2","video_name":"围追堵截2","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"3","video_name":"围追堵截3","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"4","video_name":"围追堵截4","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"5","video_name":"围追堵截5","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"6","video_name":"围追堵截6","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"7","video_name":"围追堵截1-1","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"8","video_name":"围追堵截1-2","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"9","video_name":"围追堵截1-3","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"10","video_name":"围追堵截2-1","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}]}
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
         * info : {"name":"哈哈哈","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png","level":"3"}
         * list : [{"con_id":"1","video_name":"围追堵截1","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"2","video_name":"围追堵截2","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"3","video_name":"围追堵截3","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"4","video_name":"围追堵截4","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"5","video_name":"围追堵截5","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"6","video_name":"围追堵截6","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"7","video_name":"围追堵截1-1","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"8","video_name":"围追堵截1-2","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"9","video_name":"围追堵截1-3","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"},{"con_id":"10","video_name":"围追堵截2-1","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}]
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
             * name : 哈哈哈
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             * level : 3
             */

            private String name;
            private String pic;
            private String level;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }

        public static class ListBean {
            /**
             * con_id : 1
             * video_name : 围追堵截1
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             */

            private String con_id;
            private String video_name;
            private String pic;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
