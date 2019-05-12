package com.test720.grasshoppercollege.module.xieHouYu;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.HttpBean.XieHouYUBaoDianData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BaoDianActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.top)
    ImageView top;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public MyBaseRecyclerAdapter adapter;

    List<XieHouYUBaoDianData.DataBean.InfoBean> list = new ArrayList<>();

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.cha)
    RelativeLayout cha;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        noLine = true;
        return R.layout.activity_bao_dian;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("title") != null)
            title.setText(getIntent().getStringExtra("title"));
        adapter = new MyBaseRecyclerAdapter<XieHouYUBaoDianData.DataBean.InfoBean>(list, this, R.layout.bao_dian_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final XieHouYUBaoDianData.DataBean.InfoBean item, int postion) {
                holder.setText(R.id.text, item.getQuestion());
                holder.setText(R.id.right, "*****");
                final TextView textView = holder.getView(R.id.right);
                Drawable drawable = getResources().getDrawable(R.mipmap.biyan);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable, null);
                holder.getView(R.id.right).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (textView.getText().toString().equals("*****")) {
                            Drawable drawable = getResources().getDrawable(R.mipmap.seeyan);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            textView.setCompoundDrawables(null, null, drawable, null);
                            textView.setText(item.getAnswer());
                        } else {
                            Drawable drawable = getResources().getDrawable(R.mipmap.biyan);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            textView.setCompoundDrawables(null, null, drawable, null);
                            textView.setText("*****");
                        }
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
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
        return HttpUntil.GetIntent().ChineseXieHouYuinfo();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            XieHouYUBaoDianData x = new Gson().fromJson(str, XieHouYUBaoDianData.class);
            list.clear();
            list.addAll(x.getData().getInfo());
            adapter.notifyDataSetChanged();
            GlideUntil.getInstance().imgUrl(mcontext, top, x.getData().getImg());
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            XieHouYUBaoDianData x = new Gson().fromJson(str, XieHouYUBaoDianData.class);
            list.addAll(x.getData().getInfo());
            adapter.notifyDataSetChanged();
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
        if (recyclerView.getAdapter().getItemCount() == 0) {
            recyclerView.setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            recyclerView.setBackgroundResource(R.color.touming);
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("lib_id", getIntent().getStringExtra("lib_id"));
        builder.add("p", page + "");
        if (!TextUtils.isEmpty(edittext.getText())) {
            builder.add("key", edittext.getText().toString());
        }
        return builder;
    }


    //页面改变监听
    private ViewPagerEx.OnPageChangeListener onPageChangeListener = new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d("ansen", "Page Changed: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    @OnClick({R.id.back, R.id.cha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.cha:
//                MyApplication.getmInstance().setLoginDialogIsShow(false);
//                if (!MyApplication.getmInstance().getUid().equals("")) {
                if (TextUtils.isEmpty(edittext.getText())) {
                    edittext.setError("请输入关键字");
                    return;
                }
                ShuaXin();
//                }
                break;
        }
    }

}
