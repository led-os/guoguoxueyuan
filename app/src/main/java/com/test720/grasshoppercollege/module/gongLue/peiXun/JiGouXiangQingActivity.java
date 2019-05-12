package com.test720.grasshoppercollege.module.gongLue.peiXun;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.JiGouData;
import com.test720.grasshoppercollege.myViews.PhoneCalDialog;
import com.test720.grasshoppercollege.untils.AppUploatUtils;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class JiGouXiangQingActivity extends BaseToolActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fram)
    FrameLayout fram;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.img)
    ImageView imageView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.adress)
    TextView adress;

    private List<JiGouData.DataBean.ImgBean> img = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ji_gou_xiang_qing;
    }

    @Override
    protected void initData() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        if (getIntent().getStringExtra("name") != null) {
            title.setText(getIntent().getStringExtra("name"));
        }
        webView.getSettings().setJavaScriptEnabled(true); // 启用js
        webView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("school_id", getIntent().getStringExtra("school_id"));

        if (GongLueData.getInstance().getType() == GongLueData.JIGOU) {
            postResponse(HttpUntil.GetIntent().StrategyTrainschoolInfoDesc(), httpParams, 1, true);
        } else {
            postResponse(HttpUntil.GetIntent().StrategyChildschoolInfoDesc(), httpParams, 1, true);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
        webView = null;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            JiGouData data = new Gson().fromJson(str, JiGouData.class);
            img.clear();
            img.addAll(data.getData().getImg());
            initLunBo();

            name.setText("  " + data.getData().getName());
            phone.setText("  " + data.getData().getTelephone());
            adress.setText("  " + data.getData().getProvince()
                    + data.getData().getCity()
                    + data.getData().getArea()
                    + data.getData().getOther());
            Glide.with(mcontext).load(data.getData().getCover()).into(imageView);

            webView.loadUrl(data.getData().getDesc());
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
        HashMap<String, String> urlMaps = new HashMap<>();//轮播
        /*添加轮播信息*/
        for (int i = 0; i < img.size(); i++) {
            urlMaps.put(img.get(i).getName() + i, img.get(i).getImg());
        }
        for (String name : urlMaps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(mcontext);
            textSliderView
                    .description("")//描述
                    .image(urlMaps.get(name))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
//                    .setOnSliderClickListener(onSliderClickListener);//图片点击
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);//传入参数
            slider.addSlider(textSliderView);//添加一个滑动页面
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
        slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        slider.setDuration(4000);//设置滚动时间，也是计时器时间
    }


    @OnClick({R.id.back, R.id.phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.phone:
                if (TextUtils.isEmpty(phone.getText())) return;
                PhoneCalDialog phoneCalDialog = new PhoneCalDialog();
                phoneCalDialog.setPhone(phone.getText().toString());
                phoneCalDialog.setCallListener(new PhoneCalDialog.CallListener() {
                    @Override
                    public void call() {
                        callPhoone(phone.getText().toString());
                    }
                });
                phoneCalDialog.show(getSupportFragmentManager(), "phone");
                break;
        }
    }

    /**
     * 拨号
     */
    private void callPhoone(final String phoneNum) {
        AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.CALL_PHONE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);//设置活动类型
                intent.setData(Uri.parse("tel:" + phoneNum));//设置数据
                startActivity(intent);//开启意图
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            }
        }).start();
    }

}
