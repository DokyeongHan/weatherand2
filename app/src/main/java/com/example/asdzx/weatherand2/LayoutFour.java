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

public class LayoutFour extends Fragment {
    TextView text;
    TextView jo;
    TextView jo2;
    ImageButton ad;

    public static LayoutFour newInstance() {
        LayoutFour fragment = new LayoutFour();
        return fragment;
    }

    public LayoutFour() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_four, null);
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


            String undong;
            int pty = Integer.parseInt(weather.PTY);

            int result = 0;

            String ps10 = weather.pm10;
            int p10 = Integer.parseInt(ps10.trim());

            if (p10 < 30) {
                result += 50;
                jo2.setText("미세먼지 좋음!");
            } else if (30 <= p10 && p10 <= 50) {
                result += 40;
                jo2.setText("미세먼지 보통");
            } else if (p10 > 50 && p10 <= 75) {
                result += 30;
                jo2.setText("미세먼지 나쁨");
            } else if (p10 > 75) {
                result += 10;
                jo2.setText("미세먼지 최악!");
            }

            if (pty == 0)
                result += 50;
            else if (pty > 0)
                result -= 10;

            undong = Integer.toString(result);

            if (result < 80) {
                jo.setText("실내 운동 하세요");
            } else if (result >= 80) {
                jo.setText("운동하기 좋하요");
            }

            text.setText(undong + " 점");
            super.onPostExecute(aVoid);
        }
    }
}