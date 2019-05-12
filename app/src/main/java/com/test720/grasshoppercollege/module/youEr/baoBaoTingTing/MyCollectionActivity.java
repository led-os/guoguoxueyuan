package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.CollectionlistData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicServiceData;

import butterknife.BindView;
import okhttp3.FormBody;

public class MyCollectionActivity extends BaseTwoRecyclerViewActivity<CollectionlistData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<CollectionlistData.DataBean>(getList(), mcontext, R.layout.bao_bao_shou_chang_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, CollectionlistData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.title, item.getTitle_t());
//                holder.setText(R.id.count, item.getSmall_title());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final CollectionlistData.DataBean item = getList().get(position);
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
        });
        recyclerView.setAdapter(getAdapter());
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
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().ChildBabyListeningmyCollection();
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
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            CollectionlistData data = new Gson().fromJson(str, CollectionlistData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            CollectionlistData data = new Gson().fromJson(str, CollectionlistData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

}
