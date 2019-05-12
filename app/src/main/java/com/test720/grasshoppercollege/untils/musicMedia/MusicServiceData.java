package com.test720.grasshoppercollege.untils.musicMedia;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.MusicActivity;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.GuShiNeiRongData;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
 * 获取通知栏播放音乐数据
 */
public class MusicServiceData {
    private static final MusicServiceData ourInstance = new MusicServiceData();

    public static MusicServiceData getInstance() {
        return ourInstance;
    }

    private MusicServiceData() {
    }

    private GuShiNeiRongData.DataBean.InfoBean info;//专辑数据

    private List<PingLunData> comment = new ArrayList<>();//评论数据

    private List<GuShiNeiRongData.DataBean.ListBean> list = new ArrayList<>();//专辑音频数据

    String Id = "";//当前专辑数据请求id

    //标记当前歌曲的序号
    private int index = 0;

    private boolean needUpdata = false;//标识，是否需要更新,每次musicactivity销毁时，复位为不更新

    private boolean isSpeed = false;//是否单曲循环/

    private boolean needNext = true;//是否需要自动切换到下一曲

    private MusicActivity musicActivity;

    /**
     * 返回当前播放音频标题
     *
     * @return
     */
    public String getTitle() {
        if (list.size() == 0) return "";
        return getList().get(index).getTitle_t();
    }

    /**
     * 返回当前播放音频副标题
     *
     * @return
     */
    public String getSmallTitle() {
        if (list.size() == 0) return "";
        return getList().get(index).getSmall_title();
    }

    /**
     * 返回当前专辑标题
     *
     * @return
     */
    public String getZhuanJiTitle() {
        if (getInfo() == null) return "";
        return getInfo().getAlbum().getTitle();
    }

    /**
     * 返回当前专辑id
     *
     * @return
     */
    public String getAlbumId() {
        if (getInfo() == null) return "";
        return getInfo().getAlbum_id();
    }

    /**
     * 返回当前专辑背景
     *
     * @return
     */
    public String getZhuanJiBack() {
        if (getInfo() == null) return "";
        return getInfo().getAlbum().getCover();
    }

    /**
     * 返回当前故事背景
     *
     * @return
     */
    public String getGuShiBack() {
        if (list.size() == 0) return "";
        return getList().get(index).getPic();
    }

    /**
     * 返回当前专辑故事数
     *
     * @return
     */
    public String getZhuanJiCount() {
        if (getInfo() == null) return "";
        return getInfo().getAlbum().getCount();
    }


    /**
     * 返回当前播放音频介绍
     *
     * @return
     */
    public String getDece() {
        if (list.size() == 0) return "";
        return getList().get(index).getSmall_title();
    }


    /**
     * 返回当前播放路径
     *
     * @return
     */
    public String getPath() {
        if (list.size() == 0) return "";
        return getList().get(index).getPath();
    }

    /**
     * 返回当前音频f是否收藏
     *
     * @return
     */
    public boolean isCollection() {
        if (list.size() == 0) return false;
        if (list.get(index).getCollection().equals("1")) {
            return true;
        }
        return false;
    }

    /**
     * 設置是否收藏
     */
    public void setCollection() {
        if (list.size() == 0) return;
        if (list.get(index).getCollection().equals("1")) {
            list.get(index).setCollection("0");
        } else {
            list.get(index).setCollection("1");
        }
    }

    /**
     * @param time 秒
     */
    Runnable runnable;//倒计时执行
    String downStr = "";// 倒计时类型
    Handler handler = new Handler();

    public void downTime(int time, final Context context) {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.FINISHMUSIC, "ok"));
                //处理滑动事件
                Intent intent2 = new Intent(context, MusicService.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.stopService(intent2);

                //清空数据
                MusicServiceData.getInstance().setInfo(null);
            }
        };
        handler.postDelayed(runnable, time * 1000);
    }

    public String getDownStr() {
        return downStr;
    }

    public void setDownStr(String downStr) {
        this.downStr = downStr;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
        needUpdata = true;//更新id后需要更新
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public GuShiNeiRongData.DataBean.InfoBean getInfo() {
        return info;
    }

    public void setInfo(GuShiNeiRongData.DataBean.InfoBean info) {
        this.info = info;
    }

    public List<PingLunData> getComment() {
        return comment;
    }

    public void setComment(List<PingLunData> comment) {
        this.comment = comment;
    }

    public List<GuShiNeiRongData.DataBean.ListBean> getList() {
        return list;
    }

    public void setList(List<GuShiNeiRongData.DataBean.ListBean> list) {
        this.list = list;
    }

    public boolean isNeedUpdata() {
        return needUpdata;
    }

    public void setNeedUpdata(boolean needUpdata) {
        this.needUpdata = needUpdata;
    }

    public boolean isSpeed() {
        return isSpeed;
    }

    public void setSpeed(boolean speed) {
        isSpeed = speed;
    }

    public boolean isNeedNext() {
        return needNext;
    }

    public void setNeedNext(boolean needNext) {
        this.needNext = needNext;
    }


}
