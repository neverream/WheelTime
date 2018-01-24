package com.example.qinyi.wheeltime.listener;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * Wheel items adapter interface
 */
public interface WheelViewAdapterListener {
    /**
     * Gets items count 获取条目数
     *
     * @return the count of wheel items
     */
    int getItemsCount();

    /**
     * Get a View that displays the data at the specified position in the data set
     * 获取一个视图，在数据集中显示指定位置的数据
     *
     * @param index       the item index
     * @param convertView the old view to reuse if possible
     * @param parent      the parent that this view will eventually be attached to
     * @return the wheel item View
     */
    View getItem(int index, View convertView, ViewGroup parent);

    /**
     * Get a View that displays an empty wheel item placed before the first or after
     * the last wheel item.
     * 获取一个视图，在第一个或之后显示一个空的轮项最后一个轮子项目。
     *
     * @param convertView the old view to reuse if possible
     * @param parent      the parent that this view will eventually be attached to
     * @return the empty item View
     */
    View getEmptyItem(View convertView, ViewGroup parent);

    /**
     * Register an observer that is called when changes happen to the data used by this adapter.
     * 注册一个观察者，当这个适配器使用的数据发生变化时，会被调用。
     *
     * @param observer the observer to be registered
     */
    void registerDataSetObserver(DataSetObserver observer);

    /**
     * Unregister an observer that has previously been registered     *
     * 取消注册一个先前已注册的观察者
     *
     * @param observer the observer to be unregistered
     */
    void unregisterDataSetObserver(DataSetObserver observer);
}
