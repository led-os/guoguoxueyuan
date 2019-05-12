package com.test720.grasshoppercollege.module.guoguoDou;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guoguoDou.bean.ShangPXQData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GuoGuoShangPingXQActivity extends BaseToolActivity {


    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.custom_indicator2)
    PagerIndicator customIndicator2;
    @BindView(R.id.saleCount)
    TextView saleCount;
    @BindView(R.id.yuanjia)
    TextView yuanjia;
    @BindView(R.id.vipBZ)
    TextView vipBZ;
    @BindView(R.id.vipPrice)
    TextView vipPrice;
    @BindView(R.id.jieShaoWeb)
    WebView jieShaoWeb;
    @BindView(R.id.duiHuan)
    TextView duiHuan;
    @BindView(R.id.title)
    TextView title;

    ShangPXQData data;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_guo_guo_shang_ping;
    }


    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("gid", getIntent().getStringExtra("gid"));
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/User/goodsInfo",
                httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, ShangPXQData.class);

            title.setText(data.getData().getGoods().getInfo().getName());

            String y = "果果豆:" + data.getData().getGoods().getInfo().getPrice() + "颗";
            yuanjia.setText(y);

            String v = "vip 果果豆:" + data.getData().getGoods().getInfo().getVip_price() + "颗";
            vipPrice.setText(v);

            String ys = data.getData().getGoods().getInfo().getSale_count() + "件";
            saleCount.setText(ys);

            if (data.getData().getGoods().getInfo().getVip().equals("1")) {
                vipBZ.setVisibility(View.VISIBLE);
            } else {
                vipBZ.setVisibility(View.GONE);
            }

            //轮播
            initLunBo(data.getData().getGoods().getImg());

            jieShaoWeb.loadUrl(data.getData().getGoods().getInfo().getDesc());
        }
    }

    /**
     * 轮播
     *
     * @param img 图片地址集合
     */
    private void initLunBo(List<String> img) {
        slider.removeAllSliders();
        for (int i = 0; i < img.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(mcontext);
            textSliderView
                    .description("")//描述
                    .image(img.get(i))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", "");//传入参数
            slider.addSlider(textSliderView);//添加一个滑动页面
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
        slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        slider.setDuration(4000);//设置滚动时间，也是计时器时间
    }

    /*规格弹框*/
    private void showGouWuPop() {

        GuiGeDialog dingDanTypeSetDialog = new GuiGeDialog();
        dingDanTypeSetDialog.setList(data.getData().getGoods().getSpec());
        dingDanTypeSetDialog.setGid(getIntent().getStringExtra("gid"));
        dingDanTypeSetDialog.show(getSupportFragmentManager(), "guige");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        jieShaoWeb.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jieShaoWeb.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        jieShaoWeb.onResume();
    }


    @OnClick({R.id.back, R.id.duiHuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.duiHuan:
                if (data == null) {
                    ShowToast("数据加载中，求稍后");
                    return;
                }
                if (MyApplication.getmInstance().userData == null) {
                    ShowToast("未能获取到用户信息，请尝试重新登录");
                    return;
                }
                if (data.getData().getGoods().getInfo().getVip().equals("1")
                        && !MyApplication.getmInstance().userData.getData().getVip().equals("1")) {
                    ShowToast("对不起,此商品只有vip可兑换");
                    return;
                }
                showGouWuPop();
                break;
        }
    }
}
