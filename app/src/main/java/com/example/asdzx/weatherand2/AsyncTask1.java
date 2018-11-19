package com.example.asdzx.weatherand2;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        //파싱
        try {
            parstring.miseparsing();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        parstring.chodanparsing();
        parstring.dongnae2parsing();
        parstring.dongnae5parsing();
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
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

    public static String doyes(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);  // 오늘 날짜에서 하루를 뺌.
        String date = formatter.format(calendar.getTime());
        return date;
    }

    public static String today_1(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +1);  // 오늘 날짜에서 하루를 뺌.
        String date = formatter.format(calendar.getTime());
        return date;
    }

    public static String today_2(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +2);  // 오늘 날짜에서 하루를 뺌.
        String date = formatter.format(calendar.getTime());
        return date;
    }

    public static String today_3(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +3);  // 오늘 날짜에서 하루를 뺌.
        String date = formatter.format(calendar.getTime());
        return date;
    }

    public static String today_4(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +4);  // 오늘 날짜에서 하루를 뺌.
        String date = formatter.format(calendar.getTime());
        return date;
    }

    public static String today_5(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, +5);  // 오늘 날짜에서 하루를 뺌.
        String date = formatter.format(calendar.getTime());
        return date;
    }
}