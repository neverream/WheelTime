package com.example.qinyi.wheeltime.view;

import android.view.View;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * 循环存储轮项以重复使用
 */
public class WheelRecycle {
    // Cached items 缓存条目
    private List<View> items;

    // Cached empty items 缓存空项
    private List<View> emptyItems;

    private WheelView wheel;

    public WheelRecycle(WheelView wheel) {
        this.wheel = wheel;
    }

    /**
     * 从指定的布局中回收项目。
     * 只保存了不包含在指定范围内的项。
     * 所有缓存的项都从原来的布局中删除
     *
     * @param layout    布局包含要缓存的项的布局
     * @param firstItem 第一个项目的数量
     * @param range     范围是当前轮项的范围
     * @return 返回第一个项目编号的新值
     */
    public int recycleItems(LinearLayout layout, int firstItem, ItemsRange range) {
        int index = firstItem;
        for (int i = 0; i < layout.getChildCount(); ) {
            if (!range.contains(index)) {
                recycleView(layout.getChildAt(i), index);
                layout.removeViewAt(i);
                if (i == 0) { // first item
                    firstItem++;
                }
            } else {
                i++; // go to next item
            }
            index++;
        }
        return firstItem;
    }

    /**
     * 添加视图到缓存。通过索引确定视图类型(项目视图或空视图)。
     *
     * @param view  查看要缓存的视图
     * @param index 视图索引
     */
    private void recycleView(View view, int index) {
        int count = wheel.getViewAdapterListener().getItemsCount();

        if ((index < 0 || index >= count) && !wheel.isCyclic()) {
            // empty view
            emptyItems = addView(view, emptyItems);
        } else {
            while (index < 0) {
                index = count + index;
            }
            index %= count;
            items = addView(view, items);
        }
    }
    /**
     * Gets item view
     *
     * @return the cached view
     */
    public View getItem() {
        return getCachedView(items);
    }

    /**
     * Gets empty item view
     *
     * @return the cached empty view
     */
    public View getEmptyItem() {
        return getCachedView(emptyItems);
    }

    /**
     * Clears all views
     */
    public void clearAll() {
        if (items != null) {
            items.clear();
        }
        if (emptyItems != null) {
            emptyItems.clear();
        }
    }
    /**
     * 从指定的缓存获取视图。
     *
     * @param cache the cache
     * @return the first view from cache.
     */
    private View getCachedView(List<View> cache) {
        if (cache != null && cache.size() > 0) {
            View view = cache.get(0);
            cache.remove(0);
            return view;
        }
        return null;
    }
    /**
     * 将视图添加到指定的缓存。如果它是空的，则创建一个缓存列表。
     *
     * @param view  the view to be cached
     * @param cache the cache list
     * @return the cache list
     */
    private List<View> addView(View view, List<View> cache) {
        if (cache == null) {
            cache = new LinkedList<View>();
        }

        cache.add(view);
        return cache;
    }
}
