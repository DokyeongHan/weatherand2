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

public class LayoutFive extends Fragment {
    TextView text;
    TextView jo;
    TextView jo2;
    ImageButton ad;

    public static LayoutFive newInstance() {
        LayoutFive fragment = new LayoutFive();
        return fragment;
    }

    public LayoutFive() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_five, null);
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

            String bbal;
            int pty = Integer.parseInt(weather.PTY);
            int pty1 = Integer.parseInt(weather.PTY_1);
            int pty2 = Integer.parseInt(weather.PTY_2);
            int pty3 = Integer.parseInt(weather.PTY_3);
            int pty4 = Integer.parseInt(weather.PTY_4);
            int pty5 = Integer.parseInt(weather.PTY_5);
            int pty6 = Integer.parseInt(weather.T_PTY_1);
            int pty7 = Integer.parseInt(weather.T_PTY_2);
            int pty8 = Integer.parseInt(weather.T_PTY_3);
            int pty9 = Integer.parseInt(weather.T_PTY_4);
            int pty10 = Integer.parseInt(weather.T_PTY_5);

            if (pty != 0)
                pty = 1;
            if (pty1 != 0)
                pty1 = 1;
            if (pty2 != 0)
                pty2 = 1;
            if (pty3 != 0)
                pty3 = 1;
            if (pty4 != 0)
                pty4 = 1;
            if (pty5 != 0)
                pty5 = 1;
            if (pty6 != 0)
                pty6 = 1;
            if (pty7 != 0)
                pty7 = 1;
            if (pty8 != 0)
                pty8 = 1;
            if (pty9 != 0)
                pty9 = 1;
            if (pty10 != 0)
                pty10 = 1;


            String skys1 = (weather.SKY0_1);
            String skys2 = (weather.SKY0_2);
            String skys3 = (weather.SKY0_3);
            String skys4 = (weather.SKY0_4);
            String skys5 = (weather.SKY0_5);
            String skys6 = (weather.SKY1_1);
            String skys7 = (weather.SKY1_2);
            String skys8 = (weather.SKY1_3);
            String skys9 = (weather.SKY1_4);
            String skys10 = (weather.SKY1_5);

            int sky1 = 0, sky2 = 0, sky3 = 0, sky4 = 0, sky5 = 0, sky6 = 0, sky7 = 0, sky8 = 0, sky9 = 0, sky10 = 0;

            if (weather.SKY0_1.contains("구름 많음") || weather.SKY0_1.contains("흐림"))
                sky1 = 1;
            if (skys2.contains("구름") || skys2.contains("흐림"))
                sky2 = 1;
            if (skys3.contains("구름") || skys3.contains("흐림"))
                sky3 = 1;
            if (skys4.contains("구름") || skys4.contains("흐림"))
                sky4 = 1;
            if (skys5.contains("구름") || skys5.contains("흐림"))
                sky5 = 1;
            if (skys6.contains("구름") || skys6.contains("흐림"))
                sky6 = 1;
            if (skys7.contains("구름") || skys7.contains("흐림"))
                sky7 = 1;
            if (skys8.contains("구름") || skys8.contains("흐림"))
                sky8 = 1;
            if (skys9.contains("구름") || skys9.contains("흐림"))
                sky9 = 1;
            if (skys10.contains("구름") || skys10.contains("흐림"))
                sky10 = 1;

            int ptyre1 = pty1 + pty2 + pty3 + pty4 + pty5 + pty6 + pty7 + pty8 + pty9 + pty10;
            int skyre1 = sky1 + sky2 + sky3 + sky4 + sky5 + sky6 + sky7 + sky8 + sky9 + sky10;

            int result = 100;

            if (pty == 1)
                result -= 30;

            result -= ptyre1 * 6;

            result -= skyre1 * 4;

            if (result < 0)
                result = 0;

            bbal = Integer.toString(result);

            if (ptyre1 == 0) {
                jo2.setText("내일까지 비 안와요");
            } else {
                jo2.setText("비가 올거에요");
            }

            if(skyre1 ==0){
                jo2.setText("내일까지 하늘 맑음!");
            }
            if (result >= 90) {
                jo.setText("빨래하기 좋아요");
            } else if(result <90 && result>=80){
                jo.setText("급한 빨래만");
            } else {
                jo.setText("건조기가 필요해요");
            }

            text.setText(bbal + " 점");
            super.onPostExecute(aVoid);
        }
    }
}