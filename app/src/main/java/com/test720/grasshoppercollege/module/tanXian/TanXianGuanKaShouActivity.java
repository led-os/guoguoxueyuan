package com.test720.grasshoppercollege.module.tanXian;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TanXianDialog.OpenBoxDialog;
import com.test720.grasshoppercollege.module.tanXian.bean.TanXianGuanKaData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class TanXianGuanKaShouActivity extends BaseToolActivity implements BaseTanXianAdapter.OpenBox {


    BaseTanXianAdapter adapter;
    List<List<TanXianGuanKaData.DataBean>> list = new ArrayList<>();
    private boolean isFirst = true;
    LinearLayoutManager linearLayoutManager;
    OpenBoxDialog dialog;
    MusicUntil openMusic = new MusicUntil();

    public abstract int indexType();

    //关卡适配器
    public abstract BaseTanXianAdapter adapter();


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.TANXIANTONGGUANOK) {
            getData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter = null;
        list = null;
        linearLayoutManager = null;
        dialog = null;
        openMusic = null;
        recyclerView = null;
    }

    /**
     * 开箱子
     *
     * @param id ,,箱子id
     */
    ImageView box;

    @Override
    public void openBox(String id, ImageView imageView) {
        box = imageView;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("checkpoint_id", id);
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Adventure/openBox", httpParams, 55, true);
    }

    @Override
    protected void initData() {
        linearLayoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = adapter();
        adapter.setOpenBox(this);
        recyclerView.setAdapter(adapter);
        //关卡列表信息
        getData();
    }

    /**
     * 获取关卡信息
     */
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("class", MyApplication.getmInstance().nianji);
        httpParams.put("index_type", indexType());
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Adventure/checkpointIndex", httpParams, 1, true);
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_tan_xian_guan_ka;
    }

    @Override
    public void GetHttpBackStr(final String str, int what) {
        switch (what) {
            case 55:
                if (codeIsOne(str, false)) {
                    openMusic.playRaw(mcontext, R.raw.openbox);
                    AnimationDrawable anim = (AnimationDrawable) box.getDrawable();
                    anim.start();
                    mainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData();
                            try {
                                JSONObject jsonObject = new JSONObject(str);
                                dialog = new OpenBoxDialog();
                                dialog.setTextStr(jsonObject.getJSONObject("data").getString("prompt"));
                                dialog.setImgPath(jsonObject.getJSONObject("data").getString("pic"));
                                dialog.show(getSupportFragmentManager(), "box");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 1320);

                } else {
                    ShowToast("重新再试试");
                }
                break;
            case 1:
                if (codeIsOne(str, false)) {
                    TanXianGuanKaData data = new Gson().fromJson(str, TanXianGuanKaData.class);
                    LogUtil.logError("探险关卡信息" + str);
                    list.clear();
                    list.addAll(data.getData());
                    Collections.reverse(list);
                    adapter.notifyDataSetChanged();
                    if (isFirst) {
                        recyclerView.setVisibility(View.GONE);
                        linearLayoutManager.scrollToPositionWithOffset(getindex(), 0);
                        recyclerView.setVisibility(View.VISIBLE);
                        isFirst = false;
                    }
                }
                break;
        }
    }

    public BaseTanXianAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseTanXianAdapter adapter) {
        this.adapter = adapter;
    }

    public List<List<TanXianGuanKaData.DataBean>> getList() {
        return list;
    }

    public void setList(List<List<TanXianGuanKaData.DataBean>> list) {
        this.list = list;
    }


    /**
     * 获取脚标
     *
     * @return 脚标
     */
    public int getindex() {
        if (list.size() == 0) return 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            for (int j = list.get(i).size() - 1; j >= 0; j--) {
                if (list.get(i).get(j).getOpen_up() == 0) {
                    return i;
                }
            }
        }
        return list.size() - 1;
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
