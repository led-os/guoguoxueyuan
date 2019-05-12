package com.test720.grasshoppercollege.module.userData.myTeam.daiLi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.caiWu.ZhuanYEActivity;
import com.test720.grasshoppercollege.module.userData.myTeam.bean.DaiLiShouData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class DaiLiShouActivity extends BaseTwoRecyclerViewActivity<DaiLiShouData.DataBean.ListBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    ViewHolder viewHolder;
    String agentId = "";
    String money = "";

    DaiLiTitleDialog daiLiTitleDialog;

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<DaiLiShouData.DataBean.ListBean>(getList(), mcontext, R.layout.team_yong_jin_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, DaiLiShouData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.name, "时间");
                holder.setText(R.id.time, item.getTime());
                holder.setText(R.id.jine, "收益");
                TextView textView = holder.getView(R.id.beizhu);
                textView.setTextColor(getResources().getColor(R.color.cai_wu));
                if (item.getInout().equals("1")) {
                    holder.setText(R.id.beizhu, "转入" + item.getMoney());
                } else {
                    holder.setText(R.id.beizhu, "转出" + item.getMoney());
                }
            }
        });
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mcontext).inflate(R.layout.dai_li_shou_head, null);
        viewHolder = new ViewHolder(head);
        initViewHeadEvent();
        getAdapter().setHeaderView(head);
        recyclerView.setAdapter(getAdapter());
    }

    /**
     * 提现事件
     */
    private void initViewHeadEvent() {
        viewHolder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (daiLiTitleDialog != null && daiLiTitleDialog.getDialog().isShowing()) {
                    daiLiTitleDialog.dismissAllowingStateLoss();
                } else if (daiLiTitleDialog != null && !daiLiTitleDialog.getDialog().isShowing()) {
                    daiLiTitleDialog.show(getSupportFragmentManager(), "dialog");
                }
            }
        });
        viewHolder.chaXun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, DaiLiTimeChaXunActivity.class);
                intent.putExtra("agentId", agentId);
                intent.putExtra("money", money);
                jumpToActivity(intent, false);
            }
        });
        viewHolder.tiXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money.equals("") || agentId.equals("")) return;
                Intent intent = new Intent(mcontext, ZhuanYEActivity.class);
                intent.putExtra("money", money);
                intent.putExtra("agent_id", agentId);
                intent.putExtra("type", ZhuanYEActivity.DAILI);
                jumpToActivity(intent, false);
            }
        });
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daiLiTitleDialog.show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return null;
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                if (agentId.equals("")) {
                    agentId = getIntent().getStringExtra("agent_id");
                }
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("agent_id", agentId);
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().UseragentIndex();
            }

            @Override
            public int reCount() {
                return 1;
            }

            @Override
            public RecyclerView reRecyclerView() {
                return recyclerView;
            }

            @Override
            public SwipeRefreshLayout reSwipeRefreshLayout() {
                return swipeRefresh;
            }
        };
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            DaiLiShouData data = new Gson().fromJson(str, DaiLiShouData.class);
            getList().clear();
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();
            money = data.getData().getMoney();

            String t = data.getData().getCity() + data.getData().getArea() + "代理收益";
            viewHolder.title.setText(t);
            viewHolder.shouYiPrice.setText(data.getData().getTotal_money());
            viewHolder.quYuMoney.setText(data.getData().getArea_money());
            viewHolder.vipNum.setText(data.getData().getRegister_number());
            viewHolder.quYuNum.setText(data.getData().getAgent_sub_number());
            viewHolder.tiXianMoney.setText(data.getData().getMoney());
            viewHolder.yiTiXianMoney.setText(data.getData().getDraw_money());

            if (data.getData().getLevel().equals("1")) {
                viewHolder.shouYiTxt.setText("我的代理收益");
                String dt = "代理到期时间\n" + data.getData().getExpire_time();
                viewHolder.time.setText(dt);
                String d = "代理区域编号" + data.getData().getArea_number();
                viewHolder.qyNum.setText(d);
            } else {
                viewHolder.two.setVisibility(View.GONE);
                viewHolder.shouYiTxt.setText("我的分代理收益");
                String dt = "分代理到期时间\n" + data.getData().getExpire_time();
                viewHolder.time.setText(dt);
                String d = "分代理区域编号" + data.getData().getArea_number();
                viewHolder.qyNum.setText(d);
            }
            daiLiTitleDialog = new DaiLiTitleDialog();
            daiLiTitleDialog.setArea_list(data.getData().getArea_list());
            daiLiTitleDialog.setDaiLiId(new DaiLiTitleDialog.DaiLiId() {
                @Override
                public void getAngentId(String Id) {
                    agentId = Id;
                    ShuaXin();
                }
            });
        }
    }

    @Override
    public void gengDuo(String str) {
        /*if (codeIsOne(str, false)) {
            DaiLiShouData data = new Gson().fromJson(str, DaiLiShouData.class);
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();
        }*/
    }

    static class ViewHolder {
        @BindView(R.id.back)
        ImageView back;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.tiXian)
        TextView tiXian;
        @BindView(R.id.shouYiTxt)
        TextView shouYiTxt;
        @BindView(R.id.qyNum)
        TextView qyNum;
        @BindView(R.id.shouYiPrice)
        TextView shouYiPrice;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.vipNum)
        TextView vipNum;
        @BindView(R.id.quYuNum)
        TextView quYuNum;
        @BindView(R.id.chaXun)
        TextView chaXun;
        @BindView(R.id.two)
        LinearLayout two;
        @BindView(R.id.quYuMoney)
        TextView quYuMoney;
        @BindView(R.id.tiXianMoney)
        TextView tiXianMoney;
        @BindView(R.id.yiTiXianMoney)
        TextView yiTiXianMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
