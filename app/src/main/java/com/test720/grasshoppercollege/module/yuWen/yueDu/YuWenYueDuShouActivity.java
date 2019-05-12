package com.test720.grasshoppercollege.module.yuWen.yueDu;


import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.EngLishReadShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.module.englishYueDu.YueDuShouActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class YuWenYueDuShouActivity extends YueDuShouActivity {

    @Override
    public void getData() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("class", MyApplication.getmInstance().nianji);
        Post(HttpUntil.GetIntent().ChineseReadindex(), formBuilder, 1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            EngLishReadShouData data = new Gson().fromJson(str, EngLishReadShouData.class);
            getList().clear();
            for (int i = 0; i < data.getData().size(); i++) {
                YuWenYueDuShouFragment yueDuShouFragment = new YuWenYueDuShouFragment();
                yueDuShouFragment.setIndex(i);
                getList().add(yueDuShouFragment);
            }
            getFragmentViewPagerAdapter().notifyDataSetChanged();
        }
    }
}
