package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.HuiBenNeiRongData;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.ZoomOutPageTransformer;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.LuYinUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.oss.OssService;

public class HuiBenBoFangActivity extends BaseToolActivity {

    List<Fragment> list = new ArrayList<>();
    HuiBenNeiRongData data;
    String strData;
    Map<Integer, String> map = new HashMap<>();//录音本地地址
    int status = -1;
    MusicUntil musicUntil;//音频文件播放器
    MusicUntil myLuYinUntil;//我的录音播放器
    FaBuFragment faBuFragment = new FaBuFragment();// 发布界面

    OssService ossService;

    class MyRun implements Runnable {

        @Override
        public void run() {
            if (viewPager.getCurrentItem() < data.getData().getList().size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    }

    /*获取数据*/
    public void getData() {
        if (strData != null) return;
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("status", getIntent().getIntExtra("status", 1) + "");
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChildBookcontent(), builder, 5, true);
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_hui_ben_bo_fang;
    }

    @Override
    protected void initData() {
        ossService = new OssService("Uploads/child_book/user_sound_recording/", mcontext, getSupportFragmentManager());
        status = getIntent().getIntExtra("status", -1);
        faBuFragment.setStatus(status);
        if (status == 1) {
            zanTing.setVisibility(View.VISIBLE);
        } else {//录绘本情况下，不展示暂停键
            zanTing.setVisibility(View.GONE);
        }
       /* toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    if (musicUntil != null && musicUntil.getMediaPlayer() == null || !musicUntil.getMediaPlayer().isPlaying()) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                }
            }
        });*/
//        toggleButton.setChecked(false);
        viewPager.setScanScroll(true);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                musicUntil.canlce();
                myLuYinUntil.canlce();
                //当前页是否有录音,如果是录音模块，并且当前有录音则显示录音键，否则隐藏播放录音键
                showPlayOrNot();

                if (data != null && position < data.getData().getList().size()) {//自动加载音频
                    String s = (position + 1) + "/" + (data.getData().getList().size());
                    number.setText(s);
                    playMusic();
                    zanTing.setImageResource(R.mipmap.huibenzanting);
                } else {
                    luLin.setVisibility(View.GONE);
                    toggleButton.setVisibility(View.GONE);
                    number.setVisibility(View.GONE);
                }
                //最后一页是发布页面
                if (position == viewPager.getAdapter().getCount() - 1) {
                    back.setVisibility(View.VISIBLE);
                    zanTing.setVisibility(View.GONE);
                } else {
//                    if (status == 1) zanTing.setVisibility(View.VISIBLE);
                }
                play.setImageResource(R.mipmap.huibenluyinmy);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (status == 1) {
            luLin.setVisibility(View.GONE);
        } else {
            viewPager.setScanScroll(true);
            toggleButton.setChecked(true);
            toggleButton.setVisibility(View.GONE);
            luLin.setVisibility(View.VISIBLE);
        }
        initLuYinMusic();
        initMusicUntil();


        //调起录音
        LuYinUntil luYinUntil = new LuYinUntil(mcontext, new LuYinUntil.LuYinInterface() {
            @Override
            public View root() {
                return root;
            }

            @Override
            public View changAnView() {
                return luYin;
            }

            @Override
            public void filePath(String path) {
                map.put(viewPager.getCurrentItem(), path);
                send();
                //录音完成展示播放录音键
                play.setVisibility(View.VISIBLE);
                playTxt.setVisibility(View.VISIBLE);
            }

            @Override
            public TextView shuChuTime() {
                return null;
            }
        });

        luYinUntil.setStartListener(new LuYinUntil.StartListener() {
            @Override
            public void start() {
                musicUntil.canlce();
            }

            @Override
            public void end() {

            }
        });
        //4秒后自动隐藏控件
        mainHandler.postDelayed(fullRun, 4000);
        if (status == 2) {//录绘本时请求数据，听绘本时直接获取本地数据
            getData();
        } else {
            BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
            strData = bookDataInsterUntil.getHuiBenBean(mcontext, getIntent().getStringExtra("book_id"));
            data = new Gson().fromJson(strData, HuiBenNeiRongData.class);
            if (data == null) {
                getData();
                return;
            }
            if (data.getData().getInfo().getStatus().equals("1")) {
                changeScreenOrientation();
            }
            initViewPager();

        }

    }

