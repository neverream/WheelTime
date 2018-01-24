package com.example.qinyi.wheeltime.listener;

import com.example.qinyi.wheeltime.view.WheelView;

/**
 * Wheel scrolled listener interface.滚动的侦听器接口。
 */
public interface OnWheelScrollListener {
    /**
     * Callback method to be invoked when scrolling started.滚动启动时调用的回调方法。
     * @param wheel the wheel view whose state has changed.
     */
    void onScrollingStarted(WheelView wheel);

    /**
     * Callback method to be invoked when scrolling ended.在滚动结束时调用回调方法。
     * @param wheel the wheel view whose state has changed.
     */
    void onScrollingFinished(WheelView wheel);
}
