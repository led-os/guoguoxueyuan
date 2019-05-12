package com.test720.grasshoppercollege;

import android.animation.ObjectAnimator;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

/*公用练习，竞赛，主界面*/
public abstract class PublicLianXiJinShaiActivity extends BaseToolActivity {
    String id;
    int type;

    public Button getZuo() {
        return zuo;
    }

    public Button getYou() {
        return you;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_public_lian_xi_jin_shai;
    }

    @Override
    public void initView() {
        super.initView();
        contentFrame.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (contentFrame.getAdapter().getCount() == position + 1) {
                    you.setVisibility(View.GONE);
                    zuo.setVisibility(View.VISIBLE);
                } else if (position == 0) {
                    zuo.setVisibility(View.GONE);
                    you.setVisibility(View.VISIBLE);
                } else {
                    you.setVisibility(View.VISIBLE);
                    zuo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*点击移动,上移动画*/
    private void playAnim() {
        View view;
        switch (index) {
            case 0:
                view = lin1;
                break;
            case 1:
                view = lin2;
                break;
            case 2:
                view = lin3;
                break;
            default:
                return;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, -100, 0, -50);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    /*点击移动,复原动画*/
    private void playAnimBack() {
        View view;
        switch (index) {
            case 0:
                view = lin1;
                break;
            case 1:
                view = lin2;
                break;
            case 2:
                view = lin3;
                break;
            default:
                return;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 50, 50, 0);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    @OnClick({R.id.zuo, R.id.you, R.id.back, R.id.lin1, R.id.lin2, R.id.lin3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zuo:
                contentFrame.setCurrentItem(contentFrame.getCurrentItem() - 1);
                break;
            case R.id.you:
                contentFrame.setCurrentItem(contentFrame.getCurrentItem() + 1);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.lin1:
                playAnimBack();
                index = 0;
                playAnim();
                break;
            case R.id.lin2:
                playAnimBack();
                index = 1;
                playAnim();
                  /*游客不能比赛*/
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jinShai();
                }
                break;
            case R.id.lin3:
                playAnimBack();
                index = 2;
                playAnim();
                /*游客不能比赛*/
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    JiLei();
                }
                break;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void JiLei();

    public abstract void jinShai();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ViewPager getContentFrame() {
        return contentFrame;
    }

    public void setContentFrame(ViewPager contentFrame) {
        this.contentFrame = contentFrame;
    }

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
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    @BindView(R.id.main_tabhost)
    LinearLayout mainTabhost;
    int index = 0;
    @BindView(R.id.zuo)
    Button zuo;
    @BindView(R.id.you)
    Button you;
    @BindView(R.id.back)
    ImageView back;
}
