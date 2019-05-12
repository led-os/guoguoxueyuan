package com.test720.grasshoppercollege.module.gongLue.huoDong;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.huoDong.bean.ActivityData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import q.rorbin.badgeview.QBadgeView;

public class HuoDongPinLunListActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.pinlunEditText)
    TextView pinlunEditText;
    @BindView(R.id.dianZan)
    ImageView dianZan;
    @BindView(R.id.one)
    View one;
    @BindView(R.id.zan)
    RelativeLayout zan;
    @BindView(R.id.shareImg)
    ImageView shareImg;
    @BindView(R.id.two)
    View two;
    @BindView(R.id.zhuan)
    RelativeLayout zhuan;
    @BindView(R.id.three)
    View three;
    @BindView(R.id.pinlun)
    RelativeLayout pinlun;
    @BindView(R.id.down)
    LinearLayout down;


    public QBadgeView zanQB;
    public QBadgeView zhuanQB;
    public QBadgeView pinLunQB;

    PinLunUntil pinLunUntil;

    List<PingLunData> list = new ArrayList<>();

    @Override
    public int setCount() {
        return 1;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategyactivityInfo();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5 && codeIsOne(str)) {
            ShuaXin();
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.PINGLUNOK && currencyEvent.getMsg().equals("评论成功")) {
            ShuaXin();
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ActivityData data = new Gson().fromJson(str, ActivityData.class);
            list.clear();
            list.addAll(data.getData().getComment());
            pinLunUntil.getAdapter().notifyDataSetChanged();

            /*点赞，分享，评论*/
            zanQB.bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getThumb_up_count())).setBadgeTextSize(7, true);
            pinLunQB.bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getComment_count())).setBadgeTextSize(7, true);
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ActivityData data = new Gson().fromJson(str, ActivityData.class);
            list.addAll(data.getData().getComment());
            pinLunUntil.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("activity_id", getIntent().getStringExtra("activity_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        return builder;

    }

    @Override
    protected String setTitle() {
        return "评论";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.on_line_pin_lun_dialog;
    }

    @Override
    protected void initData() {
        zanQB = new QBadgeView(this);
        zhuanQB = new QBadgeView(this);
        pinLunQB = new QBadgeView(this);
        pinLunUntil = new PinLunUntil(list, mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                dianZan(commentId);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(mcontext, com.test720.grasshoppercollege.module.gongLue.huoDong.HuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(mcontext, PinLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
                jumpToActivity(in, false);
            }
        });
        recyclerView.setAdapter(pinLunUntil.getAdapter());
    }


    private void dianZan(String commentId) {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) return;
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", commentId);
        builder.add("activity_id", getIntent().getStringExtra("activity_id"));
        Post(HttpUntil.GetIntent().StrategyactivityThumbUp(), builder, 5);
    }

    public void PingJia() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) return;
        Intent in = new Intent(mcontext, PinJiaActivity.class);
        in.putExtra("comment_id", "0");
        in.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
        jumpToActivity(in, false);
    }

    @OnClick({R.id.zan, R.id.zhuan, R.id.down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zan:
                dianZan("0");
                break;
            case R.id.zhuan:
                break;
            case R.id.down:
                PingJia();
                break;
        }
    }

}
