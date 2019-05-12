package com.test720.grasshoppercollege;

import com.test720.grasshoppercollege.untils.QuanXianUntil;

/**
 * Created by 水东流 on 2018/6/15.
 */

public abstract class BaseShouQuanXianActivity extends BaseRecyclerViewActivity {

    /*购买课程*/
    public abstract void buyCurr();

    /*购买蝈蝈豆*/
    public abstract void buyGuoGuo();

    /*分享统计*/
    public abstract void shareCurr();

    public abstract String[] shareStr();

    public String re_id;//果果豆购买，或者分享积分所需
    public String points;//购买所需蝈蝈豆
    int itemIndex = -1;

    public int getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            ShuaXin();
        }
    }

    /**
     * @param permission 权限
     * @param isPlay     是否购买
     * @param open_up    是否解锁
     * @return
     */
    public boolean isCanSee(String permission, boolean isPlay, String open_up) {
        return QuanXianUntil.GetIntent(this).isCanSee(permission, isPlay, open_up, points, shareStr(), new QuanXianUntil.QuanXianClick() {
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
}
