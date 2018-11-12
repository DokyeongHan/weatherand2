package com.example.asdzx.weatherand2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends FragmentActivity {
    MyPagerAdapter adapter;
    ViewPager pager;
    Context context = this;
    String data;
    TextView text;
    LayoutOne frag;
    FragmentManager manager;
    ViewPager viewPager;
    static weather weather;
    static parstring parstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //날씨 정보 객체와 파서객체 생성
        weather = new weather();
        try {
            parstring = new parstring();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        //findViewbyId
        text = findViewById(R.id.region_text);
        LinearLayout sbtnlayout = findViewById(R.id.sbtnlayout);
        Button sbtn = findViewById(R.id.sbtn);
        Button mbtn = findViewById(R.id.refresh);

        //viewpager 설정
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);

        //indicator 설정
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(pager);


        /* 지역선택부분에 밑줄
        TextView r_text = findViewById(R.id.region_text);
        r_text.setPaintFlags(r_text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        */

        //지역선택(글씨 클릭시)
        sbtnlayout.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), region.class);
                        startActivity(intent);
                    }
                }
        );

        //지역선택(아이콘 클릭시)
        sbtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), region.class);
                        startActivity(intent);
                    }
                }
        );

        //새로고침(아이콘 클릭시)
        mbtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        AsyncTask1 api = new AsyncTask1();
                        api.execute();
                        //Intent intent = new Intent(getApplicationContext(), menu.class);
                        //startActivity(intent);
                    }
                }
        );

        //비동기 작업 실행
        AsyncTask1 api = new AsyncTask1();
        api.execute();
    }


    @Override
    public void onResume() {
        super.onResume();
        //비동기 작업 실행
        AsyncTask1 api = new AsyncTask1();
        api.execute();

        //화면 새로고침시 지역글씨 바꿔줌
        /* TextView r_text = findViewById(R.id.region_text);
        try {
            FileInputStream inFs = openFileInput("file.txt");
            byte[] txt = new byte[50];
            inFs.read(txt);
            String str = new String(txt);
            r_text.setText(str);
            inFs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}