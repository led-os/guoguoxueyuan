package com.test720.grasshoppercollege.module.qianDao;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.qianDao.bean.RenWuData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QianDaoRenWuActivity extends BaseToolActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MyBaseRecyclerAdapter<RenWuData.DataBean> adapter;
    List<RenWuData.DataBean> list = new ArrayList<>();

    String workNum = "";// 去完成的任务num
    String sign = "0";//今日签到状态

    @Override
    protected String setTitle() {
        return "今日任务";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.qian_dao_ren_wu_list;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("sign") != null) sign = getIntent().getStringExtra("sign");
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        adapter = new MyBaseRecyclerAdapter<RenWuData.DataBean>(list, mcontext, R.layout.qian_dao_ren_wu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final RenWuData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                String num = "果果豆+" + item.getPoints();
                holder.setText(R.id.num, num);
                switch (item.getComplete()) {//0-未完成；1-已完成未领取；2-已完成已领取
                    case "0":
                        holder.setText(R.id.btn, "去完成");
                        holder.getView(R.id.btn).setBackgroundResource(R.drawable.bule_back_kuang);
                        holder.getView(R.id.btn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                workNum = item.getNumber();
                                toWork(item.getNumber());
                            }
                        });
                        break;
                    case "1":
                        holder.setText(R.id.btn, "领取");
                        holder.getView(R.id.btn).setBackgroundResource(R.drawable.green_back);
                        holder.getView(R.id.btn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lingQu(item.getNumber());
                            }
                        });
                        break;
                    case "2":
                        holder.setText(R.id.btn, "已领取");
                        holder.getView(R.id.btn).setBackgroundResource(R.drawable.orange_back);
                        holder.getView(R.id.btn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ShowToast("已领取!");
                            }
                        });
                        break;
                }
            }
        };
        recyclerView.setAdapter(adapter);
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    /**
     * 去完成
     */
    private void toWork(String num) {
        if (sign.equals("0")) {
            ShowToast("今日还未签到，请先签到!");
            return;
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("number", num);
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/IndexUser/completeTask", httpParams, 3, true);
    }

    /**
     * 刷新数据
     */
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/IndexUser/taskList", httpParams, 1, true);
    }

    /**
     * 领取
     *
     * @param number 数量
     */
    private void lingQu(String number) {
        if (sign.equals("0")) {
            ShowToast("今日还未签到，请先签到!");
            return;
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("number", number);
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/IndexUser/getTaskPoints", httpParams, 2, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                RenWuData data = new Gson().fromJson(str, RenWuData.class);
                list.clear();
                list.addAll(data.getData());
                adapter.notifyDataSetChanged();
                break;
            case 2:
                if (codeIsOne(str)) {
                    getData();
                }
                break;
            case 3:
                if (codeIsOne(str, false)) {
                    TaskUntil.getInstance().jump(mcontext, workNum);
                }
                break;
        }

    }

}
