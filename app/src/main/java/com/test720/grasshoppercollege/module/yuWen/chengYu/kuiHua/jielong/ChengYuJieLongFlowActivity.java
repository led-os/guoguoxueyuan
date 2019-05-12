package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua.jielong;

import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ChengYuJieLongFlowActivity extends BaseToolActivity {
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.shou)
    ImageView shou;
    @BindView(R.id.recyclerView)
    PullLoadMoreRecyclerView recyclerView;
    MyBaseRecyclerAdapter adapter;
    private List<ChengYuJieLongData.DataBean> list = new ArrayList<>();
    int[] colors = new int[]{R.color.bianyinghuang, R.color.chenyu1,
            R.color.chenyu2, R.color.chengyu3,
            R.color.chengyu4, R.color.chengyu5};
    int h = 50;
    public static final int SHUAXIN = 1;
    public static final int GENGDUO = 2;
    int page = 1;
    private boolean noMore = false;

    @Override
    protected String setTitle() {
        return "成语接龙";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_kui_hua;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("key") != null) {
            edittext.setText(getIntent().getStringExtra("key"));
        }
        getData(SHUAXIN);
        recyclerView.setStaggeredGridLayout(3);//参数为列数
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                ShuaXin();
            }

            @Override
            public void onLoadMore() {
                if (!noMore) {
                    page++;
                    getData(GENGDUO);
                } else {
                    recyclerView.setPullLoadMoreCompleted();
                }

            }
        });

        adapter = new MyBaseRecyclerAdapter<ChengYuJieLongData.DataBean>(list, this, R.layout.kui_hua_img_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ChengYuJieLongData.DataBean item, int postion) {

                TextView textView = holder.getView(R.id.text);
                Random rand = new Random();
                int i = rand.nextInt(150);
                h = i + 120;

                ViewGroup.LayoutParams lp = textView.getLayoutParams();
                lp.height = h;
                textView.setLayoutParams(lp);

                int col = postion % 6;
                textView.setBackgroundResource(colors[col]);
                textView.setText(item.getName());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ChengYuJieLongActivity.class);
                intent.putExtra("key", list.get(position).getName());
                intent.putExtra("value",list.get(position).getDesc());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /*获取数据方法*/
    protected void getData(int what) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("p", page + "");
        if (!TextUtils.isEmpty(edittext.getText())) {
            builder.add("key", edittext.getText().toString());
        }
        Post(HttpUntil.GetIntent().ChineseIdiombibleIndex(), builder, what);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case SHUAXIN:
                shuaXin(str);
                break;
            case GENGDUO:
                gengDuo(str);
                break;
        }
        recyclerView.setPullLoadMoreCompleted();
    }


    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ChengYuJieLongData data = new Gson().fromJson(str, ChengYuJieLongData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }


    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ChengYuJieLongData data = new Gson().fromJson(str, ChengYuJieLongData.class);
            if (data.getData().size() == 0) {
                noMore = true;
            }
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.shou)
    public void onViewClicked() {
        ShuaXin();
    }

    /*刷新数据*/
    public void ShuaXin() {
        page = 1;
        noMore = false;
        getData(SHUAXIN);
    }

}
