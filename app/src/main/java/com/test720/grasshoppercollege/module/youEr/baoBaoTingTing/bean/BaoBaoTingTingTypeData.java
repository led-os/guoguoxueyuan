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
 * 作者：水东流 编于 2018/9/5
 */
public class BaoBaoTingTingTypeData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"parent_id":"1","name":"安全教育","list":[{"cate_id":"4","name":"习惯养成"},{"cate_id":"5","name":"品格培养"},{"cate_id":"6","name":"艺术修养"},{"cate_id":"7","name":"性格培养"},{"cate_id":"8","name":"自我认知"}]},{"parent_id":"2","name":"情商培养","list":[]},{"parent_id":"3","name":"语言表达","list":[]}]
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
         * parent_id : 1
         * name : 安全教育
         * list : [{"cate_id":"4","name":"习惯养成"},{"cate_id":"5","name":"品格培养"},{"cate_id":"6","name":"艺术修养"},{"cate_id":"7","name":"性格培养"},{"cate_id":"8","name":"自我认知"}]
         */

        private String parent_id;
        private String name;
        private List<ListBean> list;

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cate_id : 4
             * name : 习惯养成
             */

            private String cate_id;
            private String name;

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
