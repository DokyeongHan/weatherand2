package com.example.asdzx.weatherand2;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.asdzx.weatherand2.MainActivity.parstring;

public class AsyncTask1 extends AsyncTask<Integer, Integer, Void> {
    static String result = "";
    String line;
    String nowday;
    String nowtime;

    @Override
    protected Void doInBackground(Integer... integers) {
        nowtime = doYearMonthDay();
        nowday = doTime();

        //파싱
        try {
            parstring.miseparsing();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
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

    //오늘 날짜 가져오기 (api 변수에 사용합니다.)
    public static String doYearMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }

    //현재 시간 가져오기 (api 변수에 사용합니다.)
    public static String doTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH00", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }
}