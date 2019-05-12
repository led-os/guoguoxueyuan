package com.test720.grasshoppercollege.module.qianDao;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.qianDao.bean.QianDaoShouBean;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QianDaoShouActivity extends BaseToolActivity {

    MyBaseRecyclerAdapter adapter;
    private List<QianDaoShouBean.DataBean.DaysBean> list = new ArrayList<>();
    QianDaoShouBean data;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_qian_dao_shou;
    }

    @Override
    protected void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 7);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new MyBaseRecyclerAdapter<QianDaoShouBean.DataBean.DaysBean>(list, mcontext, R.layout.day_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, QianDaoShouBean.DataBean.DaysBean item, int postion) {
                TextView day = holder.getView(R.id.text);
                if (item.getSign().equals("1")) {
                    day.setBackgroundResource(R.drawable.bule_back_kuang);
                } else {
                    day.setBackgroundResource(R.color.touming);
                }
                if (item.getTask().equals("1")) {
                    holder.getView(R.id.task).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.task).setVisibility(View.INVISIBLE);
                }
                day.setText(item.getDay());
            }
        };
        recyclerView.setAdapter(adapter);

        getData(true);
    }

    private void getData(boolean b) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/IndexUser/signInfo", httpParams, 2, b);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str)) {
                    qianDao.setEnabled(false);
                    MusicUntil musicUntil = new MusicUntil();
                    musicUntil.playRaw(mcontext, R.raw.huibenfabu);
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        QianDaoDOuDialog qianDaoDOuDialog = new QianDaoDOuDialog();
                        qianDaoDOuDialog.setPin(jsonObject.getJSONObject("data").getString("points"));
                        qianDaoDOuDialog.show(getSupportFragmentManager(), "point");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getData(false);
                }
                break;
            case 2:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, QianDaoShouBean.class);
                    list.clear();
                    int d = Integer.parseInt(data.getData().getWeeek());
                    for (int i = 0; i < d; i++) {
                        QianDaoShouBean.DataBean.DaysBean daysBean = new QianDaoShouBean.DataBean.DaysBean();
                        daysBean.setDay("");
                        daysBean.setSign("0");
                        daysBean.setTask("0");
                        list.add(daysBean);
                    }
                    list.addAll(data.getData().getDays());
                    adapter.notifyDataSetChanged();

                    allDay.setText(data.getData().getTotal_sign_day());
                    lianXuDay.setText(data.getData().getSign_day());
                    String t = data.getData().getYear() + "-" + data.getData().getMonth();
                    time.setText(t);
                }
                break;
        }
    }


    @OnClick({R.id.back, R.id.qianDao, R.id.qianDaoJiLv, R.id.qianDaoRenWu, R.id.guize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.qianDao:
                startQianDao();
                break;
            case R.id.qianDaoJiLv:
                jumpToActivity(QianDaoJiLvActivity.class, false);
                break;
            case R.id.qianDaoRenWu:
                if (data == null) return;
                if (data.getData().getToday_task().equals("0")) {
                    ShowToast("今日无任务哦！");
                    return;
                }
                Intent intent7 = new Intent(mcontext, QianDaoRenWuActivity.class);
                intent7.putExtra("sign", data.getData().getSign());
                jumpToActivity(intent7, false);
                break;
            case R.id.guize:
                Intent intent = new Intent(mcontext, WebViewActivity.class);
                intent.putExtra("title", "签到规则");
                intent.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/5");
                jumpToActivity(intent, false);
                break;
        }
    }

    private void startQianDao() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/IndexUser/signIn", httpParams, 1, true);
    }


    @BindView(R.id.allDay)
    TextView allDay;
    @BindView(R.id.lianXuDay)
    TextView lianXuDay;
    @BindView(R.id.qianDao)
    Button qianDao;
    @BindView(R.id.qianDaoJiLv)
    TextView qianDaoJiLv;
    @BindView(R.id.qianDaoRenWu)
    TextView qianDaoRenWu;
    @BindView(R.id.guize)
    TextView guize;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

}
