package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.ShangChengActivity;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.HuiBenBookData;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.HuiBenNeiRongData;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.zuoPin.ZuoPingXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;
import com.test720.grasshoppercollege.untils.downUntil.DownDialog;
import com.test720.grasshoppercollege.untils.downUntil.ZipUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class HuiBenXiangQingActivity extends BaseRecyclerViewActivity implements View.OnClickListener {

    List<HuiBenBookData.DataBean.RankingBean> strs = new ArrayList<>();
    ViewHolder viewHolder;
    MyBaseRecyclerAdapter adapter;
    int staus = 1;//排行，最新
    HuiBenBookData data;//數據

    HuiBenNeiRongData bookData;//听绘本对应绘本数据
    CountDownLatch countDownLatch;

    File bookfile;//本地文件

    @Override
    protected String setTitle() {
        return "我爱绘本";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        root.setBackgroundResource(R.color.white);
        @SuppressLint("InflateParams") final View head = LayoutInflater.from(mcontext).inflate(R.layout.activity_hui_ben_xiang_qing, null);
        viewHolder = new ViewHolder(head);
        viewHolder.shouChang.setOnClickListener(this);
        viewHolder.tingHui.setOnClickListener(this);
        viewHolder.luHui.setOnClickListener(this);
        viewHolder.shangCheng.setOnClickListener(this);
        viewHolder.paiHang.setOnClickListener(this);
        viewHolder.zuiXin.setOnClickListener(this);

        adapter = new MyBaseRecyclerAdapter<HuiBenBookData.DataBean.RankingBean>(strs, mcontext, R.layout.hui_ben_xiang_qing_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final HuiBenBookData.DataBean.RankingBean item, final int postion) {
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.boNum, item.getClick_count() + "");
                holder.setText(R.id.zanNum, item.getThumb_up_count() + "");
                holder.setText(R.id.time, item.getRelease_time() + "");
                if (item.getIs_thumb_up().equals("1")) {
                    holder.setImageResource(R.id.zanImg, R.mipmap.zan);
                } else {
                    holder.setImageResource(R.id.zanImg, R.mipmap.meizan);
                }

                holder.getView(R.id.zan).setOnClickListener(v -> dianZan(item.getMy_id(), "0", postion));
            }
        };
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(mcontext, ZuoPingXiangQingActivity.class);
            intent.putExtra("my_id", strs.get(position).getMy_id());
            jumpToActivity(intent, false);
        });
        adapter.setHeaderView(head);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.paiHang:
                viewHolder.zuiXin.setTextColor(getResources().getColor(R.color.gwe));
                viewHolder.paiHang.setTextColor(getResources().getColor(R.color.white));
                viewHolder.zuiXin.setBackgroundColor(getResources().getColor(R.color.white));
                viewHolder.paiHang.setBackgroundColor(getResources().getColor(R.color.appColor));
                staus = 1;
                ShuaXin();
                break;
            case R.id.zuiXin:
                viewHolder.zuiXin.setTextColor(getResources().getColor(R.color.white));
                viewHolder.paiHang.setTextColor(getResources().getColor(R.color.gwe));
                viewHolder.zuiXin.setBackgroundColor(getResources().getColor(R.color.appColor));
                viewHolder.paiHang.setBackgroundColor(getResources().getColor(R.color.white));
                staus = 2;
                ShuaXin();
                break;
            case R.id.shouChang:
                shouChang();
                break;
            case R.id.tingHui:
                if (data == null) return;
                if (zipVersion(1)) {
                    Intent intent = new Intent(mcontext, HuiBenBoFangActivity.class);
                    intent.putExtra("book_id", getIntent().getStringExtra("book_id"));
                    intent.putExtra("status", 1);
                    jumpToActivity(intent, false);
                }
                break;
            case R.id.luHui:
                if (data == null) return;
                if (zipVersion(2)) {
                    Intent intent1 = new Intent(mcontext, HuiBenBoFangActivity.class);
                    intent1.putExtra("book_id", getIntent().getStringExtra("book_id"));
                    intent1.putExtra("status", 2);
                    jumpToActivity(intent1, false);
                }
                break;
            case R.id.shangCheng:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(ShangChengActivity.class, false);
                }
                break;
        }
    }

    /**
     * 判断资源包
     */
    DownDialog downDialog;

    public boolean zipVersion(int status) {
        final BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
        String file = HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getPath();
        bookfile = new File(file);
        String bver = bookDataInsterUntil.getHuiBenVer(mcontext, data.getData().getZip_name());
        String nber = data.getData().getInfo().getVersion_number();
        if (bookfile.exists() && bver != null && nber.equals(bver)) {
            return true;
        }

        getBookData(status);

        downDialog = new DownDialog();
        downDialog.setMoudle("我爱绘本");
        downDialog.setZipName(data.getData().getZip_name());
        downDialog.setFileName(data.getData().getInfo().getName());
        downDialog.setPath(data.getData().getZip_path());
        downDialog.setZipOkEnvent(() -> {
            bookDataInsterUntil.saveHuiBenVer(mcontext, data.getData().getZip_name(), data.getData().getInfo().getVersion_number());
            //本地课本
            bookDataInsterUntil.saveHuiBenBean(mcontext, getIntent().getStringExtra("book_id"), new Gson().toJson(bookData));
            downDialog.dismissAllowingStateLoss();
        });

        return false;
    }

    //获取课件资料
    private void getBookData(int status) {
        if (isNetwork(this)) {
            ShowToast("网络异常，请确认网络是否已连接！");
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("status", status + "");
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChildBookcontent(), builder, 15, true);
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

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChildBookbookIndex();
    }

    @Override
    public void shuaXin(String str) {
        DismissDialong();
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, HuiBenBookData.class);
            strs.clear();
            strs.addAll(data.getData().getRanking());
            adapter.notifyDataSetChanged();
            /*收藏*/
            if (data.getData().getInfo().getCollection().equals("1")) {
                Drawable drawable = getResources().getDrawable(R.mipmap.huibenshouchanged);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                viewHolder.shouChang.setCompoundDrawables(drawable, null, null, null);
                viewHolder.shouChang.setText("我已收藏");
            } else {
                Drawable drawable = getResources().getDrawable(R.mipmap.huibenshouchang);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                viewHolder.shouChang.setCompoundDrawables(drawable, null, null, null);
                viewHolder.shouChang.setText("我要收藏");
            }
            viewHolder.boNum.setText(data.getData().getInfo().getClick_count());
            GlideUntil.getInstance().imgUrl(mcontext, viewHolder.bookImg, data.getData().getInfo().getPic());
            viewHolder.bookTitle.setText(data.getData().getInfo().getName());
            viewHolder.boNum.setText(data.getData().getInfo().getClick_count());


        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            HuiBenBookData data = new Gson().fromJson(str, HuiBenBookData.class);
            strs.addAll(data.getData().getRanking());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 5:
                if (codeIsOne(str)) {
                    if (data.getData().getInfo().getCollection().equals("1")) {
                        data.getData().getInfo().setCollection("0");
                        viewHolder.shouChang.setText("我要收藏");
                        Drawable drawable = getResources().getDrawable(R.mipmap.huibenshouchang);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        viewHolder.shouChang.setCompoundDrawables(drawable, null, null, null);
                    } else {
                        data.getData().getInfo().setCollection("1");
                        viewHolder.shouChang.setText("我已收藏");
                        Drawable drawable = getResources().getDrawable(R.mipmap.huibenshouchanged);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        viewHolder.shouChang.setCompoundDrawables(drawable, null, null, null);
                    }
                }
                break;
            case 6:
                if (codeIsOne(str) && index != -1) {
                    ShuaXin();
                }
                break;
            case 15:
                if (codeIsOne(str, false)) {
                    bookData = new Gson().fromJson(str, HuiBenNeiRongData.class);
                    havaBookDataSeeFileIsOk();
                }
                break;
        }
    }

    /**
     * 课本数据已经准备好，查看对应的资源文件是否存在，或者需要更新F
     */
    private void havaBookDataSeeFileIsOk() {
        if (!bookfile.exists()) {//资源包不存在，则下载资源包
            downDialog.show(getSupportFragmentManager(), "down");
            return;
        }

        ShowToast("资源需更新，请下载最新资源包");
        Runnable runnable = () -> {
            ZipUntil zipUntil = new ZipUntil();
            zipUntil.deleZipFile(data.getData().getPath(), mcontext);
            downDialog.show(getSupportFragmentManager(), "down");
        };
        new Thread(runnable).start();
    }

    /**
     * 收藏
     */
    public void shouChang() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("status", "1");
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChildBookcollection(), builder, 5, true);
    }

    /**
     * 点赞
     */
    int index = -1;//点赞位置

    public void dianZan(String myId, String commentId, int index) {
        this.index = index;
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("my_id", myId);
        builder.add("comment_id", commentId);
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChildBookthumbUp(), builder, 6, true);
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("status", staus + "");
        builder.add("p", "" + page);
        builder.add("uid", MyApplication.getmInstance().getUid());
        return builder;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        DismissDialong();
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.FABUCHENGGONG && currencyEvent.getMsg().equals("发布成功")) {
            ShuaXin();
        }
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;

    static class ViewHolder {
        @BindView(R.id.booktitle)
        TextView bookTitle;
        @BindView(R.id.shouChang)
        TextView shouChang;
        @BindView(R.id.bookImg)
        ImageView bookImg;
        @BindView(R.id.boNum)
        TextView boNum;
        @BindView(R.id.tingHui)
        LinearLayout tingHui;
        @BindView(R.id.luHui)
        LinearLayout luHui;

        @BindView(R.id.shangCheng)
        LinearLayout shangCheng;
        @BindView(R.id.paiHang)
        TextView paiHang;
        @BindView(R.id.zuiXin)
        TextView zuiXin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
