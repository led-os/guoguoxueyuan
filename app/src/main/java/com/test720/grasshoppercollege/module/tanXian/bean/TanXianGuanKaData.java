package com.test720.grasshoppercollege.module.tanXian.bean;

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
 * 作者：水东流 编于 2018/11/12
 */
public class TanXianGuanKaData {

    /**
     * code : 1
     * msg : 成功
     * data : [[{"checkpoint_id":"1","checkpoint":"1","box":"0","open_up":0,"enter":0},{"checkpoint_id":"2","checkpoint":"2","box":"0","open_up":0,"enter":0},{"checkpoint_id":"3","checkpoint":"3","box":"0","open_up":0,"enter":0},{"checkpoint_id":"4","checkpoint":"4","box":"1","open_up":0,"enter":0}],[{"checkpoint_id":"5","checkpoint":"5","box":"0","open_up":0,"enter":0},{"checkpoint_id":"6","checkpoint":"6","box":"0","open_up":0,"enter":0},{"checkpoint_id":"7","checkpoint":"7","box":"0","open_up":0,"enter":0},{"checkpoint_id":"8","checkpoint":"8","box":"1","open_up":0,"enter":0}],[{"checkpoint_id":"9","checkpoint":"9","box":"0","open_up":0,"enter":0},{"checkpoint_id":"10","checkpoint":"10","box":"0","open_up":0,"enter":0},{"checkpoint_id":"11","checkpoint":"11","box":"0","open_up":0,"enter":0},{"checkpoint_id":"12","checkpoint":"12","box":"1","open_up":0,"enter":0}],[{"checkpoint_id":"13","checkpoint":"13","box":"0","open_up":0,"enter":0},{"checkpoint_id":"14","checkpoint":"14","box":"0","open_up":0,"enter":0},{"checkpoint_id":"15","checkpoint":"15","box":"0","open_up":0,"enter":0},{"checkpoint_id":"16","checkpoint":"16","box":"1","open_up":0,"enter":0}]]
     */

    private int code;
    private String msg;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * checkpoint_id : 1
         * checkpoint : 1
         * box : 0
         * open_up : 0
         * enter : 0
         */

        private String checkpoint_id;
        private String checkpoint;
        private String box;
        private int open_up;
        private int enter;

        public String getCheckpoint_id() {
            return checkpoint_id;
        }

        public void setCheckpoint_id(String checkpoint_id) {
            this.checkpoint_id = checkpoint_id;
        }

        public String getCheckpoint() {
            return checkpoint;
        }

        public void setCheckpoint(String checkpoint) {
            this.checkpoint = checkpoint;
        }

        public String getBox() {
            return box;
        }

        public void setBox(String box) {
            this.box = box;
        }

        public int getOpen_up() {
            return open_up;
        }

        public void setOpen_up(int open_up) {
            this.open_up = open_up;
        }

        public int getEnter() {
            return enter;
        }

        public void setEnter(int enter) {
            this.enter = enter;
        }
    }
}
