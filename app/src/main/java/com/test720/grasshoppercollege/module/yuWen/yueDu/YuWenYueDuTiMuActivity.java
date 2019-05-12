package com.test720.grasshoppercollege.module.yuWen.yueDu;

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
import com.test720.grasshoppercollege.HttpBean.YuWenYueDuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.yuWen.yueDu.tiXing.YuWenDanXuanFragment;
import com.test720.grasshoppercollege.module.yuWen.yueDu.tiXing.YuWenYueDuPanDuanFragment;
import com.test720.grasshoppercollege.module.yuWen.yueDu.tiXing.YuWenYueDuTianKongFragment;
import com.test720.grasshoppercollege.module.yuWen.yueDu.tiXing.YueDuBuChongFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YuWenYueDuTiMuActivity extends BaseTiMuActivity {
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


    @Override
    protected String setTitle() {
        return null;
    }


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ji_chu_ti_mu;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void initData() {
        title.setText("同步练习");
        bookId = getIntent().getStringExtra("read_id");
        setType(15);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("read_id", bookId);
        Post(HttpUntil.GetIntent().ChineseReadcontent(), builder, 1);

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
                    YuWenYueDuData data = new Gson().fromJson(str, YuWenYueDuData.class);
                    for (int i = 0; i < data.getData().getList().size(); i++) {
                        switch (data.getData().getList().get(i).getQuestion_type()) {
                            case "1"://选择
                                YuWenDanXuanFragment baseXuanZheFragment = new YuWenDanXuanFragment();
                                baseXuanZheFragment.setDataBean(data.getData().getList().get(i));
                                baseXuanZheFragment.setBaseTiMuActivity(this);
                                baseXuanZheFragment.setViewPager(viewPager);
                                list.add(baseXuanZheFragment);
                                break;
                            case "2"://判断
                                YuWenYueDuPanDuanFragment basePanDuanTiFragment = new YuWenYueDuPanDuanFragment();
                                basePanDuanTiFragment.setBaseTiMuActivity(this);
                                basePanDuanTiFragment.setDataBean(data.getData().getList().get(i));
                                basePanDuanTiFragment.setViewPager(viewPager);
                                list.add(basePanDuanTiFragment);
                                break;
                            case "3"://填空
                                YuWenYueDuTianKongFragment baseTiankongFragment = new YuWenYueDuTianKongFragment();
                                baseTiankongFragment.setBaseTiMuActivity(this);
                                baseTiankongFragment.setDataBean(data.getData().getList().get(i));
                                baseTiankongFragment.setViewPager(viewPager);
                                list.add(baseTiankongFragment);
                                break;
                            case "4":
                                YueDuBuChongFragment buChongTiFragment = new YueDuBuChongFragment();
                                buChongTiFragment.setBaseTiMuActivity(this);
                                buChongTiFragment.setDataBean(data.getData().getList().get(i));
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
