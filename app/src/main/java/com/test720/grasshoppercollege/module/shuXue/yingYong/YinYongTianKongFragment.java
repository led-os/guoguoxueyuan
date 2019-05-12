package com.test720.grasshoppercollege.module.shuXue.yingYong;

import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.AoShuLianXiNeiRongData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.AoShuDaTiDialog;

import butterknife.BindView;
import butterknife.OnClick;

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
 * 作者：水东流 编于 2018/10/4
 */
public class YinYongTianKongFragment extends BaseTiMuOkFragment {

    StringBuffer stringBuffer = new StringBuffer();

    @Override
    public void TongGuan() {

    }

    @Override
    public String GXShareTitile() {
        return "应用练习";
    }

    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String questionId() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_ying_yong_tian_kong;
    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.zero, R.id.four, R.id.five, R.id.six, R.id.dian, R.id.seven, R.id.eight, R.id.nine, R.id.ok})
    public void onViewClicked(View view) {
        if (isAnswer && view.getId() != R.id.ok) return;
        switch (view.getId()) {
            case R.id.one:
                stringBuffer.append("1");
                break;
            case R.id.two:
                stringBuffer.append("2");
                break;
            case R.id.three:
                stringBuffer.append("3");
                break;
            case R.id.zero:
                stringBuffer.append("0");
                break;
            case R.id.four:
                stringBuffer.append("4");
                break;
            case R.id.five:
                stringBuffer.append("5");
                break;
            case R.id.six:
                stringBuffer.append("6");
                break;
            case R.id.dian:
                if (stringBuffer.toString().length() == 0) return;
                if (stringBuffer.toString().contains(".")) return;
                stringBuffer.append(".");
                break;
            case R.id.seven:
                stringBuffer.append("7");
                break;
            case R.id.eight:
                stringBuffer.append("8");
                break;
            case R.id.nine:
                stringBuffer.append("9");
                break;
            case R.id.ok:
                Toanswer();
                break;
        }
    }

    /*回答*/
    private void Toanswer() {

    }


    @BindView(R.id.tiMu)
    TextView tiMu;

    AoShuDaTiDialog dialog;
    AoShuLianXiNeiRongData.DataBean listBean;

    public AoShuLianXiNeiRongData.DataBean getListBean() {
        return listBean;
    }

    public void setListBean(AoShuLianXiNeiRongData.DataBean listBean) {
        this.listBean = listBean;
    }


}
