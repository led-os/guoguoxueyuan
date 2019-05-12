package com.test720.grasshoppercollege.fragment;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guShiCi.GuShiShouYeActivity;
import com.test720.grasshoppercollege.module.jinFanYiCi.JinFanYiShouActivity;
import com.test720.grasshoppercollege.module.xieHouYu.XieHouYuShouActivity;
import com.test720.grasshoppercollege.module.yuWen.chengYu.ChengYuShouActivity;
import com.test720.grasshoppercollege.module.yuWen.tingXieZhuShou.TingLiShouActivity;
import com.test720.grasshoppercollege.module.yuWen.yuWenJiChu.YuWenJIChuShouActivity;
import com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.pinYin.PingYinShouActivity;
import com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.shiZi.ShiZiShouYeActivity;
import com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.yuYan.YuYanShouYeActivity;
import com.test720.grasshoppercollege.module.yuWen.yueDu.YuWenYueDuShouActivity;

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

public class YuWenFragment extends BaseFragment {
    @BindView(R.id.mBackground)
    ImageView mBackground;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.text_one)
    TextView textOne;
    @BindView(R.id.text_two)
    TextView textTwo;
    @BindView(R.id.text_three)
    TextView textThree;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.text_four)
    TextView textFour;
    @BindView(R.id.four)
    LinearLayout four;
    @BindView(R.id.text_five)
    TextView textFive;
    @BindView(R.id.five)
    LinearLayout five;
    @BindView(R.id.text_six)
    TextView textSix;
    @BindView(R.id.six)
    LinearLayout six;
    @BindView(R.id.text_seven)
    TextView textSeven;
    @BindView(R.id.seven)
    LinearLayout seven;
    @BindView(R.id.text_eight)
    TextView textEight;
    @BindView(R.id.eight)
    LinearLayout eight;
    @BindView(R.id.text_nine)
    TextView textNine;
    @BindView(R.id.nine)
    LinearLayout nine;
    @BindView(R.id.text_ten)
    TextView textTen;
    @BindView(R.id.ten)
    LinearLayout ten;
    @BindView(R.id.text_eleven)
    TextView textEleven;
    @BindView(R.id.eleven)
    LinearLayout eleven;
    @BindView(R.id.text_twelve)
    TextView textTwelve;
    @BindView(R.id.twelve)
    LinearLayout twelve;
    @BindView(R.id.text_thirteen)
    TextView textThirteen;
    @BindView(R.id.thirteen)
    LinearLayout thirteen;
    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;
    Unbinder unbinder;

    @Override
    public void initData() {
        /*默认第一*/
        index = 1;
        playAnim(one);
        mBackground.setImageResource(R.mipmap.yuwenjichuback);
        type = textOne.getText().toString();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_yu_wen;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @OnClick({R.id.btn, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.ten, R.id.eleven, R.id.twelve, R.id.thirteen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                if (index == 1) return;
                playAnimBack();
                index = 1;
                playAnim(one);
                mBackground.setImageResource(R.mipmap.yuwenjichuback);
                type = textOne.getText().toString();
                break;
            case R.id.two:
                if (index == 2) return;
                playAnimBack();
                index = 2;
                playAnim(two);
                mBackground.setImageResource(R.mipmap.pingyindaoziback);
                type = textTwo.getText().toString();
                break;
            case R.id.three:
                if (index == 3) return;
                playAnimBack();
                index = 3;
                playAnim(three);
                mBackground.setImageResource(R.mipmap.zidaopingyinback);
                type = textThree.getText().toString();
                break;
            case R.id.four:
                if (index == 4) return;
                playAnimBack();
                index = 4;
                playAnim(four);
                mBackground.setImageResource(R.mipmap.ywkbtx);
                type = textFour.getText().toString();
                break;
            case R.id.five:
                if (index == 5) return;
                playAnimBack();
                index = 5;
                playAnim(five);
                mBackground.setImageResource(R.mipmap.yueduxulianback);
                type = textFive.getText().toString();
                break;
            case R.id.six:
                if (index == 6) return;
                playAnimBack();
                index = 6;
                playAnim(six);
                mBackground.setImageResource(R.mipmap.yuwendiandu);
                type = textSix.getText().toString();
                break;
            case R.id.seven:
                if (index == 7) return;
                playAnimBack();
                index = 7;
                playAnim(seven);
                mBackground.setImageResource(R.mipmap.gushiback);
                type = textSeven.getText().toString();
                break;
            case R.id.eight:
                if (index == 8) return;
                playAnimBack();
                index = 8;
                playAnim(eight);
                mBackground.setImageResource(R.mipmap.jinfanyi);
                type = textEight.getText().toString();
                break;
            case R.id.nine:
                if (index == 9) return;
                playAnimBack();
                index = 9;
                playAnim(nine);
                mBackground.setImageResource(R.mipmap.kuailexiehouback);
                type = textNine.getText().toString();
                break;
            case R.id.ten:
                if (index == 10) return;
                playAnimBack();
                index = 10;
                playAnim(ten);
                mBackground.setImageResource(R.mipmap.yuyangushiback);
                type = textTen.getText().toString();
                break;
            case R.id.eleven:
                if (index == 11) return;
                playAnimBack();
                index = 11;
                playAnim(eleven);
                mBackground.setImageResource(R.mipmap.chengyujielongback);
                type = textEleven.getText().toString();
                break;
            case R.id.twelve:
                if (index == 12) return;
                playAnimBack();
                index = 12;
                playAnim(twelve);
                mBackground.setImageResource(R.mipmap.pinyingback);
                type = textTwelve.getText().toString();
                break;
            case R.id.thirteen:
                if (index == 13) return;
                playAnimBack();
                index = 13;
                playAnim(thirteen);
                mBackground.setImageResource(R.mipmap.hanziback);
                type = textThirteen.getText().toString();
                break;
            case R.id.btn:
                jump();
                break;
        }
    }

    //初始化偏移量
    private int offset = 0;
    private int scrollViewWidth = 0;

    /*点击移动,上移动画*/
    private void playAnim(View view) {
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, -200, 0, -100);
        transAnim.setDuration(1000);
        transAnim.start();

        //获取位置下标
        scrollViewWidth = scrollView.getWidth();
        if ((scrollViewWidth + offset) < (view.getRight() + 100)) {//需要向右移动
            scrollView.smoothScrollBy(200, 0);
            offset += 200;
        }

        if (offset > (view.getLeft() - 100)) {//需要向左移动
            scrollView.smoothScrollBy(-200, 0);
            offset += -200;
        }
    }

    int index = 1;

    /*点击移动,复原动画*/
    private void playAnimBack() {
        View view;
        switch (index) {
            case 1:
                view = one;
                break;
            case 2:
                view = two;
                break;
            case 3:
                view = three;
                break;
            case 4:
                view = four;
                break;
            case 5:
                view = five;
                break;
            case 6:
                view = six;
                break;
            case 7:
                view = seven;
                break;
            case 8:
                view = eight;
                break;
            case 9:
                view = nine;
                break;
            case 10:
                view = ten;
                break;
            case 11:
                view = eleven;
                break;
            case 12:
                view = twelve;
                break;
            case 13:
                view = thirteen;
                break;
            default:
                view = one;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 50, 50, 0);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    /*当前选中模块*/
    String type = "奥数动画";

    private void jump() {
        switch (type) {
            case "基础练习":
                jumpToActivity(YuWenJIChuShouActivity.class, false);
                break;
            case "拼音到字":
//                jumpToActivity(PinYinToCnShouActivity.class, false);
                break;
            case "字到拼音":
//                jumpToActivity(CnToPinYiinShouActivity.class, false);
                break;
            case "听写助手":
                jumpToActivity(TingLiShouActivity.class, false);
                break;
            case "阅读训练":
                jumpToActivity(YuWenYueDuShouActivity.class, false);
                break;
            case "古诗三百":
                jumpToActivity(GuShiShouYeActivity.class, false);
                break;
            case "近反义词":
                jumpToActivity(JinFanYiShouActivity.class, false);
                break;
            case "快乐歇后":
                jumpToActivity(XieHouYuShouActivity.class, false);
                break;
            case "寓言故事":
                jumpToActivity(YuYanShouYeActivity.class, false);
                break;
            case "成语接龙":
                jumpToActivity(ChengYuShouActivity.class, false);
                break;
            case "拼音动画":
                jumpToActivity(PingYinShouActivity.class, false);
                break;
            case "汉字动画":
                jumpToActivity(ShiZiShouYeActivity.class, false);
                break;
        }
    }

}