    private void initMusicUntil() {
        musicUntil = new MusicUntil(1f);
        musicUntil.setStartListener(new MusicUntil.StartListener() {
            @Override
            public void startPlay() {
                zanTing.setImageResource(R.mipmap.huibenzanting);
                bofang.setImageResource(R.mipmap.huibenluyinyuan);
            }
        });
        musicUntil.setEndComListener(new MusicUntil.EndComListener() {
            @Override
            public void endListener() {
                zanTing.setImageResource(R.mipmap.huibenluyinmy);
                bofang.setImageResource(R.mipmap.huibenluyinmy);
                if (status == 1) {//如果是听绘本，则自动切换下一页
                    if (!toggleButton.isChecked() && data != null) {
                        mainHandler.postDelayed(new MyRun(), 1000);
                    }
                }
            }
        });
    }

    private void initLuYinMusic() {
        myLuYinUntil = new MusicUntil(1f);
        myLuYinUntil.setStartListener(new MusicUntil.StartListener() {
            @Override
            public void startPlay() {
                play.setImageResource(R.mipmap.huibenzanting);
            }
        });
        myLuYinUntil.setEndComListener(new MusicUntil.EndComListener() {
            @Override
            public void endListener() {
                play.setImageResource(R.mipmap.huibenluyinmy);
            }
        });
    }

    /**
     * 根据状态判断当前是否展示播放录音键，和暂停键
     */
    private void showPlayOrNot() {
        if (status == 2 && map.get(viewPager.getCurrentItem()) != null && !map.get(viewPager.getCurrentItem()).equals("")) {
            play.setVisibility(View.VISIBLE);
            playTxt.setVisibility(View.VISIBLE);
        } else {
            play.setVisibility(View.GONE);
            playTxt.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicUntil != null) musicUntil.canlce();
        if (myLuYinUntil != null) myLuYinUntil.canlce();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 5:
                if (codeIsOne(str, false)) {
                    DismissDialong();
                    Gson gson = new Gson();
                    /*切换横屏*/
                    data = gson.fromJson(str, HuiBenNeiRongData.class);
                    //初始化我的录音集合
                    for (int i = 0; i < data.getData().getList().size(); i++) {
                        map.put(i, data.getData().getList().get(i).getMy_voice());
                    }
                    //发布录音时对应的个人id
                    faBuFragment.setMyId(data.getData().getInfo().getMy_id());
                    strData = str;
                    if (data.getData().getInfo().getStatus().equals("1")) {
                        changeScreenOrientation();
                    }
                    initViewPager();
                    showPlayOrNot();
                }
                break;
            case 1001:
                if (codeIsOne(str)) {
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        String id = jsonObject.getJSONObject("data").getString("my_id");
                        if (faBuFragment != null) {
                            faBuFragment.setMyId(id);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //如果录音上传结束，并且是自动播放，则自定切换到下一页
                    if (musicUntil != null && !musicUntil.getMediaPlayer().isPlaying() && toggleButton.isChecked()) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                }
                break;
        }
    }

    /**
     * 播放当前音频
     */
    public void playMusic() {
        if (data == null) return;
        if (viewPager.getCurrentItem() < data.getData().getList().size()) {
            String s = HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getList().get(viewPager.getCurrentItem()).getVoice_b();
            File file = new File(s);
            if (file.exists()) {
                LogUtil.logError(s + "存在");
            } else {
                LogUtil.logError(s + "不存在");
            }
            musicUntil.playMusic(mcontext, s);
        }
    }

    /**
     * 初始化绘本图片
     */
    private void initViewPager() {
        for (int i = 0; i < data.getData().getList().size(); i++) {
            HuibenBoFangFragment huibenBoFangFragment = new HuibenBoFangFragment();
            huibenBoFangFragment.setUrl(HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getList().get(i).getPic_b());
            File file = new File(HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getList().get(i).getPic_b());
            if (file.exists()) {
                LogUtil.logError(HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getList().get(i).getPic_b() + "存在");
            } else {
                LogUtil.logError(HttpUntil.GetIntent().filePathHead(mcontext) + data.getData().getList().get(i).getPic_b() + "不存在");
            }
            list.add(huibenBoFangFragment);
        }
        faBuFragment.setBookId(getIntent().getStringExtra("book_id"));
        list.add(faBuFragment);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        map.put(0, data.getData().getList().get(0).getMy_voice());
        String s = (1) + "/" + (data.getData().getList().size());
        number.setText(s);
        playMusic();
    }

