package com.test720.grasshoppercollege.module.gongLue.student.smallClass;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.dingDan.BuyClassDingDanActivity;
import com.test720.grasshoppercollege.module.gongLue.student.bean.SmallClassXiangQingData;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.ContactDialog;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.DaGangVideoActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class SmallClassXiangQingActivity extends BaseTwoRecyclerViewActivity<SmallClassXiangQingData.DataBean.CourseBean> {


    String microId = "";
    SmallClassXiangQingData data;
    private ViewHolder viewHolder;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("name") != null)
                    return getIntent().getStringExtra("name");
                return "微课详情";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                microId = getIntent().getStringExtra("micro_id");
                FormBody.Builder httpParams = new FormBody.Builder();
                httpParams.add("micro_id", microId);
                httpParams.add("p", page + "");
                httpParams.add("uid", MyApplication.getmInstance().getUid());
                return httpParams;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategymicroInfo();
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
                return swipeRefreshLayout;
            }
        };
    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, SmallClassXiangQingData.class);
            Glide.with(mcontext).load(data.getData().getPic()).into(viewHolder.ivHeader);
            viewHolder.onLineTitle.setText(data.getData().getName());
            viewHolder.onLineCount.setText(data.getData().getAims());
            String n = data.getData().getSubscribe_count() + "人报名";
            viewHolder.num.setText(n);
            String v = "￥" + data.getData().getVip_price();
            viewHolder.vipPrice.setText(v);
            String p = "￥" + data.getData().getPrice();
            viewHolder.price.setText(p);


            String q = "/" + data.getData().getExpiry_date() + "天";
            viewHolder.priceBeiZhu.setText(q);
            viewHolder.vipPriceBeiZhu.setText(q);


            if (data.getData().getIs_pay().equals("1")) {
                buy.setEnabled(false);
                buy.setText(R.string.yigoumai);
            } else {
                buy.setEnabled(true);
                buy.setText(getString(R.string.liJiGouMai));
            }

            viewHolder.daYi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, WebViewActivity.class);
                    intent.putExtra("title", data.getData().getName());
                    intent.putExtra("path", data.getData().getCommon_question());
                    jumpToActivity(intent, false);
                }
            });
            viewHolder.pinLun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, WebViewActivity.class);
                    intent.putExtra("title", data.getData().getName());
                    intent.putExtra("path", data.getData().getCourse_desc());
                    jumpToActivity(intent, false);
                }
            });
            getList().clear();
            getList().addAll(data.getData().getCourse());
            getAdapter().notifyDataSetChanged();

        }

    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SmallClassXiangQingData data = new Gson().fromJson(str, SmallClassXiangQingData.class);
            getList().addAll(data.getData().getCourse());
            getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 123 && codeIsOne(str, false)) {
            if (getItemClickIndex() != -1) {
                getList().get(getItemClickIndex()).setOpen_up(1);
                getAdapter().notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.UPDATEFRAGMENT || currencyEvent.getWhat() == CurrencyEvent.GONGLUEBUGOK) {
            ShuaXin();
        }
    }

    @Override
    protected void initData() {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.small_class_head, null);
        viewHolder = new ViewHolder(view);
        viewHolder.pinluntext.setText("详情");
        setAdapter(new MyBaseRecyclerAdapter<SmallClassXiangQingData.DataBean.CourseBean>(getList(), mcontext, R.layout.ke_cheng_da_gang_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SmallClassXiangQingData.DataBean.CourseBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getDesc());
                holder.setImageByUrl(R.id.pic, item.getPic());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        });
        getAdapter().setHeaderView(view);
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                setItemClickIndex(position);
                final SmallClassXiangQingData.DataBean.CourseBean item = getList().get(position);
                String s = "我在蝈蝈学苑中学习" + item.getName() + ",你也一起来吧！";
                String[] strs = new String[]{s, item.getName(), HttpUntil.GetIntent().getShare() + (GongLueData.getInstance().getModularType() + 10)};
                boolean can = QuanXianUntil.GetIntent(mcontext).isCanSee(item.getPermissions(), item.isPay(),
                        item.getOpen_up() + "", item.getPoints(), strs, new QuanXianUntil.QuanXianClick() {

                            @Override
                            public void QuanbuyCurr() {
                                buyCurse();
                            }

                            @Override
                            public void QuanbuyGuoGuo() {
                                if (item.getMicro_class_id() == null) return;
                                HttpParams formBuilder = new HttpParams();
                                formBuilder.put("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.put("curr_type", GongLueData.getInstance().getModularType());
                                formBuilder.put("type", "2");
                                formBuilder.put("points", item.getPoints());
                                formBuilder.put("re_id", item.getMicro_class_id());
                                postResponse(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }

                            @Override
                            public void QuanshareCurr() {
                                HttpParams formBuilder = new HttpParams();
                                formBuilder.put("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.put("curr_type", GongLueData.getInstance().getModularType());
                                formBuilder.put("type", "1");
                                formBuilder.put("re_id", item.getMicro_class_id());
                                postResponse(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }
                        });
                if (can) {
                    Intent intent = new Intent(mcontext, DaGangVideoActivity.class);
                    intent.putExtra("micro_class_id", getList().get(position).getMicro_class_id());
                    if (getList().get(position).getState().equals("1")) {
                        intent.putExtra("h5", false);
                    } else {
                        intent.putExtra("h5", true);
                    }
                    intent.putExtra("name", getList().get(position).getName());
                    jumpToActivity(intent, false);
                }

            }
        });
        recyclerView.setAdapter(getAdapter());

    }

    @OnClick({R.id.contactPinTai, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contactPinTai:
                if (data == null) return;
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                ContactDialog contactDialog = new ContactDialog();
                contactDialog.setPath(data.getData().getContact_customer());
                contactDialog.show(getSupportFragmentManager(), "dialog");

                break;
            case R.id.buy:
                buyCurse();
                break;
        }
    }

    private void buyCurse() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("") || data == null) {
            return;
        }
        Intent in = new Intent(mcontext, BuyClassDingDanActivity.class);
        in.putExtra("key_id", microId);
        in.putExtra("type", data.getData().getType());
        in.putExtra("status", "0");
        in.putExtra("startTime", "");
        in.putExtra("endTime", data.getData().getExpiry_date());
        in.putExtra("time_slot", "");
        in.putExtra("day", "");
        startActivityForResult(in, 1);
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_small_class_xiang_qing;
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.buy)
    TextView buy;


    static class ViewHolder {
        @BindView(R.id.iv_header)
        ImageView ivHeader;
        @BindView(R.id.on_line_title)
        TextView onLineTitle;
        @BindView(R.id.on_line_count)
        TextView onLineCount;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.priceBeiZhu)
        TextView priceBeiZhu;
        @BindView(R.id.vipPrice)
        TextView vipPrice;
        @BindView(R.id.vipPriceBeiZhu)
        TextView vipPriceBeiZhu;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.pinluntext)
        TextView pinluntext;
        @BindView(R.id.daYi)
        LinearLayout daYi;
        @BindView(R.id.pinLun)
        LinearLayout pinLun;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
