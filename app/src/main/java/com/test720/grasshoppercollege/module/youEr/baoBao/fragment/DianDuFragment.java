package com.test720.grasshoppercollege.module.youEr.baoBao.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.LuYinUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/8/1.
 */

public class DianDuFragment extends BaseFragment {
    YueDuData.DataBean.InfoBean dataBean;
    private MusicUntil musicUntil = new MusicUntil();
    private MusicUntil luYinUntil = new MusicUntil();
    float speedF = 1;//语速
    TextToSpeech tts;
    int index = 0;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void initData() {

        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //  若装载TTS引擎成功
                if (status == TextToSpeech.SUCCESS) {
                    //  设置中文朗读
                    int result = tts.setLanguage(Locale.ENGLISH);
                    //  若不支持所设置的语言
                    if (result != TextToSpeech.LANG_AVAILABLE &&
                            result != TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                        Toast.makeText(getActivity(), "TTS暂不支持该语言的朗读",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        LuYinUntil luYinUntil = new LuYinUntil(getActivity(), new LuYinUntil.LuYinInterface() {
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
                luYinpath = path;
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
    }

    @Override
    public void onStart() {
        super.onStart();
        String p = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getPic_b();
        Glide.with(getActivity()).load(p).into(img);
        cn.setText(getDataBean().getCn());
        en.setText(getDataBean().getEn());
        root.setBackgroundColor(Color.parseColor(getDataBean().getBackground_color()));
        if (index + 1 == getViewPager().getAdapter().getCount()) {
            you.setVisibility(View.GONE);
            zuo.setVisibility(View.VISIBLE);
        } else if (index == 0) {
            zuo.setVisibility(View.GONE);
            you.setVisibility(View.VISIBLE);
        } else {
            you.setVisibility(View.VISIBLE);
            zuo.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.bao_bao_yue_du;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    public void playUrl(String url, String text) {
        luYinUntil.canlce();
        play.setImageResource(R.mipmap.huibenluyinmy);
        musicUntil.dianDuPlayMusic(mContext, url);
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            stopMediaPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            stopMediaPlayer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil = null;
        luYinUntil = null;
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    private void stopMediaPlayer() {
        if (musicUntil != null) musicUntil.canlce();
        if (luYinUntil != null) luYinUntil.canlce();
    }

    public void toBig(View view) {
        view.setPivotX(view.getWidth() / 2);  // X方向中点
        view.setPivotY(view.getHeight() / 2);   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.4f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.4f, 1f);
        animatorSet.setDuration(1000);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画
    }

    //语速弹框
    private void speedPOP() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            ShowToast("语速调节只支持23以上android系统版本！");
            return;
        }
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.speed_check, null, false);
        final PopupWindow window = new PopupWindow(contentView);
        SeekBar seekbar = contentView.findViewById(R.id.seekBar);
        seekbar.setMax(2 * 100);
        int pro = (int) (speedF * 100);
        seekbar.setProgress(pro);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
                Log.v("拖动过程中的值：", String.valueOf(progress) + ", " + String.valueOf(fromUser));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekbar) {
                Log.v("开始滑动时的值：", String.valueOf(seekbar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekbar) {
                Log.v("停止滑动时的值：", String.valueOf(seekbar.getProgress()));
                int pro = seekbar.getProgress();
                if (pro < 100) {
                    float f = (30 + (pro * 0.7f));
                    speedF = (f / 100f);
                } else {
                    speedF = (pro / 100f);
                }
                musicUntil.setSpeed(speedF);
            }
        });

        window.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bluebantoumingtwo)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//        window.showAsDropDown(view, 0, -(view.getHeight() + window.getHeight()));
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
//        window.showAtLocation(activity_ke_ben_dian_du, Gravity.LEFT|Gravity.CENTER_VERTICAL, location[0], location[1] - window.getHeight());
        window.showAtLocation(img, Gravity.CENTER, 0, 0);
    }


    @OnClick({R.id.play, R.id.luYin, R.id.speed, R.id.cn, R.id.en, R.id.img, R.id.zuo, R.id.you})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (luYinpath != null) {
                    musicUntil.canlce();
                    play.setImageResource(R.mipmap.huibenzanting);
                    luYinUntil.setEndComListener(new MusicUntil.EndComListener() {
                        @Override
                        public void endListener() {
                            play.setImageResource(R.mipmap.huibenluyinmy);
                        }
                    });
                    luYinUntil.dianDuPlayMusic(mContext, luYinpath);
                } else ShowToast("请先录音");
                break;
            case R.id.speed:
                speedPOP();
                break;
            case R.id.luYin:
                break;
            case R.id.cn:
                toBig(cn);
                String c = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getCn_voice_b();
                playUrl(c, "");
                break;
            case R.id.en:
                toBig(en);
                String e = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getEn_voice_b();
                playUrl(e, "");
                break;
            case R.id.img:
                toBig(img);
                String cn = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getCn_voice_b();
                playUrl(cn, "");
                break;
            case R.id.zuo:
                if (viewPager.getCurrentItem() > 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
                break;
            case R.id.you:
                if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                break;
        }
    }


    String luYinpath;

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public YueDuData.DataBean.InfoBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(YueDuData.DataBean.InfoBean dataBean) {
        this.dataBean = dataBean;
    }

    ViewPager viewPager;
    @BindView(R.id.cn)
    TextView cn;
    @BindView(R.id.en)
    TextView en;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.zuo)
    Button zuo;
    @BindView(R.id.you)
    Button you;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.luYin)
    ImageView luYin;
    @BindView(R.id.speed)
    ImageView speed;
    @BindView(R.id.root)
    RelativeLayout root;


}
