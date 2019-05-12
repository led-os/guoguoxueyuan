package com.test720.grasshoppercollege.myViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class MoveTextView extends android.support.v7.widget.AppCompatTextView {
    public MoveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    float moveX;
    float moveY;
    AcctionUp acctionUp;

    public AcctionUp getAcctionUp() {
        return acctionUp;
    }

    public void setAcctionUp(AcctionUp acctionUp) {
        this.acctionUp = acctionUp;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(getX() + (event.getX() - moveX));
                setTranslationY(getY() + (event.getY() - moveY));
                break;
            case MotionEvent.ACTION_UP:
                if (getAcctionUp() != null) {
                    getAcctionUp().acctionUp(this);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;

    }

    /*移动结束后触发接口*/
    public interface AcctionUp {
        void acctionUp(MoveTextView moveTextView);
    }

}
