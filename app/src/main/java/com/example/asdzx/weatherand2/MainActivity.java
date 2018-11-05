package com.example.asdzx.weatherand2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends FragmentActivity {
    MyPagerAdapter adapter;
    ViewPager pager;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);

        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        /* 지역선택부분에 밑줄
        TextView r_text = findViewById(R.id.region_text);
        r_text.setPaintFlags(r_text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        */

        LinearLayout sbtnlayout = findViewById(R.id.sbtnlayout);
        sbtnlayout.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), region.class);
                        startActivity(intent);
                    }
                }
        );

        Button sbtn = findViewById(R.id.sbtn);
        sbtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), region.class);
                        startActivity(intent);
                    }
                }
        );

        Button mbtn = findViewById(R.id.menu);
        mbtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), menu.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onResume() {
        super.onResume();

        TextView r_text = findViewById(R.id.region_text);

        try {
            FileInputStream inFs = openFileInput("file.txt");
            byte[] txt = new byte[50];
            inFs.read(txt);
            String str = new String(txt);
            r_text.setText(str);
            inFs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String doYearMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }
}