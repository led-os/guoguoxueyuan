package com.test720.grasshoppercollege;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/21 0021.
 */

public abstract class PubLicChuangGuanFragment extends BaseFragment {

    PublicLianXiJinShaiActivity publicLianXiJinShaiActivity;
    LinearLayoutManager layoutManager;
    public MyBaseRecyclerAdapter adapter;
    String[] strs;

    public String re_id;//果果豆购买，或者分享积分所需
    public String points;//购买所需蝈蝈豆

    int index = 0;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public abstract boolean isHORIZONTAL();

    @Override
    public void initData() {
        //设置布局管理器
        if (isHORIZONTAL()) {
            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
        init();
    }


    public abstract void init();

    /*刷新数据*/
    public void shuaXin() {

    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if (event.getWhat() == CurrencyEvent.UPDATEFRAGMENT || event.getWhat() == CurrencyEvent.CHENGBAOTONGGUAN) {
            LogUtil.logError("关卡数据fragment中刷新通知");
            shuaXin();
        }
    }

    @Override
    public void ThreeSuccess(String s, int what) {
        super.ThreeSuccess(s, what);
        switch (what) {
            case 123:
                if (codeIsOne(s)) {
                    EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.UPDATEFRAGMENT, "更新"));
                }
                break;
        }
    }

    /*无数据*/
    public void NoData() {
        if (recyclerView.getAdapter().getItemCount() == 0) {
            root.setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            root.setBackgroundResource(R.color.touming);
        }
    }

    @Override
    public void AfterHttp() {
        super.AfterHttp();
        NoData();
    }


    @Override
    public int setlayoutResID() {
        return R.layout.public_chuang_guan;
    }


    @OnClick(R.id.back1)
    public void onClick() {
        getActivity().finish();
    }


    /**
     * @param permission 权限
     * @param isPlay     是否购买
     * @param open_up    是否解锁
     * @return
     */
    public boolean isCanSee(String permission, boolean isPlay, String open_up, String points, String[] strs) {
        this.strs = strs;
        this.points = points;
        return QuanXianUntil.GetIntent(getActivity()).isCanSee(permission, isPlay, open_up, points, strs, new QuanXianUntil.QuanXianClick() {
            @Override
            public void QuanbuyCurr() {
                buyCurr();
            }

            @Override
            public void QuanbuyGuoGuo() {
                buyGuoGuo();
            }

            @Override
            public void QuanshareCurr() {
                shareCurr();
            }
        });
    }

    /*购买课程*/
    public abstract void buyCurr();

    /*购买蝈蝈豆*/
    public abstract void buyGuoGuo();

    /*分享统计*/
    public abstract void shareCurr();


    public RelativeLayout getRoot() {
        return root;
    }

    public void setRoot(RelativeLayout root) {
        this.root = root;
    }

    public PublicLianXiJinShaiActivity getPublicLianXiJinShaiActivity() {
        return publicLianXiJinShaiActivity;
    }

    public void setPublicLianXiJinShaiActivity(PublicLianXiJinShaiActivity publicLianXiJinShaiActivity) {
        this.publicLianXiJinShaiActivity = publicLianXiJinShaiActivity;
    }

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.back1)
    public ImageView back1;
    @BindView(R.id.jiLei)
    ImageView jiLei;
    Unbinder unbinder;
    @BindView(R.id.root)
    RelativeLayout root;

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }
}
