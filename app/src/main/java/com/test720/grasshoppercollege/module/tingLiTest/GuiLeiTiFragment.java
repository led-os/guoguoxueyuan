package com.test720.grasshoppercollege.module.tingLiTest;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.MoveTextView;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;

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

public class GuiLeiTiFragment extends TingLiTiMuFragment implements MoveTextView.AcctionUp {

    int index = 0;//答案标
    MusicUntil musicUntil = new MusicUntil();

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getBean() == null) return;
        la1.setText(getBean().getQuestion_gui().get(0));
        la2.setText(getBean().getQuestion_gui().get(1));
        for (int i = 0; i < getBean().getQuestion_content_gui().size(); i++) {
            switch (i) {
                case 0:
                    one.setVisibility(View.VISIBLE);
                    one.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    one.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    one.setAcctionUp(this);
                    move(one, 1);
                    break;
                case 1:
                    two.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    two.setVisibility(View.VISIBLE);
                    two.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    two.setAcctionUp(this);
                    move(two, 2);
                    break;
                case 2:
                    three.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    three.setVisibility(View.VISIBLE);
                    three.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    three.setAcctionUp(this);
                    move(three, 3);
                    break;
                case 3:
                    four.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    four.setVisibility(View.VISIBLE);
                    four.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    four.setAcctionUp(this);
                    move(four, 4);
                    break;
                case 4:
                    five.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    five.setVisibility(View.VISIBLE);
                    five.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    five.setAcctionUp(this);
                    move(five, 5);
                    break;
                case 5:
                    six.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    six.setVisibility(View.VISIBLE);
                    six.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    six.setAcctionUp(this);
                    move(six, 6);
                    break;
                case 6:
                    seven.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    seven.setVisibility(View.VISIBLE);
                    seven.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    seven.setAcctionUp(this);
                    move(seven, 7);
                    break;
                case 7:
                    eight.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    eight.setVisibility(View.VISIBLE);
                    eight.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    eight.setAcctionUp(this);
                    move(eight, 8);
                    break;
                case 8:
                    nine.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    nine.setVisibility(View.VISIBLE);
                    nine.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    nine.setAcctionUp(this);
                    move(nine, 9);
                    break;

                case 9:
                    nine.setTag(getBean().getQuestion_content_gui().get(i).getA());
                    nine.setVisibility(View.VISIBLE);
                    nine.setText(getBean().getQuestion_content_gui().get(i).getQ());
                    nine.setAcctionUp(this);
                    move(nine, 0);
                    break;

            }


        }

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_gui_nei_ti;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    /*动画移动*/
    public void move(MoveTextView view, int i) {
        ObjectAnimator transAnimx = ObjectAnimator.ofFloat(view, "translationX", 0, 120 * (i % 4));
        transAnimx.setDuration(1000);
        transAnimx.start();
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, 100 * (i % 3) + 100);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    /*弹回动画*/

    public void Back(MoveTextView view) {
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", view.getY(), 100);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    @Override
    public void acctionUp(MoveTextView moveTextView) {
        if (moveTextView.getY() > 650) {
            /*左边车*/
            if (moveTextView.getX() < 250) {
                /*回答正确*/
                if (moveTextView.getTag().toString().equals("0")) {
                    musicUntil.playRaw(getContext(),R.raw.righten);
                    moveTextView.setVisibility(View.GONE);
                    che1.setText(che1.getText().toString() + " " + moveTextView.getText().toString());
                    index++;
                } else {
                    musicUntil.playRaw(getContext(),R.raw.wrong);
                    ShowToast("错误");
                    Back(moveTextView);
                }

            } else {
                /*右边车*/
                /*回答正确*/
                if (moveTextView.getTag().toString().equals("1")) {
                    musicUntil.playRaw(getContext(),R.raw.righten);
                    moveTextView.setVisibility(View.GONE);
                    che2.setText(che2.getText().toString() + " " + moveTextView.getText().toString());
                    index++;
                } else {
                    musicUntil.playRaw(getContext(),R.raw.wrong);
                    ShowToast("错误");
                    Back(moveTextView);
                }
            }
            if (index == getBean().getQuestion_content_gui().size()) {
                ShowToast("回答完毕");
                /*切换到下一题*/
                if (getViewPager().getAdapter().getCount() - 1 > getViewPager().getCurrentItem()) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                } else {
                    AddGuoGuoDou();
                }
            }
        }
    }


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.one)
    MoveTextView one;
    @BindView(R.id.two)
    MoveTextView two;
    @BindView(R.id.three)
    MoveTextView three;
    @BindView(R.id.four)
    MoveTextView four;
    @BindView(R.id.five)
    MoveTextView five;
    @BindView(R.id.six)
    MoveTextView six;
    @BindView(R.id.seven)
    MoveTextView seven;
    @BindView(R.id.eight)
    MoveTextView eight;
    @BindView(R.id.nine)
    MoveTextView nine;
    @BindView(R.id.ten)
    MoveTextView ten;
    @BindView(R.id.la1)
    TextView la1;
    @BindView(R.id.la2)
    TextView la2;
    @BindView(R.id.che1)
    TextView che1;
    @BindView(R.id.che2)
    TextView che2;
}
