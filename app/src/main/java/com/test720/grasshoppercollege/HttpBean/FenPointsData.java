package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/6/26.
 */

public class FenPointsData {

    /**
     * code : 1
     * msg : 成功
     * data : {"share_points":"5","is_share":"0","points":"20"}
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
         * share_points : 5
         * is_share : 0
         * points : 20
         */

        private String share_points;
        private String is_share;
        private String points;

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

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }
}
