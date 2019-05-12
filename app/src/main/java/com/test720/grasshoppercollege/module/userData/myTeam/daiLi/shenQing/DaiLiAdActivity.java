package com.test720.grasshoppercollege.module.userData.myTeam.daiLi.shenQing;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.myTeam.bean.DaiLiAdData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import butterknife.BindView;
import butterknife.OnClick;

public class DaiLiAdActivity extends BaseToolActivity {


    @BindView(R.id.viewF)
    ViewFlipper viewF;
    @BindView(R.id.daiLiBack)
    ImageView daiLiBack;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_dai_li_ad;
    }

    @Override
    protected void initData() {
        HttpParams httpParams = new HttpParams();
        postResponse(HttpUntil.GetIntent().UserapplyAgentIndex(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            DaiLiAdData data = new Gson().fromJson(str, DaiLiAdData.class);
            GlideUntil.getInstance().imgUrl(mcontext, daiLiBack, data.getData().getImg());
            for (int i = 0; i < data.getData().getNotice().size(); i++) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.base_text, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setTextColor(getResources().getColor(R.color.hui));
                textView.setText(data.getData().getNotice().get(i));
                viewF.addView(view);
            }
        }

    }


    @OnClick({R.id.daiLiBack, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.daiLiBack:
                jumpToActivity(DaiLiAdressChooseActivity.class, true);
                break;
            case R.id.back:
                finish();
                break;
        }
    }


}
