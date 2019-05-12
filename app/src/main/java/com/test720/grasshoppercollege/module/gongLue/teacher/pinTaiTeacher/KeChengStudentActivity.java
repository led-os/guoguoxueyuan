package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiTeacher;

import android.annotation.SuppressLint;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.KeChengStudentListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KeChengStudentActivity extends BaseToolActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.flow)
    TagFlowLayout flow;
    @BindView(R.id.beiZhu)
    EditText beiZhu;
    @BindView(R.id.needEidt)
    LinearLayout needEidt;

    MyBaseRecyclerAdapter adapter;
    List<KeChengStudentListData.DataBean> list = new ArrayList<>();
    List<String> listUidFlow = new ArrayList<>();
    List<String> listNameFlow = new ArrayList<>();
    TagAdapter tagAdapter;


    @Override
    protected String setTitle() {
        return "学员";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ke_cheng_student;
    }

    @Override
    protected void initData() {
        //是否需要编辑回复
        if (getIntent().getBooleanExtra("need", false)) {
            needEidt.setVisibility(View.VISIBLE);
        } else {
            needEidt.setVisibility(View.GONE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        adapter = new MyBaseRecyclerAdapter<KeChengStudentListData.DataBean>(list, mcontext, R.layout.student_list_item) {
            @Override
            public void convert(final BaseRecyclerHolder holder, final KeChengStudentListData.DataBean item, int postion) {
                if (getIntent().getBooleanExtra("need", false)) {
                    holder.getView(R.id.edit_lin).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.edit_lin).setVisibility(View.GONE);
                }
                //0-未上课;1-已上课;2-缺席
                switch (item.getStatus()) {
                    case "0":
                        break;
                    case "1":
                        holder.setText(R.id.people_status, "已上课");
                        break;
                    case "2":
                        holder.setText(R.id.people_status, "缺席");
                        break;
                }

                holder.setText(R.id.name, item.getNickname());
                //是否是vip
                if (item.getVip().equals("1")) {
                    holder.setImageResource(R.id.vip, R.mipmap.vip);
                } else {
                    holder.setImageResource(R.id.vip, R.mipmap.novip);
                }

                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                Switch s = holder.getView(R.id.status);
                s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            holder.setText(R.id.status_text, "缺席");
                            if (!listUidFlow.contains(item.getUid())) {
                                listUidFlow.add(item.getUid());
                                listNameFlow.add(item.getNickname());
                                tagAdapter.notifyDataChanged();
                            }
                        } else {
                            holder.setText(R.id.status_text, "未缺席");
                            if (listUidFlow.contains(item.getUid())) {
                                listUidFlow.remove(item.getUid());
                                listNameFlow.remove(item.getNickname());
                                tagAdapter.notifyDataChanged();
                            }
                        }
                    }
                });
            }
        };
        tagAdapter = new TagAdapter<String>(listNameFlow) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.student_text, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(str);
                return view;
            }
        };
        flow.setAdapter(tagAdapter);
        recyclerView.setAdapter(adapter);

        HttpParams httpParams = new HttpParams();
        httpParams.put("course_id", getIntent().getStringExtra("course_id"));
        postResponse(HttpUntil.GetIntent().StrategyviewStaff(), httpParams, 1, true);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    KeChengStudentListData data = new Gson().fromJson(str, KeChengStudentListData.class);
                    list.clear();
                    list.addAll(data.getData());
                    adapter.notifyDataSetChanged();
                }
                break;
            case 5:
                if (codeIsOne(str)) {
                    finish();
                }
                break;
        }

    }


    @OnClick(R.id.submission)
    public void onViewClicked() {
        if (TextUtils.isEmpty(beiZhu.getText())) {
            beiZhu.setError("请写评语");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < listUidFlow.size(); i++) {
            if (i != 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(listUidFlow.get(i));
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("course_id", getIntent().getStringExtra("course_id"));
        httpParams.put("content", beiZhu.getText().toString());
        httpParams.put("absent", stringBuffer.toString());
        postResponse(HttpUntil.GetIntent().StrategycommentCourse(), httpParams, 5, true);
    }
}
