package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.HuiBenListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class HuiBenBookListActivity extends BaseTwoRecyclerViewActivity<HuiBenListData.DataBean.BookBean> {

    public String re_id;//果果豆购买，或者分享积分所需
    public String points;//购买所需蝈蝈豆
    public int status = 1;//0-全部 1-未听 2-未录


    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return null;
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("checkpoint", getIntent().getIntExtra("checkpoint", 1) + "");
                builder.add("status", status + "");
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().ChildBookbookList();
            }

            @Override
            public int reCount() {
                return 2;
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
    protected int setlayoutResID() {
        return R.layout.activity_hui_ben_book_list;
    }

    @Override
    protected void initData() {
        int statu = getIntent().getIntExtra("checkpoint", 1);
        if (statu % 2 == 1) {
            mytitle.setText("中文绘本");
        } else {
            mytitle.setText("英文绘本");
        }

        setAdapter(new MyBaseRecyclerAdapter<HuiBenListData.DataBean.BookBean>(getList(), mcontext, R.layout.hui_book_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, HuiBenListData.DataBean.BookBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getName());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                setItemClickIndex(position);
                HuiBenListData.DataBean.BookBean item = getList().get(position);
                String[] strs = new String[3];
                strs[0] = getList().get(position).getTitle();
                strs[1] = getList().get(position).getContent();
                strs[2] = HttpUntil.GetIntent().getShare() + getIntent().getIntExtra("type", 52);
                re_id = getList().get(position).getBook_id();
                points = getList().get(position).getPoints();
                if (QuanXianUntil.GetIntent(mcontext).isCanSee(item.getPermissions(), item.isPay(), item.getOpen_up() + "", item.getPoints(), strs,
                        new QuanXianUntil.QuanXianClick() {
                            @Override
                            public void QuanbuyCurr() {
                                Intent intent = new Intent(mcontext, BuyClassActivity.class);
                                intent.putExtra("curr_type", getIntent().getIntExtra("type", 52));
                                intent.putExtra("type", "4");
                                jumpToActivity(intent, false);
                            }

                            @Override
                            public void QuanbuyGuoGuo() {
                                if (re_id == null) return;
                                FormBody.Builder formBuilder = new FormBody.Builder();
                                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.add("curr_type", getIntent().getIntExtra("type", 52) + "");
                                formBuilder.add("type", "2");
                                formBuilder.add("points", points);
                                formBuilder.add("re_id", re_id);
                                Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }

                            @Override
                            public void QuanshareCurr() {
                                if (points == null) return;
                                FormBody.Builder formBuilder = new FormBody.Builder();
                                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.add("curr_type", getIntent().getIntExtra("type", 52) + "");
                                formBuilder.add("type", "1");
                                formBuilder.add("re_id", re_id);
                                Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }
                        })) {
                    Intent intent = new Intent(mcontext, HuiBenXiangQingActivity.class);
                    intent.putExtra("book_id", getList().get(position).getBook_id());
                    intent.putExtra("type", getIntent().getIntExtra("type", 52));
                    jumpToActivity(intent, false);
                }
            }
        });
        recyclerView.setAdapter(getAdapter());
    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            HuiBenListData data = new Gson().fromJson(str, HuiBenListData.class);
            name.setText(data.getData().getInfo().getName());
            yiTing.setText(data.getData().getInfo().getNumber_tin() + "");
            yiLu.setText(data.getData().getInfo().getNumber_lu() + "");
            getList().clear();
            getList().addAll(data.getData().getBook());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            HuiBenListData data = new Gson().fromJson(str, HuiBenListData.class);
            getList().addAll(data.getData().getBook());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 123 && codeIsOne(str) && getItemClickIndex() != -1) {
            getList().get(getItemClickIndex()).setOpen_up(1);
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            ShuaXin();
        }
    }

    @OnClick({R.id.back, R.id.myZuoPin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.myZuoPin:
                zuoPingType();
                break;
        }
    }

    /**
     * 設置
     */
    private void zuoPingType() {
        View LanHead = LayoutInflater.from(this).inflate(R.layout.hui_ben_type, null);
        final PopupWindow pop = new PopupWindow(LanHead);
        LanHead.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 0;
                myZuoPin.setText("全部");
                ShuaXin();
                pop.dismiss();
            }
        });
        LanHead.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
                myZuoPin.setText("未听");
                ShuaXin();
                pop.dismiss();
            }
        });
        LanHead.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
                myZuoPin.setText("未录");
                ShuaXin();
                pop.dismiss();
            }
        });
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.touming)));
        pop.setOutsideTouchable(true);
        pop.setTouchable(true);
        int[] xy = new int[2];
        myZuoPin.getLocationOnScreen(xy);
        pop.showAtLocation(swipeRefresh, Gravity.NO_GRAVITY, xy[0], xy[1] + myZuoPin.getHeight());
    }

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.myZuoPin)
    TextView myZuoPin;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.mytitle)
    TextView mytitle;
    @BindView(R.id.yiTing)
    TextView yiTing;
    @BindView(R.id.yiLu)
    TextView yiLu;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

}
