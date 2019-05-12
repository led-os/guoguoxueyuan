package com.test720.grasshoppercollege.myViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by 水东流 on 2018/8/8.
 */

public class OneStepOnlyListView extends ListView {
    private int minDistance = 500;
    private int ScrollDuration = 300;
    private int dataCount;
    private int offset = 0;
    public int scaledTouchSlop;
    private AfterScrollListener listener;
    private int dividerHeight;
    private boolean allowDispatch;
    //0 down, 1 up
    private int lastAction;



    public OneStepOnlyListView(Context context) {
        super(context);
    }

    public OneStepOnlyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OneStepOnlyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        updateArgs();
    }

    private void updateArgs() {
        ListAdapter listAdapter = getAdapter();
        if (listAdapter != null) {
            View listItem = listAdapter.getView(0, null, this);
            listItem.measure(0, 0);
            minDistance = listItem.getMeasuredHeight() / 2;
            dataCount = listAdapter.getCount();
        }
        scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        dividerHeight = getDividerHeight();
//        L.log("dividerHeight=" + dividerHeight + " minDistance=" + minDistance + " scaledTouchSlop=" + scaledTouchSlop);
        setMotionEventSplittingEnabled(false);
//        L.log("isMotionEventSplittingEnabled=" + isMotionEventSplittingEnabled());
    }


    private float mLastY = 0;
    private float upY = 0;


   /* @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getY();
                lastAction = 0;
                L.log("ACTION_DOWN ="+lastAction);

                break;
            case MotionEvent.ACTION_UP:
                L.log("ACTION_UP ="+lastAction);


                if (lastAction == 1) {
                    L.log("ACTION_up return");
                    lastAction = 1;
                    return true;
                }

                upY = ev.getY();
                int firstVisiblePosition = getFirstVisiblePosition();
                float distance = upY - mLastY;

                //distance is too small to move
                if (0 <= Math.abs(distance) && Math.abs(distance) < scaledTouchSlop) {
                    runListener(firstVisiblePosition);
                    L.log("1");
                    lastAction = 1;
                    break;
                }

                //drag up
                if (distance < 0) {
                    dragUp(firstVisiblePosition, distance);
                } else {
                    //drag down
                    dragDown(firstVisiblePosition, distance);
                    lastAction = 1;
                    break;
                }

                lastAction = 1;

                break;
            case MotionEvent.ACTION_CANCEL:
                L.log("ACTION_CANCEL");
                break;

        }
        return super.onTouchEvent(ev);
    }*/


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
      /*  if (allowDispatch) {
            return true;
        }
        return super.dispatchTouchEvent(ev);*/


        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getY();
                lastAction = 0;
//                L.log("ACTION_DOWN ="+lastAction);

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
//                L.log("ACTION_UP ="+lastAction);


                if (lastAction == 1) {
//                    L.log("ACTION_up return");
                    lastAction = 1;
                    return true;
                }

                upY = ev.getY();
                int firstVisiblePosition = getFirstVisiblePosition();
                float distance = upY - mLastY;
//                L.log("distance="+distance);

                //distance is too small to move
                if (0 <= Math.abs(distance) && Math.abs(distance) < scaledTouchSlop) {
                    runListener(firstVisiblePosition);
//                    L.log("1");
                    lastAction = 1;
//                    L.log("type="+1);
                    return super.dispatchTouchEvent(ev);
                }

                //drag up
                if (distance < 0) {
                    dragUp(firstVisiblePosition, distance);
//                    L.log("type="+2);
                } else {
                    //drag down
                    dragDown(firstVisiblePosition, distance);
                    lastAction = 1;
//                    L.log("type="+3);
                    return true;
                }

                lastAction = 1;

                return true;
            case MotionEvent.ACTION_CANCEL:
//                L.log("ACTION_CANCEL");
                break;

        }
        return super.dispatchTouchEvent(ev);


    }

    private void dragDown(int firstVisiblePosition, float distance) {

//        L.log("distance=" + Math.abs(distance));

        if (scaledTouchSlop < Math.abs(distance) && Math.abs(distance) < scaledTouchSlop + dividerHeight) {
            moveToPosition(firstVisiblePosition);
//            L.log("2");
            return;
        }

        if (scaledTouchSlop < Math.abs(distance) && Math.abs(distance) < minDistance) {//小于最小滑动距离, 回到原来位置
//            L.log("3");
            //when first item is visible
            if (firstVisiblePosition == 0) {
                View firstVisibleItemView = getChildAt(0);
                //when first item is at the top, small distance will cause to roll down
                if (firstVisibleItemView != null && firstVisibleItemView.getTop() >= -scaledTouchSlop) {
                    moveToPosition(firstVisiblePosition);
//                    L.log("4");
                    return;
                }
            }
            moveToPosition(firstVisiblePosition + 1);
        } else {
//            L.log("5");
            moveToPosition(firstVisiblePosition);
        }
    }

    private void dragUp(int position, float distance) {
        if (scaledTouchSlop < Math.abs(distance) && Math.abs(distance) < minDistance) {
            moveToPosition(position);
        } else {
            if (position == dataCount) {
                moveToPosition(position);
            } else {
                moveToPosition(position + 1);
            }

        }
    }

    private void moveToPosition(final int position) {
        allowDispatch = true;
        smoothScrollToPositionFromTop(position, offset, ScrollDuration);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                allowDispatch = false;
                runListener(position);
            }
        }, ScrollDuration);

    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 4);
    }


    public interface AfterScrollListener {
        void afterScroll(int position);
    }

    public int getScrollDuration() {
        return ScrollDuration;
    }

    public void setScrollDuration(int scrollDuration) {
        ScrollDuration = scrollDuration;
    }

    public AfterScrollListener getAfterScrollListener() {
        return listener;
    }

    public void setAfterScrollListener(AfterScrollListener listener) {
        this.listener = listener;
    }


    void runListener(int position) {
        if (listener != null) {
            listener.afterScroll(position);
        }
    }

}
