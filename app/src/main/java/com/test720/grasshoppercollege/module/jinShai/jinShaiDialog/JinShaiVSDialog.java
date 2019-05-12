package com.test720.grasshoppercollege.module.jinShai.jinShaiDialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.CountDownTimerUtil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
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
 * 作者：水东流 编于 2018/11/16
 */
public class JinShaiVSDialog extends BaseOkDialogFragment {
    @BindView(R.id.onePic)
    RoundedImageView onePic;
    @BindView(R.id.oneName)
    TextView oneName;
    @BindView(R.id.oneDouDou)
    TextView oneDouDou;
    @BindView(R.id.twoPic)
    RoundedImageView twoPic;
    @BindView(R.id.twoName)
    TextView twoName;
    @BindView(R.id.twoDoudou)
    TextView twoDoudou;
    @BindView(R.id.go)
    ImageView go;

    String oneNameStr = "";
    String onePicStr = "";
    String oneDouStr = "";
    String twoNameStr = "";
    String twoPicStr = "";
    String twoDouStr = "";

    MusicUntil musicUntil;

    CountDownTimerUtil countDownTimerUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
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
                return Gravity.BOTTOM;
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
        return R.layout.jin_shai_vs_dialog;
    }

    @Override
    public void start() {
        if (musicUntil == null) {
            musicUntil = new MusicUntil();
        }
        oneName.setText(oneNameStr);
        twoName.setText(twoNameStr);
        oneDouDou.setText(oneDouStr);
        twoDoudou.setText(twoDouStr);

        GlideUntil.getInstance().headByUrl(getContext(), onePic, onePicStr);
        GlideUntil.getInstance().headByUrl(getContext(), twoPic, twoPicStr);
        musicUntil.playRaw(getContext(), R.raw.pipeiok);
        if (countDownTimerUtil == null) {
            countDownTimerUtil = new CountDownTimerUtil(3);
            countDownTimerUtil.setTimeInterface(new CountDownTimerUtil.TimeInterface() {
                @Override
                public void upDataUi(int time) {

                }

                @Override
                public void timeIsOver() {
                    musicUntil.setEndComListener(new MusicUntil.EndComListener() {
                        @Override
                        public void endListener() {
                            if (goListener != null) goListener.goListener();
                            dismissAllowingStateLoss();
                        }
                    });
                    musicUntil.playRaw(getActivity(), R.raw.readygo);
                }
            });
            countDownTimerUtil.start();
        }
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (musicUntil != null) musicUntil.canlce();
        if (countDownTimerUtil != null) countDownTimerUtil.over();
    }

    GoListener goListener;

    public void setGoListener(GoListener goListener) {
        this.goListener = goListener;
    }


    @OnClick({R.id.go, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go:
               /* if (musicUntil.getMediaPlayer() != null) return;
                countDownTimerUtil.over();
                musicUntil.playRaw(getActivity(), R.raw.readygo);*/
                break;
            case R.id.back:
                if (goListener != null) goListener.back();
                break;
        }
    }

    public interface GoListener {
        void goListener();

        void back();
    }

    public void setOneNameStr(String oneNameStr) {
        this.oneNameStr = oneNameStr;
    }

    public void setOnePicStr(String onePicStr) {
        this.onePicStr = onePicStr;
    }

    public void setOneDouStr(String oneDouStr) {
        this.oneDouStr = oneDouStr;
    }

    public void setTwoNameStr(String twoNameStr) {
        this.twoNameStr = twoNameStr;
    }

    public void setTwoPicStr(String twoPicStr) {
        this.twoPicStr = twoPicStr;
    }

    public void setTwoDouStr(String twoDouStr) {
        this.twoDouStr = twoDouStr;
    }
}
