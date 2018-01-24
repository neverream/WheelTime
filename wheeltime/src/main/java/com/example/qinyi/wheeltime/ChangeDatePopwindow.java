package com.example.qinyi.wheeltime;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.qinyi.wheeltime.adapter.CalendarTextAdapter;
import com.example.qinyi.wheeltime.listener.OnWheelChangedListener;
import com.example.qinyi.wheeltime.listener.OnWheelScrollListener;
import com.example.qinyi.wheeltime.view.WheelView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 自定义日期选择window
 */
public class ChangeDatePopwindow extends PopupWindow implements View.OnClickListener {

    private Context context;
    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;
    private TextView confirm;
    private TextView cancel;

    private String selectYear;
    private String selectMonth;
    private String selectDay;

    private String month;
    private String day;

    private int maxTextSize = 24;
    private int minTextSize = 14;

    private String currentYear = getYear();
    private String currentMonth = getMonth();
    private String currentDay = getDay();

    private CalendarTextAdapter mYearAdapter;
    private CalendarTextAdapter mMonthAdapter;
    private CalendarTextAdapter mDaydapter;

    private ArrayList<String> arry_years = new ArrayList<>();
    private ArrayList<String> arry_months = new ArrayList<>();
    private ArrayList<String> arry_days = new ArrayList<>();

    private OnBirthListener onBirthListener;

    public interface OnBirthListener {
        void onClick(String year, String month, String day);
    }

    public void setBirthdayListener(OnBirthListener onBirthListener) {
        this.onBirthListener = onBirthListener;
    }

    public ChangeDatePopwindow(final Context context, String Year, String Month, String Day) {
        super(context);
        this.context = context;
        View view = View.inflate(context, R.layout.time_dialog, null);
        wvYear = (WheelView) view.findViewById(R.id.wv_birth_year);
        wvMonth = (WheelView) view.findViewById(R.id.wv_birth_month);
        wvDay = (WheelView) view.findViewById(R.id.wv_birth_day);
        confirm = (TextView) view.findViewById(R.id.confirm);
        cancel = (TextView) view.findViewById(R.id.cancel);

        //设置SelectPicPopupWindow的View
        setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//		setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable cd = new ColorDrawable(0x4c000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        setBackgroundDrawable(cd);

        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);

        setDate(Year, Month, Day);

        initYears();
        mYearAdapter = new CalendarTextAdapter(context, arry_years, setYear(currentYear), maxTextSize, minTextSize);
        wvYear.setVisibleItems(5);
        wvYear.setViewAdapter(mYearAdapter);
        wvYear.setCurrentItem(setYear(currentYear));

        initMonths(Integer.parseInt(month));
        mMonthAdapter = new CalendarTextAdapter(context, arry_months, setMonth(currentMonth), maxTextSize, minTextSize);
        wvMonth.setVisibleItems(5);
        wvMonth.setViewAdapter(mMonthAdapter);
        wvMonth.setCurrentItem(setMonth(currentMonth));

        initDays(Integer.parseInt(day));
        mDaydapter = new CalendarTextAdapter(context, arry_days, Integer.parseInt(currentDay) - 1, maxTextSize, minTextSize);
        wvDay.setVisibleItems(5);
        wvDay.setViewAdapter(mDaydapter);
        wvDay.setCurrentItem(Integer.parseInt(currentDay) - 1);

