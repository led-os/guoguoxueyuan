package com.test720.grasshoppercollege.module.gongLue.peiXun.jiaoShi;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.TeacherData;
import com.test720.grasshoppercollege.untils.AppUploatUtils;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class TeacherJieShaoActivity extends BaseToolActivity {
    AgentWeb mAgentWeb;


    private List<String> img = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_teacher_jie_shao;
//        return R.layout.activity_teacher_jie_shao_lin;
    }

    @Override
    protected void initData() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true); // 启用js
        webView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        if (GongLueData.getInstance().getType() == GongLueData.JIGOU) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("ts_id", getIntent().getStringExtra("ts_id"));
            Post(HttpUntil.GetIntent().StrategyTraingoodTSInfo(), builder, 1);
        } else if (GongLueData.getInstance().getType() == GongLueData.SCHOOL) {
            if (getIntent().getStringExtra("school_id") != null) {//园长介绍
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("school_id", GongLueData.getInstance().getSchoolId());
                Post(HttpUntil.GetIntent().StrategyChilddirectorIntroduce(), builder, 1);
            } else {//幼儿教师
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("ts_id", getIntent().getStringExtra("ts_id"));
                Post(HttpUntil.GetIntent().StrategyChildgoodTSInfo(), builder, 1);
            }

        }


    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            TeacherData data = new Gson().fromJson(str, TeacherData.class);

            name.setText(data.getData().getName());
            String t = data.getData().getName() + "介绍";
            title.setText(t);
            GlideUntil.getInstance().imgUrl(mcontext, head, data.getData().getPic());

            adress.setText(data.getData().getProfessional_title());
           /* mAgentWeb = AgentWeb.with(this)//传入Activity
                    .setAgentWebParent(fram, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                    .useDefaultIndicator()// 使用默认进度条
                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .createAgentWeb()//
                    .ready()
                    .go(data.getData().getDesc());*/
            webView.loadUrl(data.getData().getDesc());
          /*  Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("path", data.getData().getDesc());
            jumpToActivity(intent, false);*/

            img.clear();
            img.addAll(data.getData().getImg());
            initLunBo();
        }

    }

    private void dealWithViewPager() {
        int toolBarPositionY = toolbar.getHeight();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ViewGroup.LayoutParams params = fram.getLayoutParams();
            params.height = AppUploatUtils.getScreenHeightPx(mcontext) - toolBarPositionY + 1;
            fram.setLayoutParams(params);
        }

    }

    /**
     * 轮播加载
     */
    private void initLunBo() {
        slider.removeAllSliders();
        /*添加轮播信息*/
        for (int i = 0; i < img.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(mcontext);
            textSliderView
                    .description("")//描述
                    .image(img.get(i))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
//                    .setOnSliderClickListener(onSliderClickListener);//图片点击
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", "");//传入参数
            slider.addSlider(textSliderView);//添加一个滑动页面
        }

        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
        slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        slider.setDuration(4000);//设置滚动时间，也是计时器时间
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAgentWeb != null) mAgentWeb.destroy();
        mAgentWeb = null;
        webView.destroy();
    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
        webView.onPause();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();

    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.fram)
    FrameLayout fram;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.adress)
    TextView adress;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.webView)
    WebView webView;
}
