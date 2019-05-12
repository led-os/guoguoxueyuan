package com.test720.grasshoppercollege.myViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
 * 作者：水东流 编于 2018/11/12
 */
public class TanXianGuankaView extends FrameLayout {
    private ImageView img;
    private RoundedImageView suo;

    boolean suoB = true;// 当前关卡状态是否解锁

    public TanXianGuankaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TanXianGuankaView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.tan_xian_guan_ka_view, this, true);
        img = findViewById(R.id.img);
        suo = findViewById(R.id.suo);
        @SuppressLint({"Recycle", "CustomViewStyleable"}) TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TanXianGuanKaView);
        if (attributes != null) {
            //处理关卡图
            int imgSrc = attributes.getResourceId(R.styleable.TanXianGuanKaView_src, Color.WHITE);
            img.setImageResource(imgSrc);
            //关卡是否解锁
            suoB = attributes.getBoolean(R.styleable.TanXianGuanKaView_suo, true);
            if (suoB) {
                suo.setVisibility(VISIBLE);
            } else {
                suo.setVisibility(INVISIBLE);
            }
        }
        attributes.recycle();
    }

    public void setSuoB(boolean suoB) {
        this.suoB = suoB;
        if (suoB) {
            suo.setVisibility(VISIBLE);
        } else {
            suo.setVisibility(INVISIBLE);
        }
    }

    public ImageView getImg() {
        return img;
    }
}
