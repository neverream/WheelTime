package com.example.qinyi.wheeltime.view;

/**
 * 可见项的范围。
 */
public class ItemsRange {
    // First item number
    private int first;

    // Items count
    private int count;

    /**
     * 默认构造函数。创建一个空的范围
     */
    public ItemsRange() {
        this(0, 0);
    }

    /**
     * Constructor
     *
     * @param first the number of first item
     * @param count the count of items
     */
    public ItemsRange(int first, int count) {
        this.first = first;
        this.count = count;
    }

    /**
     * Gets number of  first item
     *
     * @return 第一个项目的数量
     */
    public int getFirst() {
        return first;
    }

    /**
     * Gets number of last item
     *
     * @return 最后一项的数量
     */
    public int getLast() {
        return getFirst() + getCount() - 1;
    }

    /**
     * Get items count
     *
     * @return the count of items
     */
    public int getCount() {
        return count;
    }

    /**
     * 测试项目是否包含范围
     *
     * @param index the item number
     * @return true if item is contained
     */
    public boolean contains(int index) {
        return index >= getFirst() && index <= getLast();
    }
}
