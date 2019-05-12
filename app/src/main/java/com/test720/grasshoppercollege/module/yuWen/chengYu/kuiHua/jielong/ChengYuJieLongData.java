package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua.jielong;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/29.
 */

public class ChengYuJieLongData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"name":"一字千金","desc":"123"},{"name":"金枝玉叶","desc":"111"},{"name":"页共奶空","desc":"222"},{"name":"龙宫竞赛","desc":"333"},{"name":"走火入魔","desc":"444"},{"name":"好好学习","desc":"555"},{"name":"天天向上","desc":"666"},{"name":"阴云密布","desc":"777"},{"name":"晴天霹雳","desc":"88888"},{"name":"不不不哈","desc":"11"}]
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
         * name : 一字千金
         * desc : 123
         */

        private String name;
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
