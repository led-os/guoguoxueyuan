package com.test720.grasshoppercollege.module.gongLue.peiXun;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.GongLueListActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.PeiXunJiGouJieShaoData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.jiaoShi.TeacherListActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.xiangChe.XiangCeListActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.xiaoXi.XiaoXiListActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.zhaoSheng.ZhaoShengListActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MechanismActivity extends BaseToolActivity {


    private List<PeiXunJiGouJieShaoData.DataBean.NoticeBean> notice = new ArrayList<>();
    PeiXunJiGouJieShaoData data;

    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("title") != null) return getIntent().getStringExtra("title");
        return "机构介绍";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_mechanism;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("title") != null) {
            titleJiGou.setText(getIntent().getStringExtra("title"));
        }
        GongLueData.getInstance().setType(GongLueData.JIGOU);//设置数据缓存类型为培训
        HttpParams httpParams = new HttpParams();
        httpParams.put("school_id", getIntent().getStringExtra("school_id"));
        postResponse(HttpUntil.GetIntent().StrategyTrainschoolInfo(), httpParams, 1, true);
    }

    /**
     * 公告
     */
    private void initGongGao(final String type) {
        for (int i = 0; i < notice.size(); i++) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.pei_xun_gong_gao, null);
            TextView textView = view.findViewById(R.id.text);
            textView.setText(notice.get(i).getName());
            LinearLayout lin = view.findViewById(R.id.lin);
            if (i < notice.size() - 1) {
                i++;
                TextView textViewtwo = view.findViewById(R.id.texttwo);
                textViewtwo.setText(notice.get(i).getName());
            }
            lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, GongLueListActivity.class);
                    intent.putExtra("notice_type", type);
                    intent.putExtra("school_id", getIntent().getStringExtra("school_id"));
                    jumpToActivity(intent, false);
                }
            });
            gongGao.addView(view);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, PeiXunJiGouJieShaoData.class);
            notice.clear();
            notice.addAll(data.getData().getNotice());
            initGongGao(data.getData().getNotice_type());
            Glide.with(mcontext).load(data.getData().getInfo().getPic()).into(img);
            GongLueData.getInstance().setSchoolId(data.getData().getInfo().getSchool_id());
        }

    }


    @OnClick({R.id.jieshao, R.id.keChengTS, R.id.yxTeacher, R.id.schoolActivity, R.id.haveRY, R.id.schoolhj, R.id.zhaoSheng, R.id.chengJiu, R.id.schoolxc, R.id.goodxy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jieshao:
                Intent intent = new Intent(mcontext, JiGouXiangQingActivity.class);
                intent.putExtra("name", data.getData().getInfo().getName());
                intent.putExtra("school_id", data.getData().getInfo().getSchool_id());
                jumpToActivity(intent, false);
                break;
            case R.id.keChengTS:
                GongLueData.getInstance().setTuwen(GongLueData.KCTS);
                Intent intent11 = new Intent(this, XiaoXiListActivity.class);
                intent11.putExtra("title", "课程特色");
                jumpToActivity(intent11, false);
                break;
            case R.id.yxTeacher:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setTs(GongLueData.T);
                jumpToActivity(TeacherListActivity.class, false);
                break;
            case R.id.schoolActivity:
                GongLueData.getInstance().setTuwen(GongLueData.XXHD);
                Intent intent12 = new Intent(this, XiaoXiListActivity.class);
                intent12.putExtra("title", "学校活动");
                jumpToActivity(intent12, false);
                break;
            case R.id.haveRY:
                GongLueData.getInstance().setTuwen(GongLueData.SHRY);
                Intent intent31 = new Intent(mcontext, XiaoXiListActivity.class);
                intent31.putExtra("title", "所获荣誉");
                jumpToActivity(intent31, false);
                break;
            case R.id.schoolhj:
                GongLueData.getInstance().setPhotoType(GongLueData.HJ);
                Intent intent21 = new Intent(mcontext, XiangCeListActivity.class);
                intent21.putExtra("title", "校园环境");
                jumpToActivity(intent21, false);
                break;
            case R.id.zhaoSheng:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setZhaoXQType(GongLueData.ZHAOSHENG);
                Intent intent51 = new Intent(mcontext, ZhaoShengListActivity.class);
                intent51.putExtra("title", "招生信息");
                jumpToActivity(intent51, false);
                break;
            case R.id.chengJiu:
                GongLueData.getInstance().setTuwen(GongLueData.BXCJ);
                Intent intent13 = new Intent(this, XiaoXiListActivity.class);
                intent13.putExtra("title", "办学成就");
                jumpToActivity(intent13, false);
                break;
            case R.id.schoolxc:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setPhotoType(GongLueData.XC);
                Intent intent22 = new Intent(mcontext, XiangCeListActivity.class);
                intent22.putExtra("title", "学校相册");
                jumpToActivity(intent22, false);
                break;
            case R.id.goodxy:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setTs(GongLueData.S);
                jumpToActivity(TeacherListActivity.class, false);
                break;
        }
    }

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.title_ji_gou)
    TextView titleJiGou;
    @BindView(R.id.gongGao)
    ViewFlipper gongGao;

}
