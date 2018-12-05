package com.example.asdzx.weatherand2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class LayoutFour extends Fragment {
    TextView text;

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

            if (p10 < 30)
                result += 50;
            else if (30 <= p10 && p10 <= 50)
                result += 40;
            else if (p10 > 50 && p10 <= 75)
                result += 30;
            else if (p10 > 75)
                result += 10;

            if(pty == 0)
                result += 50;
            else if(pty > 0)
                result -= 10;

            undong = Integer.toString(result);

            text.setText(undong);
            super.onPostExecute(aVoid);
        }
    }
}