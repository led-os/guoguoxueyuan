package com.test720.grasshoppercollege.module.gongLue.jiaZhang.myCollection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.bean.MyCollectionData;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi.ZiXunShiXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import okhttp3.FormBody;

public class MyCollectionActivity extends BaseTwoRecyclerViewActivity<MyCollectionData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "咨询师收藏";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("status", "consultant");
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategymyCollection();
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
            MyCollectionData myCollectionData = new Gson().fromJson(str, MyCollectionData.class);
            getList().clear();
            getList().addAll(myCollectionData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MyCollectionData myCollectionData = new Gson().fromJson(str, MyCollectionData.class);
            getList().addAll(myCollectionData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<MyCollectionData.DataBean>(getList(), mcontext, R.layout.zai_xian_zhuan_jia) {
            @Override
            public void convert(BaseRecyclerHolder holder, MyCollectionData.DataBean item, int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.consultant_num, item.getConsulting_number());
                float praise = Float.parseFloat(item.getGood_praise());
                int good = (int) (praise * 100);
                holder.setText(R.id.praise, good + "");
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                TagFlowLayout tagFlowLayout = holder.getView(R.id.flow);
                tagFlowLayout.setAdapter(new TagAdapter<String>(item.getTag()) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.shang_pin_biao_qian, null);
                        TextView textView = view.findViewById(R.id.text);
                        textView.setText(o);
                        textView.setBackgroundResource(R.drawable.button_bian_kuang);
                        textView.setTextSize(12);
                        textView.setTextColor(getResources().getColor(R.color.appColor));
                        return view;
                    }
                });
            }
        });

        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ZiXunShiXiangQingActivity.class);
                intent.putExtra("tid", getList().get(position).getTid());
                intent.putExtra("status", "consultant");
                jumpToActivity(intent, false);
            }
        });

        recyclerView.setAdapter(getAdapter());
    }

}
