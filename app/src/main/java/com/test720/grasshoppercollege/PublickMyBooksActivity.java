package com.test720.grasshoppercollege;

import android.graphics.Typeface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class PublickMyBooksActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tianjia_wanc)
    TextView tianjiaWanc;
    @BindView(R.id.set)
    ImageView set;
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.activity_publick_my_books)
    LinearLayout activityPublickMyBooks;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.emptyTxt)
    TextView emptyTxt;
    @BindView(R.id.lin3)
    LinearLayout lin3;


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_publick_my_books;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ShuaXin();
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

    /*点击完成，的方法*/
    public abstract void wanCheng();

    public abstract String Booktitle();

    @Override
    public void init() {
        super.init();
        setNoGengDuo(true);
    }

    /*编辑课本*/
    public abstract void setBook();

    @Override
    public void recyclerViewInitView() {
        super.recyclerViewInitView();
        if (Booktitle() != null) title.setText(Booktitle());
        Typeface fontFace = Typeface.createFromAsset(getAssets(),
                "fonts/hkhbt.ttf");
        // 字体文件必须是true type font的格式(ttf)；
        // 当使用外部字体却又发现字体没有变化的时候(以 Droid Sans代替)，通常是因为
        // 这个字体android没有支持,而非你的程序发生了错误
        title.setTypeface(fontFace);
        if (Booktitle() != null) emptyTxt.setText("快来添加课本，开启你的" + Booktitle() + "之旅吧！");
        empoty(true);
    }

    /*添加课本跳转方法，在子类中实现*/
    public abstract void add();

    /*数据为空时显示逻辑*/
    public void empoty(Boolean b) {
        if (b) {
            lin1.setVisibility(View.GONE);
            swipeRefresh.setVisibility(View.GONE);
            lin3.setVisibility(View.VISIBLE);
        } else {
            lin1.setVisibility(View.VISIBLE);
            swipeRefresh.setVisibility(View.VISIBLE);
            lin3.setVisibility(View.GONE);
        }
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @OnClick({R.id.add, R.id.back, R.id.tianjia_wanc, R.id.set})
    public void onClick(View view) {
        if (view.getId() != R.id.back) {
            MyApplication.getmInstance().setLoginDialogIsShow(false);
            /*游客不能添加和编辑课本,UID为空，说明是游客*/
            if (MyApplication.getmInstance().getUid() == null || MyApplication.getmInstance().getUid().equals("")) {
                emptyTxt.setText("暂无课程，敬请期待");
                return;
            }
        }
        switch (view.getId()) {
            case R.id.add:
                add();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tianjia_wanc:
                if (set.getVisibility() == View.GONE) {
                    set.setVisibility(View.VISIBLE);
                    tianjiaWanc.setBackgroundResource(R.mipmap.tianjiaicon);
                    tianjiaWanc.setText("添加课本");
                    wanCheng();
                } else {
                    add();
                }
                break;
            case R.id.set:
                set.setVisibility(View.GONE);
                tianjiaWanc.setBackgroundResource(R.mipmap.wanc);
                tianjiaWanc.setText("√完成");
                setBook();
                break;
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0) {
            empoty(false);
        }

    }
}
