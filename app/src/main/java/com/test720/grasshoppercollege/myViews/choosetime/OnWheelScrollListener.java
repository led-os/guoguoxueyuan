package com.test720.grasshoppercollege.myViews.choosetime;

/**
 * Created by Administrator on 2016/11/14.
 *
 */
public interface OnWheelScrollListener {

    /**
     * Callback method to be invoked when scrolling started.
     * @param wheel the wheel view whose state has changed.
     */
    void onScrollingStarted(WheelView wheel);

    /**
     * Callback method to be invoked when scrolling ended.
     * @param wheel the wheel view whose state has changed.
     */
    void onScrollingFinished(WheelView wheel);

}
