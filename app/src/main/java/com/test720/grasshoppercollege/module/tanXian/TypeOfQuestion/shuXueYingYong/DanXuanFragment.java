package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueYingYong;

import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
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
 * 作者：水东流 编于 2018/11/12
 */
public class DanXuanFragment extends BaseTanXianTiMuFragment {
    @Override
    public void startShow() {
        if (getBean().getSelect_a() != null) {
            a.setVisibility(View.VISIBLE);
            a.setText("A：" + getBean().getSelect_a());
        } else {
            a.setVisibility(View.GONE);
        }
        if (getBean().getSelect_b() != null) {
            b.setVisibility(View.VISIBLE);
            b.setText("B：" + getBean().getSelect_b());
        } else {
            b.setVisibility(View.GONE);
        }
        if (getBean().getSelect_c() != null) {
            c.setVisibility(View.VISIBLE);
            c.setText("C：" + getBean().getSelect_c());
        } else {
            c.setVisibility(View.GONE);
        }
        if (getBean().getSelect_d() != null) {
            d.setVisibility(View.VISIBLE);
            d.setText("D：" + getBean().getSelect_d());
        } else {
            d.setVisibility(View.GONE);
        }
        if (getBean().getQuestion() != null) tiMu.setText(getBean().getQuestion_content());

    }

    @OnClick({R.id.a, R.id.b, R.id.c, R.id.d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a:
                check("A");
                break;
            case R.id.b:
                check("B");
                break;
            case R.id.c:
                check("C");
                break;
            case R.id.d:
                check("D");
                break;
        }
    }

    //*给出了答案*//
    private void check(String s) {
        if (isAnswer()) return;
        if (dialog != null) {
            dialog.show(getChildFragmentManager(), "dialog");
            return;
        }
        dialog = new AoShuDaTiDialog();
        dialog.setS(getBean().getDesc());
        dialog.setRightAnswer(getBean().getAnswer().toString());
        if (getBean().getAnswer().get(0).equals(s)) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        dialog.setRight(isAnswerIsRight());
        dialog.setNextIn(new AoShuDaTiDialog.Next() {
            @Override
            public void nextClick() {
                nextItem();
            }
        });
        dialog.show(getChildFragmentManager(), "dialog");
        setAnswer(true);
    }

    @Override
    public int setlayoutResID() {
        return R.layout.shu_xue_ying_yong_dan_xuan;
    }

    @BindView(R.id.tiMu)
    TextView tiMu;
    @BindView(R.id.a)
    TextView a;
    @BindView(R.id.b)
    TextView b;
    @BindView(R.id.c)
    TextView c;
    @BindView(R.id.d)
    TextView d;
    AoShuDaTiDialog dialog;
}
