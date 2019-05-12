package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.ShangChengActivity;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.BaoBaoTingTingShouData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;
import com.test720.grasshoppercollege.untils.lunBo.Banner;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicService;
import com.test720.grasshoppercollege.untils.musicMedia.MusicServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BaoBaoTingTingShouActivity extends BaseToolActivity {
    private List<Banner> banner = new ArrayList<>();
    private List<BaoBaoTingTingShouData.DataBean.TuijianAlbumBean> tuijian_album = new ArrayList<>();
    private List<BaoBaoTingTingShouData.DataBean.BestContentBean> best_album = new ArrayList<>();

    MyBaseRecyclerAdapter jinXuanAdapter;
    MyBaseRecyclerAdapter zhongBangAdapter;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_bao_bao_ting_ting_shou;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出模块时，关闭音乐播放
        MusicServiceData.getInstance().downTime(0, mcontext);

    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.BAOBAOPAUSE) {
            //关闭动画
            AnimationDrawable animationDrawable = (AnimationDrawable) ting.getDrawable();
            if (animationDrawable != null) animationDrawable.stop();
        } else if (currencyEvent.getWhat() == CurrencyEvent.BAOBAOSTART) {
            //开启动画
            AnimationDrawable animationDrawable = (AnimationDrawable) ting.getDrawable();
            if (animationDrawable != null) animationDrawable.start();
        }
    }

    @Override
    protected void initData() {
        /**启动音乐服务*/
        Intent intent = new Intent(mcontext, MusicService.class);
        startService(intent);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetData();
                swipeRefresh.setRefreshing(false);
            }
        });

        jinXuanRecyclerView.setHasFixedSize(true);
        jinXuanRecyclerView.setNestedScrollingEnabled(false);


        zhongBangRecyclerView.setHasFixedSize(true);
        zhongBangRecyclerView.setNestedScrollingEnabled(false);


        initJingXuan();
        initZhongBang();
        GetData();

    }

    /**
     * 轮播
     */
    boolean lubo = false;

    private void initLunBo() {
        if (lubo) return;
        LunBoUntil lunBoUntil = new LunBoUntil(slider, banner, mcontext);
        lunBoUntil.start();
        lubo = true;
    }

    /**
     * 重磅
     */
    private void initZhongBang() {
        zhongBangRecyclerView.setLayoutManager(new GridLayoutManager(mcontext, 2));
        zhongBangAdapter = new MyBaseRecyclerAdapter<BaoBaoTingTingShouData.DataBean.BestContentBean>(best_album, mcontext, R.layout.baobao_ting_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, BaoBaoTingTingShouData.DataBean.BestContentBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.title, item.getTitle_t());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        };
        zhongBangAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                zhongBangItem(best_album.get(position));
            }
        });
        zhongBangRecyclerView.setAdapter(zhongBangAdapter);
    }

    /**
     * 重磅,点击直接播放
     */
    private void zhongBangItem(final BaoBaoTingTingShouData.DataBean.BestContentBean item) {
        String[] strs = new String[3];
        strs[0] = item.getTitle();
        strs[1] = item.getContent();
        strs[2] = HttpUntil.GetIntent().getShare() + "71";
        boolean can = QuanXianUntil.GetIntent(mcontext).isCanSee(item.getPermissions(), item.isPay(), item.getOpen_up() + "", item.getPoints()
                , strs, new QuanXianUntil.QuanXianClick() {
                    @Override
                    public void QuanbuyCurr() {
                        Intent intent = new Intent(mcontext, BuyClassActivity.class);
                        intent.putExtra("curr_type", getIntent().getIntExtra("type", 61));
                        intent.putExtra("type", "4");
                        jumpToActivity(intent, false);
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
            MusicServiceData.getInstance().setId(item.getCon_id());
            intent.putExtra("updata", "updata");
            jumpToActivity(intent, false);
        }
    }

    /**
     * 精选
     */
    private void initJingXuan() {
        jinXuanRecyclerView.setLayoutManager(new GridLayoutManager(mcontext, 1));
        jinXuanAdapter = new MyBaseRecyclerAdapter<BaoBaoTingTingShouData.DataBean.TuijianAlbumBean>(tuijian_album, mcontext, R.layout.jin_xuan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, BaoBaoTingTingShouData.DataBean.TuijianAlbumBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.title, item.getTitle());
                holder.setText(R.id.num, item.getCount());
            }
        };
        jinXuanAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ZhuanJiGuShiListActivity.class);
                intent.putExtra("album_id", tuijian_album.get(position).getAlbum_id());
                intent.putExtra("title", tuijian_album.get(position).getTitle());
                jumpToActivity(intent, false);

            }
        });
        jinXuanRecyclerView.setAdapter(jinXuanAdapter);
    }


    /**
     * 获取数据
     */
    private void GetData() {
        HttpParams httpParams = new HttpParams();
        postResponse(HttpUntil.GetIntent().ChildBabyListeningindex(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            BaoBaoTingTingShouData data = new Gson().fromJson(str, BaoBaoTingTingShouData.class);
            banner.clear();
            banner.addAll(data.getData().getBanner());
            initLunBo();


            tuijian_album.clear();
            tuijian_album.addAll(data.getData().getTuijian_album());
            jinXuanAdapter.notifyDataSetChanged();

            best_album.clear();
            best_album.addAll(data.getData().getBest_content());
            zhongBangAdapter.notifyDataSetChanged();


        }
    }


    @OnClick({R.id.go, R.id.gushi, R.id.back, R.id.ting, R.id.jinXuanMore, R.id.zhongBangMore, R.id.shouChang, R.id.shangCheng, R.id.fenLei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!TextUtils.isEmpty(edittext.getText()) && !MyApplication.getmInstance().getUid().equals("")) {
                    Intent intent22 = new Intent(mcontext, SearchZhuanJiActivity.class);
                    intent22.putExtra("key", edittext.getText().toString());
                    jumpToActivity(intent22, false);
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.ting:
                if (MusicServiceData.getInstance().getInfo() == null) return;
                Intent intent = new Intent(mcontext, MusicActivity.class);
                intent.putExtra("activity", BaoBaoTingTingShouActivity.class);
                jumpToActivity(intent, false);
                break;
            case R.id.jinXuanMore:
                Intent intent1 = new Intent(mcontext, ZhuanJilistActivity.class);
                intent1.putExtra("status", "1");
                jumpToActivity(intent1, false);
                break;
            case R.id.zhongBangMore:
                Intent intent2 = new Intent(mcontext, ZhongBangListActivity.class);
                jumpToActivity(intent2, false);
                break;
            case R.id.shouChang:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                Intent intent3 = new Intent(mcontext, MyCollectionActivity.class);
                jumpToActivity(intent3, false);
                break;
            case R.id.shangCheng:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(ShangChengActivity.class, true);
                break;
            case R.id.fenLei:
                jumpToActivity(BaoBaoTypeActivity.class, false);
                break;
            case R.id.gushi:
                Intent intent11 = new Intent(mcontext, ZhuanJilistActivity.class);
                intent11.putExtra("title", "蝈蝈故事");
                intent11.putExtra("cate_id", "-1");
                intent11.putExtra("status", "3");
                jumpToActivity(intent11, false);
                break;
        }
    }

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.ting)
    ImageView ting;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.jinXuanMore)
    RelativeLayout jinXuanMore;
    @BindView(R.id.jinXuanRecyclerView)
    RecyclerView jinXuanRecyclerView;
    @BindView(R.id.zhongBangRecyclerView)
    RecyclerView zhongBangRecyclerView;
    @BindView(R.id.zhongBangMore)
    RelativeLayout zhongBangMore;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
}
