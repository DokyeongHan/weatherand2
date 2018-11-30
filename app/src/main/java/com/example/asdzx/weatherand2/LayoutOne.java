package com.example.asdzx.weatherand2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.asdzx.weatherand2.MainActivity.weather;

public class LayoutOne extends Fragment {
    TextView text;

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
            result = 13.12 + 0.6215 * T - 11.37 * V2 + 0.3965 * V2 * T;
            chegam = String.format("%.1f",result);
            text.setText(chegam);
            super.onPostExecute(aVoid);
        }
    }
}