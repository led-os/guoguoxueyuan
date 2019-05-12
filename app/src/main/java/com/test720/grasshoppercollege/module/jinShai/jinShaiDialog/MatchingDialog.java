package com.test720.grasshoppercollege.module.jinShai.jinShaiDialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.HttpBean.JingShaiPiPeiData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.CountDownTimerUtil;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.Date;

import butterknife.BindView;

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
public class MatchingDialog extends BaseOkDialogFragment {
    @BindView(R.id.time)
    TextView time;
    int timeInt = 10;//倒计时间
    String mid;//房间id
    CountDownTimerUtil countDownTimerUtil;
    MatchOkListener matchOkListener;
    JingShaiPiPeiData data;

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setMatchOkListener(MatchOkListener matchOkListener) {
        this.matchOkListener = matchOkListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (matchOkListener != null) matchOkListener.matchOk(data);
        countDownTimerUtil.over();
    }

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return true;
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
        return R.layout.jin_shai_matching_dialog;
    }

    @Override
    public void start() {
        if (countDownTimerUtil == null) {
            countDownTimerUtil = new CountDownTimerUtil(timeInt);
            countDownTimerUtil.setTimeInterface(new CountDownTimerUtil.TimeInterface() {
                @Override
                public void upDataUi(int t) {
                    String s = t + "";
                    time.setText(s);
                    if (t % 3 == 0) {
                        GetPeople();
                    }
                }

                @Override
                public void timeIsOver() {
                    dismissAllowingStateLoss();
                }
            });
            countDownTimerUtil.start();
        }
    }

    /*获取对手信息*/
    private void GetPeople() {
        long time = new Date(System.currentTimeMillis()).getTime();
        HttpParams httpParams = new HttpParams();
        httpParams.put("mid", mid);//请求参数一
        httpParams.put("class", MyApplication.getmInstance().nianji);//请求参数一
        httpParams.put("time", time + "");//请求参数一
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        if (countDownTimerUtil.getTime() < 4) {
            httpParams.put("last", "1");
        }
        post(HttpUntil.GetIntent().startMatch(), httpParams, 2);
    }

    @Override
    public void getSuccess(String s, int what) {
        /*匹配成功*/
        if (codeIsOne(s, false)) {
            if (data == null) {
                data = new Gson().fromJson(s, JingShaiPiPeiData.class);
                countDownTimerUtil.over();
                dismissAllowingStateLoss();
            }
        }
    }


    public interface MatchOkListener {
        void matchOk(JingShaiPiPeiData data);
    }
}
