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
public class KaiShiPeiYinData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"con_id":"1","video_name":"围追堵截1","video_path":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4","video_path_q":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie+Q.mp4"},"list":[{"count":"5","sort":"1","cn":"但矮蛋却没难么容易放弃","en":"But Humpty would not give up so easily","video_path":"","start":"0","end":"2.43","time":"2.43"},{"count":"5","sort":"2","cn":"我有麻烦了 猫","en":"I'm in trouble,Puss!","video_path":"","start":"2.55","end":"4.55","time":"2"},{"count":"5","sort":"3","cn":"是蓝小子的一个混混 我欠他一笔钱","en":"It's Boy Blue and his gang.I owe them some money","video_path":"","start":"5.08","end":"7.15","time":"2.07"},{"count":"5","sort":"4","cn":"他们要来找我了 快帮我翻过这座墙","en":"They're coming for me.Just get me over this wall.","video_path":"","start":"7.44","end":"9.2","time":"1.76"},{"count":"5","sort":"5","cn":"我们快走 帮我翻墙 快！","en":"I gotta go.Help me up the wall.Get me up the wall.Hurry!Hurry!","video_path":"","start":"9.55","end":"13.04","time":"3.49"}]}
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
         * info : {"con_id":"1","video_name":"围追堵截1","video_path":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4","video_path_q":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie+Q.mp4"}
         * list : [{"count":"5","sort":"1","cn":"但矮蛋却没难么容易放弃","en":"But Humpty would not give up so easily","video_path":"","start":"0","end":"2.43","time":"2.43"},{"count":"5","sort":"2","cn":"我有麻烦了 猫","en":"I'm in trouble,Puss!","video_path":"","start":"2.55","end":"4.55","time":"2"},{"count":"5","sort":"3","cn":"是蓝小子的一个混混 我欠他一笔钱","en":"It's Boy Blue and his gang.I owe them some money","video_path":"","start":"5.08","end":"7.15","time":"2.07"},{"count":"5","sort":"4","cn":"他们要来找我了 快帮我翻过这座墙","en":"They're coming for me.Just get me over this wall.","video_path":"","start":"7.44","end":"9.2","time":"1.76"},{"count":"5","sort":"5","cn":"我们快走 帮我翻墙 快！","en":"I gotta go.Help me up the wall.Get me up the wall.Hurry!Hurry!","video_path":"","start":"9.55","end":"13.04","time":"3.49"}]
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
             * con_id : 1
             * video_name : 围追堵截1
             * video_path : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4
             * video_path_q : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie+Q.mp4
             */

            private String con_id;
            private String video_name;
            private String video_path;
            private String video_path_q;

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

            public String getVideo_path() {
                return video_path;
            }

            public void setVideo_path(String video_path) {
                this.video_path = video_path;
            }

            public String getVideo_path_q() {
                return video_path_q;
            }

            public void setVideo_path_q(String video_path_q) {
                this.video_path_q = video_path_q;
            }
        }

        public static class ListBean {
            /**
             * count : 5
             * sort : 1
             * cn : 但矮蛋却没难么容易放弃
             * en : But Humpty would not give up so easily
             * video_path :
             * start : 0
             * end : 2.43
             * time : 2.43
             */

            private String count;
            private String sort;
            private String cn;
            private String en;
            private String video_path;
            private String start;
            private String end;
            private String time;

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

            public String getVideo_path() {
                return video_path;
            }

            public void setVideo_path(String video_path) {
                this.video_path = video_path;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
