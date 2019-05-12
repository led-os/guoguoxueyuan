package com.test720.grasshoppercollege.module.userData.myTeam;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.userData.TuiJianActivity;
import com.test720.grasshoppercollege.module.userData.caiWu.ZhuanYEActivity;
import com.test720.grasshoppercollege.module.userData.myTeam.bean.MyTeamBean;
import com.test720.grasshoppercollege.module.userData.myTeam.daiLi.DaiLiShouActivity;
import com.test720.grasshoppercollege.module.userData.myTeam.daiLi.shenQing.DaiLiAdActivity;
import com.test720.grasshoppercollege.module.userData.myTeam.team.MyTeamActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamShouActivity extends BaseToolActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.head)
    RoundedImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.allmoney)
    TextView allmoney;
    @BindView(R.id.status)
    TextView status;

    MyTeamBean myTeamBean;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_team_shou;
    }

    @Override
    protected void initData() {
        GlideUntil.getInstance().headByUrl(mcontext, head, MyApplication.getmInstance().userData.getData().getHeader());
        name.setText(MyApplication.getmInstance().userData.getData().getNickname());
        getData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().UserfenXiaoCenter(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            myTeamBean = new Gson().fromJson(str, MyTeamBean.class);
            String m = "￥" + myTeamBean.getData().getCommis_money();
            money.setText(m);

            String n = "￥" + myTeamBean.getData().getCommission();
            allmoney.setText(n);

            //代理状态 0-审核中；1-审核通过；2-审核失败；3-未申请；4-代理账号禁用状态
            switch (myTeamBean.getData().getDaili_status()) {
                case "0":
                    status.setText("审核中");
                    break;
                case "1":
                    status.setText("代理");
                    break;
                case "2":
                    status.setText("重新申请");
                    break;
                case "3":
                    status.setText("申请代理");
                    break;
                case "4":
                    status.setText("已禁用");
                    break;
            }
        }
    }


    @OnClick({R.id.teamDece, R.id.back, R.id.zhuanYE, R.id.yongJIN, R.id.statusLin, R.id.mingXi, R.id.myTeam, R.id.tui_jian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.zhuanYE:
                if (myTeamBean != null) {
                    Intent intent = new Intent(mcontext, ZhuanYEActivity.class);
                    intent.putExtra("money", myTeamBean.getData().getCommis_money());
                    intent.putExtra("type", ZhuanYEActivity.YONGJIN);
                    jumpToActivity(intent, false);
                }
                break;
            case R.id.yongJIN:
                jumpToActivity(TeamYongJinActivity.class, false);
                break;
            case R.id.statusLin:
                if (myTeamBean != null) {
                    if (myTeamBean.getData().getDaili_status().equals("2") ||
                            myTeamBean.getData().getDaili_status().equals("3"))
                        jumpToActivity(DaiLiAdActivity.class, false);
                    else if (myTeamBean.getData().getDaili_status().equals("1")) {
                        Intent in = new Intent(mcontext, DaiLiShouActivity.class);
                        in.putExtra("agent_id", myTeamBean.getData().getAgent_id());
                        jumpToActivity(in, false);
                    }
                }
                break;
            case R.id.mingXi:
                jumpToActivity(ZhuanChuMingXiActivity.class, false);
                break;
            case R.id.myTeam:
                jumpToActivity(MyTeamActivity.class, false);
                break;
            case R.id.tui_jian:
                jumpToActivity(TuiJianActivity.class, false);
                break;
            case R.id.teamDece:
                if (myTeamBean == null) return;
                Intent intent = new Intent(mcontext, WebViewActivity.class);
                intent.putExtra("path", myTeamBean.getData().getTeam_instructions());
                intent.putExtra("title", "团队说明");
                jumpToActivity(intent, false);
                break;
        }
    }
}
