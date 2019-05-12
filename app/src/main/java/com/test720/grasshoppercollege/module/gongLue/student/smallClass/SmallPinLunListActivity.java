package com.test720.grasshoppercollege.module.gongLue.student.smallClass;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.bean.OnLinePingLunData;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.HuiFuActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.PingJiaActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.PingLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import q.rorbin.badgeview.QBadgeView;

public class SmallPinLunListActivity extends BaseRecyclerViewActivity {


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

    PinLunUntil pinLunUntil;
    String line_id = "";
    List<PingLunData> list = new ArrayList<>();

    public QBadgeView zanQB;
    public QBadgeView pinLunQB;

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
        return HttpUntil.GetIntent().getTryListeningInfoComment();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5) {
            if (codeIsOne(str))
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
            OnLinePingLunData data = new Gson().fromJson(str, OnLinePingLunData.class);
            list.clear();
            list.addAll(data.getData().getComment());
            pinLunUntil.getAdapter().notifyDataSetChanged();
            /*点赞，分享，评论*/
            zanQB.bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getInfo().getThumb_up_count())).setBadgeTextSize(7, true);
            pinLunQB.bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getInfo().getComment_count())).setBadgeTextSize(7, true);
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            OnLinePingLunData data = new Gson().fromJson(str, OnLinePingLunData.class);
            list.addAll(data.getData().getComment());
            pinLunUntil.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        line_id = getIntent().getStringExtra("micro_class_id");
        FormBody.Builder httpParams = new FormBody.Builder();
        httpParams.add("micro_class_id", line_id);
        httpParams.add("p", page + "");
        httpParams.add("uid", MyApplication.getmInstance().getUid());
        return httpParams;

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
        pinLunQB = new QBadgeView(this);
        pinLunUntil = new PinLunUntil(list, mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("micro_class_id", line_id);
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("comment_id", commentId);
                postResponse(HttpUntil.GetIntent().getIP() + "small.php/Strategy/tryListeningThumbUp", httpParams, 5, false);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(mcontext, HuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("micro_class_id", line_id);
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(mcontext, PingLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("micro_class_id", line_id);
                jumpToActivity(in, false);
            }
        });
        recyclerView.setAdapter(pinLunUntil.getAdapter());
    }


    private void Zan() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("micro_class_id", line_id);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("comment_id", "0");
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Strategy/tryListeningThumbUp", httpParams, 5, false);
    }

    @OnClick({R.id.zan, R.id.zhuan, R.id.down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zan:
                Zan();
                break;
            case R.id.zhuan:
                break;
            case R.id.down:
                Intent intent = new Intent(mcontext, PingJiaActivity.class);
                intent.putExtra("micro_class_id", line_id);
                jumpToActivity(intent, false);
                break;
        }
    }

}
