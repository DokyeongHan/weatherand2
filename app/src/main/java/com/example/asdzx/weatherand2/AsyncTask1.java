package com.example.asdzx.weatherand2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        //백그라운드로 요청
        nowtime = doYearMonthDay();
        nowday = doTime();

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        //가져온 String을 저장
        parstring.setMiseString(nowtime);

        //파싱
        //parstring.parse();


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

    //오늘 날짜 가져오기 (api 변수에 씁니다.)
    public static String doYearMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }

    //현재 시간 가져오기 (api 변수에 씁니다.)
    public static String doTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH00", Locale.KOREA);
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }
}