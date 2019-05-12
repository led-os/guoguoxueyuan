package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.KuiHuaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua.jielong.ChengYuDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class KuiHuaActivity extends BaseToolActivity {


    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.shou)
    ImageView shou;
    @BindView(R.id.recyclerView)
    PullLoadMoreRecyclerView recyclerView;

    List<KuiHuaData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    int[] colors = new int[]{R.color.yellow, R.color.chenyu1,
            R.color.chenyu2, R.color.chengyu3,
            R.color.chengyu4, R.color.green};
    public static final int SHUAXIN = 1;
    public static final int GENGDUO = 2;
    int h = 50;
    int page = 1;
    private boolean noMore = false;

    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("title") != null) return getIntent().getStringExtra("title");
        return "成语";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_kui_hua;
    }

    @Override
    protected void initData() {
        getData(SHUAXIN);
        recyclerView.setStaggeredGridLayout(3);
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
        adapter = new MyBaseRecyclerAdapter<KuiHuaData.DataBean>(list, this, R.layout.kui_hua_img_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, KuiHuaData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                TextView textView = holder.getView(R.id.text);

                /*动态设置高度*/
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
                ChengYuDialog chengYuDialog = new ChengYuDialog();
                chengYuDialog.setTitleStr(list.get(position).getName());
                chengYuDialog.setUrl(list.get(position).getWeb_url());
                chengYuDialog.show(getSupportFragmentManager(), "chenyu");
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /*获取数据方法*/
    protected void getData(int what) {
        if (getIntent().getStringExtra("class") != null) {
            FormBody.Builder builder = new FormBody.Builder();
            if (!TextUtils.isEmpty(edittext.getText())) {
                builder.add("key", edittext.getText().toString());
            }
            builder.add("p", page + "");
            builder.add("class", getIntent().getStringExtra("class"));
            Post(HttpUntil.GetIntent().ChineseIdiomidiomList(), builder, what);
        }

        if (getIntent().getStringExtra("cate_id") != null) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("cate_id", getIntent().getStringExtra("cate_id"));
            if (!TextUtils.isEmpty(edittext.getText())) {
                builder.add("key", edittext.getText().toString());
            }
            builder.add("p", page + "");
            Post(HttpUntil.GetIntent().ChineseIdiomcateContent(), builder, what);
        }
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
            KuiHuaData data = new Gson().fromJson(str, KuiHuaData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }


    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            KuiHuaData data = new Gson().fromJson(str, KuiHuaData.class);
            if (data.getData().size() == 0) {
                noMore = true;
            }
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }


    /*刷新数据*/
    public void ShuaXin() {
        page = 1;
        noMore = false;
        getData(SHUAXIN);
    }

    @OnClick(R.id.shou)
    public void onViewClicked() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (!MyApplication.getmInstance().getUid().equals("")) {
            getData(SHUAXIN);
        }
    }
}
