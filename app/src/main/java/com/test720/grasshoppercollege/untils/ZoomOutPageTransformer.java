package com.test720.grasshoppercollege.untils;

import android.support.v4.view.ViewPager;
import android.view.View;

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
 * 作者：水东流 编于 2018/12/6
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        /**
         * position：这个position不是手指滑动的坐标位置，而是滑动页面相对于手机屏幕的位置，
         * 范围位：[-1,1],[-1,0)表示页面向左滑出屏幕，0表示处于中心（即当前显示）的页面，
         * (0,1]表示页面向右滑出屏幕
         */
        if (position < -1) { //表示已经滑出屏幕（左边）
            view.setAlpha(0);
        } else if (position <= 1) { // [-1,1]
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            //缩放
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            //设置透明度
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else {
            //表示已经滑出屏幕（右边）
            view.setAlpha(0);
        }
    }
}