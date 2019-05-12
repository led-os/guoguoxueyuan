package com.test720.grasshoppercollege.module.guoguoDou;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

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
 * 作者：水东流 编于 2018/12/1
 */
public class GongXiDialog extends BaseOkDialogFragment {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.close)
    Button close;
    @BindView(R.id.ok)
    Button ok;
    String jinBi = "";
    MusicUntil musicUntil = new MusicUntil();

    public void setJinBi(String jinBi) {
        this.jinBi = jinBi;
    }

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return false;
            }

            @Override
            public int gravity() {
                return Gravity.CENTER;
            }

            @Override
            public int widthLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }

            @Override
            public int heightLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }
        };
    }

    @Override
    public int layoutId() {
        return R.layout.jin_bi_gong_xi_dialog;
    }

    @Override
    public void start() {
        if (jinBi != null) {
            String s = "恭喜你获得" + jinBi + "金币";
            text.setText(s);
        }
        musicUntil.playRaw(getActivity(), R.raw.huibenfabu);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.close, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismissAllowingStateLoss();
                break;
            case R.id.ok:
                if (okClickListener != null) okClickListener.ok();
                dismissAllowingStateLoss();
                break;
        }
    }

    OkClickListener okClickListener;

    public void setOkClickListener(OkClickListener okClickListener) {
        this.okClickListener = okClickListener;
    }

    public interface OkClickListener {
        void ok();
    }

}
