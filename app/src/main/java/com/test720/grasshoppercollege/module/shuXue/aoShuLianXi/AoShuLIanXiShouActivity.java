package com.test720.grasshoppercollege.module.shuXue.aoShuLianXi;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.AoShuShouYeGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.jinShai.JinShaiShouYeActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class AoShuLIanXiShouActivity extends BaseToolActivity {


    @BindView(R.id.content_frame)
    ViewPager contentFrame;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    @BindView(R.id.back)
    public ImageView back;

    @BindView(R.id.left)
    public ImageView left;
    @BindView(R.id.right)
    public ImageView right;

    @BindView(R.id.root)
    public RelativeLayout root;
    @BindView(R.id.main_tabhost)
    public LinearLayout mainTabhost;
    List<Fragment> list = new ArrayList<>();

    public ViewPager getContentFrame() {
        return contentFrame;
    }

    public void setContentFrame(ViewPager contentFrame) {
        this.contentFrame = contentFrame;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ao_shu_lian_xi_shou;
    }

    @Override
    protected void initData() {
        contentFrame.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    left.setVisibility(View.GONE);
                    right.setVisibility(View.VISIBLE);
                } else if (position == contentFrame.getAdapter().getCount() - 1) {
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.GONE);
                } else {
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("type", "18");
        Post(HttpUntil.GetIntent().MathOlyPracticeindex(), builder, 1, false);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            AoShuShouYeGuanKaData aoShuShouYeGuanKaData = new Gson().fromJson(str, AoShuShouYeGuanKaData.class);
            for (int i = 0; i < aoShuShouYeGuanKaData.getData().size(); i++) {
                AoShuLianXiOneFragment aoShuLianXiOneFragment = new AoShuLianXiOneFragment();
                aoShuLianXiOneFragment.setIndex(i);
                list.add(aoShuLianXiOneFragment);
            }
            if (aoShuShouYeGuanKaData.getData().size() == 1) {
                left.setVisibility(View.GONE);
                right.setVisibility(View.GONE);
            }
            contentFrame.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }
    }

    @Override
    public void AfterHttp() {
        super.AfterHttp();
        if (list.size() == 0) {
            contentFrame.setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            contentFrame.setBackgroundResource(R.color.touming);
        }
    }

    @OnClick({R.id.back, R.id.lin1, R.id.lin2, R.id.lin3, R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin1:

                break;
            case R.id.lin2:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    Intent intent = new Intent(this, JinShaiShouYeActivity.class);
                    intent.putExtra("type", "18");
                    jumpToActivity(intent, false);
                }
                break;
            case R.id.lin3:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(AoShuLianXiJiLeiActivity.class, false);
                }
                break;
            case R.id.left:
                if (contentFrame.getAdapter() == null) return;
                contentFrame.setCurrentItem(contentFrame.getCurrentItem() - 1);
                break;
            case R.id.right:
                if (contentFrame.getAdapter() == null) return;
                contentFrame.setCurrentItem(contentFrame.getCurrentItem() + 1);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
