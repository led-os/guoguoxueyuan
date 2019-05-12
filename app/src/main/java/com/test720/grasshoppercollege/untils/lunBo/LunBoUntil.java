package com.test720.grasshoppercollege.untils.lunBo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.shangCheng.ShangPinXiangQingActivity;

import java.util.List;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/9/19
 */
public class LunBoUntil {
    SliderLayout slider; //轮播框
    private List<Banner> banner;//数据
    Context context;

    public LunBoUntil(SliderLayout slider, List<Banner> banner, Context context) {
        this.slider = slider;
        this.banner = banner;
        this.context = context;
    }

    public void start() {
        slider.removeAllSliders();
        for (int i = 0; i < banner.size(); i++) {
            final int j = i;
            TextSliderView textSliderView = new TextSliderView(context);
            textSliderView
                    .description(banner.get(i).getName())//描述
                    .image(banner.get(i).getPic())//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", banner.get(i).getName());//传入参数
            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    switch (banner.get(j).getLocation_type()) {
                        case "1":
                            break;
                        case "2":
                            Intent intent = new Intent(context, WebViewActivity.class);
                            intent.putExtra("title", banner.get(j).getName());
                            intent.putExtra("path", banner.get(j).getLocation_link());
                            context.startActivity(intent);
                            break;
                        case "3":
                            Intent intent1 = new Intent(context, ShangPinXiangQingActivity.class);
                            intent1.putExtra("gid", banner.get(j).getG_id());
                            context.startActivity(intent1);
                            break;
                    }

                }
            });
            slider.addSlider(textSliderView);//添加一个滑动页面
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
        slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        slider.setDuration(4000);//设置滚动时间，也是计时器时间
    }

}
