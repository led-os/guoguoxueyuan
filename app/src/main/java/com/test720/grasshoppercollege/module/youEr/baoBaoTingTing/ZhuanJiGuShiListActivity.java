package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.dingDan.BuyClassDingDanActivity;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.BaoBaoZhuanJiGuShiListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicServiceData;

import butterknife.BindView;
import okhttp3.FormBody;

public class ZhuanJiGuShiListActivity extends BaseTwoRecyclerViewActivity<BaoBaoZhuanJiGuShiListData.DataBean.ListBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    ImageView img;
    TextView title, smallTitle, price, vipPrice;

    @Override
    protected int setlayoutResID() {
        haveHeader = true;
        return R.layout.base_shua_xin;
    }


    @Override
    protected void initData() {
        final View head = LayoutInflater.from(mcontext).inflate(R.layout.zhuan_ji_gu_shi_head, null);
        title = head.findViewById(R.id.title);
        smallTitle = head.findViewById(R.id.smallTitle);
        price = head.findViewById(R.id.price);
        vipPrice = head.findViewById(R.id.vipPrice);
        img = head.findViewById(R.id.img);
        setAdapter(new MyBaseRecyclerAdapter<BaoBaoZhuanJiGuShiListData.DataBean.ListBean>(getList(), mcontext, R.layout.zhuan_ji_gu_shi_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, BaoBaoZhuanJiGuShiListData.DataBean.ListBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.title, item.getTitle_t());
                holder.setText(R.id.content, item.getSmall_title());
                holder.setText(R.id.time, "  " + item.getTime_length());
                holder.setText(R.id.playNum, "  " + item.getPlay_count());
                holder.setText(R.id.pingLunNum, "  " + item.getComment_count());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }

            }
        });
        getAdapter().setHeaderView(head);
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final BaoBaoZhuanJiGuShiListData.DataBean.ListBean item = getList().get(position);
                setItemClickIndex(position);
                String[] strs = new String[3];
                strs[0] = item.getTitle();
                strs[1] = item.getContent();
                strs[2] = HttpUntil.GetIntent().getShare() + "71";
                boolean can = QuanXianUntil.GetIntent(mcontext).isCanSee(item.getPermissions(), item.getPay(), item.getOpen_up() + "", item.getPoints()
                        , strs, new QuanXianUntil.QuanXianClick() {
                            @Override
                            public void QuanbuyCurr() {
                                Intent in = new Intent(mcontext, BuyClassDingDanActivity.class);
                                in.putExtra("key_id", getIntent().getStringExtra("album_id"));
                                in.putExtra("type", "61");
                                in.putExtra("status", "0");
                                in.putExtra("startTime", "");
                                in.putExtra("endTime", "");
                                in.putExtra("time_slot", "");
                                in.putExtra("day", "");
                                startActivityForResult(in, 1);
                            }

                            @Override
                            public void QuanbuyGuoGuo() {
                                if (item.getCon_id() == null) return;
                                FormBody.Builder formBuilder = new FormBody.Builder();
                                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.add("curr_type", "61");
                                formBuilder.add("type", "2");
                                formBuilder.add("points", item.getPoints());
                                formBuilder.add("re_id", item.getCon_id());
                                Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }

                            @Override
                            public void QuanshareCurr() {
                                if (item.getPoints() == null) return;
                                FormBody.Builder formBuilder = new FormBody.Builder();
                                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.add("curr_type", "61");
                                formBuilder.add("type", "1");
                                formBuilder.add("re_id", item.getCon_id());
                                Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }
                        });
                if (can) {
                    Intent intent = new Intent(mcontext, MusicActivity.class);
                    MusicServiceData.getInstance().setId(getList().get(position).getCon_id());
                    intent.putExtra("updata", "updata");
                    jumpToActivity(intent, false);
                }
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            ShuaXin();
        }
        if (currencyEvent.getWhat() == CurrencyEvent.GONGLUEBUGOK) {
            ShuaXin();
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
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("title") != null)
                    return getIntent().getStringExtra("title");
                return "";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("album_id", getIntent().getStringExtra("album_id"));
                builder.add("p", page + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().ChildBabyListeningalbumIndex();
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
        BaoBaoZhuanJiGuShiListData data = new Gson().fromJson(str, BaoBaoZhuanJiGuShiListData.class);
        getList().clear();
        getList().addAll(data.getData().getList());
        getAdapter().notifyDataSetChanged();
        Glide.with(mcontext).load(data.getData().getInfo().getPic()).into(img);
        title.setText(data.getData().getInfo().getDesc());
        smallTitle.setText("共" + data.getData().getInfo().getCount() + "个故事");
        String p = "￥" + data.getData().getInfo().getPrice();
        price.setText(p);
        String vp = "vip￥" + data.getData().getInfo().getVip_price();
        vipPrice.setText(vp);
    }

    @Override
    public void gengDuo(String str) {
        BaoBaoZhuanJiGuShiListData data = new Gson().fromJson(str, BaoBaoZhuanJiGuShiListData.class);
        getList().addAll(data.getData().getList());
        getAdapter().notifyDataSetChanged();
    }

}
