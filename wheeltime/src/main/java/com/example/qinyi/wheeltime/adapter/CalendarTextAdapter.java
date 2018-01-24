package com.example.qinyi.wheeltime.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.qinyi.wheeltime.R;

import java.util.ArrayList;

public class CalendarTextAdapter extends AbstractWheelTextAdapter {
    ArrayList<String> list;

    public CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
        super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
        this.list = list;
        setItemTextResource(R.id.tempValue);
    }

    public void updateList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        View view = super.getItem(index, cachedView, parent);
        return view;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public CharSequence getItemText(int index) {
        return list.get(index) + "";
    }
}
