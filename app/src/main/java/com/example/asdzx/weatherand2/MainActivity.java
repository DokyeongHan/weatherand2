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
    static weather a;
    static String result = "";
    String line;
    static parstring b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = new weather();
        b = new parstring();

        text = findViewById(R.id.region_text);

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
        APItask api = new APItask();
        api.execute();
        Button mbtn = findViewById(R.id.refresh);
        mbtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        APItask api = new APItask();
                        api.execute();
                        //Intent intent = new Intent(getApplicationContext(), menu.class);
                        //startActivity(intent);
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



    public class APItask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            BufferedReader br = null;
            try {
                String urlstr = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?" +
                        "stationName=석사동&" +
                        "dataTerm=month&" +
                        "pageNo=1&" +
                        "numOfRows=1&" +
                        "ServiceKey=PExw%2FhWxDuRH5PLh2T7Z0gRK0KPerENji1Yskvtp4ApbzepFsKLRH%2FZnI17I6dRfs0Obd6DBevxgbonv%2BUdq9g%3D%3D&" +
                        "ver=1.3";

                URL url = new URL(urlstr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                result = "";
                while ((line = br.readLine()) != null) {
                    result = result + line + "\n";
                }
                b.setMise(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}