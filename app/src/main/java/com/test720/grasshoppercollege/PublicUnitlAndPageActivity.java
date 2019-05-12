package com.test720.grasshoppercollege;

import android.widget.ExpandableListView;

import com.test720.grasshoppercollege.untils.QuanXianUntil;

import butterknife.BindView;

public abstract class PublicUnitlAndPageActivity extends BaseToolActivity {

    String[] strs;
    public String re_id;//果果豆购买，或者分享积分所需
    public String points;//购买所需蝈蝈豆

    @BindView(R.id.exListView)
    public ExpandableListView exListView;


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_public_unitl_and_page;
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            getData(false);
        }
    }

    /**
     * 获取数据
     *
     * @param b
     */
    public void getData(boolean b) {

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
        //跳过所有权限判断，则说明可见
    }

    /*购买课程*/
    public abstract void buyCurr();

    /*购买蝈蝈豆*/
    public abstract void buyGuoGuo();

    /*分享统计*/
    public abstract void shareCurr();


}
