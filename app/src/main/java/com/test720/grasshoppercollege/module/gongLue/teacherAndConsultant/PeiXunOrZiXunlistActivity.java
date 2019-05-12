package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.TeacherListData;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.ZhuTiData;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.peiXunTeacher.PeiXunTeacherXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi.ZiXunShiXiangQingActivity;
import com.test720.grasshoppercollege.myViews.PopWindow;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class PeiXunOrZiXunlistActivity extends BaseRecyclerViewActivity {


    List<TeacherListData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    List<String> zhuTiList = new ArrayList<>();
    PopWindow popupWindow;//主题弹框
    PopWindow paiXunPoP;//排序
    int index = 0;//主题标示
    String status = "";
    String tag = "";
    String number_order = "0";

    @Override
    protected String setTitle() {
        return "教师列表";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yu_yue_zhuan_jia_shou;
    }

    @Override
    protected void initData() {
        status = getIntent().getStringExtra("status");
        /*if (status.equals("teacher")) {//培训师
            paiXu.setVisibility(View.GONE);
        } else {//咨询师
            paiXu.setVisibility(View.VISIBLE);
        }*/
        HttpParams httpParams = new HttpParams();
        httpParams.put("status", status);
        postResponse(HttpUntil.GetIntent().StrategyteacherTheme(), httpParams, 8, true);
        adapter = new MyBaseRecyclerAdapter<TeacherListData.DataBean>(list, this, R.layout.zai_xian_zhuan_jia) {
            @Override
            public void convert(BaseRecyclerHolder holder, TeacherListData.DataBean item, int postion) {
                if (getIntent().getStringExtra("status").equals("teacher")) {
                    holder.getView(R.id.call).setVisibility(View.INVISIBLE);
                }
                if (status.equals("teacher")) {
                    holder.setText(R.id.ziXunOrFen, "粉丝数：");
                } else {
                    holder.setText(R.id.ziXunOrFen, "咨询次数：");
                }
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
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (status) {
                    case "teacher":
                        Intent intent = new Intent(mcontext, PeiXunTeacherXiangQingActivity.class);
                        intent.putExtra("tid", list.get(position).getTid());
                        intent.putExtra("status", getIntent().getStringExtra("status"));
                        jumpToActivity(intent, false);
                        break;
                    case "consultant":
                        Intent intent1 = new Intent(mcontext, ZiXunShiXiangQingActivity.class);
                        intent1.putExtra("tid", list.get(position).getTid());
                        intent1.putExtra("status", getIntent().getStringExtra("status"));
                        jumpToActivity(intent1, false);
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        PaiXuInit();
    }


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
        return HttpUntil.GetIntent().StrategygetTeacherList();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 8:
                if (codeIsOne(str, false)) {
                    ZhuTiData zhuTidata = new Gson().fromJson(str, ZhuTiData.class);
                    zhuTiList.clear();
                    zhuTiList.add(getString(R.string.all));
                    zhuTiList.addAll(zhuTidata.getData());

                    @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.zhu_ti_dialog, null);
                    popupWindow = new PopWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    RecyclerView re = view.findViewById(R.id.recyclerView);
                    Button queDing = view.findViewById(R.id.queDing);
                    queDing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tag = zhuTiList.get(index);
                            ShuaXin();
                            popupWindow.dismiss();
                        }
                    });
                    GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
                    //设置为垂直布局，这也是默认的
                    //设置布局管理器
                    re.setLayoutManager(layoutManager);
                    MyBaseRecyclerAdapter zhuTiAdapter = new MyBaseRecyclerAdapter<String>(zhuTiList, this, R.layout.zhuan_jia_zhu_ti_biao_qian) {
                        @Override
                        public void convert(BaseRecyclerHolder holder, String item, final int postion) {
                            holder.setText(R.id.text, item);
                            TextView text = holder.getView(R.id.text);
                            if (index == postion) {
                                text.setTextColor(getResources().getColor(R.color.appColor));
                                text.setBackgroundResource(R.drawable.bule_bian_kuang);
                            } else {
                                text.setTextColor(getResources().getColor(R.color.gwe));
                                text.setBackgroundResource(R.color.white);
                            }
                            text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    index = postion;
                                    notifyDataSetChanged();
                                }
                            });
                        }
                    };

                    re.setAdapter(zhuTiAdapter);
                    // 允许点击外部消失
                    popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bantouming)));//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
                    popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
                    popupWindow.setFocusable(true);
                }
                break;
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            TeacherListData data = new Gson().fromJson(str, TeacherListData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            TeacherListData data = new Gson().fromJson(str, TeacherListData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        status = getIntent().getStringExtra("status");
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("status", status);
        builder.add("p", page + "");

        if (tag.equals("全部")) {
            builder.add("tag", "");
        } else {
            builder.add("tag", tag);
        }

        builder.add("number_order", number_order);
        if (!TextUtils.isEmpty(edittext.getText())) {
            builder.add("key", edittext.getText().toString());
        }
        return builder;
    }


    @OnClick({R.id.shou, R.id.zhuti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shou:
                if (TextUtils.isEmpty(edittext.getText())) {
                    edittext.setError("请正确输入");
                    return;
                }
                ShuaXin();
                break;
            case R.id.zhuti:
                if (popupWindow != null) popupWindow.showAsDropDown(zhuti);
                break;

        }
    }


    /*初始化排序 */
    private void PaiXuInit() {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.pai_xu_dialog, null);
        paiXunPoP = new PopWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        RecyclerView re = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        re.setLayoutManager(linearLayoutManager);
        re.addItemDecoration(new DividerItemDecoration(this, 1));
        final List<String> list = new ArrayList<>();
        list.add("咨询次数由低到高");
        list.add("咨询次数由高到低");
        MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<String>(list, this, R.layout.pai_xun_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {
                holder.setText(R.id.text, item);
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                number_order = position + "";
                ShuaXin();
                paiXu.setText(list.get(position));
                paiXunPoP.dismiss();
            }
        });
        re.setAdapter(adapter);
        // 允许点击外部消失
        paiXunPoP.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bantouming)));//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        paiXunPoP.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        paiXunPoP.setFocusable(true);
    }

    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.zhuti)
    TextView zhuti;
    @BindView(R.id.paiXu)
    TextView paiXu;

}
