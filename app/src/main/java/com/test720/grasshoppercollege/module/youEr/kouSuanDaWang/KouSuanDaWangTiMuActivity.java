package com.test720.grasshoppercollege.module.youEr.kouSuanDaWang;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.KouSuanData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shuXue.kouSuan.KouSuanTiMuActivity;
import com.test720.grasshoppercollege.module.shuXue.kouSuan.KouSuanTiOneFragment;
import com.test720.grasshoppercollege.module.shuXue.kouSuan.KouSuanTiTwoFragment;
import com.test720.grasshoppercollege.myViews.MainViewPager;
import com.test720.grasshoppercollege.untils.CountDownTimerUtil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.TimeUtils;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class KouSuanDaWangTiMuActivity extends KouSuanTiMuActivity {
    List<Fragment> list = new ArrayList<>();
    private CountDownTimerUtil timerUtil;
    MusicUntil kousuanmusic = new MusicUntil();
    boolean daojiTime = false;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_kou_suan_ti_mu;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        }
        setType(getIntent().getIntExtra("type", 1));
        if (getIntent().getStringExtra("eq_id") != null) {
            setDingZheng(true);
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("eq_id", getIntent().getStringExtra("eq_id"));
            builder.add("practice", "2");
            Post(HttpUntil.GetIntent().MathMentalArithmeticcontent(), builder, 1);
        } else {
            setDingZheng(false);
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("mop_id", getIntent().getStringExtra("mop_id"));
            builder.add("practice", "1");
            Post(HttpUntil.GetIntent().ChildMentalArithmeticcontent(), builder, 1);
        }
        viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                jindu.setText((position + 1) + "");//当前已做题目数量
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.KOUSUANOVER) {
            kousuanmusic.canlce();
            if (timerUtil != null) timerUtil.over();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        kousuanmusic.canlce();
        if (timerUtil != null) timerUtil.over();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            KouSuanData data = new Gson().fromJson(str, KouSuanData.class);
            initSeek(data);
            for (int i = 0; i < data.getData().getList().size(); i++) {
                switch (data.getData().getList().get(i).getAnswer().size()) {
                    case 1:
                        KouSuanTiOneFragment kouSuanTiOneFragment = new KouSuanTiOneFragment();
                        kouSuanTiOneFragment.setBaseTiMuActivity(this);
                        kouSuanTiOneFragment.setIndex(i);
                        kouSuanTiOneFragment.setViewPager(viewPager);
                        kouSuanTiOneFragment.setListBean(data.getData().getList().get(i));
                        list.add(kouSuanTiOneFragment);
                        break;

                    case 2:
                        KouSuanTiTwoFragment kouSuanTiTwoFragment = new KouSuanTiTwoFragment();
                        kouSuanTiTwoFragment.setBaseTiMuActivity(this);
                        kouSuanTiTwoFragment.setIndex(i);
                        kouSuanTiTwoFragment.setViewPager(viewPager);
                        kouSuanTiTwoFragment.setListBean(data.getData().getList().get(i));
                        list.add(kouSuanTiTwoFragment);
                        break;

                }
            }
            viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }
    }

    private void initSeek(KouSuanData data) {
        setTimeInt(Integer.parseInt(data.getData().getTime()));
        int c = Integer.parseInt(data.getData().getCount());
        setFen(100 - c);
        seekBar.setMax(getTimeInt());//进度
        timerUtil = new CountDownTimerUtil(getTimeInt());
        timerUtil.setTimeInterface(new CountDownTimerUtil.TimeInterface() {
            @Override
            public void upDataUi(int t) {
                if (!daojiTime && t < 10) {
                    kousuanmusic.playRaw(mcontext, R.raw.kousuandaojishi);
                    daojiTime = true;
                }
                time.setText(TimeUtils.long2String(t * 1000));
                seekBar.setProgress(getTimeInt() - t);
            }

            @Override
            public void timeIsOver() {
                time.setText("00:00");
                setTimeInt(0);
                EventBus.getDefault().post(new CurrencyEvent("时间到", CurrencyEvent.KOUSUANTIME));
            }
        });
    }


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.timelin)
    LinearLayout timelin;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.jindu)
    TextView jindu;
    @BindView(R.id.viewPager)
    MainViewPager viewPager;


    @OnClick({R.id.back, R.id.time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.time:
                if (startplay) return;
                if (timerUtil != null) {
                    kousuanmusic.playRaw(mcontext, R.raw.readygo);
                    timerUtil.start();
                    startplay = true;
                }
                break;
        }
    }
}
