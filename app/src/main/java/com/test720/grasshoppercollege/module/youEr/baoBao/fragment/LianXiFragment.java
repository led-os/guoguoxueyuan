package com.test720.grasshoppercollege.module.youEr.baoBao.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBao.YouErBaoBaoShouActivity;
import com.test720.grasshoppercollege.myViews.FlickerTextView;
import com.test720.grasshoppercollege.myViews.GetGuoGuoDouDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/8/1.
 */

public class LianXiFragment extends BaseFragment {
    LianXiData.DataBean.InfoBean dataBean;
    private MediaPlayer mediaPlayer;
    private MediaPlayer rightOrWrongMedia;
    float speedF = 1;//语速


    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        name.setText(getDataBean().getContent());
        text.setText(getDataBean().getQuestion());
        String a = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getSelect_a_b();
        String b = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getSelect_b_b();
        String c = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getSelect_c_b();

        Glide.with(getActivity()).load(a).into(one);
        Glide.with(getActivity()).load(b).into(two);
        Glide.with(getActivity()).load(c).into(three);
    }

    public void startMove() {
        move(one);
        move(two);
        move(three);
        String f = HttpUntil.GetIntent().filePathHead(mContext) + getDataBean().getVoice_b();
        playUrl(f);
    }

    public void move(View view) {
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 450);
        transAnim.setDuration(3000);
        transAnim.start();
    }

    public void playUrl(String url) {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                mediaPlayer = new MediaPlayer();
            } else {
                mediaPlayer = new MediaPlayer();
            }
            mediaPlayer.setDataSource(url); // 设置数据源
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//语速调节，只支持23以上api
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speedF));
            }
            mediaPlayer.prepare();
            mediaPlayer.start();// prepare自动播放
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("1111111111111111111111", "" + e);
        }
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

    private void stopMediaPlayer() {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

            if (rightOrWrongMedia != null && rightOrWrongMedia.isPlaying()) {
                rightOrWrongMedia.stop();
                rightOrWrongMedia.release();
                rightOrWrongMedia = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMediaPlayer();
    }

    @Override
    public int setlayoutResID() {
        return R.layout.bao_bao_lian_xi_fragment;
    }

    @Override
    public void getSuccess(String s, int what) {

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


    @OnClick({R.id.one, R.id.two, R.id.three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                toBig(one);
                Myanswer("A");
                break;
            case R.id.two:
                toBig(two);
                Myanswer("B");
                break;
            case R.id.three:
                toBig(three);
                Myanswer("C");
                break;
        }
    }

    private void Myanswer(String a) {
        if (isAnswer) return;
        if (rightOrWrongMedia != null) return;
        if (getDataBean().getAnswer().equals(a)) {
            if (YouErBaoBaoShouActivity.type == 49) {
                rightOrWrongMedia = MediaPlayer.create(mContext, R.raw.righten);
            } else {
                rightOrWrongMedia = MediaPlayer.create(mContext, R.raw.rightcn);
            }
            rightOrWrongMedia.start();
            rightOrWrongMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    answerNext();
                }
            });
        } else {
            rightOrWrongMedia = MediaPlayer.create(mContext, R.raw.wrong);
            rightOrWrongMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    rightOrWrongMedia.stop();
                    rightOrWrongMedia.release();
                    rightOrWrongMedia = null;
                }
            });
            rightOrWrongMedia.start();
        }
    }

    /**
     * 回答正确的逻辑
     */

    boolean isAnswer = false;

    private void answerNext() {
        isAnswer = true;
        if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
            getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            mediaPlayer = MediaPlayer.create(mContext, R.raw.excellent);
            mediaPlayer.start();
            GetGuoGuoDouDialog dialog = new GetGuoGuoDouDialog();
            dialog.setFenStr("恭喜您学习完成");
            dialog.setShareClick(new GetGuoGuoDouDialog.Share() {
                @Override
                public void share() {

                }

                @Override
                public void back() {
                    getActivity().finish();
                }
            });
            dialog.show(getChildFragmentManager(), "share");
        }
    }


    public LianXiData.DataBean.InfoBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(LianXiData.DataBean.InfoBean dataBean) {
        this.dataBean = dataBean;
    }

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.name)
    FlickerTextView name;
    @BindView(R.id.one)
    ImageView one;
    @BindView(R.id.two)
    ImageView two;
    @BindView(R.id.three)
    ImageView three;

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    ViewPager viewPager;


}
