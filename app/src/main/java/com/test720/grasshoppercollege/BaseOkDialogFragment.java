package com.test720.grasshoppercollege;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

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
 * 作者：水东流 编于 2018/8/24
 */
public abstract class BaseOkDialogFragment extends MyDialogFragment {

    public abstract DialogInit dialogInit();

    @LayoutRes
    public abstract int layoutId();

    public interface DialogInit {

        boolean outTouch();

        int gravity();

        int widthLayout();

        int heightLayout();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(mContext, R.style.NoTitleFullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(layoutId());
        if (dialogInit() != null)
            dialog.setCanceledOnTouchOutside(dialogInit().outTouch()); // 外部点击取消
        else
            dialog.setCanceledOnTouchOutside(true);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        assert window != null;
        WindowManager.LayoutParams lp = window.getAttributes();
        if (dialogInit() != null) {
            window.setGravity(dialogInit().gravity());//位置
            lp.width = dialogInit().widthLayout(); // 宽度持平
            lp.height = dialogInit().heightLayout();
        } else {
            window.setGravity(Gravity.BOTTOM);
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        window.setAttributes(lp);
        ButterKnife.bind(this, dialog); // Dialog即View
        return dialog;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        assert window != null;
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
        start();
    }

    public abstract void start();
}
