package com.example.asdzx.weatherand2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class LayoutTwo extends Fragment {
    TextView text;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView text9;
    TextView text10;
    ImageView image11;
    ImageView image12;
    ImageView image13;
    ImageView image14;
    ImageView image15;
    TextView text16;
    TextView text17;
    TextView text18;
    TextView text19;
    TextView text20;
    TextView text21;
    TextView text22;
    TextView text23;
    TextView text24;
    TextView text25;
    TextView text26;
    TextView text27;
    TextView text28;
    TextView text29;
    TextView text30;
    TextView text31;
    TextView text32;
    ImageView image33;
    ImageView image34;
    ImageView image35;
    ImageView image36;
    ImageView image37;
    ImageView image38;
    ImageView image39;
    TextView text40;
    TextView text41;
    TextView text42;
    TextView text43;
    TextView text44;
    TextView text45;
    TextView text46;
    TextView text47;
    TextView text48;
    TextView text49;
    TextView text50;
    TextView text51;
    TextView text52;
    TextView text53;
    TextView text54;
    TextView text55;
    TextView text56;
    TextView text57;
    TextView text58;
    TextView text59;
    TextView text60;
    TextView text61;
    TextView text62;
    TextView text63;
    ImageView image64;
    ImageView image65;
    ImageView image66;
    ImageView image67;
    ImageView image68;
    ImageView image69;
    TextView text70;
    TextView text71;
    TextView text72;

    public static LayoutTwo newInstance() {
        LayoutTwo fragment = new LayoutTwo();
        return fragment;
    }

    public LayoutTwo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_two, null);

        text = root.findViewById(R.id.tem);
        text1 = root.findViewById(R.id.win);
        text2 = root.findViewById(R.id.moi);
        text3 = root.findViewById(R.id.prob);
        text4 = root.findViewById(R.id.low);
        text5 = root.findViewById(R.id.high);
        text6 = root.findViewById(R.id.sunrise);
        text7 = root.findViewById(R.id.sunset);
        text8 = root.findViewById(R.id.civil);
        text9 = root.findViewById(R.id.nau);
        text10 = root.findViewById(R.id.ast);
        image11 = root.findViewById(R.id.sky1);
        image12 = root.findViewById(R.id.sky2);
        image13 = root.findViewById(R.id.sky3);
        image14 = root.findViewById(R.id.sky4);
        image15 = root.findViewById(R.id.sky5);
        text16 = root.findViewById(R.id.tem1);
        text17 = root.findViewById(R.id.tem2);
        text18 = root.findViewById(R.id.tem3);
        text19 = root.findViewById(R.id.tem4);
        text20 = root.findViewById(R.id.tem5);
        text21 = root.findViewById(R.id.win1);
        text22 = root.findViewById(R.id.win2);
        text23 = root.findViewById(R.id.win3);
        text24 = root.findViewById(R.id.win4);
        text25 = root.findViewById(R.id.win5);
        text26 = root.findViewById(R.id.yoil1);
        text27 = root.findViewById(R.id.yoil2);
        text28 = root.findViewById(R.id.yoil3);
        text29 = root.findViewById(R.id.yoil4);
        text30 = root.findViewById(R.id.yoil5);
        text31 = root.findViewById(R.id.yoil6);
        text32 = root.findViewById(R.id.yoil7);
        image33 = root.findViewById(R.id.sky6);
        image34 = root.findViewById(R.id.sky7);
        image35 = root.findViewById(R.id.sky8);
        image36 = root.findViewById(R.id.sky9);
        image37 = root.findViewById(R.id.sky10);
        image38 = root.findViewById(R.id.sky11);
        image39 = root.findViewById(R.id.sky12);
        text40 = root.findViewById(R.id.low1);
        text41 = root.findViewById(R.id.low2);
        text42 = root.findViewById(R.id.low3);
        text43 = root.findViewById(R.id.low4);
        text44 = root.findViewById(R.id.low5);
        text45 = root.findViewById(R.id.low6);
        text46 = root.findViewById(R.id.low7);
        text47 = root.findViewById(R.id.high1);
        text48 = root.findViewById(R.id.high2);
        text49 = root.findViewById(R.id.high3);
        text50 = root.findViewById(R.id.high4);
        text51 = root.findViewById(R.id.high5);
        text52 = root.findViewById(R.id.high6);
        text53 = root.findViewById(R.id.high7);
        text54 = root.findViewById(R.id.tem6);
        text55 = root.findViewById(R.id.tem7);
        text56 = root.findViewById(R.id.tem8);
        text57 = root.findViewById(R.id.tem9);
        text58 = root.findViewById(R.id.tem10);
        text59 = root.findViewById(R.id.win6);
        text60 = root.findViewById(R.id.win7);
        text61 = root.findViewById(R.id.win8);
        text62 = root.findViewById(R.id.win9);
        text63 = root.findViewById(R.id.win10);
        image64 = root.findViewById(R.id.sky13);
        image65 = root.findViewById(R.id.sky14);
        image66 = root.findViewById(R.id.sky15);
        image67 = root.findViewById(R.id.sky16);
        image68 = root.findViewById(R.id.sky17);
        image69 = root.findViewById(R.id.toprain);
        text70 = root.findViewById(R.id.prob);
        text71 = root.findViewById(R.id.dust);
        text72 = root.findViewById(R.id.chodust);


        APItask api = new APItask();
        api.execute();

        return root;
    }

    public class APItask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String tem = weather.PTY;

            int i = Integer.parseInt(tem);

            if (i == 0) {
                tem = "비 안옴";
            } else if (i == 1) {
                tem = "비";
            } else if (i == 2) {
                tem = "비/눈";
            } else {
                tem = "눈";
            }

            String sunrise, sunset, civil, nau, ast;
            sunrise = "" + weather.sunrise.substring(0, 2) + " : " + weather.sunrise.substring(2, 4);
            sunset = "" + weather.sunset.substring(0, 2) + " : " + weather.sunset.substring(2, 4);
            civil = "" + weather.civile.substring(0, 2) + " : " + weather.civile.substring(2, 4);
            nau = "" + weather.naute.substring(0, 2) + " : " + weather.naute.substring(2, 4);
            ast = "" + weather.aste.substring(0, 2) + " : " + weather.aste.substring(2, 4);


            text.setText(weather.T1H + " ℃");
            text1.setText(weather.WDF + " m/s");
            text2.setText(weather.REH + " %");
            text3.setText(tem);

            if(Integer.parseInt(weather.T3H_1)<=Integer.parseInt(weather.T3H_5)){
                text4.setText(weather.T3H_1 + " ℃");

            }

            if(Integer.parseInt(weather.T3H_1)>=Integer.parseInt(weather.T3H_5)){
                text4.setText(weather.T3H_5 + " ℃");
            }


            text5.setText(weather.T3H_3 + " ℃");
            text6.setText(sunrise);
            text7.setText(sunset);
            text8.setText(civil);
            text9.setText(nau);
            text10.setText(ast);

            int tmpID;
            tmpID = getResources().getIdentifier(getsky2(weather.SKY0_1, Integer.parseInt(weather.PTY_1)), "drawable", "com.example.asdzx.weatherand2");
            image11.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY0_2, Integer.parseInt(weather.PTY_2)), "drawable", "com.example.asdzx.weatherand2");
            image12.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY0_3, Integer.parseInt(weather.PTY_3)), "drawable", "com.example.asdzx.weatherand2");
            image13.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY0_4, Integer.parseInt(weather.PTY_4)), "drawable", "com.example.asdzx.weatherand2");
            image14.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY0_5, Integer.parseInt(weather.PTY_5)), "drawable", "com.example.asdzx.weatherand2");
            image15.setImageResource(tmpID);


            text16.setText(weather.T3H_1 + " ℃");
            text17.setText(weather.T3H_2 + " ℃");
            text18.setText(weather.T3H_3 + " ℃");
            text19.setText(weather.T3H_4 + " ℃");
            text20.setText(weather.T3H_5 + " ℃");
            text21.setText(weather.WSD_1 + " m/s");
            text22.setText(weather.WSD_2 + " m/s");
            text23.setText(weather.WSD_3 + " m/s");
            text24.setText(weather.WSD_4 + " m/s");
            text25.setText(weather.WSD_5 + " m/s");
            text26.setText(getyoil(0));
            text27.setText(getyoil(1));
            text28.setText(getyoil(2));
            text29.setText(getyoil(3));
            text30.setText(getyoil(4));
            text31.setText(getyoil(5));
            text32.setText(getyoil(6));

            tmpID = getResources().getIdentifier(getweeksky(weather.SKY1_3, Integer.parseInt(weather.T_PTY_1), Integer.parseInt(weather.T_PTY_2), Integer.parseInt(weather.T_PTY_3), Integer.parseInt(weather.T_PTY_4), Integer.parseInt(weather.T_PTY_5)), "drawable", "com.example.asdzx.weatherand2");
            image33.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getweeksky(weather.SKY2_3, Integer.parseInt(weather.F_PTY_1), Integer.parseInt(weather.F_PTY_2), Integer.parseInt(weather.F_PTY_3), Integer.parseInt(weather.F_PTY_4), Integer.parseInt(weather.F_PTY_5)), "drawable", "com.example.asdzx.weatherand2");
            image34.setImageResource(tmpID);

            tmpID = getResources().getIdentifier(getsky(weather.SKY3), "drawable", "com.example.asdzx.weatherand2");
            image35.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky(weather.SKY4), "drawable", "com.example.asdzx.weatherand2");
            image36.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky(weather.SKY5), "drawable", "com.example.asdzx.weatherand2");
            image37.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky(weather.SKY6), "drawable", "com.example.asdzx.weatherand2");
            image38.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky(weather.SKY7), "drawable", "com.example.asdzx.weatherand2");
            image39.setImageResource(tmpID);


            text40.setText(weather.TMN1.substring(0, weather.TMN1.length() - 2) + " ℃");
            text41.setText(weather.TMN2.substring(0, weather.TMN2.length() - 2) + " ℃");
            text42.setText(weather.TMN3 + " ℃");
            text43.setText(weather.TMN4 + " ℃");
            text44.setText(weather.TMN5 + " ℃");
            text45.setText(weather.TMN6 + " ℃");
            text46.setText(weather.TMN7 + " ℃");

            text47.setText(weather.TMX1.substring(0, weather.TMX1.length() - 2) + " ℃");
            text48.setText(weather.TMX2.substring(0, weather.TMX2.length() - 2) + " ℃");
            text49.setText(weather.TMX3 + " ℃");
            text50.setText(weather.TMX4 + " ℃");
            text51.setText(weather.TMX5 + " ℃");
            text52.setText(weather.TMX6 + " ℃");
            text53.setText(weather.TMX7 + " ℃");

            text54.setText(weather.T_T3H_1 + " ℃");
            text55.setText(weather.T_T3H_2 + " ℃");
            text56.setText(weather.T_T3H_3 + " ℃");
            text57.setText(weather.T_T3H_4 + " ℃");
            text58.setText(weather.T_T3H_5 + " ℃");


            text59.setText(weather.T_WSD_1 + " m/s");
            text60.setText(weather.T_WSD_2 + " m/s");
            text61.setText(weather.T_WSD_3 + " m/s");
            text62.setText(weather.T_WSD_4 + " m/s");
            text63.setText(weather.T_WSD_5 + " m/s");


            tmpID = getResources().getIdentifier(getsky2(weather.SKY1_1, Integer.parseInt(weather.T_PTY_1)), "drawable", "com.example.asdzx.weatherand2");
            image64.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY1_2, Integer.parseInt(weather.T_PTY_2)), "drawable", "com.example.asdzx.weatherand2");
            image65.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY1_3, Integer.parseInt(weather.T_PTY_3)), "drawable", "com.example.asdzx.weatherand2");
            image66.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY1_4, Integer.parseInt(weather.T_PTY_4)), "drawable", "com.example.asdzx.weatherand2");
            image67.setImageResource(tmpID);
            tmpID = getResources().getIdentifier(getsky2(weather.SKY1_5, Integer.parseInt(weather.T_PTY_5)), "drawable", "com.example.asdzx.weatherand2");
            image68.setImageResource(tmpID);

            tmpID = getResources().getIdentifier(getpty(Integer.parseInt(weather.PTY)), "drawable", "com.example.asdzx.weatherand2");
            image69.setImageResource(tmpID);

            if(Integer.parseInt(weather.PTY)==0) {
                text70.setText("비 안옴");
            }
            if(Integer.parseInt(weather.PTY)==1 || Integer.parseInt(weather.PTY)==2) {
                text70.setText("비 옴");
            }
            if(Integer.parseInt(weather.PTY)==3) {
                text70.setText("눈 옴");
            }

            text71.setText(weather.pm10+" ㎛");
            text72.setText(weather.pm25+" ㎛");

            super.onPostExecute(aVoid);
        }
    }

    public static String getyoil(int i) {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = (cal.get(Calendar.DAY_OF_WEEK) + i) % 7 + 1;

        String korDOW = "";
        switch (dayOfWeek) {
            case 1:
                korDOW = "일요일";
                break;
            case 2:
                korDOW = "월요일";
                break;
            case 3:
                korDOW = "화요일";
                break;
            case 4:
                korDOW = "수요일";
                break;
            case 5:
                korDOW = "목요일";
                break;
            case 6:
                korDOW = "금요일";
                break;
            case 7:
                korDOW = "토요일";
                break;
        }
        return korDOW;
    }


    public static String getsky(String a) {
        String result = "sunny";
        if (a.contains("맑음")) {
            result = "sunny";
        }
        if (a.contains("눈") || a.contains("눈/비")) {
            result = "snow";
        }
        if (a.contains("비") || a.contains("비/눈")) {
            result = "rain";
        }
        if (a.contains("조금")) {
            result = "cloud2";
        }
        if (a.contains("많음") || a.contains("흐림")) {
            result = "cloud";
        }
        return result;
    }

    public static String getsky2(String a, int i) {
        String result = "sunny";

        if (i == 1 || i == 2) {
            result = "rain";
        }
        if (1 == 3) {
            result = "snow";
        }
        if (a.contains("맑음")) {
            result = "sunny";
        }
        if (a.contains("많음") || a.contains("흐림")) {
            result = "cloud";
        }
        if (a.contains("조금")) {
            result = "cloud2";
        }

        return result;
    }

    public static String getweeksky(String c, int i1, int i2, int i3, int i4, int i5) {
        String result = "sunny";

        if (i1 == 1 || i1 == 2 || i2 == 1 || i2 == 2 || i3 == 1 || i3 == 2 || i4 == 1 || i4 == 2 || i5 == 1 || i5 == 2) {
            result = "rain";
        }
        if (i1 == 3 || i2 == 3 || i3 == 3 || i4 == 3 || i5 == 3) {
            result = "snow";
        }
        if (c.contains("맑음")) {
            result = "sunny";
        }
        if (c.contains("많음") || c.contains("흐림")) {
            result = "cloud";
        }
        if (c.contains("조금")) {
            result = "cloud2";
        }

        return result;
    }

    public static String getpty(int i) {
        String result = "sunny";

        if (i == 1 || i == 2) {
            result = "rain";
        }
        if (i == 3) {
            result = "snow";
        }

        return result;
    }
}