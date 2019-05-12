package com.test720.grasshoppercollege.module.tingLiTest;

import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TingLiTypeActivity extends BaseToolActivity {


    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.activity_ting_li_type)
    LinearLayout activityTingLiType;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ting_li_type;
    }

    @Override
    protected void initData() {
        Typeface fontFace = Typeface.createFromAsset(getAssets(),
                "fonts/hkhbt.ttf");
        // 字体文件必须是true type font的格式(ttf)；
        // 当使用外部字体却又发现字体没有变化的时候(以 Droid Sans代替)，通常是因为
        // 这个字体android没有支持,而非你的程序发生了错误

        one.setTypeface(fontFace);
        two.setTypeface(fontFace);
        three.setTypeface(fontFace);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back, R.id.one, R.id.two, R.id.three, R.id.activity_ting_li_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.one:
                jumpToActivity(TiMuActivity.class, false);
                break;
            case R.id.two:
                jumpToActivity(TiMuActivity.class, false);
                break;
            case R.id.three:
                jumpToActivity(TiMuActivity.class, false);
                break;
            case R.id.activity_ting_li_type:
                break;
        }
    }
}
