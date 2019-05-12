package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.MyHuiBenShouChangData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class MyShouChangActivity extends BaseTwoRecyclerViewActivity<MyHuiBenShouChangData.DataBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public String re_id;//果果豆购买，或者分享积分所需
    public String points;//购买所需蝈蝈豆

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<MyHuiBenShouChangData.DataBean>(getList(), mcontext, R.layout.hui_ben_shou_chang_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final MyHuiBenShouChangData.DataBean item, final int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setImageByUrl(R.id.img, item.getPic());

                holder.getView(R.id.dele).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getList().remove(postion);
                        quXiaoShouChang(item.getCollection_id());
                        getAdapter().notifyDataSetChanged();
                    }
                });
                holder.getView(R.id.item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClick(postion);
                    }
                });
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    /**
     * 点击进入详情
     *
     * @param position //标示
     */
    private void itemClick(int position) {
        MyHuiBenShouChangData.DataBean item = getList().get(position);
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
                        intent.putExtra("curr_type", 52);
                        intent.putExtra("type", "4");
                        jumpToActivity(intent, false);
                    }

                    @Override
                    public void QuanbuyGuoGuo() {
                        if (re_id == null) return;
                        FormBody.Builder formBuilder = new FormBody.Builder();
                        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                        formBuilder.add("curr_type", 52 + "");
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

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 123:
                if (codeIsOne(str)) ;
                break;
        }
    }

    /**
     * 取消收藏
     *
     * @param collection_id
     */
    private void quXiaoShouChang(String collection_id) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("collection_id", collection_id);
        Post(HttpUntil.GetIntent().ChildBookdelCollection(), builder, 123);
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "我的收藏";
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
                return HttpUntil.GetIntent().ChildBookmyCollection();
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
        MyHuiBenShouChangData huiBenListData = new Gson().fromJson(str, MyHuiBenShouChangData.class);
        getList().clear();
        getList().addAll(huiBenListData.getData());
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void gengDuo(String str) {
        MyHuiBenShouChangData huiBenListData = new Gson().fromJson(str, MyHuiBenShouChangData.class);
        getList().addAll(huiBenListData.getData());
        getAdapter().notifyDataSetChanged();
    }


}