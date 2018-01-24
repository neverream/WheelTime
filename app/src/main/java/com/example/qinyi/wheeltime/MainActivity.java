package com.example.qinyi.wheeltime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTime = findViewById(R.id.start_time_text);
        startTime.setOnClickListener(this);
        startTime.setText(getYear() + "年-" + getMonth() + "月-" + getDay() + "日");
    }

    @Override
    public void onClick(View v) {
        selectDate(startTime, startTime.getText().toString());
    }

    private void selectDate(final TextView textView, String s) {
        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(this, getYear(s), getMonth(s), getDay(s));
        mChangeBirthDialog.showAtLocation(textView, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {

                textView.setText(year + "-" + month + "-" + day);
                Toast.makeText(MainActivity.this, year + "-" + month + "-" + day, Toast
                        .LENGTH_LONG).show();

            }
        });
    }

    public String getYear(String s) {
        String year = s.split("年-")[0];
        return year;
    }

    public String getMonth(String s) {
        String mouth = s.split("年-")[1].split("月-")[0];
        return mouth;
    }

    public String getDay(String s) {
        String day = s.split("月-")[1].split("日")[0];
        return day;
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
