package com.test720.grasshoppercollege.module.yuWen.chengYu;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua.KuiHuaShouYeActivity;
import com.test720.grasshoppercollege.module.yuWen.chengYu.seeVideo.SeeVideoShouActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ChengYuShouActivity extends BaseToolActivity {


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

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_cheng_yu_shou;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.one:
                jumpToActivity(SeeVideoShouActivity.class, false);
                break;
            case R.id.two:
                Intent intent = new Intent(this, ChengYuGuanKaActivity.class);
                intent.putExtra("type", 31);
                jumpToActivity(intent, false);
                break;
            case R.id.three:
                Intent intent1 = new Intent(this, ChengYuGuanKaActivity.class);
                intent1.putExtra("type", 32);
                jumpToActivity(intent1, false);
                break;
            case R.id.four:
                jumpToActivity(KuiHuaShouYeActivity.class, false);
                break;
            case R.id.five:
                Intent intent2 = new Intent(this, ChengYuGuanKaActivity.class);
                intent2.putExtra("type", 33);
                jumpToActivity(intent2, false);
                break;
        }
    }
}
