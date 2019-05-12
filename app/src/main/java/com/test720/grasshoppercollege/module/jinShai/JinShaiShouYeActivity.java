package com.test720.grasshoppercollege.module.jinShai;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.JingShaiPiPeiData;
import com.test720.grasshoppercollege.HttpBean.JingShaiShouYeData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.jinShai.jinShaiDialog.JinShaiVSDialog;
import com.test720.grasshoppercollege.module.jinShai.jinShaiDialog.MatchingDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class JinShaiShouYeActivity extends BaseToolActivity {


    @BindView(R.id.pic)
    RoundedImageView pic;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.guoguonum)
    TextView guoguonum;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.lev)
    TextView lev;

    MyBaseRecyclerAdapter adapter;//房间列表

    MatchingDialog matchingDialog;//倒计时框
    JinShaiVSDialog jinShaiVSDialog;//匹配信息框

    int index = -1;//点击赛场的标记
    String roomId;
    static String type = "";//竞赛模块
    List<JingShaiShouYeData.DataBean.ListBean> list = new ArrayList<>();
    boolean out = false;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_jin_shai_shou_ye;
    }

    @Override
    protected void initData() {

        root.setBackground(getResources().getDrawable(R.mipmap.pkbj));
        guoguonum.setText(MyApplication.getmInstance().userData.getData().getPoints());
        name.setText(MyApplication.getmInstance().userData.getData().getNickname());
        GlideUntil.getInstance().headByUrl(mcontext, pic, MyApplication.getmInstance().userData.getData().getHeader());
        initHome();

    }

    @Override
    protected void onResume() {
        super.onResume();
        GetListData();
        out = false;

    }


    /**
     * 初始化房间列表
     */
    private void initHome() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        adapter = new MyBaseRecyclerAdapter<JingShaiShouYeData.DataBean.ListBean>(list, mcontext, R.layout.jin_shai_type_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JingShaiShouYeData.DataBean.ListBean item, int postion) {
                TextView textView = holder.getView(R.id.name);
                AssetManager mgr = getContext().getAssets();
                Typeface tf = Typeface.createFromAsset(mgr, "fonts/hkhbt.ttf");
                textView.setTypeface(tf);
                textView.setText(item.getName());
                holder.setText(R.id.level, "等级:" + item.getLimit_level().get(0) + "---" + item.getLimit_level().get(1));
                holder.setText(R.id.doudou, "花费" + item.getPoints() + "颗果果豆");
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (index == position) return;//防止重复点击
                index = position;
                checkUserQualification(list.get(position).getMid());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /*获取房间信息*/
    private void GetListData() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (getIntent().getStringExtra("type") != null) {
            type = getIntent().getStringExtra("type");
        }
        formBuilder.add("type", type);//请求参数一
        formBuilder.add("class", MyApplication.getmInstance().nianji);//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().Match1index(), formBuilder, 1, true);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    JingShaiShouYeData data = new Gson().fromJson(str, JingShaiShouYeData.class);
                    String l = "LV." + data.getData().getInfo().getLevel() + "  经验值：" + data.getData().getInfo().getExperience_value();
                    lev.setText(l);
                    guoguonum.setText(data.getData().getInfo().getPoints());
                    MyApplication.getmInstance().userData.getData().setPoints(data.getData().getInfo().getPoints());

                    list.clear();
                    list.addAll(data.getData().getList());
                    adapter.notifyDataSetChanged();
                }
                break;
            case 3://检查竞赛资格
                if (codeIsOne(str, true)) {
                    recyclerView.setVisibility(View.GONE);
                    if (matchingDialog == null) {
                        matchingDialog = new MatchingDialog();
                        matchingDialog.setMid(list.get(index).getMid());
                        matchingDialog.setMatchOkListener(new MatchingDialog.MatchOkListener() {
                            @Override
                            public void matchOk(final JingShaiPiPeiData data) {
                                matchingDialog = null;
                                if (data == null
                                        || data.getData() == null
                                        || data.getData().getOpponent() == null
                                        || data.getData().getOpponent().getNickname() == null
                                        || data.getData().getOpponent().getNickname().equals("")) {//未匹配到人就退出匹配
                                    recyclerView.setVisibility(View.VISIBLE);
                                    index = -1;
                                    ShowToast("未能匹配到选手!");
                                    return;
                                }
                                root.setBackgroundResource(R.mipmap.kask);
                                roomId = data.getData().getRoom_id();
                                String o = "竞赛费" + list.get(index).getPoints() + "颗果果豆";
                                matchingDialog = null;
                                if (jinShaiVSDialog == null) {
                                    jinShaiVSDialog = new JinShaiVSDialog();
                                    jinShaiVSDialog.setOneDouStr(o);
                                    final String t = "竞赛费" + list.get(index).getPoints() + "颗果果豆";
                                    jinShaiVSDialog.setTwoDouStr(t);
                                    jinShaiVSDialog.setOneNameStr(data.getData().getMy().getNickname());
                                    jinShaiVSDialog.setOnePicStr(data.getData().getMy().getHeader());
                                    jinShaiVSDialog.setTwoNameStr(data.getData().getOpponent().getNickname());
                                    jinShaiVSDialog.setTwoPicStr(data.getData().getOpponent().getHeader());
                                    jinShaiVSDialog.setGoListener(new JinShaiVSDialog.GoListener() {
                                        @Override
                                        public void goListener() {
                                            jinShaiVSDialog = null;
                                            out = true;
                                            index = -1;
                                            Intent intent = new Intent(mcontext, JinSaiActivity.class);
                                            intent.putExtra("room_id", roomId);
                                            jumpToActivity(intent, false);
                                            root.setBackgroundResource(R.mipmap.pkbj);
                                            recyclerView.setVisibility(View.VISIBLE);
                                            jinShaiVSDialog = null;
                                        }

                                        @Override
                                        public void back() {
                                            finish();
                                        }
                                    });
                                    jinShaiVSDialog.show(getSupportFragmentManager(), "vs");
                                }
                            }
                        });
                        matchingDialog.show(getSupportFragmentManager(), "match");
                    }
                }
                break;
        }
    }

    /*检查用户资格*/
    public void checkUserQualification(String mid) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("mid", mid);//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().checkUserQualification(), formBuilder, 3, false);
    }


    /*中途退出*/
    public void tuiChu() {
        if (roomId == null || out) return;
        LogUtil.logError("中途退出");
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("room_id", roomId);//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().MatchoutMatch(), formBuilder, 4, false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        tuiChu();
        roomId = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
