package com.example.asdzx.weatherand2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class LayoutThree extends Fragment {
    TextView text;

    public static LayoutThree newInstance() {
        LayoutThree fragment = new LayoutThree();
        return fragment;
    }

    public LayoutThree() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_three, null);
        text = root.findViewById(R.id.result);

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

            String secha;
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


            String ptys11 = weather.SKY3;
            String ptys12 = weather.SKY4;
            String ptys13 = weather.SKY5;
            String ptys14 = weather.SKY6;
            String ptys15 = weather.SKY7;

            int pty11 = 0;
            int pty12 = 0;
            int pty13 = 0;
            int pty14 = 0;
            int pty15 = 0;

            if (ptys11.contains("비") || ptys11.contains("눈") || ptys11.contains("구름많음"))
                pty11 = 1;
            if (ptys12.contains("비") || ptys12.contains("눈") || ptys12.contains("구름많음"))
                pty12 = 1;
            if (ptys13.contains("비") || ptys13.contains("눈") || ptys13.contains("구름많음"))
                pty13 = 1;
            if (ptys14.contains("비") || ptys14.contains("눈") || ptys14.contains("구름많음"))
                pty14 = 1;
            if (ptys15.contains("비") || ptys15.contains("눈") || ptys15.contains("구름많음"))
                pty15 = 1;


            int ptyre1 = pty + pty1 + pty2 + pty3 + pty4 + pty5 + pty6 + pty7 + pty8 + pty9 + pty10;
            int ptyre2 = pty11 + pty12 + pty13 + pty14 + pty15;

            int result = 0;


            String ps10 = weather.pm10;
            int p10 = Integer.parseInt(ps10.trim());

            if (p10 < 30)
                result += 30;
            else if (30 <= p10 && p10 <= 50)
                result += 20;
            else if (p10 > 50 && p10 <= 75)
                result += 10;
            else if (p10 > 75)
                result += 0;

            if (ptyre1 > 0)
                result += 0;
            else if (ptyre1 == 0)
                result += 35;

            result += 35 - ptyre2 * 7;

            if (pty>0)
                result =20;

            secha = Integer.toString(result);

            text.setText(secha);
            super.onPostExecute(aVoid);
        }
    }
}