package com.example.asdzx.weatherand2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class LayoutOne extends Fragment {
    TextView text;
    TextView jo;
    TextView jo2;
    ImageButton ad;

    public static LayoutOne newInstance() {
        LayoutOne fragment = new LayoutOne();
        return fragment;
    }

    public LayoutOne() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_one, null);
        text = root.findViewById(R.id.result);
        jo = root.findViewById(R.id.jo);
        jo2 = root.findViewById(R.id.jo2);
        ad = root.findViewById(R.id.ad);

        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getContext(), // 현재 화면의 제어권자
                        adad.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });

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
            String chegam;
            double T = Double.parseDouble(weather.T1H);
            double V = Double.parseDouble(weather.WDF);
            double V2 = Math.pow(V, 0.16);
            double result;

            String i = weather.PTY;
            int i2 = Integer.parseInt(i);

            if(i2==0){
                jo.setText("맑은 날이에요");
            }else if(i2==1 || i2==2){
                jo.setText("밖에 비와요");
            }else if(i2==3){
                jo.setText("밖에 눈와요");
            }

            i=weather.WDF;
            double i3 = Double.parseDouble(i);

            if(i3<=0.2){
                jo2.setText("바람 안불어요");
            } else if(i3>0.2 && i3<=1.5){
                jo2.setText("실바람");
            } else if(i3>1.5 && i3<=3.3){
                jo2.setText("남실바람");
            } else if (i3>3.3 && i3<=5.4){
                jo2.setText("산들바람");
            }else if (i3>5.4 && i3<=7.9){
                jo2.setText("건들바람");
            }else if (i3>7.9 && i3<=10.7){
                jo2.setText("흔들바람");
            }else if (i3>10.7 && i3<=13.8){
                jo2.setText("된바람");
            }else if (i3>13.8 && i3<=17.1){
                jo2.setText("센바람");
            }

            result = 13.12 + 0.6215 * T - 11.37 * V2 + 0.3965 * V2 * T;
            chegam = String.format("%.1f",result);
            text.setText(chegam+ " ℃");
            super.onPostExecute(aVoid);
        }
    }
}