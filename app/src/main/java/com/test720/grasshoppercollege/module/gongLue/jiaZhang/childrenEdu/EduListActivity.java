package com.test720.grasshoppercollege.module.gongLue.jiaZhang.childrenEdu;

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
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.EduListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class EduListActivity extends BaseTwoRecyclerViewActivity<EduListData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public String[] shareStr = new String[3];//分享数据保存

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "教子有方";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("status", getIntent().getStringExtra("status"));
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategyjiaoziList();
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
        if (codeIsOne(str, false)) {
            EduListData data = new Gson().fromJson(str, EduListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            EduListData data = new Gson().fromJson(str, EduListData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<EduListData.DataBean>(getList(), mcontext, R.layout.gong_lue_jiao_yu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, EduListData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.book, item.getCover());
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.number, item.getTime());
            }
        });

        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final EduListData.DataBean item = getList().get(position);
                //填充分享数据
                shareStr[0] = getList().get(position).getTitle();
                shareStr[1] = getList().get(position).getContent();
                shareStr[2] = HttpUntil.GetIntent().getShare() + 72;

                boolean canSee = QuanXianUntil.GetIntent(mcontext).isCanSee(item.getPermissions()
                        , item.isPay(), item.getOpen_up() + "", item.getPoints(), shareStr, new QuanXianUntil.QuanXianClick() {
                            @Override
                            public void QuanbuyCurr() {
                                Intent intent = new Intent(mcontext, BuyClassActivity.class);
                                intent.putExtra("curr_type", 62);
                                intent.putExtra("type", "4");
                                jumpToActivity(intent, false);
                            }

                            @Override
                            public void QuanbuyGuoGuo() {
                                if (item.getJiaozi_id() == null) return;
                                FormBody.Builder formBuilder = new FormBody.Builder();
                                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.add("curr_type", "62");
                                formBuilder.add("type", "2");
                                formBuilder.add("points", item.getPoints());
                                formBuilder.add("re_id", item.getJiaozi_id());
                                Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }

                            @Override
                            public void QuanshareCurr() {
                                FormBody.Builder formBuilder = new FormBody.Builder();
                                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.add("curr_type", "62");
                                formBuilder.add("type", "1");
                                formBuilder.add("re_id", item.getJiaozi_id());
                                Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }
                        });//标记是否可看

                if (canSee) {
                    Intent in = new Intent(mcontext, EduVideoActivity.class);
                    in.putExtra("id", getList().get(position).getJiaozi_id());
                    in.putExtra("title", getList().get(position).getName());
                    jumpToActivity(in, false);
                }

            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 123 && codeIsOne(str)) {
            ShuaXin();
        }
    }
}
