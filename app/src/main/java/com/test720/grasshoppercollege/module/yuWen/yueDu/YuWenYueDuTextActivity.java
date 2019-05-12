package com.test720.grasshoppercollege.module.yuWen.yueDu;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.ReadTxtData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.SubjectActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YuWenYueDuTextActivity extends BaseToolActivity {
    @BindView(R.id.back1)
    ImageView back1;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.dati)
    TextView dati;
    ReadTxtData data;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_read_text;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, ReadTxtData.class);
            text.setText(data.getData().getContent());
        }
    }

    @Override
    protected void initData() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("read_id", getIntent().getStringExtra("read_id"));
        Post(HttpUntil.GetIntent().ChineseReadreadContent(), formBuilder, 1);
    }

    @OnClick({R.id.back1, R.id.dati})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.dati:
              /*  Intent intent = new Intent(this, YuWenYueDuTiMuActivity.class);
                intent.putExtra("read_id", getIntent().getStringExtra("read_id"));
                startActivity(intent);*/
                if (data == null) return;
                Intent intent = new Intent(mcontext, SubjectActivity.class);
                intent.putExtra("type","语文阅读");
                intent.putExtra("path", data.getData().getWeb_url());
                intent.putExtra("title", "练习详情");
                jumpToActivity(intent, false);
                break;
        }
    }
}
