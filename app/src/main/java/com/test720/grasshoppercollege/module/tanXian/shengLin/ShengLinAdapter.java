package com.test720.grasshoppercollege.module.tanXian.shengLin;

import android.support.v7.app.AppCompatActivity;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.bean.TanXianGuanKaData;

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
 * 作者：水东流 编于 2018/10/23
 */
public class ShengLinAdapter extends BaseTanXianAdapter {


    public ShengLinAdapter(List<List<TanXianGuanKaData.DataBean>> list, AppCompatActivity context) {
        super(list, context);
    }

    @Override
    public int onelayout() {
        return R.layout.tan_xian_shen_lin_one;
    }

    @Override
    public int twolayout() {
        return R.layout.tan_xian_shen_lin_two;
    }

    @Override
    public int threelayout() {
        return R.layout.tan_xian_shen_lin_three;
    }

    @Override
    public int fourlayout() {
        return R.layout.tan_xian_shen_lin_four;
    }


}