package com.test720.grasshoppercollege.module.gongLue;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/7.
 */

public class NianJIDialog extends BaseOkDialogFragment {
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.four)
    TextView four;
    @BindView(R.id.five)
    TextView five;
    @BindView(R.id.six)
    TextView six;


    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return true;
            }

            @SuppressLint("RtlHardcoded")
            @Override
            public int gravity() {
                return Gravity.RIGHT;
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
        return R.layout.nian_ji_list;
    }

    @Override
    public void start() {

    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                if (clickBack != null) clickBack.clickBack(one.getText().toString());
                break;
            case R.id.two:
                if (clickBack != null) clickBack.clickBack(two.getText().toString());
                break;
            case R.id.three:
                if (clickBack != null) clickBack.clickBack(three.getText().toString());
                break;
            case R.id.four:
                if (clickBack != null) clickBack.clickBack(four.getText().toString());
                break;
            case R.id.five:
                if (clickBack != null) clickBack.clickBack(five.getText().toString());
                break;
            case R.id.six:
                if (clickBack != null) clickBack.clickBack(six.getText().toString());
                break;
            case R.id.rel:
                break;
        }
        dismissAllowingStateLoss();
    }

    ClickBack clickBack;

    public void setClickBack(ClickBack clickBack) {
        this.clickBack = clickBack;
    }

    public interface ClickBack {
        void clickBack(String str);
    }
}
