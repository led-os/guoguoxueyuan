package com.test720.grasshoppercollege;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class Mp3AndViewPagerActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.viewPager)
    public ViewPager viewPager;
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.activity_mp3_and_view_pager)
    public LinearLayout activityMp3AndViewPager;

    @BindView(R.id.rel)
    RelativeLayout rel;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.full)
    ImageView full;


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_mp3_and_view_pager;
    }


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


    boolean bf = false;

    @OnClick({R.id.play, R.id.full})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (bf) {
                    play.setImageResource(R.mipmap.zt);
                    bf = false;
                } else {
                    play.setImageResource(R.mipmap.bf);
                    bf = true;
                }
                break;
            case R.id.full:
                if (lin.getVisibility() == View.GONE) {
                    lin.setVisibility(View.VISIBLE);
                } else {
                    lin.setVisibility(View.GONE);
                }
                break;
        }
    }


}
