package com.test720.grasshoppercollege.module.siWeiXuLian;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.GameActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.siWeiXuLian.bean.SiWeiShouData;
import com.test720.grasshoppercollege.module.siWeiXuLian.fragment.ShangPinFragment;
import com.test720.grasshoppercollege.module.siWeiXuLian.fragment.TopFragment;
import com.test720.grasshoppercollege.untils.EndLessOnScrollListener;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class SiWeiXuLianShouActivity extends BaseToolActivity {
    MyBaseRecyclerAdapter adapter;
    HeadOneHolder headOneHolder;
    HeadTwoHolder headTwoHolder;
    View headOne;
    View headTwo;
    private int newCode = 1;
    private int moreCode = 2;

    public EndLessOnScrollListener endLessOnScrollListener;


    private SiWeiShouData.DataBean.ThinkingTopBean thinking_top;
    private List<List<SiWeiShouData.DataBean.ThinkingBean>> thinking = new ArrayList<>();
    private List<SiWeiShouData.DataBean.CateBean> cate = new ArrayList<>();
    private List<SiWeiShouData.DataBean.GameBean> game = new ArrayList<>();

    int itemIndex = -1;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_si_wei_xu_lian_shou;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowToast("顶部左右滑动查看更多视频哦！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thinking_top = null;
        thinking = null;
        cate = null;
        game = null;
    }

    @Override
    public void initView() {
        super.initView();
        headOne = LayoutInflater.from(this).inflate(R.layout.si_wei_xu_lian_one_head, null);
        headTwo = LayoutInflater.from(this).inflate(R.layout.si_wei_xu_lian_head_two, null);
        headOneHolder = new HeadOneHolder(headOne);
        headTwoHolder = new HeadTwoHolder(headTwo);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (i == 0) {
                    return 2;
                } else if (i == 1) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        endLessOnScrollListener = new EndLessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                page = currentPage;
                getData(moreCode);
            }
        };
        recyclerView.addOnScrollListener(endLessOnScrollListener);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (endLessOnScrollListener != null) {
                    page = 1;
                    endLessOnScrollListener.onRefrsh();
                }
                getData(newCode);
                swipeRefresh.setRefreshing(false);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * 顶部初始化
     */
    private void initOneHead() {
        if (thinking_top != null && !thinking_top.getPic().equals("") && !thinking_top.getChild_id().equals("")) {
            Glide.with(mcontext).load(thinking_top.getPic()).into(headOneHolder.topImg);
            headOneHolder.topImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, VideoActivity.class);
                    intent.putExtra("child_id", thinking_top.getChild_id());
                    intent.putExtra("title", thinking_top.getName());
                    jumpToActivity(intent, false);
                }
            });
        }


        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < thinking.size(); i++) {
            TopFragment top = new TopFragment();
            top.setList(thinking.get(i));
            list.add(top);
        }
        headOneHolder.topViewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }

    private void initTwoHead() {
        List<String> titles = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < cate.size(); i++) {
            titles.add(cate.get(i).getName());
            ShangPinFragment fragment = new ShangPinFragment();
            fragment.setList(cate.get(i).getGoods());
            list.add(fragment);
        }
        headTwoHolder.fiveViewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list, titles));
        headTwoHolder.tabs.setupWithViewPager(headTwoHolder.fiveViewPager);
    }

    @Override
    protected void initData() {
        title.setText("思维训练");
        Typeface fontFace = Typeface.createFromAsset(getAssets(),
                "fonts/hkhbt.ttf");
        title.setTypeface(fontFace);

        adapter = new MyBaseRecyclerAdapter<SiWeiShouData.DataBean.GameBean>(game, mcontext, R.layout.si_wei_xu_lian_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SiWeiShouData.DataBean.GameBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setImageByUrl(R.id.img, item.getPic());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                itemIndex = position;
                String[] strings = new String[3];
                strings[0] = game.get(position).getTitle();
                strings[1] = game.get(position).getContent();
                strings[2] = HttpUntil.GetIntent().getShare() + 56;
                if (QuanXianUntil.GetIntent(mcontext).isCanSee(game.get(position).getOpen_up() + "",
                        game.get(position).getRemaining_time(), strings,
                        new QuanXianUntil.QuanXianClick() {
                            @Override
                            public void QuanbuyCurr() {

                            }

                            @Override
                            public void QuanbuyGuoGuo() {

                            }

                            @Override
                            public void QuanshareCurr() {
                                HttpParams httpParams = new HttpParams();
                                httpParams.put("uid", MyApplication.getmInstance().getUid());
                                httpParams.put("game_id", game.get(position).getGame_id());
                                postResponse(HttpUntil.GetIntent().ChildBabyshareUnlock(), httpParams, 123, false);
                            }
                        })) {
                    Intent in = new Intent(mcontext, GameActivity.class);
                    in.putExtra("path", game.get(position).getPath());
                    in.putExtra("time", game.get(position).getRemaining_time());
                    in.putExtra("game_id", game.get(position).getGame_id());
                    jumpToActivity(in, true);

                }
            }
        });
        adapter.setHeaderView(headOne);
        adapter.setmHeadViewTwo(headTwo);
        recyclerView.setAdapter(adapter);
        initOneHead();
        initTwoHead();
        getData(newCode);

    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if (event.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            page = 1;
            getData(newCode);

        }
    }

    public void getData(int what) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("p", page + "");
        builder.add("type", 40 + "");
        builder.add("uid", MyApplication.getmInstance().getUid() + "");
        Post(HttpUntil.GetIntent().ChildThinkingindex(), builder, what);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                shuaXin(str, what);
                break;
            case 2:
                gengDuo(str, what);
                break;
            case 123:
                if (codeIsOne(str) && itemIndex != -1) {
                    game.get(itemIndex).setOpen_up(1);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        if (recyclerView.getAdapter().getItemCount() == 0) {
            root.setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            root.setBackgroundResource(R.color.touming);
        }
    }

    private void gengDuo(String str, int what) {
        if (codeIsOne(str)) {
            SiWeiShouData data = new Gson().fromJson(str, SiWeiShouData.class);
            thinking_top = data.getData().getThinking_top();
            thinking.addAll(data.getData().getThinking());
            cate.addAll(data.getData().getCate());
            game.addAll(data.getData().getGame());
            adapter.notifyDataSetChanged();
        }
    }

    private void shuaXin(String str, int what) {
        if (codeIsOne(str)) {
            SiWeiShouData data = new Gson().fromJson(str, SiWeiShouData.class);
            thinking_top = data.getData().getThinking_top();

            thinking.clear();
            thinking.addAll(data.getData().getThinking());

            cate.clear();
            cate.addAll(data.getData().getCate());

            game.clear();
            game.addAll(data.getData().getGame());
            adapter.notifyDataSetChanged();

            initOneHead();
            initTwoHead();
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


    int page = 1;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.root)
    LinearLayout root;


    static class HeadOneHolder {
        @BindView(R.id.topImg)
        ImageView topImg;
        @BindView(R.id.top_viewPager)
        ViewPager topViewPager;

        HeadOneHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class HeadTwoHolder {
        @BindView(R.id.tabs)
        TabLayout tabs;
        @BindView(R.id.five_view_Pager)
        ViewPager fiveViewPager;

        HeadTwoHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
