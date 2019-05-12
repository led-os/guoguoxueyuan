package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiTeacher;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.PinTaiTeacherData;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/9/14
 */
public class PinTaiFragment extends BaseRecyclerViewFragment {
    MyBaseRecyclerAdapter adapter;

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
        return HttpUntil.GetIntent().platformTeacherIndex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            PinTaiTeacherData data = new Gson().fromJson(str, PinTaiTeacherData.class);
            list.clear();
            list.addAll(data.getData().getCourse());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            PinTaiTeacherData data = new Gson().fromJson(str, PinTaiTeacherData.class);
            list.addAll(data.getData().getCourse());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        assert getArguments() != null;
        httpParams.put("status", getArguments().getInt("status"));
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<PinTaiTeacherData.DataBean.CourseBeanX>(list, mContext, R.layout.ping_tai_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, PinTaiTeacherData.DataBean.CourseBeanX item, int postion) {
                holder.setText(R.id.title, item.getCourse_name());
                final RecyclerView recyclerView = holder.getView(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setNestedScrollingEnabled(false);
                if (postion == 0) recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(new MyBaseRecyclerAdapter<PinTaiTeacherData.DataBean.CourseBeanX.CourseBean>(item.getCourse(), mContext, R.layout.pin_tai_children_item) {
                    @Override
                    public void convert(BaseRecyclerHolder holder, final PinTaiTeacherData.DataBean.CourseBeanX.CourseBean childitem, int postion) {
                        //0-待上课；1-待填写；2-待审核；3-审核成功；4-审核失败
                        switch (getArguments().getInt("status")) {
                            case 0:
                                holder.setText(R.id.status, "上课完");
                                holder.getView(R.id.status).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        classIsOk(childitem.getCourse_id());
                                    }
                                });
                                break;
                            case 1:
                                holder.setText(R.id.status, "写评语");
                                holder.getView(R.id.status).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(mContext, KeChengStudentActivity.class);
                                        intent.putExtra("need", true);
                                        intent.putExtra("course_id", childitem.getCourse_id());
                                        jumpToActivity(intent, false);
                                    }
                                });
                                break;
                            case 2:
                                holder.setText(R.id.status, "待审核");
                                break;
                            case 3:
                                holder.setText(R.id.status, "审核成功");
                                break;
                            case 4:
                                holder.setText(R.id.status, "查看原因");
                                holder.getView(R.id.status).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TextDialog textDialog = new TextDialog();
                                        textDialog.setTitleStr("原因");
                                        textDialog.setTextStr(childitem.getWhy_fail());
                                        textDialog.show(getChildFragmentManager(), "text");
                                    }
                                });
                                break;
                        }

                        holder.setText(R.id.time, childitem.getTimes());
                        holder.setText(R.id.content, childitem.getName());
                        holder.setText(R.id.num, (postion + 1) + "");
                        holder.getView(R.id.see_people).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, KeChengStudentActivity.class);
                                intent.putExtra("need", false);
                                intent.putExtra("course_id", childitem.getCourse_id());
                                jumpToActivity(intent, false);
                            }
                        });
                    }
                });
                holder.getView(R.id.title).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (recyclerView.getVisibility() == View.VISIBLE)
                            recyclerView.setVisibility(View.GONE);
                        else recyclerView.setVisibility(View.VISIBLE);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    /**
     * 上课完成
     *
     * @param course_id 课程id
     */
    private void classIsOk(final String course_id) {
        TextDialog textDialog = new TextDialog();
        textDialog.setTextStr("确认课程已完结吗？");
        textDialog.setOkClick(new TextDialog.OkClick() {
            @Override
            public void click() {
                HttpParams httpParams = new HttpParams();
                httpParams.put("course_id", course_id);
                post(HttpUntil.GetIntent().StrategyhaveClass(), httpParams, 5, true);
            }

            @Override
            public void no() {

            }
        });
        assert getFragmentManager() != null;
        textDialog.show(getFragmentManager(), "classIsOk");

    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        if (what == 5 && codeIsOne(str)) {
            ShuaXin();
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    List<PinTaiTeacherData.DataBean.CourseBeanX> list = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

}
