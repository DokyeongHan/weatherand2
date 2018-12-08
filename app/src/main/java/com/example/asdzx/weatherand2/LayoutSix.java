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

public class LayoutSix extends Fragment {
    TextView text;
    TextView content;
    TextView jo;
    TextView jo2;
    ImageButton ad;

    public static LayoutSix newInstance() {
        LayoutSix fragment = new LayoutSix();
        return fragment;
    }

    public LayoutSix() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_six, null);
        text = root.findViewById(R.id.result);
        content = root.findViewById(R.id.content);
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
            String aste1;
            String sky = weather.SKY0_5;
            String result1 = ".";
            aste1 = "" + weather.aste.substring(0, 2) + " : " + weather.aste.substring(2, 4);

            if (sky.contains("맑음")) {
                result1 = "오늘밤은 하늘이 맑아요";
                jo2.setText("별 잘 보여요");
            }
            if (sky.contains("조금")) {
                result1 = "오늘밤은 구름 조금";
            }
            if (sky.contains("많음")) {
                result1 = "오늘밤은 구름 많음";
                jo2.setText("별 잘 안보여요");
            }

            jo.setText(result1);
            text.setText(aste1);
            super.onPostExecute(aVoid);
        }
    }
}