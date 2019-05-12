package com.test720.grasshoppercollege.module.gongLue.youEr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.test720.grasshoppercollege.module.gongLue.peiXun.JiGouXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.jiaoShi.TeacherJieShaoActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.jiaoShi.TeacherListActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.xiaoXi.XiaoXiListActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.zhaoSheng.ZhaoShengListActivity;
import com.test720.grasshoppercollege.module.gongLue.peiXun.zhaoSheng.ZhaoShengXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.youEr.bean.YouErJieShaoData;
import com.test720.grasshoppercollege.module.gongLue.youEr.classXC.ClassListActivity;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KindergartenActivity extends BaseToolActivity {

    private List<YouErJieShaoData.DataBean.NoticeBean> notice = new ArrayList<>();
    YouErJieShaoData data;

    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("title") != null) return getIntent().getStringExtra("title");
        return "幼儿园介绍";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_kindergarten;
    }

    @Override
    protected void initData() {
        GongLueData.getInstance().setType(GongLueData.SCHOOL);
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("school_id", getIntent().getStringExtra("school_id"));
        postResponse(HttpUntil.GetIntent().StrategyChildschoolInfo(), httpParams, 1, true);
    }

    /**
     * 关注,取消关注
     */
    private void GuanZhu() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) return;

        if (data != null && !data.getData().getInfo().getGuanzhu().equals("1")) {
            TextDialog textDialog = new TextDialog();
            textDialog.setTextStr(data.getData().getInfo().getGuanzhu_time_limit());
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    HttpParams httpParams = new HttpParams();
                    httpParams.put("uid", MyApplication.getmInstance().getUid());
                    httpParams.put("school_id", getIntent().getStringExtra("school_id"));
                    postResponse(HttpUntil.GetIntent().StrategyChildcloseGuanZhu(), httpParams, 5, true);
                }

                @Override
                public void no() {

                }
            });
            textDialog.show(getSupportFragmentManager(), "guanZhu");
        } else {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            httpParams.put("school_id", getIntent().getStringExtra("school_id"));
            postResponse(HttpUntil.GetIntent().StrategyChildcloseGuanZhu(), httpParams, 5, true);
        }
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
                    intent.putExtra("school_id", getIntent().getStringExtra("school_id"));
                    intent.putExtra("notice_type", type);
                    jumpToActivity(intent, false);
                }
            });
            gongGao.addView(view);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, YouErJieShaoData.class);
                    notice.clear();
                    notice.addAll(data.getData().getNotice());
                    initGongGao(data.getData().getNotice_type());
                    Glide.with(mcontext).load(data.getData().getInfo().getPic()).into(img);
                    GongLueData.getInstance().setSchoolId(data.getData().getInfo().getSchool_id());
                    if (data.getData().getInfo().getGuanzhu().equals("1")) {
                        guanZhu.setText("取消关注");
                        Drawable drawable = getResources().getDrawable(R.mipmap.aixin2);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        guanZhu.setCompoundDrawables(null, null, drawable, null);
                    } else {
                        guanZhu.setText("添加关注");
                        Drawable drawable = getResources().getDrawable(R.mipmap.aixinsc);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        guanZhu.setCompoundDrawables(null, null, drawable, null);
                    }
                }
                break;
            case 5:
                if (codeIsOne(str)) {
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        data.getData().getInfo().setGuanzhu(jsonObject.getJSONObject("data").getString("guanzhu"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (data.getData().getInfo().getGuanzhu().equals("1")) {
                        guanZhu.setText("取消关注");
                        Drawable drawable = getResources().getDrawable(R.mipmap.aixin2);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        guanZhu.setCompoundDrawables(null, null, drawable, null);
                    } else {
                        guanZhu.setText("添加关注");
                        Drawable drawable = getResources().getDrawable(R.mipmap.aixinsc);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        guanZhu.setCompoundDrawables(null, null, drawable, null);
                    }
                }
                break;
        }

    }


    @OnClick({R.id.xingQu, R.id.guanZhu, R.id.jieshao, R.id.zhaosheng, R.id.huodong, R.id.yuanZhang, R.id.teacher, R.id.rongYu, R.id.teSe, R.id.classXC, R.id.classTeacher, R.id.shiPu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xingQu:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setZhaoXQType(GongLueData.XINGQU);
                Intent intent12 = new Intent(mcontext, ZhaoShengListActivity.class);
                intent12.putExtra("title", "兴趣班");
                jumpToActivity(intent12, false);
                break;
            case R.id.guanZhu:
                GuanZhu();
                break;
            case R.id.jieshao:
                Intent intent10 = new Intent(mcontext, JiGouXiangQingActivity.class);
                intent10.putExtra("name", data.getData().getInfo().getName());
                intent10.putExtra("school_id", data.getData().getInfo().getSchool_id());
                jumpToActivity(intent10, false);
                break;
            case R.id.zhaosheng:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setZhaoXQType(GongLueData.ZHAOSHENG);
                Intent intent6 = new Intent(mcontext, ZhaoShengXiangQingActivity.class);
                intent6.putExtra("name", "招生信息");
                jumpToActivity(intent6, false);

                break;
            case R.id.huodong:
                GongLueData.getInstance().setTuwen(GongLueData.HDZQ);
                Intent intent51 = new Intent(mcontext, XiaoXiListActivity.class);
                intent51.putExtra("title", "幼儿园活动");
                jumpToActivity(intent51, false);
                break;
            case R.id.yuanZhang:
                Intent intent = new Intent(mcontext, TeacherJieShaoActivity.class);
                intent.putExtra("school_id", data.getData().getInfo().getSchool_id());
                jumpToActivity(intent, false);
                break;
            case R.id.teacher:
                GongLueData.getInstance().setGood(GongLueData.GG);
                jumpToActivity(TeacherListActivity.class, false);
                break;
            case R.id.rongYu:
                GongLueData.getInstance().setTuwen(GongLueData.YEYRY);
                Intent intent61 = new Intent(mcontext, XiaoXiListActivity.class);
                intent61.putExtra("title", "幼儿园荣誉");
                jumpToActivity(intent61, false);
                break;
            case R.id.teSe:
                GongLueData.getInstance().setTuwen(GongLueData.YEYTS);
                Intent intent23 = new Intent(this, XiaoXiListActivity.class);
                intent23.putExtra("title", "幼儿园特色");
                jumpToActivity(intent23, false);
                break;
            case R.id.classXC:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                if (data != null && data.getData().getInfo().getGuanzhu().equals("1")) {
                    GongLueData.getInstance().setPhotoType(GongLueData.XC);
                    jumpToActivity(ClassListActivity.class, false);
                } else {
                    ShowToast("关注后才可查看");
                }
                break;
            case R.id.classTeacher:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                if (data != null && data.getData().getInfo().getGuanzhu().equals("1")) {
                    GongLueData.getInstance().setGood(GongLueData.QT);
                    jumpToActivity(TeacherListActivity.class, false);
                } else {
                    ShowToast("关注后才可查看");
                }
                break;
            case R.id.shiPu:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                if (data != null && data.getData().getInfo().getGuanzhu().equals("1")) {
                    jumpToActivity(MeiZhouShiPuActivity.class, false);
                } else {
                    ShowToast("关注后才可查看");
                }
                break;
        }
    }


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.gongGao)
    ViewFlipper gongGao;
    @BindView(R.id.guanZhu)
    TextView guanZhu;
}
