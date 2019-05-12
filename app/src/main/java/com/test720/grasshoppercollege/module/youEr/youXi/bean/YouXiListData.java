package com.test720.grasshoppercollege.module.youEr.youXi.bean;

import java.util.List;

/**
 * Created by 水东流 on 2018/8/2.
 */

public class YouXiListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"game":[{"game_id":"12","name":"塔防","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-01/5b61148b26ca5.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"28","name":"斗地主","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"29","name":"斗地主11","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"30","name":"斗地主21","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"31","name":"斗地主31","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"32","name":"斗地主41","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"33","name":"斗地主51","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"34","name":"斗地主61","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"35","name":"斗地主71","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"},{"game_id":"36","name":"斗地主81","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-02/5b627f090285d.jpg","path":"https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html","permissions":"1","is_permissions":"1","remaining_time":"0","open_up":0,"pay":false,"title":"fasdas","content":"aaaa"}]}
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
        private List<GameBean> game;

        public List<GameBean> getGame() {
            return game;
        }

        public void setGame(List<GameBean> game) {
            this.game = game;
        }

        public static class GameBean {
            /**
             * game_id : 12
             * name : 塔防
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/child_game/pic/2018-08-01/5b61148b26ca5.jpg
             * path : https://www.hzggedu.com/ggxydemo/Uploads/child_game/game/tafang/index.html
             * permissions : 1
             * is_permissions : 1
             * remaining_time : 0
             * open_up : 0
             * pay : false
             * title : fasdas
             * content : aaaa
             */

            private String game_id;
            private String name;
            private String pic;
            private String path;
            private String permissions;
            private String is_permissions;
            private String remaining_time;
            private String game_time;
            private int open_up;
            private boolean pay;
            private String title;
            private String content;

            public String getGame_time() {
                return game_time;
            }

            public void setGame_time(String game_time) {
                this.game_time = game_time;
            }

            public String getGame_id() {
                return game_id;
            }

            public void setGame_id(String game_id) {
                this.game_id = game_id;
            }

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

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getPermissions() {
                return permissions;
            }

            public void setPermissions(String permissions) {
                this.permissions = permissions;
            }

            public String getIs_permissions() {
                return is_permissions;
            }

            public void setIs_permissions(String is_permissions) {
                this.is_permissions = is_permissions;
            }

            public String getRemaining_time() {
                return remaining_time;
            }

            public void setRemaining_time(String remaining_time) {
                this.remaining_time = remaining_time;
            }

            public int getOpen_up() {
                return open_up;
            }

            public void setOpen_up(int open_up) {
                this.open_up = open_up;
            }

            public boolean isPay() {
                return pay;
            }

            public void setPay(boolean pay) {
                this.pay = pay;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
