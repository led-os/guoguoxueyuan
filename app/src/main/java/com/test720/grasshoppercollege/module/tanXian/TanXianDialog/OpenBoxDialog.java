package com.test720.grasshoppercollege.module.tanXian.TanXianDialog;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

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
 * 作者：水东流 编于 2018/12/4
 */
public class OpenBoxDialog extends BaseOkDialogFragment {
    String textStr;
    String imgPath;
    @BindView(R.id.img)
    RoundedImageView img;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.go)
    TextView go;
    @BindView(R.id.ok)
    TextView ok;


    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
        return R.layout.open_box_dialog;
    }

    @Override
    public void start() {
        text.setText(textStr);
        GlideUntil.getInstance().imgUrl(mContext, img, imgPath);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.go, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go:
                Toast.makeText(getContext(), "前往背包开启蝈蝈农场之旅吧", Toast.LENGTH_SHORT).show();
                dismissAllowingStateLoss();
                break;
            case R.id.ok:
                dismissAllowingStateLoss();
                break;
        }
    }
}
