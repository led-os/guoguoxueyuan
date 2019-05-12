package com.test720.grasshoppercollege.module.gongLue.peiXun.jiaoShi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.PeopleListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class TeacherListActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    MyBaseRecyclerAdapter adapter;
    private List<PeopleListData.DataBean> list = new ArrayList<>();

    @Override
    protected String setTitle() {
        if (GongLueData.getInstance().getType() == GongLueData.JIGOU) {
            switch (GongLueData.getInstance().getTs()) {
                case "1":
                    return "优秀教师";
                default:
                    return "学员列表";
            }
        } else {
            switch (GongLueData.getInstance().getGood()) {
                case "1":
                    return "骨干教师";
                default:
                    return "班级老师";
            }
        }

    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<PeopleListData.DataBean>(list, mcontext, R.layout.teacher_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, PeopleListData.DataBean item, int postion) {
                holder.setHeaderByUrl(R.id.pic, item.getPic());
                if (!item.getClassX().equals("")) {
                    holder.setText(R.id.name, item.getClassX() + "/" + item.getName());
                } else {
                    holder.setText(R.id.name, item.getName());
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, TeacherJieShaoActivity.class);
                intent.putExtra("ts_id", list.get(position).getTs_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int setCount() {
        return 2;
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
        if (GongLueData.getInstance().getType() == GongLueData.JIGOU) {
            return HttpUntil.GetIntent().StrategyTraingoodTS();
        } else {
            return HttpUntil.GetIntent().StrategyChildclassTeacher();
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            PeopleListData data = new Gson().fromJson(str, PeopleListData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            PeopleListData data = new Gson().fromJson(str, PeopleListData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        if (GongLueData.getInstance().getType() == GongLueData.JIGOU) {
            builder.add("school_id", GongLueData.getInstance().getSchoolId());
            builder.add("ts", GongLueData.getInstance().getTs());
            builder.add("p", page + "");
        } else {
            builder.add("school_id", GongLueData.getInstance().getSchoolId());
            builder.add("good", GongLueData.getInstance().getGood());
            builder.add("ts", "1");
            builder.add("p", page + "");
        }
        return builder;
    }


}
