package com.test720.grasshoppercollege.myViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 水东流 on 2018/7/12.
 */

public class XuanFuView extends android.support.v7.widget.AppCompatImageView {
    float moveX;
    float moveY;
    private long startTime = 0;
    private long endTime = 0;
    public XuanFuView(Context context) {
        super(context);
    }

    public XuanFuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XuanFuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                startTime = System.currentTimeMillis();

                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(getX() + (event.getX() - moveX));
                setTranslationY(getY() + (event.getY() - moveY));
                break;
            case MotionEvent.ACTION_UP:
               /* if(Math.abs(event.getX()-moveX)<30&&Math.abs(event.getY()-moveY)<30){
                    performClick();
                }*/
                endTime = System.currentTimeMillis();
                if(Math.abs(endTime-startTime)<200){
                    performClick();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
