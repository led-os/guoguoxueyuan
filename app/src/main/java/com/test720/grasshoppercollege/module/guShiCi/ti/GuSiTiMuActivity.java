package com.test720.grasshoppercollege.module.guShiCi.ti;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.MathData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shuXue.jiChu.tiXing.BuChongTiFragment;
import com.test720.grasshoppercollege.module.shuXue.jiChu.tiXing.JiChuDanXuanFragment;
import com.test720.grasshoppercollege.module.shuXue.jiChu.tiXing.JiChuPanDuanFragment;
import com.test720.grasshoppercollege.module.shuXue.jiChu.tiXing.JiChuTianKongFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class GuSiTiMuActivity extends BaseTiMuActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.help)
    TextView help;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.jindu)
    TextView jindu;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    String bookId = "";
    String unit = "";
    String page = "";

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_gu_si_ti_mu;
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void initData() {
        bookId = getIntent().getStringExtra("poetry_id");
        setId(bookId);
        setType(34);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("poetry_id", bookId);
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChineseAncientPoetrypracticeIndex(), builder, 1);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainHandler.sendMessage(new Message());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void MainHandlerVoid(Message msg) {
        super.MainHandlerVoid(msg);
        if (viewPager.getAdapter() != null) {
            seekBar.setProgress(viewPager.getCurrentItem());
            jindu.setText((viewPager.getCurrentItem() + 1) + "/" + (viewPager.getAdapter().getCount()));
        }

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    MathData data = new Gson().fromJson(str, MathData.class);
                    for (int i = 0; i < data.getData().size(); i++) {
                        switch (data.getData().get(i).getQuestion_type()) {
                            case "1"://选择
                                JiChuDanXuanFragment baseXuanZheFragment = new JiChuDanXuanFragment();
                                baseXuanZheFragment.setDataBean(data.getData().get(i));
                                baseXuanZheFragment.setBaseTiMuActivity(this);
                                baseXuanZheFragment.setViewPager(viewPager);
                                list.add(baseXuanZheFragment);
                                break;
                            case "2"://判断
                                JiChuPanDuanFragment basePanDuanTiFragment = new JiChuPanDuanFragment();
                                basePanDuanTiFragment.setBaseTiMuActivity(this);
                                basePanDuanTiFragment.setDataBean(data.getData().get(i));
                                basePanDuanTiFragment.setViewPager(viewPager);
                                list.add(basePanDuanTiFragment);
                                break;
                            case "3"://填空
                                JiChuTianKongFragment baseTiankongFragment = new JiChuTianKongFragment();
                                baseTiankongFragment.setBaseTiMuActivity(this);
                                baseTiankongFragment.setDataBean(data.getData().get(i));
                                baseTiankongFragment.setViewPager(viewPager);
                                list.add(baseTiankongFragment);
                                break;
                            case "4":
                                BuChongTiFragment buChongTiFragment = new BuChongTiFragment();
                                buChongTiFragment.setBaseTiMuActivity(this);
                                buChongTiFragment.setDataBean(data.getData().get(i));
                                buChongTiFragment.setViewPager(viewPager);
                                list.add(buChongTiFragment);
                                break;
                        }
                    }
                    viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
                    seekBar.setMax(list.size());
                    jindu.setText((viewPager.getCurrentItem() + 1) + "/" + (viewPager.getAdapter().getCount()));
                }
                break;
        }
    }


    @OnClick({R.id.back, R.id.help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.help:
                break;
        }
    }

}