        ChangingListener();
        ScrollingListener();
    }

    private void ChangingListener() {
        wvYear.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                selectYear = currentText;
                setTextviewSize(currentText, mYearAdapter);
                currentYear = currentText.substring(0, currentText.length() - 1).toString();
                Log.d("currentYear==", currentYear);

                setYear(currentYear);//设置月份
                calDays(currentYear, month);//计算每月多少天
                initMonths(Integer.parseInt(month));
                initDays(Integer.parseInt(day));

                // TODO
                mMonthAdapter = new CalendarTextAdapter(context, arry_months, 0, maxTextSize, minTextSize);
                wvMonth.setVisibleItems(5);
                wvMonth.setViewAdapter(mMonthAdapter);
//                    wvMonth.setCurrentItem(Integer.parseInt(getMonth()) - 1);
                wvMonth.setCurrentItem(0);

                mDaydapter = new CalendarTextAdapter(context, arry_days, 0, maxTextSize, minTextSize);
                wvDay.setVisibleItems(5);
                wvDay.setViewAdapter(mDaydapter);
                wvDay.setCurrentItem(0);


            }
        });
        wvMonth.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                selectMonth = currentText;
                setTextviewSize(currentText, mMonthAdapter);
                setMonth(currentText.length() == 2 ?
                        currentText.substring(0, 1) :
                        currentText.substring(0, 2));

                calDays(currentYear, month);
                initDays(Integer.parseInt(day));
                // TODO

                mDaydapter = new CalendarTextAdapter(context, arry_days, 0, maxTextSize, minTextSize);
                wvDay.setVisibleItems(5);
                wvDay.setViewAdapter(mDaydapter);
                wvDay.setCurrentItem(0);

            }
        });
        wvDay.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
                selectDay = currentText;
            }
        });
    }

    private void ScrollingListener() {
        wvYear.addScrollingListener(new OnWheelScrollListener() {

            @Override//滚动开始
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override//滚动结束
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mYearAdapter);
            }
        });
        wvMonth.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMonthAdapter);
            }
        });
        wvDay.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
            }
        });

    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == confirm) {
            if (onBirthListener != null) {
                onBirthListener.onClick(selectYear, selectMonth, selectDay);
            }
        } else {
            dismiss();
        }
        dismiss();
    }

    /**
     * 设置年月日
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(String year, String month, String day) {
        selectYear = year + "年";
        selectMonth = month + "月";
        selectDay = day + "日";
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
        if (year == getYear()) {
            this.month = getMonth();
        } else {
            this.month = 12 + "";
        }
        calDays(year, month);
    }

    /**
     * 设置年份
     *
     * @param year
     */
    public int setYear(String year) {
        int yearIndex = 0;
        if (!year.equals(getYear())) {
            this.month = 12 + "";
        } else {
            this.month = getMonth();
        }
        System.out.println(this.month);
        for (int i = Integer.parseInt(getYear()); i > 1950; i--) {
            if (i == Integer.parseInt(year)) {
                return yearIndex;
            }
            yearIndex++;
        }
        return yearIndex;
    }

    /**
     * 设置月份
     *
     * @param month
     * @param month
     * @return
     */
    public int setMonth(String month) {
        int monthIndex = 0;
        calDays(currentYear, month);
        for (int i = 1; i < Integer.parseInt(this.month); i++) {
            if (Integer.parseInt(month) == i) {
                return monthIndex;
            } else {
                monthIndex++;
            }
        }
        return monthIndex;
    }

    /**
     * 计算每月多少天
     *
     * @param month
     * @param year
     */
    public void calDays(String year, String month) {
        boolean leayyear;
        if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        for (int i = 1; i <= 12; i++) {
            switch (Integer.parseInt(month)) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    this.day = 31 + "";
                    break;
                case 2:
                    if (leayyear) {
                        this.day = 29 + "";
                    } else {
                        this.day = 28 + "";
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    this.day = 30 + "";
                    break;
            }
        }
        System.out.println(this.day);
        if (year.equals(getYear()) && month.equals(getMonth())) {
            this.day = getDay();
            System.out.println(this.day);
        }
        initDays(Integer.parseInt(day));
    }

    public void initYears() {
        for (int i = Integer.parseInt(getYear()); i > 1950; i--) {
            arry_years.add(i + "年");
        }
    }

    public void initMonths(int months) {
        arry_months.clear();
        for (int i = 1; i <= months; i++) {
            arry_months.add(i + "月");
        }
    }

    public void initDays(int days) {
        arry_days.clear();
        for (int i = 1; i <= days; i++) {
            arry_days.add(i + "日");
        }
    }

    public String getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "";
    }

    public String getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1 + "";
    }

    public String getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE) + "";
    }
}
