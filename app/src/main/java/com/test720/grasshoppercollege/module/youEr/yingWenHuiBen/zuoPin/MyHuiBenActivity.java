package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.zuoPin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.HuiBenBoFangActivity;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.HuiBenNeiRongData;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.MyHuiBenListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;
import com.test720.grasshoppercollege.untils.downUntil.DownDialog;
import com.test720.grasshoppercollege.untils.downUntil.ZipUntil;

import java.io.File;

import butterknife.BindView;
import okhttp3.FormBody;

public class MyHuiBenActivity extends BaseTwoRecyclerViewActivity<MyHuiBenListData.DataBean.InfoBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    HuiBenNeiRongData bookData;//听绘本对应绘本数据；

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
        setAdapter(new MyBaseRecyclerAdapter<MyHuiBenListData.DataBean.InfoBean>(getList(), mcontext, R.layout.my_hui_ben) {
            @Override
            public void convert(BaseRecyclerHolder holder, MyHuiBenListData.DataBean.InfoBean item, int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setText(R.id.time, item.getTime());
                holder.setText(R.id.zanNum, item.getThumb_up_count() + "");
                TextView textView = holder.getView(R.id.status);
                if (item.getStatus().equals("0")) {
                    textView.setText("未发布");
                    textView.setTextColor(getResources().getColor(R.color.red));
                } else {
                    textView.setText("已发布");
                    textView.setTextColor(getResources().getColor(R.color.appColor));
                }
                if (item.getThumb_up_count().equals("1")) {
                    holder.setImageResource(R.id.zanImg, R.mipmap.zan);
                } else {
                    holder.setImageResource(R.id.zanImg, R.mipmap.meizan);
                }
                holder.setImageByUrl(R.id.img, item.getPic());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (getList().get(position).getStatus().equals("0")) {
                    if (!zipVersion(getList().get(position))) {
                        return;
                    }
                    Intent intent1 = new Intent(mcontext, HuiBenBoFangActivity.class);
                    intent1.putExtra("book_id", getList().get(position).getBook_id());
                    intent1.putExtra("status", 2);
                    jumpToActivity(intent1, false);
                } else {
                    Intent intent = new Intent(mcontext, ZuoPingXiangQingActivity.class);
                    intent.putExtra("my_id", getList().get(position).getMy_id());
                    jumpToActivity(intent, false);
                }

            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.FABUCHENGGONG && currencyEvent.getMsg().equals("发布成功")) {
            ShuaXin();
        }
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "我的作品";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("type", getIntent().getIntExtra("type", 52) + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().ChildBookmyWorks();
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
        MyHuiBenListData huiBenListData = new Gson().fromJson(str, MyHuiBenListData.class);
        getList().clear();
        getList().addAll(huiBenListData.getData().getInfo());
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void gengDuo(String str) {
        MyHuiBenListData huiBenListData = new Gson().fromJson(str, MyHuiBenListData.class);
        getList().addAll(huiBenListData.getData().getInfo());
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 15) {
            bookData = new Gson().fromJson(str, HuiBenNeiRongData.class);
        }
    }

    public boolean zipVersion(final MyHuiBenListData.DataBean.InfoBean bean) {
        final BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
        String file = HttpUntil.GetIntent().filePathHead(mcontext) + bean.getPath();
        File f = new File(file);
        String bver = bookDataInsterUntil.getHuiBenVer(mcontext, bean.getZip_name());
        String nber = bean.getVersion_number();
        if (f.exists() && bver != null && nber.equals(bver)) {
            return true;
        }

        //获取课件资料
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("status", 1 + "");
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChildBookcontent(), builder, 15, true);


        final DownDialog downDialog = new DownDialog();
        downDialog.setMoudle("我爱绘本");
        downDialog.setFileName(bean.getName());
        downDialog.setZipName(bean.getZip_name());
        downDialog.setPath(bean.getZip_path());
        downDialog.setZipOkEnvent(new ZipUntil.ZipOkEnvent() {
            @Override
            public void zipOkEnvent() {
                bookDataInsterUntil.saveHuiBenBean(mcontext, getIntent().getStringExtra("book_id"), new Gson().toJson(bookData));
                bookDataInsterUntil.saveHuiBenVer(mcontext, bean.getZip_name(), bean.getVersion_number());
                downDialog.dismissAllowingStateLoss();
            }
        });

        if (!f.exists()) {//资源包不存在，则下载资源包
            downDialog.show(getSupportFragmentManager(), "down");
            return false;
        }
        ShowToast("资源需更新，请下载最新资源包");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ZipUntil zipUntil = new ZipUntil();
                zipUntil.deleZipFile(bean.getPath(), mcontext);
                downDialog.show(getSupportFragmentManager(), "down");
            }
        };
        mainHandler.postDelayed(runnable, 0);
        return false;
    }

}