    /**
     * 设置为横屏
     */
    public void changeScreenOrientation() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", strData);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            DismissDialong();
            strData = savedInstanceState.getString("data");
            data = new Gson().fromJson(strData, HuiBenNeiRongData.class);
            initViewPager();
        }
    }

    /*发表*/
    public void send() {
        if (map.get(viewPager.getCurrentItem()) == null) {
            ShowToast("请先录音");
            return;
        }
        ossService.beginupload(MyApplication.getmInstance().getUid(), map.get(viewPager.getCurrentItem()), new OssService.SignSendBack() {
            @Override
            public void sucess(String filepath) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("read_id", data.getData().getList().get(viewPager.getCurrentItem()).getRead_id());
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("book_id", getIntent().getStringExtra("book_id"));
                httpParams.put("file", filepath);
                postResponse(HttpUntil.GetIntent().ChildBookuploadSoundRecording(), httpParams, 1001, true);
            }

            @Override
            public void failure() {

            }

            @Override
            public void onProgressCallback(double progress) {

            }
        });


    }

    //**监听长时间不触摸屏幕，则全屏显示
    Runnable fullRun = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getAdapter() == null) return;
            if (viewPager.getCurrentItem() != viewPager.getAdapter().getCount() - 1) {
                back.setVisibility(View.GONE);
            } else {
                back.setVisibility(View.VISIBLE);
            }
            zanTing.setVisibility(View.GONE);
            toggleButton.setVisibility(View.GONE);
            number.setVisibility(View.GONE);
            luLin.setVisibility(View.GONE);
        }
    };

    @OnClick({R.id.back, R.id.play, R.id.bofang, R.id.zanTing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.play://播放我的录音
                mainHandler.removeCallbacks(fullRun);
                musicUntil.canlce();
                bofang.setImageResource(R.mipmap.huibenluyinyuan);
                if (map.get(viewPager.getCurrentItem()) != null && !map.get(viewPager.getCurrentItem()).equals("")) {
                    myLuYinUntil.playMusic(map.get(viewPager.getCurrentItem()));
                    play.setImageResource(R.mipmap.huibenzanting);
                } else ShowToast("请先录音");
                break;
            case R.id.bofang://播放当前音频
                mainHandler.removeCallbacks(fullRun);
                myLuYinUntil.canlce();
                play.setImageResource(R.mipmap.huibenluyinmy);
                if (data == null) return;
                if (!musicUntil.isPlaying()) {//当前还未加载
                    playMusic();
                    bofang.setImageResource(R.mipmap.huibenluyinyuan);
                    return;
                }
                //暂停或继续
                if (musicUntil.pauseOrStart()) {
                    bofang.setImageResource(R.mipmap.huibenluyinmy);
                } else {
                    bofang.setImageResource(R.mipmap.huibenluyinyuan);
                }
                break;
            case R.id.zanTing:
                if (!musicUntil.isPlaying()) {//当前还未加载
                    playMusic();
                    zanTing.setImageResource(R.mipmap.huibenzanting);
                    return;
                }
                if (musicUntil.pauseOrStart()) {
                    zanTing.setImageResource(R.mipmap.huibenluyinmy);
                } else {
                    zanTing.setImageResource(R.mipmap.huibenzanting);
                }
                break;
        }
    }


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == CurrencyEvent.HUIBENCLICKIMG) {

            //如果当前view是显示状态，点击中间图片，隐藏view
            if (number.getVisibility() != View.VISIBLE) {
                back.setVisibility(View.VISIBLE);
                number.setVisibility(View.VISIBLE);
                //录音状态下暂停键不展示
                if (status == 1) zanTing.setVisibility(View.VISIBLE);
                else zanTing.setVisibility(View.GONE);

                //录音状态下，显示录音键
                if (status == 2) {
                    toggleButton.setVisibility(View.GONE);
                    luLin.setVisibility(View.VISIBLE);
                }
                //5秒后自动全屏
                mainHandler.postDelayed(fullRun, 5000);
            } else {
                mainHandler.postDelayed(fullRun, 0);
            }
        }
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    public void setSys() {
        super.setSys();
        isFull = true;
    }

    @BindView(R.id.luLin)
    LinearLayout luLin;
    @BindView(R.id.root)
    FrameLayout root;
    @BindView(R.id.luYin)
    ImageView luYin;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.zanTing)
    ImageView zanTing;
    @BindView(R.id.bofang)
    ImageView bofang;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.playTxt)
    TextView playTxt;

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.toggleButton)
    ToggleButton toggleButton;


}
