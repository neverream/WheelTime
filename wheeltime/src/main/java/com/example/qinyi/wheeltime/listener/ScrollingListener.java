package com.example.qinyi.wheeltime.listener;

/**
 * 滚动侦听器接口
 */
public interface ScrollingListener {
    /**
     * 在执行滚动时滚动回调。
     *
     * @param distance 滚动的距离
     */
    void onScroll(int distance);

    /**
     * 当滚动启动时，启动回调
     */
    void onStarted();

    /**
     * 结束回调
     */
    void onFinished();

    /**
     * 在滚动结束时为回调进行辩护，以证明视图的合理性
     */
    void onJustify();
}
