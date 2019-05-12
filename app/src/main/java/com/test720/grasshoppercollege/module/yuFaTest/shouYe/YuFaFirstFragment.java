package com.test720.grasshoppercollege.module.yuFaTest.shouYe;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.YuFaShouYeData;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.yuFaTest.YuFaTestShouTwoActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水东流 on 2018/7/17.
 */

public class YuFaFirstFragment extends PubLicChuangGuanFragment {
    private List<List<YuFaShouYeData.DataBean>> list = new ArrayList<>();

    @Override
    public boolean isHORIZONTAL() {
        return true;
    }

    @Override
    public void init() {
        back1.setVisibility(View.GONE);
        post(HttpUntil.GetIntent().GrammarPracticeindex(), new HttpParams(), 1);
    }

    @Override
    public void buyCurr() {

    }

    @Override
    public void onStart() {
        super.onStart();
        ShowToast("左右滑动查看更多题目哦！");
    }

    private void initadapter() {
        adapter = new MyBaseRecyclerAdapter<List<YuFaShouYeData.DataBean>>(list, getActivity(), R.layout.yu_fa_shou_ye) {
            @Override
            public void convert(BaseRecyclerHolder holder, List<YuFaShouYeData.DataBean> item, int postion) {
                for (int i = 0; i < item.size(); i++) {
                    // 位置，每页20个，根据位置显示
                    int sort = Integer.parseInt(item.get(i).getSort());
                    switch (sort) {
                        case 1:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.one));
                            break;
                        case 2:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.two));
                            break;
                        case 3:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.three));
                            break;
                        case 4:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.four));
                            break;
                        case 5:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.five));
                            break;
                        case 6:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.six));
                            break;
                        case 7:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.seven));
                            break;
                        case 8:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.eight));
                            break;
                        case 9:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.nine));
                            break;
                        case 10:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.ten));
                            break;
                        case 11:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.eleven));
                            break;
                        case 12:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.twelve));
                            break;
                        case 13:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.thirteen));
                            break;
                        case 14:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.fourteen));
                            break;
                        case 15:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.fifteen));
                            break;
                        case 16:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.sixteen));
                            break;
                        case 17:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.seventeen));
                            break;
                        case 18:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.eighteen));
                            break;
                        case 19:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.nineteen));
                            break;
                        case 20:
                            setText(item.get(i), i, (TextView) holder.getView(R.id.twenty));
                            break;
                    }
                }
            }

            //设置textview
            private void setText(final YuFaShouYeData.DataBean item, final int i, TextView textView) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(item.getName());
                if (!item.getBackground().equals("")) {
                    textView.setBackgroundColor(Color.parseColor(item.getBackground()));
                }
//                textView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/hkhbt.ttf"));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(getActivity(), YuFaTestShouTwoActivity.class);
                        in.putExtra("title", item.getName());
                        in.putExtra("book_id", item.getBook_id());
                        jumpToActivity(in, false);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void buyGuoGuo() {

    }

    @Override
    public void shareCurr() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s, false)) {
            YuFaShouYeData data = new Gson().fromJson(s, YuFaShouYeData.class);
            list.clear();
            list.addAll(data.getData());
            initadapter();
        }
    }
}
