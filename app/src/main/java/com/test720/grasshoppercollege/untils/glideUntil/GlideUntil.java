package com.test720.grasshoppercollege.untils.glideUntil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.R;

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
 * 作者：水东流 编于 2018/10/4
 */
public class GlideUntil {
    private static final GlideUntil ourInstance = new GlideUntil();

    public static GlideUntil getInstance() {
        return ourInstance;
    }

    private GlideUntil() {

    }

    public void headByUrl(final Context context, final RoundedImageView roundedImageView, String url) {

        Glide.with(context)
                .load(url)
                .asBitmap()
                .error(R.mipmap.pic_head_default)
                .placeholder(R.mipmap.pic_head_default)
                .into(new BitmapImageViewTarget(roundedImageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        if (resource != null) {
                            roundedImageView.setImageBitmap(resource);
                        }
                    }
                });
    }

    public void imgUrl(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).error(R.drawable.shu_xue_back).into(imageView);
    }

    public void imgBack(Context context, final View view, String url) {
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(drawable);   //设置背景
                }
            }
        });
    }

}
