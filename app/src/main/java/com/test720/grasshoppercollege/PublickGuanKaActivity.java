package com.test720.grasshoppercollege;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import butterknife.BindView;
import butterknife.OnClick;

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
 * Created by 水东流 on 2018/3/15 0015.
 */
/*竖向闯关*/
public abstract class PublickGuanKaActivity extends BaseToolActivity {


    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.back1)
    ImageView back1;
    @BindView(R.id.jiLei)
    public ImageView jiLei;
    @BindView(R.id.root)
    RelativeLayout root;
    private LinearLayoutManager layoutManager;
    public MyBaseRecyclerAdapter adapter;
    String[] strs;

    public String re_id;//果果豆购买，或者分享积分所需
    public String points;//购买所需蝈蝈豆

    int itemIndexCount = -1;//item 位置
    int childitemCount = -1;//子item位置


    public RelativeLayout getRoot() {
        return root;
    }

    public void setRoot(RelativeLayout root) {
        this.root = root;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.public_chuang_guan;
    }


    /*是横向还是竖向*/
    public abstract boolean isHORIZONTAL();

    @Override
    protected void initData() {
        layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        if (isHORIZONTAL()) {
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        } else {
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        }
        recyclerView.setLayoutManager(layoutManager);
        init();
        ShuaXin();
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            ShuaXin();
        }
    }

    @Override
    public void AfterHttp() {
        super.AfterHttp();
        if (recyclerView.getAdapter().getItemCount() == 0) {
            root.setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            root.setBackgroundResource(R.color.touming);
        }
        if (recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0 && itemIndexCount != -1) {
            recyclerView.setScrollingTouchSlop(itemIndexCount);
        }
    }

    public abstract void ShuaXin();

    public abstract void init();


    @Override
    public void setSys() {
        super.setSys();
        //设置全屏
        isFull = true;
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
        /*已经解锁的关卡，直接执行*/
        return QuanXianUntil.GetIntent(mcontext).isCanSee(permission, isPlay, open_up, points, strs, new QuanXianUntil.QuanXianClick() {
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

    @OnClick(R.id.back1)
    public void onClick() {
        finish();
    }

    public int getChilditemCount() {
        return childitemCount;
    }

    public void setChilditemCount(int childitemCount) {
        this.childitemCount = childitemCount;
    }

    public int getItemIndexCount() {
        return itemIndexCount;
    }

    public void setItemIndexCount(int itemIndexCount) {
        this.itemIndexCount = itemIndexCount;
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }
}
