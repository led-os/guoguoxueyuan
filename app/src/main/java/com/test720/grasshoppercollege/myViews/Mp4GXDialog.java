package com.test720.grasshoppercollege.myViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

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
 * 作者：水东流 编于 2018/12/26
 */
public class Mp4GXDialog extends BaseOkDialogFragment {
    @BindView(R.id.countTwo)
    TextView countTwo;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.share)
    TextView share;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.shareLin)
    LinearLayout shareLin;
    Unbinder unbinder;
    String share_points;//分享果果豆积分
    String points;//视频积分

    Share shareClick;

    String shareTitle, shareBody;

    public void setShareClick(Share shareClick) {
        this.shareClick = shareClick;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public void setShareBody(String shareBody) {
        this.shareBody = shareBody;
    }


    public void setShare_points(String share_points) {
        this.share_points = share_points;
    }

    public void setPoints(String points) {
        this.points = points;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void getSuccess(String s, int what) {

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
        return R.layout.dan_ti_jie_su_pop;
    }


    @Override
    public void start() {
        String s = "    ";
        String ss = "    ";

        if (points != null) {
            s = s + ",\n此次获" + points + "颗果果豆。";
        }
        if (share_points != null) {
            ss = ss + "此版块每天第一次分享可获" + share_points + "颗果果豆,\n(vip可获双倍果果豆)";
        } else {
            shareLin.setVisibility(View.GONE);
        }
        count.setText(s);
        countTwo.setText(ss);
    }

    @OnClick({R.id.share, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share:
                shareClick.share();
                dismissAllowingStateLoss();

                break;
            case R.id.close:
                shareClick.back();
                dismissAllowingStateLoss();
                break;
        }
    }


    public interface Share {
        void share();

        void back();
    }
}