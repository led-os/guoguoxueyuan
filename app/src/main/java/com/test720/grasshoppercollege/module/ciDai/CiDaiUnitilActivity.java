package com.test720.grasshoppercollege.module.ciDai;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.CiDaiUnitAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.CiDaiNeiRongData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CiDaiBoFangDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class CiDaiUnitilActivity extends BaseToolActivity {
    List<CiDaiNeiRongData.DataBean.ListBean> list = new ArrayList<>();
    public CiDaiUnitAdapter adapter;
    @BindView(R.id.all_book_rel)
    RelativeLayout all_book_rel;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bookPlay)
    LinearLayout bookPlay;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.activity_public_unitl_and_page)
    LinearLayout activityPublicUnitlAndPage;
    CiDaiNeiRongData data;
    CiDaiBoFangDialog daiBoFangDialog;
    float speedF = 1;//语速
    String url = "";


    @Override
    protected String setTitle() {
        return "磁带";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.ci_dai_nei_rong_unit;
    }

    @Override
    protected void initData() {
        adapter = new CiDaiUnitAdapter(list, this) {
            @Override
            public void childrenClick(int groupPosition, int childPosition) {
                url = data.getData().getList().get(groupPosition).getPage().get(childPosition).getPage_voice();
                playUrl();
            }

            @Override
            public void fatherClick(int groupPosition) {
                url = data.getData().getList().get(groupPosition).getUnit_voice();
                playUrl();
            }

        };
        exListView.setAdapter(adapter);

        if (getIntent().getStringExtra("bookName") != null) {
            BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
            data = bookDataInsterUntil.getCiDaiBean(mcontext, getIntent().getStringExtra("bookName"));
            title.setText("(" + data.getData().getInfo().getBook_version() + ")"
                    + data.getData().getInfo().getClassX()
                    + data.getData().getInfo().getStatus());
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("book_id", getIntent().getStringExtra("book_id"));
            Post(HttpUntil.GetIntent().bookContent(), builder, 1);
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        switch (currencyEvent.getWhat()) {
            case CurrencyEvent.CIDAIPLAYALL:
                all_book_rel.setBackgroundResource(R.color.bantoumingblue);
                adapter.setChildrenPos(-1);
                adapter.setFatherPos(-1);
                adapter.notifyDataSetChanged();
                break;
            case CurrencyEvent.CIDAIPLAYUNTIL:
                all_book_rel.setBackgroundResource(R.color.touming);
                break;

        }

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, CiDaiNeiRongData.class);
            title.setText("(" + data.getData().getInfo().getBook_version() + ")"
                    + data.getData().getInfo().getClassX()
                    + data.getData().getInfo().getStatus());
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }


    @OnClick({R.id.bookPlay})
    public void onViewClicked(View view) {
        if (data == null) return;
        switch (view.getId()) {
            case R.id.bookPlay:
                url = data.getData().getInfo().getVoice();
                playUrl();
                EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.CIDAIPLAYALL, "ok"));
                break;
        }
    }

    public void playUrl() {
        url = myPath(url);//替换成本地资源地址
        if (daiBoFangDialog != null) daiBoFangDialog.dismissAllowingStateLoss();
        daiBoFangDialog = null;
        daiBoFangDialog = new CiDaiBoFangDialog();
        daiBoFangDialog.setUrl(url);
        daiBoFangDialog.setSpeedF(speedF);
        daiBoFangDialog.show(getSupportFragmentManager(), "d");
    }

    /**
     * 截取为本地资源
     *
     * @param str。。
     * @return
     */
    public String myPath(String str) {
        LogUtil.logError("截取前" + str);
        String newStr = HttpUntil.GetIntent().filePathHead(mcontext);
        str = newStr + str;
        LogUtil.logError("截取后" + str);
        return str;
    }

}
