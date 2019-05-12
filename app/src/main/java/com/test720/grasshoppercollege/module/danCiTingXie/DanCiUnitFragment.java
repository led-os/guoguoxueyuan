package com.test720.grasshoppercollege.module.danCiTingXie;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.FanYiGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2018/3/26.
 */

public class DanCiUnitFragment extends PubLicChuangGuanFragment {

    List<FanYiGuanKaData.DataBean> list = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    MyBaseRecyclerAdapter adapter;
    String bookId = "";

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public List<FanYiGuanKaData.DataBean> getList() {
        return list;
    }

    public void setList(List<FanYiGuanKaData.DataBean> list) {
        this.list = list;
    }

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void initData() {
        back1.setVisibility(View.GONE);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setPadding(0, 20, 0, 0);
        recyclerView.setLayoutManager(gridLayoutManager);
        init();
    }

    public void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("book_id", bookId);
        post(HttpUntil.GetIntent().Wordindex(), httpParams, 1);
    }

    @Override
    public void shuaXin() {
        super.shuaXin();
        getData();
    }


    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<FanYiGuanKaData.DataBean>(list, getActivity(), R.layout.dan_ci_unit_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, FanYiGuanKaData.DataBean item, int postion) {
                Typeface fontFace = Typeface.createFromAsset(getActivity().getAssets(),
                        "fonts/hkhbt.ttf");
                // 字体文件必须是true type font的格式(ttf)；
                // 当使用外部字体却又发现字体没有变化的时候(以 Droid Sans代替)，通常是因为
                // 这个字体android没有支持,而非你的程序发生了错误
                TextView textView = holder.getView(R.id.text);
                textView.setText(item.getUnit());
                textView.setTypeface(fontFace);
                if (item.getOpen_up() == 1) {
                    holder.setImageResource(R.id.img, R.mipmap.zhuqiu);
                } else {
                    holder.setImageResource(R.id.img, R.mipmap.huiqiu);
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                re_id = list.get(position).getUnit_id();
                points = list.get(position).getPoints();
                String[] strings;//分享信息
                strings = new String[3];
                strings[0] = list.get(position).getTitle();
                strings[1] = list.get(position).getContent();
                strings[2] = HttpUntil.GetIntent().getShare() + 33;
                if (isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "", list.get(position).getPoints(), strings)) {
                    Intent in = new Intent(getActivity(), DanCiTingXieTiMuActivity.class);
                    in.putExtra("unit_id", list.get(position).getUnit_id());
                    jumpToActivity(in, false);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        getData();
    }


    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", 23);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 23);
        httpParams.put("type", 2);
        httpParams.put("points", points);
        httpParams.put("re_id", re_id);
        httpParams.put("book_id", bookId);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void shareCurr() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 23);
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        httpParams.put("book_id", bookId);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String str, int what) {
        if (codeIsOne(str, false)) {
            FanYiGuanKaData guankaData = new Gson().fromJson(str, FanYiGuanKaData.class);
            list.clear();
            list.addAll(guankaData.getData().get(getIndex()));
            adapter.notifyDataSetChanged();
        }
    }
}
