package com.test720.grasshoppercollege.module.youEr.baoBao;

import android.content.Intent;
import android.view.View;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import butterknife.OnClick;

public class YouErBaoBaoShouActivity extends BaseToolActivity {
    public static int type = 47;//47.汉子，48，拼音，49，英语//幼儿宝宝类型

    @Override
    protected String setTitle() {
        return "点读乐园";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_you_er_bao_bao_shou;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.one, R.id.two, R.id.three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                Intent intent2 = new Intent(mcontext, BaoBaoShouActivity.class);
                type=49;
                jumpToActivity(intent2, false);
                break;
            case R.id.two:
                Intent intent = new Intent(mcontext, BaoBaoShouActivity.class);
                type=47;
                jumpToActivity(intent, false);
                break;
            case R.id.three:
                Intent intent1 = new Intent(mcontext, BaoBaoShouActivity.class);;
                type=48;
                jumpToActivity(intent1, false);
                break;
        }
    }
}
