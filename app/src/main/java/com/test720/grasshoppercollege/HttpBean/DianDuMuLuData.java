package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/20.
 */

public class DianDuMuLuData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"unit":"Unit 1","lesson":[{"lesson":"Part A","page":[{"page_id":"5338","page":"1"},{"page_id":"5349","page":"2"},{"page_id":"5354","page":"3"},{"page_id":"5358","page":"4"},{"page_id":"5366","page":"5"},{"page_id":"5373","page":"6"}]},{"lesson":"Part B","page":[{"page_id":"5381","page":"7"},{"page_id":"5389","page":"8"},{"page_id":"5394","page":"9"}]},{"lesson":"Part C","page":[{"page_id":"5398","page":"10"},{"page_id":"5399","page":"11"}]}]},{"unit":"Unit 2","lesson":[{"lesson":"Part A","page":[{"page_id":"5409","page":"12"},{"page_id":"5416","page":"13"},{"page_id":"5419","page":"14"},{"page_id":"5427","page":"15"},{"page_id":"5437","page":"16"}]},{"lesson":"Part B","page":[{"page_id":"5445","page":"17"},{"page_id":"5455","page":"18"},{"page_id":"5470","page":"19"}]},{"lesson":"Part C","page":[{"page_id":"5479","page":"20"},{"page_id":"5480","page":"21"}]}]},{"unit":"Unit 3","lesson":[{"lesson":"Part A","page":[{"page_id":"5494","page":"22"},{"page_id":"5500","page":"23"},{"page_id":"5506","page":"24"},{"page_id":"5517","page":"25"},{"page_id":"5532","page":"26"},{"page_id":"5585","page":"32"},{"page_id":"5586","page":"34"},{"page_id":"5591","page":"35"}]},{"lesson":"Part B","page":[{"page_id":"5541","page":"27"},{"page_id":"5556","page":"28"},{"page_id":"5567","page":"29"}]},{"lesson":"Part C","page":[{"page_id":"5571","page":"30"},{"page_id":"5573","page":"31"}]}]},{"unit":"Unit 4","lesson":[{"lesson":"Part A","page":[{"page_id":"5592","page":"36"},{"page_id":"5596","page":"37"},{"page_id":"5604","page":"38"},{"page_id":"5609","page":"39"},{"page_id":"5623","page":"40"}]},{"lesson":"Part B","page":[{"page_id":"5632","page":"41"},{"page_id":"5645","page":"42"},{"page_id":"5654","page":"43"}]},{"lesson":"Part C","page":[{"page_id":"5659","page":"44"},{"page_id":"5661","page":"45"}]}]},{"unit":"Unit 5","lesson":[{"lesson":"Part A","page":[{"page_id":"5673","page":"46"},{"page_id":"5677","page":"47"},{"page_id":"5683","page":"48"},{"page_id":"5692","page":"49"},{"page_id":"5703","page":"50"}]},{"lesson":"Part B","page":[{"page_id":"5713","page":"51"},{"page_id":"5721","page":"52"},{"page_id":"5750","page":"53"}]},{"lesson":"Part C","page":[{"page_id":"5761","page":"54"},{"page_id":"5763","page":"55"}]}]},{"unit":"Unit 6","lesson":[{"lesson":"Part A","page":[{"page_id":"5775","page":"56"},{"page_id":"5782","page":"57"},{"page_id":"5785","page":"58"},{"page_id":"5803","page":"59"},{"page_id":"5811","page":"60"},{"page_id":"5865","page":"66"},{"page_id":"5868","page":"67"},{"page_id":"5869","page":"69"}]},{"lesson":"Part B","page":[{"page_id":"5817","page":"61"},{"page_id":"5831","page":"62"},{"page_id":"5839","page":"63"}]},{"lesson":"Part C","page":[{"page_id":"5856","page":"64"},{"page_id":"5858","page":"65"}]}]}]
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
         * unit : Unit 1
         * lesson : [{"lesson":"Part A","page":[{"page_id":"5338","page":"1"},{"page_id":"5349","page":"2"},{"page_id":"5354","page":"3"},{"page_id":"5358","page":"4"},{"page_id":"5366","page":"5"},{"page_id":"5373","page":"6"}]},{"lesson":"Part B","page":[{"page_id":"5381","page":"7"},{"page_id":"5389","page":"8"},{"page_id":"5394","page":"9"}]},{"lesson":"Part C","page":[{"page_id":"5398","page":"10"},{"page_id":"5399","page":"11"}]}]
         */

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
             * lesson : Part A
             * page : [{"page_id":"5338","page":"1"},{"page_id":"5349","page":"2"},{"page_id":"5354","page":"3"},{"page_id":"5358","page":"4"},{"page_id":"5366","page":"5"},{"page_id":"5373","page":"6"}]
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
                 * page_id : 5338
                 * page : 1
                 */

                private String page_id;//书页id
                private String page;//书页名
                private int pageIndex;//书的页码。0开始

                public int getPageIndex() {
                    return pageIndex;
                }

                public void setPageIndex(int pageIndex) {
                    this.pageIndex = pageIndex;
                }

                public String getPage_id() {
                    return page_id;
                }

                public void setPage_id(String page_id) {
                    this.page_id = page_id;
                }

                public String getPage() {
                    return page;
                }

                public void setPage(String page) {
                    this.page = page;
                }
            }
        }
    }
}
