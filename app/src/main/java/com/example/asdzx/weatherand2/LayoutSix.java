package com.example.asdzx.weatherand2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class LayoutSix extends Fragment {
    TextView text;
    TextView content;

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
            String aste1 = weather.aste;
            String sky = weather.SKY0_5;
            String result1;

            if (sky.contains("맑음")) {
                result1 = "오늘은 별보기 좋은 날";
                content.setText(result1);
            }


            text.setText(aste1);
            super.onPostExecute(aVoid);
        }
    }
}