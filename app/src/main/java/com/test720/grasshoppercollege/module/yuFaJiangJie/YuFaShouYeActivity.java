package com.test720.grasshoppercollege.module.yuFaJiangJie;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.YuFaShouYeData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublickGuanKaActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class YuFaShouYeActivity extends PublickGuanKaActivity {
    private List<List<YuFaShouYeData.DataBean>> list = new ArrayList<>();


    @Override
    public void GetHttpBackStr(String str, int what) {
        if (what == 1 && codeIsOne(str, false)) {
            YuFaShouYeData data = new Gson().fromJson(str, YuFaShouYeData.class);
            list.clear();
            list.addAll(data.getData());
            initadapter();
        }

    }

    @Override
    public void ShuaXin() {
        FormBody.Builder builder = new FormBody.Builder();
        Post(HttpUntil.GetIntent().EnGrammarindex(), builder, 1);
    }

    @Override
    public boolean isHORIZONTAL() {
        return true;
    }

    @Override
    public void init() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        ShowToast("左右滑动查看更多题目哦！");
    }

    private void initadapter() {
        adapter = new MyBaseRecyclerAdapter<List<YuFaShouYeData.DataBean>>(list, this, R.layout.yu_fa_shou_ye) {
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
                    textView.setBackgroundColor(Color.parseColor(item.getBackground()));//使用后台设置的的16进制类型值
                    if (item.getBackground().equals("#1295db")) {
                        textView.setTextColor(getResources().getColor(R.color.yellow));
                    } else {
                        textView.setTextColor(getResources().getColor(R.color.appColor));
                    }
                }
//                textView.setTypeface(Typeface.createFromAsset(mcontext.getAssets(), "fonts/hkhbt.ttf"));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(YuFaShouYeActivity.this, YuFaShouTwoActivity.class);
                        in.putExtra("book_id", item.getBook_id());
                        in.putExtra("title", item.getName());
                        jumpToActivity(in, false);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 6);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "6");
        formBuilder.add("type", "2");
        formBuilder.add("points", points);
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public void shareCurr() {
        if (points == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "6");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }
}
