package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.zuoPin;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.FaBuFragment;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.HuibenBoFangFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.SimplePageTransform;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ZuoPingBoFangActivity extends BaseToolActivity {


    List<Fragment> list = new ArrayList<>();
    MusicUntil musicUntil;
    ZuoPinData zuoPinData;

    FaBuFragment faBuFragment = new FaBuFragment();// 发布界面

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_zuo_ping_bo_fang;
    }

    @Override
    public void setSys() {
        super.setSys();
        isFull = true;
    }

    class MyRun implements Runnable {

        @Override
        public void run() {
            if (viewPager.getCurrentItem() < zuoPinData.getData().size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    }

    @Override
    protected void initData() {
        if (getIntent().getBooleanExtra("isOrientation", false)) {
            changeScreenOrientation();
        }
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewPager.setScanScroll(isChecked);
            }
        });
//        toggleButton.setChecked(false);
//        viewPager.setScanScroll(false);
        viewPager.setScanScroll(true);

        viewPager.setPageTransformer(true, new SimplePageTransform());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (zuoPinData != null && position < zuoPinData.getData().size()) {
                    String s = (position + 1) + "/" + (zuoPinData.getData().size());
                    number.setText(s);
                    playMusic();
                } else {
                    toggleButton.setVisibility(View.GONE);
                    number.setVisibility(View.GONE);
                }
                if (position == viewPager.getAdapter().getCount() - 1) {
                    back.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2 && musicUntil != null) musicUntil.canlce();
            }
        });
        musicUntil = new MusicUntil(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (!toggleButton.isChecked() && zuoPinData != null) {
                    mainHandler.postDelayed(new MyRun(), 1000);
                }
            }
        }, 1f);
        getData();
//4秒后自动隐藏控件
        mainHandler.postDelayed(fullRun, 4000);
    }

    /*获取数据*/
    public void getData() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("my_id", getIntent().getStringExtra("my_id"));
        Post(HttpUntil.GetIntent().getIP() + "small.php/ChildBook/wordsContent", builder, 5, true);
    }

    /**
     * 播放当前音频
     */
    public void playMusic() {
        if (zuoPinData == null) return;
        if (viewPager.getCurrentItem() < zuoPinData.getData().size()) {
            String s = zuoPinData.getData().get(viewPager.getCurrentItem()).getVoice();
            musicUntil.playMusic(s);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        zuoPinData = new Gson().fromJson(str, ZuoPinData.class);
        for (int i = 0; i < zuoPinData.getData().size(); i++) {
            HuibenBoFangFragment huibenBoFangFragment = new HuibenBoFangFragment();
            huibenBoFangFragment.setUrl(zuoPinData.getData().get(i).getPic());
            list.add(huibenBoFangFragment);
        }
        faBuFragment.setStatus(1);
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
    protected void onDestroy() {
        super.onDestroy();
        if (musicUntil != null) musicUntil.canlce();
    }


    //**监听长时间不触摸屏幕，则全屏显示
    Runnable fullRun = new Runnable() {
        @Override
        public void run() {
            back.setVisibility(View.GONE);
            toggleButton.setVisibility(View.GONE);
            number.setVisibility(View.GONE);
        }
    };

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == CurrencyEvent.HUIBENCLICKIMG) {
            playMusic();
            if (toggleButton.getVisibility() != View.VISIBLE) {
                back.setVisibility(View.VISIBLE);
//                toggleButton.setVisibility(View.VISIBLE);
                number.setVisibility(View.VISIBLE);
                mainHandler.postDelayed(fullRun, 3000);
            } else {
                mainHandler.postDelayed(fullRun, 0);
            }
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toggleButton)
    ToggleButton toggleButton;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.root)
    FrameLayout root;

    class ZuoPinData {


        /**
         * code : 1
         * msg : 成功
         * data : [{"read_id":"1","count":"5","sort":"1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/child_animation/import/test/Chrysanthemum.jpg","voice":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.mp3","cn":"What's this,Mocky?"},{"read_id":"2","count":"5","sort":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.jpg","voice":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.mp3","cn":"It's a banana."}]
         */

        private int code;
        private String msg;
        private List<DataBean> data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * read_id : 1
             * count : 5
             * sort : 1
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/child_animation/import/test/Chrysanthemum.jpg
             * voice : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.mp3
             * cn : What's this,Mocky?
             */

            private String read_id;
            private String count;
            private String sort;
            private String pic;
            private String voice;
            private String cn;

            public String getRead_id() {
                return read_id;
            }

            public void setRead_id(String read_id) {
                this.read_id = read_id;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getVoice() {
                return voice;
            }

            public void setVoice(String voice) {
                this.voice = voice;
            }

            public String getCn() {
                return cn;
            }

            public void setCn(String cn) {
                this.cn = cn;
            }
        }
    }
}
