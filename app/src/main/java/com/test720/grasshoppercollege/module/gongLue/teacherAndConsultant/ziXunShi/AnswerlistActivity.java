package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa.WenDaHuiFuActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.AnswerlistData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

/**
 * 在线问答
 */
public class AnswerlistActivity extends BaseTwoRecyclerViewActivity<AnswerlistData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.edit)
    ImageView edit;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "在线问答";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("tid", getIntent().getStringExtra("tid"));
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().teacherAnswerComplete();
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
            AnswerlistData data = new Gson().fromJson(str, AnswerlistData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            AnswerlistData data = new Gson().fromJson(str, AnswerlistData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_answer_list;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<AnswerlistData.DataBean>(getList(), mcontext, R.layout.public_wen_da_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, AnswerlistData.DataBean item, int postion) {
                holder.setText(R.id.name, item.getUser_nickname());
                holder.setText(R.id.time, item.getQuestion_time());
                holder.setHeaderByUrl(R.id.pic, item.getUser_header());
                holder.setText(R.id.content, "\t\t\t\t" + item.getQuestion());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, WenDaHuiFuActivity.class);
                intent.putExtra("answer_id", getList().get(position).getAnswer_id());
                intent.putExtra("type", WenDaHuiFuActivity.WENDA);
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }


    @OnClick(R.id.edit)
    public void onViewClicked() {
        Intent intent = new Intent(mcontext, LaunchAnswerActivity.class);
        intent.putExtra("tid", getIntent().getStringExtra("tid"));
        jumpToActivity(intent, false);

    }
}
