package com.example.qinyi.wheeltime.listener;


import com.example.qinyi.wheeltime.view.WheelView;

/**
 * 轮子改变了侦听器接口。
 * <p>The onChanged() method is called whenever current wheel positions is changed:
 * <li> New Wheel position is set
 * <li> Wheel view is scrolled
 */
public interface OnWheelChangedListener {
    /**
     * 当前项改变时调用回调方法
     *
     * @param wheel    the wheel view whose state has changed
     * @param oldValue the old value of current item
     * @param newValue the new value of current item
     */
    void onChanged(WheelView wheel, int oldValue, int newValue);
}